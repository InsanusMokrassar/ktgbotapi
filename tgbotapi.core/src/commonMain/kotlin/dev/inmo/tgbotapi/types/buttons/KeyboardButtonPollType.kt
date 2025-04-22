package dev.inmo.tgbotapi.types.buttons

import dev.inmo.micro_utils.common.Warning
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

/**
 * Poll type for [RequestPollKeyboardButton]. Visit https://core.telegram.org/bots/api#keyboardbuttonpolltype for more
 * info and see inheritors.
 *
 * @see KeyboardButtonPollTypeSerializer
 */
@Serializable(KeyboardButtonPollTypeSerializer::class)
sealed interface KeyboardButtonPollType {
    val type: String
}

@Serializable
@Warning("This type should be used only in cases you are sure that it is required")
class UnknownKeyboardButtonPollType internal constructor(override val type: String) : KeyboardButtonPollType

/**
 * Just a regular poll type
 *
 * @see dev.inmo.tgbotapi.types.polls.RegularPoll
 * @see RequestPollKeyboardButton
 */
@Serializable
object RegularKeyboardButtonPollType : KeyboardButtonPollType {
    override val type: String = regularPollType
}

/**
 * Quiz poll type
 *
 * @see dev.inmo.tgbotapi.types.polls.QuizPoll
 * @see RequestPollKeyboardButton
 */
@Serializable
object QuizKeyboardButtonPollType : KeyboardButtonPollType {
    override val type: String = quizPollType
}

@RiskFeature
object KeyboardButtonPollTypeSerializer : KSerializer<KeyboardButtonPollType> {
    private val internalSerializer = JsonElement.serializer()
    override val descriptor: SerialDescriptor = internalSerializer.descriptor

    override fun deserialize(decoder: Decoder): KeyboardButtonPollType {
        val type =
            when (val asJson = internalSerializer.deserialize(decoder)) {
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
    override fun serialize(
        encoder: Encoder,
        value: KeyboardButtonPollType,
    ) {
        internalSerializer.serialize(
            encoder,
            JsonObject(
                mapOf(
                    typeField to JsonPrimitive(value.type),
                ),
            ),
        )
    }
}
