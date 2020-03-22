package com.github.insanusmokrassar.TelegramBotAPI.types.buttons

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.utils.nonstrictJsonFormat
import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable(KeyboardButtonSerializer::class)
sealed class KeyboardButton {
    abstract val text: String
}

@Serializable
data class SimpleKeyboardButton(
    override val text: String
) : KeyboardButton()

@Serializable
data class UnknownKeyboardButton internal constructor(
    override val text: String,
    val raw: String
) : KeyboardButton()

@Serializable
data class RequestContactKeyboardButton(
    override val text: String
) : KeyboardButton() {
    @SerialName(requestContactField)
    val requestContact: Boolean = true
}

@Serializable
data class RequestLocationKeyboardButton(
    override val text: String
) : KeyboardButton() {
    @SerialName(requestLocationField)
    val requestLocation: Boolean = true
}

@Serializable
data class RequestPollKeyboardButton(
    override val text: String,
    @SerialName(requestPollField)
    val requestPoll: KeyboardButtonPollType
) : KeyboardButton()

@Serializer(KeyboardButton::class)
internal object KeyboardButtonSerializer : KSerializer<KeyboardButton> {
    override fun deserialize(decoder: Decoder): KeyboardButton {
        val asJson = JsonElementSerializer.deserialize(decoder)

        return when {
            asJson is JsonPrimitive -> SimpleKeyboardButton(asJson.content)
            asJson is JsonObject && asJson.getPrimitiveOrNull(requestContactField) != null -> RequestContactKeyboardButton(
                asJson.getPrimitive(textField).content
            )
            asJson is JsonObject && asJson.getPrimitiveOrNull(requestLocationField) != null -> RequestLocationKeyboardButton(
                asJson.getPrimitive(textField).content
            )
            asJson is JsonObject && asJson.getObjectOrNull(requestPollField) != null -> RequestPollKeyboardButton(
                asJson.getPrimitive(textField).content,
                nonstrictJsonFormat.fromJson(
                    KeyboardButtonPollType.serializer(),
                    asJson.getObject(requestPollField)
                )
            )
            else -> UnknownKeyboardButton(
                when (asJson) {
                    is JsonObject -> asJson.getPrimitive(textField).content
                    is JsonArray -> ""
                    is JsonPrimitive -> asJson.content
                },
                asJson.toString()
            )
        }
    }

    override fun serialize(encoder: Encoder, value: KeyboardButton) {
        when (value) {
            is RequestContactKeyboardButton -> RequestContactKeyboardButton.serializer().serialize(encoder, value)
            is RequestLocationKeyboardButton -> RequestLocationKeyboardButton.serializer().serialize(encoder, value)
            is RequestPollKeyboardButton -> RequestPollKeyboardButton.serializer().serialize(encoder, value)
            is SimpleKeyboardButton -> encoder.encodeString(value.text)
            is UnknownKeyboardButton -> JsonElementSerializer.serialize(encoder, nonstrictJsonFormat.parseJson(value.raw))
        }
    }
}

