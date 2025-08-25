@file:OptIn(ExperimentalSerializationApi::class)

package dev.inmo.tgbotapi.types.chat

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.nonstrictJsonFormat
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

private val formatter
    get() = nonstrictJsonFormat

@Serializable(ChatTypeSerializer::class)
sealed class ChatType {
    abstract val stringified: String
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(ChatTypeSerializer::class)
    data object Sender : ChatType() { override val stringified = "sender" }
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(ChatTypeSerializer::class)
    data object Private : ChatType() { override val stringified = "private" }
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(ChatTypeSerializer::class)
    data object Group : ChatType() { override val stringified = "group" }
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(ChatTypeSerializer::class)
    data object Supergroup : ChatType() { override val stringified = "supergroup" }
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(ChatTypeSerializer::class)
    data object Channel : ChatType() { override val stringified = "channel" }
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(ChatTypeSerializer::class)
    data class Unknown(override val stringified: String) : ChatType()

    override fun toString(): String {
        return stringified
    }
}
val String.asChatType
    get() = when (this) {
        ChatType.Sender.stringified -> ChatType.Sender
        ChatType.Private.stringified -> ChatType.Private
        ChatType.Group.stringified -> ChatType.Group
        ChatType.Supergroup.stringified -> ChatType.Supergroup
        ChatType.Channel.stringified -> ChatType.Channel
        else -> ChatType.Unknown(this)
    }

@RiskFeature
object ChatTypeSerializer : KSerializer<ChatType> {
    override val descriptor: SerialDescriptor = String.serializer().descriptor
    override fun deserialize(decoder: Decoder): ChatType {
        return decoder.decodeString().asChatType
    }

    override fun serialize(encoder: Encoder, value: ChatType) {
        encoder.encodeString(value.stringified)
    }
}

@RiskFeature
object ChatSerializer : KSerializer<Chat> {
    @OptIn(InternalSerializationApi::class)
    override val descriptor: SerialDescriptor = buildSerialDescriptor("PreviewChatSerializer", PolymorphicKind.OPEN)

    override fun deserialize(decoder: Decoder): Chat {
        val decodedJson = JsonObject.serializer().deserialize(decoder)

        return try {
            formatter.decodeFromJsonElement(ExtendedChatSerializer, decodedJson)
        } catch (_: SerializationException) {
            val type = decodedJson[typeField] ?.jsonPrimitive ?.content ?.asChatType
            val isForum = decodedJson[isForumField] ?.jsonPrimitive ?.booleanOrNull == true
            val original = decodedJson[originField]

            when (type) {
                ChatType.Sender -> formatter.decodeFromJsonElement(PrivateChatImpl.serializer(), decodedJson)
                ChatType.Private -> formatter.decodeFromJsonElement(PrivateChatImpl.serializer(), decodedJson)
                ChatType.Group -> formatter.decodeFromJsonElement(GroupChatImpl.serializer(), decodedJson)
                ChatType.Supergroup -> if (isForum) {
                    formatter.decodeFromJsonElement(ForumChatImpl.serializer(), decodedJson)
                } else {
                    formatter.decodeFromJsonElement(SupergroupChatImpl.serializer(), decodedJson)
                }
                ChatType.Channel -> formatter.decodeFromJsonElement(ChannelChatImpl.serializer(), decodedJson)
                is ChatType.Unknown -> UnknownChatType(
                    formatter.decodeFromJsonElement(Long.serializer(), decodedJson[chatIdField] ?: JsonPrimitive(-1)).toChatId(),
                    decodedJson.toString(),
                    decodedJson
                )
                null -> {
                    when {
                        original != null -> formatter.decodeFromJsonElement(BusinessChatImpl.serializer(), decodedJson)
                        else -> error("Field $typeField must be presented for common types (excluding Business chats), but absent in $decodedJson")
                    }
                }
            }
        }
    }

    override fun serialize(encoder: Encoder, value: Chat) {
        when (value) {
            is ExtendedChat -> ExtendedChatSerializer.serialize(encoder, value)
            is PreviewChat -> PreviewChatSerializer.serialize(encoder, value)
            is ExtendedBot -> ExtendedBot.serializer().serialize(encoder, value)
        }
    }
}

@RiskFeature
object PreviewChatSerializer : KSerializer<PreviewChat> {
    @OptIn(InternalSerializationApi::class)
    override val descriptor: SerialDescriptor = buildSerialDescriptor("PreviewChatSerializer", PolymorphicKind.OPEN)

    override fun deserialize(decoder: Decoder): PreviewChat {
        val decodedJson = JsonObject.serializer().deserialize(decoder)

        val type = decodedJson[typeField] ?.jsonPrimitive ?.content ?.asChatType
        val isForum = decodedJson[isForumField] ?.jsonPrimitive ?.booleanOrNull == true
        val isChannelDirectMessages = decodedJson[isDirectMessagesField] ?.jsonPrimitive ?.booleanOrNull == true
        val original = decodedJson[originField]

        return when (type) {
            ChatType.Sender -> formatter.decodeFromJsonElement(PrivateChatImpl.serializer(), decodedJson)
            ChatType.Private -> formatter.decodeFromJsonElement(PrivateChatImpl.serializer(), decodedJson)
            ChatType.Group -> formatter.decodeFromJsonElement(GroupChatImpl.serializer(), decodedJson)
            ChatType.Supergroup -> {
                @Suppress("SimplifyBooleanWithConstants")
                when {
                    isForum == false -> formatter.decodeFromJsonElement(SupergroupChatImpl.serializer(), decodedJson)
                    isChannelDirectMessages -> formatter.decodeFromJsonElement(ChannelDirectMessagesChatImpl.serializer(), decodedJson)
                    else -> formatter.decodeFromJsonElement(ForumChatImpl.serializer(), decodedJson)
                }
            }
            ChatType.Channel -> formatter.decodeFromJsonElement(ChannelChatImpl.serializer(), decodedJson)
            is ChatType.Unknown -> UnknownChatType(
                formatter.decodeFromJsonElement(Long.serializer(), decodedJson[chatIdField] ?: JsonPrimitive(-1)).toChatId(),
                decodedJson.toString(),
                decodedJson
            )
            null -> {
                when {
                    original != null -> formatter.decodeFromJsonElement(PreviewBusinessChat.serializer(), decodedJson)
                    else -> error("Field $typeField must be presented for common types (excluding Business chats), but absent in $decodedJson")
                }
            }
        }
    }

    override fun serialize(encoder: Encoder, value: PreviewChat) {
        when (value) {
            is PrivateChatImpl -> PrivateChatImpl.serializer().serialize(encoder, value)
            is BusinessChatImpl -> BusinessChatImpl.serializer().serialize(encoder, value)
            is GroupChatImpl -> GroupChatImpl.serializer().serialize(encoder, value)
            is SupergroupChatImpl -> SupergroupChatImpl.serializer().serialize(encoder, value)
            is ForumChatImpl -> ForumChatImpl.serializer().serialize(encoder, value)
            is ChannelDirectMessagesChatImpl -> ChannelDirectMessagesChatImpl.serializer().serialize(encoder, value)
            is ChannelChatImpl -> ChannelChatImpl.serializer().serialize(encoder, value)
            is CommonBot -> CommonBot.serializer().serialize(encoder, value)
            is CommonUser -> CommonUser.serializer().serialize(encoder, value)
            is UnknownChatType -> JsonObject.serializer().serialize(encoder, value.rawJson)
        }
    }
}

@RiskFeature
sealed class ExtendedChatSerializer : KSerializer<ExtendedChat> {
    @OptIn(InternalSerializationApi::class)
    override val descriptor: SerialDescriptor = buildSerialDescriptor("ExtendedChatSerializer", PolymorphicKind.OPEN)

    override fun deserialize(decoder: Decoder): ExtendedChat {
        val decodedJson = JsonObject.serializer().deserialize(decoder)

        val type = decodedJson[typeField] ?.jsonPrimitive ?.content ?.asChatType
        val isForum = decodedJson[isForumField] ?.jsonPrimitive ?.booleanOrNull == true
        val isChannelDirectMessages = decodedJson[isDirectMessagesField] ?.jsonPrimitive ?.booleanOrNull == true
        val original = decodedJson[originField]

        return when (type) {
            ChatType.Sender -> formatter.decodeFromJsonElement(ExtendedPrivateChatImpl.serializer(), decodedJson)
            ChatType.Private -> formatter.decodeFromJsonElement(ExtendedPrivateChatImpl.serializer(), decodedJson)
            ChatType.Group -> formatter.decodeFromJsonElement(ExtendedGroupChatImpl.serializer(), decodedJson)
            ChatType.Supergroup -> {
                @Suppress("SimplifyBooleanWithConstants")
                when {
                    isForum == false -> formatter.decodeFromJsonElement(ExtendedSupergroupChatImpl.serializer(), decodedJson)
                    isChannelDirectMessages -> formatter.decodeFromJsonElement(ExtendedChannelDirectMessagesChatImpl.serializer(), decodedJson)
                    else -> formatter.decodeFromJsonElement(ExtendedForumChatImpl.serializer(), decodedJson)
                }
            }
            ChatType.Channel -> formatter.decodeFromJsonElement(ExtendedChannelChatImpl.serializer(), decodedJson)
            is ChatType.Unknown -> UnknownExtendedChat(
                formatter.decodeFromJsonElement(Long.serializer(), decodedJson[chatIdField] ?: JsonPrimitive(-1)).toChatId(),
                decodedJson.toString(),
                decodedJson
            )
            null -> {
                when {
                    original != null -> formatter.decodeFromJsonElement(ExtendedBusinessChatImpl.serializer(), decodedJson)
                    else -> error("Field $typeField must be presented for common types (excluding Business chats), but absent in $decodedJson")
                }
            }
        }
    }

    override fun serialize(encoder: Encoder, value: ExtendedChat) {
        when (value) {
            is ExtendedBusinessChatImpl -> ExtendedBusinessChatImpl.serializer().serialize(encoder, value)
            is ExtendedPrivateChatImpl -> ExtendedPrivateChatImpl.serializer().serialize(encoder, value)
            is ExtendedGroupChatImpl -> ExtendedGroupChatImpl.serializer().serialize(encoder, value)
            is ExtendedSupergroupChatImpl -> ExtendedSupergroupChatImpl.serializer().serialize(encoder, value)
            is ExtendedForumChatImpl -> ExtendedForumChatImpl.serializer().serialize(encoder, value)
            is ExtendedChannelDirectMessagesChatImpl -> ExtendedChannelDirectMessagesChatImpl.serializer().serialize(encoder, value)
            is ExtendedChannelChatImpl -> ExtendedChannelChatImpl.serializer().serialize(encoder, value)
            is ExtendedBot -> ExtendedBot.serializer().serialize(encoder, value)
            is UnknownExtendedChat -> JsonObject.serializer().serialize(encoder, value.rawJson)
        }
    }

    class BasedOnForumThread(private val threadId: MessageThreadId) : ExtendedChatSerializer() {
        override fun deserialize(decoder: Decoder): ExtendedChat {
            return super.deserialize(decoder).let {
                if (it is ExtendedForumChatImpl) {
                    it.copy(
                        id = (it.id as? ChatIdWithThreadId) ?: ChatIdWithThreadId(it.id.chatId, threadId)
                    )
                } else {
                    it
                }
            }
        }
    }
    class BasedOnBusinessConnection(private val businessConnectionId: BusinessConnectionId) : ExtendedChatSerializer() {
        override fun deserialize(decoder: Decoder): ExtendedChat {
            return super.deserialize(decoder).let {
                if (it is ExtendedPrivateChatImpl) {
                    ExtendedBusinessChatImpl(
                        BusinessChatId(it.id.chatId, businessConnectionId),
                        it
                    )
                } else {
                    it
                }
            }
        }
    }

    companion object : ExtendedChatSerializer()
}

@RiskFeature
object UserSerializer : KSerializer<User> {
    private val internalSerializer = JsonObject.serializer()
    override val descriptor: SerialDescriptor = internalSerializer.descriptor
    override fun deserialize(decoder: Decoder): User {
        val asJson = internalSerializer.deserialize(decoder)

        return when {
            asJson[isBotField] ?.jsonPrimitive ?.booleanOrNull != true -> nonstrictJsonFormat.decodeFromJsonElement(
                CommonUser.serializer(),
                asJson
            )
            else -> {
                if ((asJson[canJoinGroupsField]
                        ?: asJson[canReadAllGroupMessagesField]
                        ?: asJson[supportInlineQueriesField]) != null
                ) {
                    nonstrictJsonFormat.decodeFromJsonElement(
                        ExtendedBot.serializer(),
                        asJson
                    )
                } else {
                    nonstrictJsonFormat.decodeFromJsonElement(
                        CommonBot.serializer(),
                        asJson
                    )
                }
            }
        }
    }

    override fun serialize(encoder: Encoder, value: User) {
        when (value) {
            is CommonUser -> CommonUser.serializer().serialize(encoder, value)
            is CommonBot -> CommonBot.serializer().serialize(encoder, value)
            is ExtendedBot -> ExtendedBot.serializer().serialize(encoder, value)
        }
    }
}
