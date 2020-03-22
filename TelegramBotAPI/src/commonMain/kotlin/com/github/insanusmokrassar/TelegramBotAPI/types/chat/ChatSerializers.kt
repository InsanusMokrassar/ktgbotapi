package com.github.insanusmokrassar.TelegramBotAPI.types.chat

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.UnknownChatType
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.extended.ExtendedChat
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.extended.*
import com.github.insanusmokrassar.TelegramBotAPI.utils.nonstrictJsonFormat
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.JsonObjectSerializer

private val formatter = nonstrictJsonFormat

internal object PreviewChatSerializer : KSerializer<Chat> {
    override val descriptor: SerialDescriptor = SerialDescriptor("PreviewChatSerializer", PolymorphicKind.OPEN)

    override fun deserialize(decoder: Decoder): Chat {
        val decodedJson = JsonObjectSerializer.deserialize(decoder)

        val type = decodedJson.getPrimitive(typeField).content

        return when (type) {
            "private" -> formatter.fromJson(PrivateChatImpl.serializer(), decodedJson)
            "group" -> formatter.fromJson(GroupChatImpl.serializer(), decodedJson)
            "supergroup" -> formatter.fromJson(SupergroupChatImpl.serializer(), decodedJson)
            "channel" -> formatter.fromJson(ChannelChatImpl.serializer(), decodedJson)
            else -> UnknownChatType(
                formatter.fromJson(Long.serializer(), decodedJson.getPrimitive(chatIdField)).toChatId(),
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
    override val descriptor: SerialDescriptor = SerialDescriptor("PreviewChatSerializer", PolymorphicKind.OPEN)

    override fun deserialize(decoder: Decoder): ExtendedChat {
        val decodedJson = JsonObjectSerializer.deserialize(decoder)

        val type = decodedJson.getPrimitive(typeField).content

        return when (type) {
            "private" -> formatter.fromJson(ExtendedPrivateChatImpl.serializer(), decodedJson)
            "group" -> formatter.fromJson(ExtendedGroupChatImpl.serializer(), decodedJson)
            "supergroup" -> formatter.fromJson(ExtendedSupergroupChatImpl.serializer(), decodedJson)
            "channel" -> formatter.fromJson(ExtendedChannelChatImpl.serializer(), decodedJson)
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



