package dev.inmo.tgbotapi.requests.send.polls

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.SuggestedPostParameters
import dev.inmo.tgbotapi.types.message.asTextSources
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.types.polls.ApproximateScheduledCloseInfo
import dev.inmo.tgbotapi.types.polls.ExactScheduledCloseInfo
import dev.inmo.tgbotapi.types.polls.InputPollOption
import dev.inmo.tgbotapi.types.polls.ScheduledCloseInfo
import dev.inmo.tgbotapi.utils.extensions.makeSourceString
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy


@Serializable
class SendQuizPoll internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(questionField)
    override val question: String,
    @SerialName(optionsField)
    override val options: List<InputPollOption>,
    @SerialName(correctOptionIdField)
    val correctOptionId: Int,
    @SerialName(questionParseModeField)
    override val questionParseMode: ParseMode? = null,
    @SerialName(questionEntitiesField)
    private val rawQuestionEntities: List<RawMessageEntity> = emptyList(),
    @SerialName(isAnonymousField)
    override val isAnonymous: Boolean = true,
    @SerialName(isClosedField)
    override val isClosed: Boolean = false,
    @SerialName(explanationField)
    val explanation: String? = null,
    @SerialName(explanationParseModeField)
    val explanationParseMode: ParseMode? = null,
    @SerialName(explanationEntitiesField)
    private val rawExplanationEntities: List<RawMessageEntity>? = null,
    @SerialName(openPeriodField)
    override val openPeriod: LongSeconds? = null,
    @SerialName(closeDateField)
    override val closeDate: LongSeconds? = null,
    @OptIn(ExperimentalSerializationApi::class)
    @SerialName(messageThreadIdField)
    @EncodeDefault
    override val threadId: MessageThreadId? = chatId.threadId,
    @OptIn(ExperimentalSerializationApi::class)
    @EncodeDefault
    @SerialName(directMessagesTopicIdField)
    override val directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(protectContentField)
    override val protectContent: Boolean = false,
    @SerialName(allowPaidBroadcastField)
    override val allowPaidBroadcast: Boolean = false,
    @SerialName(messageEffectIdField)
    override val effectId: EffectId? = null,
    @SerialName(suggestedPostParametersField)
    override val suggestedPostParameters: SuggestedPostParameters?,
    @SerialName(replyParametersField)
    override val replyParameters: ReplyParameters? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : SendPoll() {
    override val type: String = quizPollType
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
    override val textSources: List<TextSource>
        get() = rawQuestionEntities.asTextSources(question)
    val explanationTextEntities: List<TextSource>? by lazy {
        rawExplanationEntities ?.asTextSources(text ?: return@lazy null)
    }

    constructor(
        chatId: ChatIdentifier,
        question: String,
        options: List<InputPollOption>,
        correctOptionId: Int,
        explanation: String?,
        questionParseMode: ParseMode? = null,
        explanationParseMode: ParseMode? = null,
        isAnonymous: Boolean = true,
        isClosed: Boolean = false,
        openPeriod: LongSeconds? = null,
        closeDate: LongSeconds? = null,
        threadId: MessageThreadId? = chatId.threadId,
        directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
        businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
        disableNotification: Boolean = false,
        protectContent: Boolean = false,
        allowPaidBroadcast: Boolean = false,
        effectId: EffectId? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: KeyboardMarkup? = null
    ) : this(
        chatId = chatId,
        question = question,
        options = options,
        correctOptionId = correctOptionId,
        questionParseMode = questionParseMode,
        rawQuestionEntities = emptyList(),
        isAnonymous = isAnonymous,
        isClosed = isClosed,
        explanation = explanation,
        explanationParseMode = explanationParseMode,
        rawExplanationEntities = emptyList(),
        openPeriod = openPeriod,
        closeDate = closeDate,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    constructor(
        chatId: ChatIdentifier,
        questionEntities: List<TextSource>,
        options: List<InputPollOption>,
        correctOptionId: Int,
        explanation: String?,
        explanationParseMode: ParseMode? = null,
        isAnonymous: Boolean = true,
        isClosed: Boolean = false,
        openPeriod: LongSeconds? = null,
        closeDate: LongSeconds? = null,
        threadId: MessageThreadId? = chatId.threadId,
        directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
        businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
        disableNotification: Boolean = false,
        protectContent: Boolean = false,
        allowPaidBroadcast: Boolean = false,
        effectId: EffectId? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: KeyboardMarkup? = null
    ) : this(
        chatId = chatId,
        question = questionEntities.makeSourceString(),
        options = options,
        correctOptionId = correctOptionId,
        questionParseMode = null,
        rawQuestionEntities = questionEntities.toRawMessageEntities(),
        isAnonymous = isAnonymous,
        isClosed = isClosed,
        explanation = explanation,
        explanationParseMode = explanationParseMode,
        rawExplanationEntities = emptyList(),
        openPeriod = openPeriod,
        closeDate = closeDate,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    constructor(
        chatId: ChatIdentifier,
        question: String,
        options: List<InputPollOption>,
        correctOptionId: Int,
        questionParseMode: ParseMode? = null,
        explanationTextSources: List<TextSource>? = null,
        isAnonymous: Boolean = true,
        isClosed: Boolean = false,
        openPeriod: LongSeconds? = null,
        closeDate: LongSeconds? = null,
        threadId: MessageThreadId? = chatId.threadId,
        directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
        businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
        disableNotification: Boolean = false,
        protectContent: Boolean = false,
        allowPaidBroadcast: Boolean = false,
        effectId: EffectId? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: KeyboardMarkup? = null
    ) : this(
        chatId = chatId,
        question = question,
        options = options,
        correctOptionId = correctOptionId,
        questionParseMode = questionParseMode,
        rawQuestionEntities = emptyList(),
        isAnonymous = isAnonymous,
        isClosed = isClosed,
        explanation = explanationTextSources ?.makeSourceString(),
        explanationParseMode = null,
        rawExplanationEntities = explanationTextSources ?.toRawMessageEntities(),
        openPeriod = openPeriod,
        closeDate = closeDate,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    constructor(
        chatId: ChatIdentifier,
        questionEntities: List<TextSource>,
        options: List<InputPollOption>,
        correctOptionId: Int,
        explanationTextSources: List<TextSource>? = null,
        isAnonymous: Boolean = true,
        isClosed: Boolean = false,
        openPeriod: LongSeconds? = null,
        closeDate: LongSeconds? = null,
        threadId: MessageThreadId? = chatId.threadId,
        directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
        businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
        disableNotification: Boolean = false,
        protectContent: Boolean = false,
        allowPaidBroadcast: Boolean = false,
        effectId: EffectId? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: KeyboardMarkup? = null
    ) : this(
        chatId = chatId,
        question = questionEntities.makeSourceString(),
        options = options,
        correctOptionId = correctOptionId,
        questionParseMode = null,
        rawQuestionEntities = questionEntities.toRawMessageEntities(),
        isAnonymous = isAnonymous,
        isClosed = isClosed,
        explanation = explanationTextSources ?.makeSourceString(),
        explanationParseMode = null,
        rawExplanationEntities = explanationTextSources ?.toRawMessageEntities(),
        openPeriod = openPeriod,
        closeDate = closeDate,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    init {
        checkPollInfo(question, options)
        closeInfo ?.checkSendData()
        val correctOptionIdRange = 0 .. options.size
        if (correctOptionId !in correctOptionIdRange) {
            throw IllegalArgumentException("Correct option id must be in range of $correctOptionIdRange, but actual " +
                    "value is $correctOptionId")
        }
        if (explanation != null && explanation.length !in explanationLimit) {
            error("Quiz poll explanation size must be in range $explanationLimit," +
                    "but actual explanation contains ${text.length} symbols")
        }
    }
}

fun SendQuizPoll(
    chatId: ChatIdentifier,
    question: String,
    options: List<InputPollOption>,
    correctOptionId: Int,
    closeInfo: ScheduledCloseInfo?,
    explanation: String?,
    questionParseMode: ParseMode? = null,
    explanationParseMode: ParseMode? = null,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = SendQuizPoll(
    chatId = chatId,
    question = question,
    options = options,
    correctOptionId = correctOptionId,
    explanation = explanation,
    questionParseMode = questionParseMode,
    explanationParseMode = explanationParseMode,
    isAnonymous = isAnonymous,
    isClosed = isClosed,
    openPeriod = (closeInfo as? ApproximateScheduledCloseInfo)?.openPeriod,
    closeDate = (closeInfo as? ExactScheduledCloseInfo)?.closeDate,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

fun SendQuizPoll(
    chatId: ChatIdentifier,
    questionEntities: List<TextSource>,
    options: List<InputPollOption>,
    correctOptionId: Int,
    closeInfo: ScheduledCloseInfo?,
    explanation: String?,
    explanationParseMode: ParseMode? = null,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = SendQuizPoll(
    chatId = chatId,
    questionEntities = questionEntities,
    options = options,
    correctOptionId = correctOptionId,
    explanation = explanation,
    explanationParseMode = explanationParseMode,
    isAnonymous = isAnonymous,
    isClosed = isClosed,
    openPeriod = (closeInfo as? ApproximateScheduledCloseInfo)?.openPeriod,
    closeDate = (closeInfo as? ExactScheduledCloseInfo)?.closeDate,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

fun SendQuizPoll(
    chatId: ChatIdentifier,
    question: String,
    options: List<InputPollOption>,
    correctOptionId: Int,
    closeInfo: ScheduledCloseInfo?,
    questionParseMode: ParseMode? = null,
    explanationTextSources: List<TextSource>? = null,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = SendQuizPoll(
    chatId = chatId,
    question = question,
    options = options,
    correctOptionId = correctOptionId,
    questionParseMode = questionParseMode,
    explanationTextSources = explanationTextSources,
    isAnonymous = isAnonymous,
    isClosed = isClosed,
    openPeriod = (closeInfo as? ApproximateScheduledCloseInfo)?.openPeriod,
    closeDate = (closeInfo as? ExactScheduledCloseInfo)?.closeDate,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

fun SendQuizPoll(
    chatId: ChatIdentifier,
    questionEntities: List<TextSource>,
    options: List<InputPollOption>,
    correctOptionId: Int,
    closeInfo: ScheduledCloseInfo?,
    explanationTextSources: List<TextSource>? = null,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = SendQuizPoll(
    chatId = chatId,
    questionEntities = questionEntities,
    options = options,
    correctOptionId = correctOptionId,
    explanationTextSources = explanationTextSources,
    isAnonymous = isAnonymous,
    isClosed = isClosed,
    openPeriod = (closeInfo as? ApproximateScheduledCloseInfo)?.openPeriod,
    closeDate = (closeInfo as? ExactScheduledCloseInfo)?.closeDate,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)
