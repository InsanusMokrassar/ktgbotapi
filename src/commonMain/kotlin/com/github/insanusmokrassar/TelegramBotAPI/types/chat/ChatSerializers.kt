package com.github.insanusmokrassar.TelegramBotAPI.types.chat

import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.extended.ExtendedChat
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.extended.*
import com.github.insanusmokrassar.TelegramBotAPI.types.typeField
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringDescriptor
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObjectSerializer

private val formatter = Json.nonstrict

internal object PreviewChatSerializer : KSerializer<Chat> {
    override val descriptor: SerialDescriptor = StringDescriptor.withName("PreviewChatSerializer")

    override fun deserialize(decoder: Decoder): Chat {
        val decodedJson = JsonObjectSerializer.deserialize(decoder)

        val type = decodedJson.getPrimitive(typeField).content

        return when (type) {
            "private" -> formatter.fromJson(PrivateChatImpl.serializer(), decodedJson)
            "group" -> formatter.fromJson(GroupChatImpl.serializer(), decodedJson)
            "supergroup" -> formatter.fromJson(SupergroupChatImpl.serializer(), decodedJson)
            "channel" -> formatter.fromJson(ChannelChatImpl.serializer(), decodedJson)
            else -> throw IllegalArgumentException("Unknown type of chat")
        }
    }

    override fun serialize(encoder: Encoder, obj: Chat) {
        when (obj) {
            is ExtendedChat -> ExtendedChatSerializer.serialize(encoder, obj)
            is PrivateChatImpl -> PrivateChatImpl.serializer().serialize(encoder, obj)
            is GroupChatImpl -> GroupChatImpl.serializer().serialize(encoder, obj)
            is SupergroupChatImpl -> SupergroupChatImpl.serializer().serialize(encoder, obj)
            is ChannelChatImpl -> ChannelChatImpl.serializer().serialize(encoder, obj)
        }
    }
}

internal object ExtendedChatSerializer : KSerializer<ExtendedChat> {
    override val descriptor: SerialDescriptor = StringDescriptor.withName("PreviewChatSerializer")

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

    override fun serialize(encoder: Encoder, obj: ExtendedChat) {
        when (obj) {
            is ExtendedPrivateChatImpl -> ExtendedPrivateChatImpl.serializer().serialize(encoder, obj)
            is ExtendedGroupChatImpl -> ExtendedGroupChatImpl.serializer().serialize(encoder, obj)
            is ExtendedSupergroupChatImpl -> ExtendedSupergroupChatImpl.serializer().serialize(encoder, obj)
            is ExtendedChannelChatImpl -> ExtendedChannelChatImpl.serializer().serialize(encoder, obj)
        }
    }
}



