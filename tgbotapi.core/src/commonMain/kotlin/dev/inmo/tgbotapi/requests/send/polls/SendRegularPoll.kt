package dev.inmo.tgbotapi.requests.send.polls

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.asTextSources
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.types.polls.ApproximateScheduledCloseInfo
import dev.inmo.tgbotapi.types.polls.ExactScheduledCloseInfo
import dev.inmo.tgbotapi.types.polls.InputPollOption
import dev.inmo.tgbotapi.types.polls.ScheduledCloseInfo
import dev.inmo.tgbotapi.utils.EntitiesBuilder
import dev.inmo.tgbotapi.utils.EntitiesBuilderBody
import dev.inmo.tgbotapi.utils.extensions.makeSourceString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy

@Serializable
class SendRegularPoll private constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(questionField)
    override val question: String,
    @SerialName(optionsField)
    override val options: List<InputPollOption>,
    @SerialName(questionParseModeField)
    override val questionParseMode: ParseMode? = null,
    @SerialName(questionEntitiesField)
    private val rawQuestionEntities: List<RawMessageEntity> = emptyList(),
    @SerialName(isAnonymousField)
    override val isAnonymous: Boolean = true,
    @SerialName(isClosedField)
    override val isClosed: Boolean = false,
    @SerialName(allowsMultipleAnswersField)
    val allowMultipleAnswers: Boolean = false,
    @SerialName(openPeriodField)
    override val openPeriod: LongSeconds?= null,
    @SerialName(closeDateField)
    override val closeDate: LongSeconds? = null,
    @SerialName(messageThreadIdField)
    override val threadId: MessageThreadId? = chatId.threadId,
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(protectContentField)
    override val protectContent: Boolean = false,
    @SerialName(replyParametersField)
    override val replyParameters: ReplyParameters? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : SendPoll() {
    override val textSources: List<TextSource>
        get() = rawQuestionEntities.asTextSources(text)

    constructor(
        chatId: ChatIdentifier,
        questionEntities: List<TextSource>,
        options: List<InputPollOption>,
        isAnonymous: Boolean = true,
        isClosed: Boolean = false,
        allowMultipleAnswers: Boolean = false,
        openPeriod: LongSeconds?= null,
        closeDate: LongSeconds? = null,
        threadId: MessageThreadId? = chatId.threadId,
        businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
        disableNotification: Boolean = false,
        protectContent: Boolean = false,
        replyParameters: ReplyParameters? = null,
        replyMarkup: KeyboardMarkup? = null
    ) : this(
        chatId = chatId,
        question = questionEntities.makeSourceString(),
        options = options,
        questionParseMode = null,
        rawQuestionEntities = questionEntities.toRawMessageEntities(),
        isAnonymous = isAnonymous,
        isClosed = isClosed,
        allowMultipleAnswers = allowMultipleAnswers,
        openPeriod = openPeriod,
        closeDate = closeDate,
        threadId = threadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    constructor(
        chatId: ChatIdentifier,
        question: String,
        options: List<InputPollOption>,
        questionParseMode: ParseMode? = null,
        isAnonymous: Boolean = true,
        isClosed: Boolean = false,
        allowMultipleAnswers: Boolean = false,
        openPeriod: LongSeconds?= null,
        closeDate: LongSeconds? = null,
        threadId: MessageThreadId? = chatId.threadId,
        businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
        disableNotification: Boolean = false,
        protectContent: Boolean = false,
        replyParameters: ReplyParameters? = null,
        replyMarkup: KeyboardMarkup? = null
    ) : this(
        chatId = chatId,
        question = question,
        options = options,
        questionParseMode = questionParseMode,
        rawQuestionEntities = emptyList(),
        isAnonymous = isAnonymous,
        isClosed = isClosed,
        allowMultipleAnswers = allowMultipleAnswers,
        openPeriod = openPeriod,
        closeDate = closeDate,
        threadId = threadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

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
    options: List<InputPollOption>,
    closeInfo: ScheduledCloseInfo?,
    questionParseMode: ParseMode? = null,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    allowMultipleAnswers: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = SendRegularPoll(
    chatId,
    question,
    options,
    questionParseMode,
    isAnonymous,
    isClosed,
    allowMultipleAnswers,
    (closeInfo as? ApproximateScheduledCloseInfo) ?.openPeriod,
    (closeInfo as? ExactScheduledCloseInfo) ?.closeDate,
    threadId,
    businessConnectionId,
    disableNotification,
    protectContent,
    replyParameters,
    replyMarkup
)

fun SendRegularPoll(
    chatId: ChatIdentifier,
    questionTextSources: List<TextSource>,
    options: List<InputPollOption>,
    closeInfo: ScheduledCloseInfo?,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    allowMultipleAnswers: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = SendRegularPoll(
    chatId,
    questionTextSources,
    options,
    isAnonymous,
    isClosed,
    allowMultipleAnswers,
    (closeInfo as? ApproximateScheduledCloseInfo) ?.openPeriod,
    (closeInfo as? ExactScheduledCloseInfo) ?.closeDate,
    threadId,
    businessConnectionId,
    disableNotification,
    protectContent,
    replyParameters,
    replyMarkup
)

fun SendRegularPoll(
    chatId: ChatIdentifier,
    options: List<InputPollOption>,
    closeInfo: ScheduledCloseInfo?,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    allowMultipleAnswers: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    builder: EntitiesBuilderBody
) = SendRegularPoll(
    chatId,
    EntitiesBuilder().apply(builder).build(),
    options,
    closeInfo,
    isAnonymous,
    isClosed,
    allowMultipleAnswers,
    threadId,
    businessConnectionId,
    disableNotification,
    protectContent,
    replyParameters,
    replyMarkup
)