package com.github.insanusmokrassar.TelegramBotAPI.types.buttons

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.utils.nonstrictJsonFormat
import kotlinx.serialization.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
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
        val asJson = JsonElement.serializer().deserialize(decoder)

        return when {
            asJson is JsonPrimitive -> SimpleKeyboardButton(asJson.content)
            asJson is JsonObject && asJson[requestContactField] != null -> RequestContactKeyboardButton(
                asJson[textField]!!.jsonPrimitive.content
            )
            asJson is JsonObject && asJson[requestLocationField] != null -> RequestLocationKeyboardButton(
                asJson[textField]!!.jsonPrimitive.content
            )
            asJson is JsonObject && asJson[requestPollField] != null -> RequestPollKeyboardButton(
                asJson[textField]!!.jsonPrimitive.content,
                nonstrictJsonFormat.decodeFromJsonElement(
                    KeyboardButtonPollType.serializer(),
                    asJson[requestPollField] ?.jsonObject ?: buildJsonObject {  }
                )
            )
            else -> UnknownKeyboardButton(
                when (asJson) {
                    is JsonObject -> asJson[textField]!!.jsonPrimitive.content
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
            is UnknownKeyboardButton -> JsonElement.serializer().serialize(encoder, nonstrictJsonFormat.parseToJsonElement(value.raw))
        }
    }
}

