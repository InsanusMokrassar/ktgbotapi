package dev.inmo.tgbotapi.types.buttons

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

@Serializable(KeyboardButtonPollTypeSerializer::class)
sealed interface KeyboardButtonPollType {
    val type: String
}

@Serializable
class UnknownKeyboardButtonPollType internal constructor(override val type: String): KeyboardButtonPollType

@Serializable
object RegularKeyboardButtonPollType : KeyboardButtonPollType {
    override val type: String = regularPollType
}

@Serializable
object QuizKeyboardButtonPollType : KeyboardButtonPollType {
    override val type: String = quizPollType
}

@RiskFeature
object KeyboardButtonPollTypeSerializer : KSerializer<KeyboardButtonPollType> {
    private val internalSerializer = JsonElement.serializer()
    override val descriptor: SerialDescriptor = internalSerializer.descriptor

    override fun deserialize(decoder: Decoder): KeyboardButtonPollType {
        val type = when (val asJson = internalSerializer.deserialize(decoder)) {
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
        internalSerializer.serialize(
            encoder,
            JsonObject(
                mapOf(
                    typeField to JsonPrimitive(value.type)
                )
            )
        )
    }
}
