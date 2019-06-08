package com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardButtons

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringDescriptor
import kotlinx.serialization.json.*

object InlineKeyboardButtonSerializer : KSerializer<InlineKeyboardButton> {
    override val descriptor: SerialDescriptor = StringDescriptor.withName("com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardButtons.InlineKeyboardButton")

    private fun resolveSerializer(json: JsonObject): KSerializer<out InlineKeyboardButton> {
        return when {
            json[callbackDataField] != null -> CallbackDataInlineKeyboardButton.serializer()
            json[loginUrlField] != null -> LoginURLInlineKeyboardButton.serializer()
            json[payField] != null -> PayInlineKeyboardButton.serializer()
            json[switchInlineQueryField] != null -> SwitchInlineQueryInlineKeyboardButton.serializer()
            json[switchInlineQueryCurrentChatField] != null -> SwitchInlineQueryCurrentChatInlineKeyboardButton.serializer()
            json[urlField] != null -> URLInlineKeyboardButton.serializer()
            else -> throw IllegalArgumentException("Can't find correct serializer for inline button serialized as $json")
        }
    }

    override fun deserialize(decoder: Decoder): InlineKeyboardButton {
        val json = JsonElementSerializer.deserialize(decoder)

        return Json.nonstrict.fromJson(resolveSerializer(json.jsonObject), json)
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
