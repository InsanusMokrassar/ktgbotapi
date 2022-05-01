package dev.inmo.tgbotapi.requests.send.polls

import com.soywiz.klock.DateTime
import dev.inmo.tgbotapi.CommonAbstracts.TextedOutput
import dev.inmo.tgbotapi.requests.send.abstracts.ReplyingMarkupSendMessageRequest
import dev.inmo.tgbotapi.requests.send.abstracts.SendMessageRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.PollContent
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.types.polls.*
import dev.inmo.tgbotapi.utils.extensions.makeString
import kotlinx.serialization.*

private val commonResultDeserializer: DeserializationStrategy<ContentMessage<PollContent>> = TelegramBotAPIMessageDeserializationStrategyClass()

private inline val ApproximateScheduledCloseInfo.openPeriod
    get() = openDuration.millisecondsLong.div(1000)
private inline val ExactScheduledCloseInfo.closeDate
    get() = closeDateTime.unixMillisLong.div(1000)

private fun checkPollInfo(
    question: String,
    options: List<String>
) {
    if (question.length !in pollQuestionTextLength) {
        throw IllegalArgumentException("The length of questions for polls must be in $pollQuestionTextLength range, but was ${question.length}")
    }
    options.forEach {
        if (it.length !in pollOptionTextLength) {
            throw IllegalArgumentException("The length of question option text for polls must be in $pollOptionTextLength range, but was ${it.length}")
        }
    }
    if (options.size !in pollOptionsLimit) {
        throw IllegalArgumentException("The amount of question options for polls must be in $pollOptionsLimit range, but was ${options.size}")
    }
}

fun SendPoll(
    chatId: ChatIdentifier,
    question: String,
    options: List<String>,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = SendRegularPoll(
    chatId,
    question,
    options,
    isAnonymous,
    isClosed,
    allowSendingWithoutReply = allowSendingWithoutReply,
    disableNotification = disableNotification,
    replyToMessageId = replyToMessageId,
    replyMarkup = replyMarkup
)

/**
 * @return [SendPoll] in case when all is right. It can return [SendRegularPoll] for [QuizPoll] in case if
 * [QuizPoll.correctOptionId] equal to null
 */
fun Poll.createRequest(
    chatId: ChatIdentifier,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = when (this) {
    is RegularPoll -> SendRegularPoll(
        chatId,
        question,
        options.map { it.text },
        isAnonymous,
        isClosed,
        allowMultipleAnswers,
        scheduledCloseInfo,
        disableNotification,
        protectContent,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup
    )
    is QuizPoll -> correctOptionId ?.let { correctOptionId ->
        SendQuizPoll(
            chatId,
            question,
            options.map { it.text },
            correctOptionId,
            isAnonymous,
            isClosed,
            textSources,
            scheduledCloseInfo,
            disableNotification,
            protectContent,
            replyToMessageId,
            allowSendingWithoutReply,
            replyMarkup
        )
    } ?: SendRegularPoll(
        chatId,
        question,
        options.map { it.text },
        isAnonymous,
        isClosed,
        false,
        scheduledCloseInfo,
        disableNotification,
        protectContent,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup
    )
    is UnknownPollType -> SendRegularPoll(
        chatId,
        question,
        options.map { it.text },
        isAnonymous,
        isClosed,
        false,
        scheduledCloseInfo,
        disableNotification,
        protectContent,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup
    )
}

private fun ScheduledCloseInfo.checkSendData() {
    val span = when (this) {
        is ExactScheduledCloseInfo -> (closeDateTime - DateTime.now()).seconds
        is ApproximateScheduledCloseInfo -> openDuration.seconds
    }.toInt()
    if (span !in openPeriodPollSecondsLimit) {
        error("Duration of autoclose for polls must be in range $openPeriodPollSecondsLimit, but was $span")
    }
}

sealed class SendPoll : SendMessageRequest<ContentMessage<PollContent>>,
    ReplyingMarkupSendMessageRequest<ContentMessage<PollContent>> {
    abstract val question: String
    abstract val options: List<String>
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

@Serializable
data class SendRegularPoll(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(questionField)
    override val question: String,
    @SerialName(optionsField)
    override val options: List<String>,
    @SerialName(isAnonymousField)
    override val isAnonymous: Boolean = true,
    @SerialName(isClosedField)
    override val isClosed: Boolean = false,
    @SerialName(allowsMultipleAnswersField)
    val allowMultipleAnswers: Boolean = false,
    @SerialName(openPeriodField)
    override val openPeriod: LongSeconds?= null,
    @SerialName(closeDateField)
    override val closeDate: LongSeconds?,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(protectContentField)
    override val protectContent: Boolean = false,
    @SerialName(replyToMessageIdField)
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(allowSendingWithoutReplyField)
    override val allowSendingWithoutReply: Boolean? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : SendPoll() {
    override val type: String = regularPollType
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    init {
        checkPollInfo(question, options)
        closeInfo ?.checkSendData()
    }
}

fun SendRegularPoll(
    chatId: ChatIdentifier,
    question: String,
    options: List<String>,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    allowMultipleAnswers: Boolean = false,
    closeInfo: ScheduledCloseInfo? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = SendRegularPoll(
    chatId,
    question,
    options,
    isAnonymous,
    isClosed,
    allowMultipleAnswers,
    (closeInfo as? ApproximateScheduledCloseInfo) ?.openPeriod,
    (closeInfo as? ExactScheduledCloseInfo) ?.closeDate,
    disableNotification,
    protectContent,
    replyToMessageId,
    allowSendingWithoutReply,
    replyMarkup
)

fun SendQuizPoll(
    chatId: ChatIdentifier,
    question: String,
    options: List<String>,
    correctOptionId: Int,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    explanation: String? = null,
    parseMode: ParseMode? = null,
    closeInfo: ScheduledCloseInfo? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = SendQuizPoll(
    chatId,
    question,
    options,
    correctOptionId,
    isAnonymous,
    isClosed,
    explanation,
    parseMode,
    null,
    closeInfo,
    disableNotification,
    protectContent,
    replyToMessageId,
    allowSendingWithoutReply,
    replyMarkup
)

fun SendQuizPoll(
    chatId: ChatIdentifier,
    question: String,
    options: List<String>,
    correctOptionId: Int,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    entities: List<TextSource>,
    closeInfo: ScheduledCloseInfo? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = SendQuizPoll(
    chatId,
    question,
    options,
    correctOptionId,
    isAnonymous,
    isClosed,
    entities.makeString(),
    null,
    entities.toRawMessageEntities(),
    closeInfo,
    disableNotification,
    protectContent,
    replyToMessageId,
    allowSendingWithoutReply,
    replyMarkup
)

internal fun SendQuizPoll(
    chatId: ChatIdentifier,
    question: String,
    options: List<String>,
    correctOptionId: Int,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    explanation: String? = null,
    parseMode: ParseMode? = null,
    rawEntities: List<RawMessageEntity>? = null,
    closeInfo: ScheduledCloseInfo? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = SendQuizPoll(
    chatId,
    question,
    options,
    correctOptionId,
    isAnonymous,
    isClosed,
    explanation,
    parseMode,
    rawEntities,
    (closeInfo as? ApproximateScheduledCloseInfo) ?.openPeriod,
    (closeInfo as? ExactScheduledCloseInfo) ?.closeDate,
    disableNotification,
    protectContent,
    replyToMessageId,
    allowSendingWithoutReply,
    replyMarkup
)

@Serializable
data class SendQuizPoll internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(questionField)
    override val question: String,
    @SerialName(optionsField)
    override val options: List<String>,
    @SerialName(correctOptionIdField)
    val correctOptionId: Int,
    @SerialName(isAnonymousField)
    override val isAnonymous: Boolean = true,
    @SerialName(isClosedField)
    override val isClosed: Boolean = false,
    @SerialName(explanationField)
    override val text: String? = null,
    @SerialName(explanationParseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(explanationEntitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    @SerialName(openPeriodField)
    override val openPeriod: LongSeconds? = null,
    @SerialName(closeDateField)
    override val closeDate: LongSeconds? = null,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(protectContentField)
    override val protectContent: Boolean = false,
    @SerialName(replyToMessageIdField)
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(allowSendingWithoutReplyField)
    override val allowSendingWithoutReply: Boolean? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : SendPoll(), TextedOutput {
    override val type: String = quizPollType
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
    override val textSources: List<TextSource>? by lazy {
        rawEntities ?.asTextSources(text ?: return@lazy null)
    }

    init {
        checkPollInfo(question, options)
        closeInfo ?.checkSendData()
        val correctOptionIdRange = 0 .. options.size
        if (correctOptionId !in correctOptionIdRange) {
            throw IllegalArgumentException("Correct option id must be in range of $correctOptionIdRange, but actual " +
                "value is $correctOptionId")
        }
        if (text != null && text.length !in explanationLimit) {
            error("Quiz poll explanation size must be in range $explanationLimit," +
                "but actual explanation contains ${text.length} symbols")
        }
    }
}
