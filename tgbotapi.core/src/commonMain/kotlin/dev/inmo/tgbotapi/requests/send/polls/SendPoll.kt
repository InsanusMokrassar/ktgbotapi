package dev.inmo.tgbotapi.requests.send.polls

import dev.inmo.tgbotapi.abstracts.TextedInput
import dev.inmo.tgbotapi.requests.send.abstracts.ReplyingMarkupSendMessageRequest
import dev.inmo.tgbotapi.requests.send.abstracts.SendContentMessageRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.PollContent
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.polls.*
import korlibs.time.DateTime
import korlibs.time.millisecondsLong
import korlibs.time.seconds
import kotlinx.serialization.*

private val commonResultDeserializer: DeserializationStrategy<ContentMessage<PollContent>> = TelegramBotAPIMessageDeserializationStrategyClass()

internal inline val ApproximateScheduledCloseInfo.openPeriod
    get() = openDuration.millisecondsLong.div(1000)
internal inline val ExactScheduledCloseInfo.closeDate
    get() = closeDateTime.unixMillisLong.div(1000)

internal fun checkPollInfo(
    question: String,
    options: List<InputPollOption>,
) {
    if (question.length !in pollQuestionTextLength) {
        throw IllegalArgumentException(
            "The length of questions for polls must be in $pollQuestionTextLength range, but was ${question.length}",
        )
    }
    options.forEach {
        if (it.text.length !in pollOptionTextLength) {
            throw IllegalArgumentException(
                "The length of question option text for polls must be in $pollOptionTextLength range, but was ${it.text.length}",
            )
        }
    }
    if (options.size !in pollOptionsLimit) {
        throw IllegalArgumentException(
            "The amount of question options for polls must be in $pollOptionsLimit range, but was ${options.size}",
        )
    }
}

fun SendPoll(
    chatId: ChatIdentifier,
    question: String,
    options: List<InputPollOption>,
    questionParseMode: ParseMode? = null,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
) = SendRegularPoll(
    chatId,
    question,
    options,
    questionParseMode,
    isAnonymous,
    isClosed,
    threadId = threadId,
    businessConnectionId = businessConnectionId,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    disableNotification = disableNotification,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
)

fun SendPoll(
    chatId: ChatIdentifier,
    textSources: TextSourcesList,
    options: List<InputPollOption>,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
) = SendRegularPoll(
    chatId = chatId,
    questionEntities = textSources,
    options = options,
    isAnonymous = isAnonymous,
    isClosed = isClosed,
    threadId = threadId,
    businessConnectionId = businessConnectionId,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    disableNotification = disableNotification,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
)

/**
 * @return [SendPoll] in case when all is right. It can return [SendRegularPoll] for [QuizPoll] in case if
 * [QuizPoll.correctOptionId] equal to null
 */
fun Poll.createRequest(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
) = when (this) {
    is RegularPoll ->
        SendRegularPoll(
            chatId = chatId,
            questionTextSources = textSources,
            options = options.map { it.asInput() },
            closeInfo = scheduledCloseInfo,
            isAnonymous = isAnonymous,
            isClosed = isClosed,
            allowMultipleAnswers = allowMultipleAnswers,
            threadId = threadId,
            businessConnectionId = businessConnectionId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            replyParameters = replyParameters,
            replyMarkup = replyMarkup,
        )
    is QuizPoll ->
        correctOptionId ?.let { correctOptionId ->
            SendQuizPoll(
                chatId = chatId,
                questionEntities = textSources,
                options = options.map { it.asInput() },
                correctOptionId = correctOptionId,
                closeInfo = scheduledCloseInfo,
                explanationTextSources = explanationTextSources,
                isAnonymous = isAnonymous,
                isClosed = isClosed,
                threadId = threadId,
                businessConnectionId = businessConnectionId,
                disableNotification = disableNotification,
                protectContent = protectContent,
                allowPaidBroadcast = allowPaidBroadcast,
                effectId = effectId,
                replyParameters = replyParameters,
                replyMarkup = replyMarkup,
            )
        } ?: SendRegularPoll(
            chatId = chatId,
            questionTextSources = textSources,
            options = options.map { it.asInput() },
            closeInfo = scheduledCloseInfo,
            isAnonymous = isAnonymous,
            isClosed = isClosed,
            allowMultipleAnswers = false,
            threadId = threadId,
            businessConnectionId = businessConnectionId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            replyParameters = replyParameters,
            replyMarkup = replyMarkup,
        )
    is UnknownPollType ->
        SendRegularPoll(
            chatId = chatId,
            questionTextSources = textSources,
            options = options.map { it.asInput() },
            closeInfo = scheduledCloseInfo,
            isAnonymous = isAnonymous,
            isClosed = isClosed,
            allowMultipleAnswers = false,
            threadId = threadId,
            businessConnectionId = businessConnectionId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            replyParameters = replyParameters,
            replyMarkup = replyMarkup,
        )
}

internal fun ScheduledCloseInfo.checkSendData() {
    val span = when (this) {
        is ExactScheduledCloseInfo -> (closeDateTime - DateTime.now()).seconds
        is ApproximateScheduledCloseInfo -> openDuration.seconds
    }.toInt()
    if (span !in openPeriodPollSecondsLimit) {
        error("Duration of autoclose for polls must be in range $openPeriodPollSecondsLimit, but was $span")
    }
}

sealed class SendPoll :
    SendContentMessageRequest<ContentMessage<PollContent>>,
    ReplyingMarkupSendMessageRequest<ContentMessage<PollContent>>,
    TextedInput {
    abstract val question: String
    override val text: String
        get() = question
    abstract val questionParseMode: ParseMode?
    abstract val options: List<InputPollOption>
    abstract val isAnonymous: Boolean
    abstract val isClosed: Boolean
    abstract val type: String

    internal abstract val openPeriod: LongSeconds?
    internal abstract val closeDate: LongSeconds?

    protected val creationDate = DateTime.now()
    open val closeInfo: ScheduledCloseInfo?
        get() {
            val openPeriod = openPeriod
            val closeDate = closeDate
            return when {
                openPeriod != null -> openPeriod.asApproximateScheduledCloseInfo(creationDate)
                closeDate != null -> closeDate.asExactScheduledCloseInfo
                else -> null
            }
        }

    override fun method(): String = "sendPoll"

    override val resultDeserializer: DeserializationStrategy<ContentMessage<PollContent>>
        get() = commonResultDeserializer
}
