package dev.inmo.tgbotapi.types.polls

import dev.inmo.tgbotapi.abstracts.TextedInput
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.decodeDataAndJson
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import korlibs.time.DateTime
import korlibs.time.TimeSpan
import korlibs.time.seconds
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

@ClassCastsIncluded
sealed interface ScheduledCloseInfo {
    val closeDateTime: DateTime
}

data class ExactScheduledCloseInfo(
    override val closeDateTime: DateTime,
) : ScheduledCloseInfo

data class ApproximateScheduledCloseInfo(
    val openDuration: TimeSpan,
    @Suppress("MemberVisibilityCanBePrivate")
    val startPoint: DateTime = DateTime.now(),
) : ScheduledCloseInfo {
    override val closeDateTime: DateTime = startPoint + openDuration
}

val LongSeconds.asApproximateScheduledCloseInfo
    get() = ApproximateScheduledCloseInfo(
        TimeSpan(this * 1000.0),
    )

fun LongSeconds.asApproximateScheduledCloseInfo(startPoint: DateTime) = ApproximateScheduledCloseInfo(
    TimeSpan(this * 1000.0),
    startPoint,
)

val LongSeconds.asExactScheduledCloseInfo
    get() = ExactScheduledCloseInfo(
        DateTime(unixMillis = this * 1000.0),
    )

@Serializable(PollSerializer::class)
@ClassCastsIncluded
sealed interface Poll : ReplyInfo.External.ContentVariant, TextedInput {
    val id: PollId
    val question: String
    override val text: String
        get() = question
    val questionTextSources: List<TextSource>
        get() = textSources
    val options: List<PollOption>
    val votesCount: Int
    val isClosed: Boolean
    val isAnonymous: Boolean
    val scheduledCloseInfo: ScheduledCloseInfo?
}

@Serializable(PollSerializer::class)
sealed interface MultipleAnswersPoll : Poll {
    val allowMultipleAnswers: Boolean
}

@Serializable
private class RawPoll(
    @SerialName(idField)
    val id: PollId,
    @SerialName(questionField)
    val question: String,
    @SerialName(optionsField)
    val options: List<PollOption>,
    @SerialName(totalVoterCountField)
    val votesCount: Int,
    @SerialName(questionEntitiesField)
    val questionEntities: List<RawMessageEntity> = emptyList(),
    @SerialName(isClosedField)
    val isClosed: Boolean = false,
    @SerialName(isAnonymousField)
    val isAnonymous: Boolean = false,
    @SerialName(typeField)
    val type: String,
    @SerialName(allowsMultipleAnswersField)
    val allowMultipleAnswers: Boolean = false,
    @SerialName(correctOptionIdField)
    val correctOptionId: Int? = null,
    @SerialName(explanationField)
    val explanation: String? = null,
    @SerialName(explanationEntitiesField)
    val explanationEntities: List<RawMessageEntity> = emptyList(),
    @SerialName(openPeriodField)
    val openPeriod: LongSeconds? = null,
    @SerialName(closeDateField)
    val closeDate: LongSeconds? = null,
) {
    @Transient
    val scheduledCloseInfo: ScheduledCloseInfo? = closeDate ?.asExactScheduledCloseInfo ?: openPeriod ?.asApproximateScheduledCloseInfo
}

@Serializable
data class UnknownPollType internal constructor(
    @SerialName(idField)
    override val id: PollId,
    @SerialName(questionField)
    override val question: String,
    @SerialName(optionsField)
    override val options: List<PollOption>,
    @SerialName(totalVoterCountField)
    override val votesCount: Int,
    @SerialName(questionEntitiesField)
    override val textSources: List<TextSource> = emptyList(),
    @SerialName(isClosedField)
    override val isClosed: Boolean = false,
    @SerialName(isAnonymousField)
    override val isAnonymous: Boolean = false,
    @Serializable
    val raw: JsonElement? = null,
) : Poll {
    @Transient
    override val scheduledCloseInfo: ScheduledCloseInfo? = raw ?.jsonObject ?.let {
        (it[closeDateField] ?: it[openPeriodField])
            ?.jsonPrimitive
            ?.longOrNull
            ?.asApproximateScheduledCloseInfo
    }
}

@Serializable(PollSerializer::class)
data class RegularPoll(
    override val id: PollId,
    override val question: String,
    override val textSources: List<TextSource>,
    override val options: List<PollOption>,
    override val votesCount: Int,
    override val isClosed: Boolean = false,
    override val isAnonymous: Boolean = false,
    override val allowMultipleAnswers: Boolean = false,
    override val scheduledCloseInfo: ScheduledCloseInfo? = null,
) : MultipleAnswersPoll

@Serializable(PollSerializer::class)
data class QuizPoll(
    override val id: PollId,
    override val question: String,
    override val textSources: List<TextSource> = emptyList(),
    override val options: List<PollOption>,
    override val votesCount: Int,
    /**
     * Nullable due to documentation (https://core.telegram.org/bots/api#poll)
     */
    val correctOptionId: Int? = null,
    val explanation: String?,
    val explanationTextSources: List<TextSource> = emptyList(),
    override val isClosed: Boolean = false,
    override val isAnonymous: Boolean = false,
    override val scheduledCloseInfo: ScheduledCloseInfo? = null,
) : Poll

@RiskFeature
object PollSerializer : KSerializer<Poll> {
    override val descriptor: SerialDescriptor
        get() = RawPoll.serializer().descriptor

    override fun deserialize(decoder: Decoder): Poll {
        val (rawPoll, asJson) = decoder.decodeDataAndJson(RawPoll.serializer())

        return when (rawPoll.type) {
            quizPollType ->
                QuizPoll(
                    rawPoll.id,
                    rawPoll.question,
                    rawPoll.questionEntities.asTextSources(rawPoll.question),
                    rawPoll.options,
                    rawPoll.votesCount,
                    rawPoll.correctOptionId,
                    rawPoll.explanation,
                    rawPoll.explanation?.let { rawPoll.explanationEntities.asTextSources(it) } ?: emptyList(),
                    rawPoll.isClosed,
                    rawPoll.isAnonymous,
                    rawPoll.scheduledCloseInfo,
                )
            regularPollType ->
                RegularPoll(
                    rawPoll.id,
                    rawPoll.question,
                    rawPoll.questionEntities.asTextSources(rawPoll.question),
                    rawPoll.options,
                    rawPoll.votesCount,
                    rawPoll.isClosed,
                    rawPoll.isAnonymous,
                    rawPoll.allowMultipleAnswers,
                    rawPoll.scheduledCloseInfo,
                )
            else ->
                UnknownPollType(
                    rawPoll.id,
                    rawPoll.question,
                    rawPoll.options,
                    rawPoll.votesCount,
                    rawPoll.questionEntities.asTextSources(rawPoll.question),
                    rawPoll.isClosed,
                    rawPoll.isAnonymous,
                    asJson,
                )
        }
    }

    override fun serialize(
        encoder: Encoder,
        value: Poll,
    ) {
        val closeInfo = value.scheduledCloseInfo
        val rawPoll = when (value) {
            is RegularPoll ->
                RawPoll(
                    value.id,
                    value.question,
                    value.options,
                    value.votesCount,
                    value.textSources.toRawMessageEntities(),
                    value.isClosed,
                    value.isAnonymous,
                    regularPollType,
                    value.allowMultipleAnswers,
                    openPeriod = (closeInfo as? ApproximateScheduledCloseInfo) ?.openDuration ?.seconds ?.toLong(),
                    closeDate = (closeInfo as? ExactScheduledCloseInfo) ?.closeDateTime ?.unixMillisLong ?.div(1000L),
                )
            is QuizPoll ->
                RawPoll(
                    value.id,
                    value.question,
                    value.options,
                    value.votesCount,
                    value.textSources.toRawMessageEntities(),
                    value.isClosed,
                    value.isAnonymous,
                    regularPollType,
                    correctOptionId = value.correctOptionId,
                    explanation = value.text,
                    explanationEntities = value.textSources.toRawMessageEntities(),
                    openPeriod = (closeInfo as? ApproximateScheduledCloseInfo) ?.openDuration ?.seconds ?.toLong(),
                    closeDate = (closeInfo as? ExactScheduledCloseInfo) ?.closeDateTime ?.unixMillisLong ?.div(1000L),
                )
            is UnknownPollType -> {
                if (value.raw == null) {
                    UnknownPollType.serializer().serialize(encoder, value)
                } else {
                    JsonElement.serializer().serialize(encoder, value.raw)
                }
                return
            }
        }
        RawPoll.serializer().serialize(encoder, rawPoll)
    }
}
