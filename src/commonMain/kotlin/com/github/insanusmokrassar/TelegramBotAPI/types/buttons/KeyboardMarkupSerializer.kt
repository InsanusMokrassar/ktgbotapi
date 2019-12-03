package com.github.insanusmokrassar.TelegramBotAPI.types.buttons

import kotlinx.serialization.*
import kotlinx.serialization.internal.StringDescriptor

@Serializer(KeyboardMarkup::class)
object KeyboardMarkupSerializer : KSerializer<KeyboardMarkup> {
    override val descriptor: SerialDescriptor = StringDescriptor.withName(KeyboardMarkup::class.toString())
    override fun serialize(encoder: Encoder, obj: KeyboardMarkup) {
        when(obj) {
            is ForceReply -> ForceReply.serializer().serialize(encoder, obj)
            is InlineKeyboardMarkup -> InlineKeyboardMarkup.serializer().serialize(encoder, obj)
            is ReplyKeyboardMarkup -> ReplyKeyboardMarkup.serializer().serialize(encoder, obj)
            is ReplyKeyboardRemove -> ReplyKeyboardRemove.serializer().serialize(encoder, obj)
        }
    }

    override fun deserialize(decoder: Decoder): KeyboardMarkup {
        TODO("not implemented")
    }
}
