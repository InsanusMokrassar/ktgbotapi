package com.github.insanusmokrassar.TelegramBotAPI.types.polls

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.*
import kotlinx.serialization.internal.ArrayListSerializer
import kotlinx.serialization.json.*

@Serializable(PollSerializer::class)
sealed class Poll {
    abstract val id: PollIdentifier
    abstract val question: String
    abstract val options: List<PollOption>
    abstract val votesCount: Int
    abstract val closed: Boolean
    abstract val isAnonymous: Boolean
}

@Serializable
data class UnknownPollType(
    @SerialName(idField)
    override val id: PollIdentifier,
    @SerialName(questionField)
    override val question: String,
    @SerialName(optionsField)
    override val options: List<PollOption>,
    @SerialName(totalVoterCountField)
    override val votesCount: Int,
    @SerialName(isClosedField)
    override val closed: Boolean = false,
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
    override val closed: Boolean = false,
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
    @SerialName(isClosedField)
    override val closed: Boolean = false,
    @SerialName(isAnonymousField)
    override val isAnonymous: Boolean = false,
    @SerialName(correctOptionIdField)
    val correctOptionId: Boolean = false
) : Poll()

@Serializer(Poll::class)
internal object PollSerializer : KSerializer<Poll> {
    private val pollOptionsSerializer = ArrayListSerializer(PollOption.serializer())
    override fun deserialize(decoder: Decoder): Poll {
        val asJson = JsonObjectSerializer.deserialize(decoder)

        return when (asJson.getPrimitive(typeField).content) {
            regularPollType -> Json.nonstrict.fromJson(
                RegularPoll.serializer(),
                asJson
            )
            quizPollType -> Json.nonstrict.fromJson(
                QuizPoll.serializer(),
                asJson
            )
            else -> UnknownPollType(
                asJson.getPrimitive(idField).content,
                asJson.getPrimitive(questionField).content,
                Json.nonstrict.fromJson(
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

    override fun serialize(encoder: Encoder, obj: Poll) {
        val asJson = when (obj) {
            is RegularPoll -> Json.nonstrict.toJson(RegularPoll.serializer(), obj)
            is QuizPoll -> Json.nonstrict.toJson(QuizPoll.serializer(), obj)
            is UnknownPollType -> throw IllegalArgumentException("Currently unable to correctly serialize object of poll $obj")
        }
        val resultJson = JsonObject(
            asJson.jsonObject + (typeField to when (obj) {
                is RegularPoll -> JsonPrimitive(regularPollType)
                is QuizPoll -> JsonPrimitive(quizPollType)
                is UnknownPollType -> throw IllegalArgumentException("Currently unable to correctly serialize object of poll $obj")
            })
        )
        JsonObjectSerializer.serialize(encoder, resultJson)
    }
}
