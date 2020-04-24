package com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardButtons

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.utils.nonstrictJsonFormat
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonElementSerializer
import kotlinx.serialization.json.JsonObject

internal object InlineKeyboardButtonSerializer : KSerializer<InlineKeyboardButton> {
    override val descriptor: SerialDescriptor = SerialDescriptor(
        "com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardButtons.InlineKeyboardButton",
        PolymorphicKind.SEALED
    )

    private fun resolveSerializer(json: JsonObject): KSerializer<out InlineKeyboardButton>? {
        return when {
            json[callbackDataField] != null -> CallbackDataInlineKeyboardButton.serializer()
            json[callbackGameField] != null -> CallbackGameInlineKeyboardButton.serializer()
            json[loginUrlField] != null -> LoginURLInlineKeyboardButton.serializer()
            json[payField] != null -> PayInlineKeyboardButton.serializer()
            json[switchInlineQueryField] != null -> SwitchInlineQueryInlineKeyboardButton.serializer()
            json[switchInlineQueryCurrentChatField] != null -> SwitchInlineQueryCurrentChatInlineKeyboardButton.serializer()
            json[urlField] != null -> URLInlineKeyboardButton.serializer()
            else -> null
        }
    }

    override fun deserialize(decoder: Decoder): InlineKeyboardButton {
        val json = JsonElementSerializer.deserialize(decoder)

        return (json as? JsonObject) ?.let { resolveSerializer(it) } ?.let {
            nonstrictJsonFormat.fromJson(it, json)
        } ?: UnknownInlineKeyboardButton("", json)
    }

    override fun serialize(encoder: Encoder, value: InlineKeyboardButton) {
        when (value) {
            is CallbackDataInlineKeyboardButton -> CallbackDataInlineKeyboardButton.serializer().serialize(encoder, value)
            is LoginURLInlineKeyboardButton -> LoginURLInlineKeyboardButton.serializer().serialize(encoder, value)
            is PayInlineKeyboardButton -> PayInlineKeyboardButton.serializer().serialize(encoder, value)
            is SwitchInlineQueryInlineKeyboardButton -> SwitchInlineQueryInlineKeyboardButton.serializer().serialize(encoder, value)
            is SwitchInlineQueryCurrentChatInlineKeyboardButton -> SwitchInlineQueryCurrentChatInlineKeyboardButton.serializer().serialize(encoder, value)
            is URLInlineKeyboardButton -> URLInlineKeyboardButton.serializer().serialize(encoder, value)
            is CallbackGameInlineKeyboardButton -> CallbackGameInlineKeyboardButton.serializer().serialize(encoder, value)
            is UnknownInlineKeyboardButton -> JsonElementSerializer.serialize(encoder, value.rawData)
        }
    }
}
