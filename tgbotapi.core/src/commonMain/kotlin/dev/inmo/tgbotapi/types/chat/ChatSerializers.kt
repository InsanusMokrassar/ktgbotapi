package dev.inmo.tgbotapi.types.chat

import dev.inmo.tgbotapi.types.*
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
    @Serializable(ChatTypeSerializer::class)
    object PrivateChatType : ChatType() { override val stringified = "private" }
    @Serializable(ChatTypeSerializer::class)
    object GroupChatType : ChatType() { override val stringified = "group" }
    @Serializable(ChatTypeSerializer::class)
    object SupergroupChatType : ChatType() { override val stringified = "supergroup" }
    @Serializable(ChatTypeSerializer::class)
    object ChannelChatType : ChatType() { override val stringified = "channel" }
    @Serializable(ChatTypeSerializer::class)
    class UnknownChatType(override val stringified: String) : ChatType()
}
val String.asChatType
    get() = when (this) {
        ChatType.PrivateChatType.stringified -> ChatType.PrivateChatType
        ChatType.GroupChatType.stringified -> ChatType.GroupChatType
        ChatType.SupergroupChatType.stringified -> ChatType.SupergroupChatType
        ChatType.ChannelChatType.stringified -> ChatType.ChannelChatType
        else -> ChatType.UnknownChatType(this)
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
        } catch (e: SerializationException) {
            val type = decodedJson[typeField] ?.jsonPrimitive ?.content ?.asChatType ?: error("Field $typeField must be presented, but absent in $decodedJson")
            val isForum = decodedJson[isForumField] ?.jsonPrimitive ?.booleanOrNull == true

            when (type) {
                ChatType.PrivateChatType -> formatter.decodeFromJsonElement(PrivateChatImpl.serializer(), decodedJson)
                ChatType.GroupChatType -> formatter.decodeFromJsonElement(GroupChatImpl.serializer(), decodedJson)
                ChatType.SupergroupChatType -> if (isForum) {
                    formatter.decodeFromJsonElement(ForumChatImpl.serializer(), decodedJson)
                } else {
                    formatter.decodeFromJsonElement(SupergroupChatImpl.serializer(), decodedJson)
                }
                ChatType.ChannelChatType -> formatter.decodeFromJsonElement(ChannelChatImpl.serializer(), decodedJson)
                is ChatType.UnknownChatType -> UnknownChatType(
                    formatter.decodeFromJsonElement(Long.serializer(), decodedJson[chatIdField] ?: JsonPrimitive(-1)).toChatId(),
                    decodedJson.toString(),
                    decodedJson
                )
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

        val type = decodedJson[typeField] ?.jsonPrimitive ?.content ?.asChatType ?: error("Field $typeField must be presented, but absent in $decodedJson")
        val isForum = decodedJson[isForumField] ?.jsonPrimitive ?.booleanOrNull == true

        return when (type) {
            ChatType.PrivateChatType -> formatter.decodeFromJsonElement(PrivateChatImpl.serializer(), decodedJson)
            ChatType.GroupChatType -> formatter.decodeFromJsonElement(GroupChatImpl.serializer(), decodedJson)
            ChatType.SupergroupChatType -> if (isForum) {
                formatter.decodeFromJsonElement(ForumChatImpl.serializer(), decodedJson)
            } else {
                formatter.decodeFromJsonElement(SupergroupChatImpl.serializer(), decodedJson)
            }
            ChatType.ChannelChatType -> formatter.decodeFromJsonElement(ChannelChatImpl.serializer(), decodedJson)
            is ChatType.UnknownChatType -> UnknownChatType(
                formatter.decodeFromJsonElement(Long.serializer(), decodedJson[chatIdField] ?: JsonPrimitive(-1)).toChatId(),
                decodedJson.toString(),
                decodedJson
            )
        }
    }

    override fun serialize(encoder: Encoder, value: PreviewChat) {
        when (value) {
            is PrivateChatImpl -> PrivateChatImpl.serializer().serialize(encoder, value)
            is GroupChatImpl -> GroupChatImpl.serializer().serialize(encoder, value)
            is SupergroupChatImpl -> SupergroupChatImpl.serializer().serialize(encoder, value)
            is ForumChatImpl -> ForumChatImpl.serializer().serialize(encoder, value)
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

        val type = decodedJson[typeField] ?.jsonPrimitive ?.content ?.asChatType ?: error("Field $typeField must be presented, but absent in $decodedJson")
        val isForum = decodedJson[isForumField] ?.jsonPrimitive ?.booleanOrNull == true

        return when (type) {
//            else -> throw IllegalArgumentException("Unknown type of chat")
            ChatType.PrivateChatType -> formatter.decodeFromJsonElement(ExtendedPrivateChatImpl.serializer(), decodedJson)
            ChatType.GroupChatType -> formatter.decodeFromJsonElement(ExtendedGroupChatImpl.serializer(), decodedJson)
            ChatType.SupergroupChatType -> if (isForum) {
                formatter.decodeFromJsonElement(ExtendedForumChatImpl.serializer(), decodedJson)
            } else {
                formatter.decodeFromJsonElement(ExtendedSupergroupChatImpl.serializer(), decodedJson)
            }
            ChatType.ChannelChatType -> formatter.decodeFromJsonElement(ExtendedChannelChatImpl.serializer(), decodedJson)
            is ChatType.UnknownChatType -> UnknownExtendedChat(
                formatter.decodeFromJsonElement(Long.serializer(), decodedJson[chatIdField] ?: JsonPrimitive(-1)).toChatId(),
                decodedJson.toString(),
                decodedJson
            )
        }
    }

    override fun serialize(encoder: Encoder, value: ExtendedChat) {
        when (value) {
            is ExtendedPrivateChatImpl -> ExtendedPrivateChatImpl.serializer().serialize(encoder, value)
            is ExtendedGroupChatImpl -> ExtendedGroupChatImpl.serializer().serialize(encoder, value)
            is ExtendedSupergroupChatImpl -> ExtendedSupergroupChatImpl.serializer().serialize(encoder, value)
            is ExtendedForumChatImpl -> ExtendedForumChatImpl.serializer().serialize(encoder, value)
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
