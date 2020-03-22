package com.github.insanusmokrassar.TelegramBotAPI.types.polls

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.utils.nonstrictJsonFormat
import kotlinx.serialization.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.*

@Serializable(PollSerializer::class)
sealed class Poll {
    abstract val id: PollIdentifier
    abstract val question: String
    abstract val options: List<PollOption>
    abstract val votesCount: Int
    abstract val isClosed: Boolean
    abstract val isAnonymous: Boolean
}

@Serializable
data class UnknownPollType internal constructor(
    @SerialName(idField)
    override val id: PollIdentifier,
    @SerialName(questionField)
    override val question: String,
    @SerialName(optionsField)
    override val options: List<PollOption>,
    @SerialName(totalVoterCountField)
    override val votesCount: Int,
    @SerialName(isClosedField)
    override val isClosed: Boolean = false,
    @SerialName(isAnonymousField)
    override val isAnonymous: Boolean = false,
    val raw: String
) : Poll()

@Serializable
data class RegularPoll(
    @SerialName(idField)
    override val id: PollIdentifier,
    @SerialName(questionField)
    override val question: String,
    @SerialName(optionsField)
    override val options: List<PollOption>,
    @SerialName(totalVoterCountField)
    override val votesCount: Int,
    @SerialName(isClosedField)
    override val isClosed: Boolean = false,
    @SerialName(isAnonymousField)
    override val isAnonymous: Boolean = false,
    @SerialName(allowsMultipleAnswersField)
    val allowMultipleAnswers: Boolean = false
) : Poll()

@Serializable
data class QuizPoll(
    @SerialName(idField)
    override val id: PollIdentifier,
    @SerialName(questionField)
    override val question: String,
    @SerialName(optionsField)
    override val options: List<PollOption>,
    @SerialName(totalVoterCountField)
    override val votesCount: Int,
    /**
     * Nullable due to documentation (https://core.telegram.org/bots/api#poll)
     */
    @SerialName(correctOptionIdField)
    val correctOptionId: Int? = null,
    @SerialName(isClosedField)
    override val isClosed: Boolean = false,
    @SerialName(isAnonymousField)
    override val isAnonymous: Boolean = false
) : Poll()

@Serializer(Poll::class)
internal object PollSerializer : KSerializer<Poll> {
    private val pollOptionsSerializer = ListSerializer(PollOption.serializer())
    override fun deserialize(decoder: Decoder): Poll {
        val asJson = JsonObjectSerializer.deserialize(decoder)

        return when (asJson.getPrimitive(typeField).content) {
            regularPollType -> nonstrictJsonFormat.fromJson(
                RegularPoll.serializer(),
                asJson
            )
            quizPollType -> nonstrictJsonFormat.fromJson(
                QuizPoll.serializer(),
                asJson
            )
            else -> UnknownPollType(
                asJson.getPrimitive(idField).content,
                asJson.getPrimitive(questionField).content,
                nonstrictJsonFormat.fromJson(
                    pollOptionsSerializer,
                    asJson.getArray(optionsField)
                ),
                asJson.getPrimitive(totalVoterCountField).int,
                asJson.getPrimitiveOrNull(isClosedField) ?.booleanOrNull ?: false,
                asJson.getPrimitiveOrNull(isAnonymousField) ?.booleanOrNull ?: true,
                asJson.toString()
            )
        }
    }

    override fun serialize(encoder: Encoder, value: Poll) {
        val asJson = when (value) {
            is RegularPoll -> nonstrictJsonFormat.toJson(RegularPoll.serializer(), value)
            is QuizPoll -> nonstrictJsonFormat.toJson(QuizPoll.serializer(), value)
            is UnknownPollType -> throw IllegalArgumentException("Currently unable to correctly serialize object of poll $value")
        }
        val resultJson = JsonObject(
            asJson.jsonObject + (typeField to when (value) {
                is RegularPoll -> JsonPrimitive(regularPollType)
                is QuizPoll -> JsonPrimitive(quizPollType)
                is UnknownPollType -> throw IllegalArgumentException("Currently unable to correctly serialize object of poll $value")
            })
        )
        JsonObjectSerializer.serialize(encoder, resultJson)
    }
}
