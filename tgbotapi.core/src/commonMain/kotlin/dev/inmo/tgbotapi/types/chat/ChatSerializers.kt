package dev.inmo.tgbotapi.types.chat

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import dev.inmo.tgbotapi.types.chat.abstracts.UnknownChatType
import dev.inmo.tgbotapi.types.chat.abstracts.extended.ExtendedChat
import dev.inmo.tgbotapi.types.chat.extended.*
import dev.inmo.tgbotapi.utils.nonstrictJsonFormat
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

private val formatter
    get() = nonstrictJsonFormat

internal object PreviewChatSerializer : KSerializer<Chat> {
    @InternalSerializationApi
    override val descriptor: SerialDescriptor = buildSerialDescriptor("PreviewChatSerializer", PolymorphicKind.OPEN)

    override fun deserialize(decoder: Decoder): Chat {
        val decodedJson = JsonObject.serializer().deserialize(decoder)

        val type = decodedJson[typeField] ?.jsonPrimitive ?.content ?: error("Field $typeField must be presented, but absent in $decodedJson")

        return when (type) {
            "private" -> formatter.decodeFromJsonElement(PrivateChatImpl.serializer(), decodedJson)
            "group" -> formatter.decodeFromJsonElement(GroupChatImpl.serializer(), decodedJson)
            "supergroup" -> formatter.decodeFromJsonElement(SupergroupChatImpl.serializer(), decodedJson)
            "channel" -> formatter.decodeFromJsonElement(ChannelChatImpl.serializer(), decodedJson)
            else -> UnknownChatType(
                formatter.decodeFromJsonElement(Long.serializer(), decodedJson[chatIdField] ?: JsonPrimitive(-1)).toChatId(),
                decodedJson.toString()
            )
        }
    }

    override fun serialize(encoder: Encoder, value: Chat) {
        when (value) {
            is ExtendedChat -> ExtendedChatSerializer.serialize(encoder, value)
            is PrivateChatImpl -> PrivateChatImpl.serializer().serialize(encoder, value)
            is GroupChatImpl -> GroupChatImpl.serializer().serialize(encoder, value)
            is SupergroupChatImpl -> SupergroupChatImpl.serializer().serialize(encoder, value)
            is ChannelChatImpl -> ChannelChatImpl.serializer().serialize(encoder, value)
        }
    }
}

internal object ExtendedChatSerializer : KSerializer<ExtendedChat> {
    @InternalSerializationApi
    override val descriptor: SerialDescriptor = buildSerialDescriptor("PreviewChatSerializer", PolymorphicKind.OPEN)

    override fun deserialize(decoder: Decoder): ExtendedChat {
        val decodedJson = JsonObject.serializer().deserialize(decoder)

        val type = decodedJson[typeField] ?.jsonPrimitive ?.content ?: error("Field $typeField must be presented, but absent in $decodedJson")

        return when (type) {
            "private" -> formatter.decodeFromJsonElement(ExtendedPrivateChatImpl.serializer(), decodedJson)
            "group" -> formatter.decodeFromJsonElement(ExtendedGroupChatImpl.serializer(), decodedJson)
            "supergroup" -> formatter.decodeFromJsonElement(ExtendedSupergroupChatImpl.serializer(), decodedJson)
            "channel" -> formatter.decodeFromJsonElement(ExtendedChannelChatImpl.serializer(), decodedJson)
            else -> throw IllegalArgumentException("Unknown type of chat")
        }
    }

    override fun serialize(encoder: Encoder, value: ExtendedChat) {
        when (value) {
            is ExtendedPrivateChatImpl -> ExtendedPrivateChatImpl.serializer().serialize(encoder, value)
            is ExtendedGroupChatImpl -> ExtendedGroupChatImpl.serializer().serialize(encoder, value)
            is ExtendedSupergroupChatImpl -> ExtendedSupergroupChatImpl.serializer().serialize(encoder, value)
            is ExtendedChannelChatImpl -> ExtendedChannelChatImpl.serializer().serialize(encoder, value)
        }
    }
}



