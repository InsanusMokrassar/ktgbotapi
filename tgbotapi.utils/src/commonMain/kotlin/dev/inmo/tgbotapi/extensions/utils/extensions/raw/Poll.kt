package dev.inmo.tgbotapi.extensions.utils.extensions.raw

import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.polls.*
import dev.inmo.tgbotapi.utils.RiskFeature
import korlibs.time.seconds
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@RiskFeature(RawFieldsUsageWarning)
val Poll.total_voter_count: Int
    get() = votesCount
@RiskFeature(RawFieldsUsageWarning)
val Poll.type: String
    get() = when (this) {
        is RegularPoll -> "regular"
        is UnknownPollType -> raw!!.jsonObject["type"]!!.jsonPrimitive.content
        is QuizPoll -> "quiz"
    }
@RiskFeature(RawFieldsUsageWarning)
val Poll.allows_multiple_answers: Boolean
    get() = asMultipleAnswersPoll() ?.allowMultipleAnswers == true
@RiskFeature(RawFieldsUsageWarning)
val Poll.correct_option_id: Int?
    get() = asQuizPoll() ?.correctOptionId
@RiskFeature(RawFieldsUsageWarning)
val Poll.explanation: String?
    get() = asQuizPoll() ?.text
@RiskFeature(RawFieldsUsageWarning)
val Poll.explanation_entities: TextSourcesList?
    get() = asQuizPoll() ?.textSources
@RiskFeature(RawFieldsUsageWarning)
val Poll.open_period: Seconds?
    get() = scheduledCloseInfo ?.asApproximateScheduledCloseInfo() ?.openDuration ?.seconds ?.toInt()
@RiskFeature(RawFieldsUsageWarning)
val Poll.close_date: TelegramDate?
    get() = scheduledCloseInfo ?.asExactScheduledCloseInfo() ?.closeDateTime ?.toTelegramDate()
@RiskFeature(RawFieldsUsageWarning)
val Poll.is_closed: Boolean
    get() = isClosed
@RiskFeature(RawFieldsUsageWarning)
val Poll.is_anonymous: Boolean
    get() = isAnonymous
