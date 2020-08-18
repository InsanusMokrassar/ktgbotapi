package com.github.insanusmokrassar.TelegramBotAPI.types.buttons

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

@Serializable(KeyboardButtonPollTypeSerializer::class)
sealed class KeyboardButtonPollType {
    abstract val type: String
}

@Serializable
class UnknownKeyboardButtonPollType internal constructor(override val type: String): KeyboardButtonPollType()

@Serializable
object RegularKeyboardButtonPollType : KeyboardButtonPollType() {
    override val type: String = regularPollType
}

@Serializable
object QuizKeyboardButtonPollType : KeyboardButtonPollType() {
    override val type: String = quizPollType
}

@Serializer(KeyboardButtonPollType::class)
internal object KeyboardButtonPollTypeSerializer : KSerializer<KeyboardButtonPollType> {
    override fun deserialize(decoder: Decoder): KeyboardButtonPollType {
        val asJson = JsonElement.serializer().deserialize(decoder)

        val type = when (asJson) {
            is JsonPrimitive -> asJson.content
            else -> asJson.jsonObject[typeField] ?.jsonPrimitive ?.content ?: "absent"
        }

        return when (type) {
            regularPollType -> RegularKeyboardButtonPollType
            quizPollType -> QuizKeyboardButtonPollType
            else -> UnknownKeyboardButtonPollType(type)
        }
    }

    /**
     * Crutch due to the fact that direct serialization of objects currently does not work perfectly
     */
    override fun serialize(encoder: Encoder, value: KeyboardButtonPollType) {
        JsonObject.serializer().serialize(
            encoder,
            JsonObject(
                mapOf(
                    typeField to JsonPrimitive(value.type)
                )
            )
        )
    }
}
