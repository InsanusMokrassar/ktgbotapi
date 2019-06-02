package com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardButtons

import kotlinx.serialization.*
import kotlinx.serialization.internal.StringDescriptor
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElementSerializer

object InlineKeyboardButtonSerializer : KSerializer<InlineKeyboardButton> {
    override val descriptor: SerialDescriptor = StringDescriptor.withName("com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardButtons.InlineKeyboardButton")

    private val serializers = listOf(
        CallbackDataInlineKeyboardButton.serializer(),
        LoginURLInlineKeyboardButton.serializer(),
        PayInlineKeyboardButton.serializer(),
        SwitchInlineQueryInlineKeyboardButton.serializer(),
        SwitchInlineQueryCurrentChatInlineKeyboardButton.serializer(),
        URLInlineKeyboardButton.serializer()
    )

    override fun deserialize(decoder: Decoder): InlineKeyboardButton {
        val json = JsonElementSerializer.deserialize(decoder)

        serializers.forEach {
            try {
                return Json.nonstrict.fromJson(it, json)
            } catch (e: SerializationException) {
                e
            }
        }

        throw IllegalArgumentException("There is no known type of serializer for \"$json\" as inline button")
    }

    override fun serialize(encoder: Encoder, obj: InlineKeyboardButton) {
        when (obj) {
            is CallbackDataInlineKeyboardButton -> CallbackDataInlineKeyboardButton.serializer().serialize(encoder, obj)
            is LoginURLInlineKeyboardButton -> LoginURLInlineKeyboardButton.serializer().serialize(encoder, obj)
            is PayInlineKeyboardButton -> PayInlineKeyboardButton.serializer().serialize(encoder, obj)
            is SwitchInlineQueryInlineKeyboardButton -> SwitchInlineQueryInlineKeyboardButton.serializer().serialize(encoder, obj)
            is SwitchInlineQueryCurrentChatInlineKeyboardButton -> SwitchInlineQueryCurrentChatInlineKeyboardButton.serializer().serialize(encoder, obj)
            is URLInlineKeyboardButton -> URLInlineKeyboardButton.serializer().serialize(encoder, obj)
        }
    }
}
