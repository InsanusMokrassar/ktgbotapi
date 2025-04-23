package dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.nonstrictJsonFormat
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

@RiskFeature
object InlineKeyboardButtonSerializer : KSerializer<InlineKeyboardButton> {
    @OptIn(InternalSerializationApi::class)
    override val descriptor: SerialDescriptor = buildSerialDescriptor(
        "dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.InlineKeyboardButton",
        PolymorphicKind.SEALED,
    )

    private fun resolveSerializer(json: JsonObject): KSerializer<out InlineKeyboardButton>? {
        return when {
            json[callbackDataField] != null -> CallbackDataInlineKeyboardButton.serializer()
            json[callbackGameField] != null -> CallbackGameInlineKeyboardButton.serializer()
            json[loginUrlField] != null -> LoginURLInlineKeyboardButton.serializer()
            json[payField] != null -> PayInlineKeyboardButton.serializer()
            json[switchInlineQueryField] != null -> SwitchInlineQueryInlineKeyboardButton.serializer()
            json[switchInlineQueryCurrentChatField] != null -> SwitchInlineQueryCurrentChatInlineKeyboardButton.serializer()
            json[switchInlineQueryChosenChatField] != null -> SwitchInlineQueryChosenChatInlineKeyboardButton.serializer()
            json[urlField] != null -> URLInlineKeyboardButton.serializer()
            json[copyTextField] != null -> CopyTextButton.serializer()
            else -> null
        }
    }

    override fun deserialize(decoder: Decoder): InlineKeyboardButton {
        val json = JsonElement.serializer().deserialize(decoder)

        return (json as? JsonObject) ?.let { resolveSerializer(it) }?.let {
            nonstrictJsonFormat.decodeFromJsonElement(it, json)
        } ?: UnknownInlineKeyboardButton(json)
    }

    override fun serialize(
        encoder: Encoder,
        value: InlineKeyboardButton,
    ) {
        when (value) {
            is CallbackDataInlineKeyboardButton -> CallbackDataInlineKeyboardButton.serializer().serialize(encoder, value)
            is LoginURLInlineKeyboardButton -> LoginURLInlineKeyboardButton.serializer().serialize(encoder, value)
            is PayInlineKeyboardButton -> PayInlineKeyboardButton.serializer().serialize(encoder, value)
            is SwitchInlineQueryInlineKeyboardButton -> SwitchInlineQueryInlineKeyboardButton.serializer().serialize(encoder, value)
            is SwitchInlineQueryCurrentChatInlineKeyboardButton ->
                SwitchInlineQueryCurrentChatInlineKeyboardButton.serializer().serialize(
                    encoder,
                    value,
                )
            is SwitchInlineQueryChosenChatInlineKeyboardButton ->
                SwitchInlineQueryChosenChatInlineKeyboardButton.serializer().serialize(
                    encoder,
                    value,
                )
            is URLInlineKeyboardButton -> URLInlineKeyboardButton.serializer().serialize(encoder, value)
            is CopyTextButton -> CopyTextButton.serializer().serialize(encoder, value)
            is WebAppInlineKeyboardButton -> WebAppInlineKeyboardButton.serializer().serialize(encoder, value)
            is CallbackGameInlineKeyboardButton -> CallbackGameInlineKeyboardButton.serializer().serialize(encoder, value)
            is UnknownInlineKeyboardButton -> JsonElement.serializer().serialize(encoder, value.rawData)
        }
    }
}
