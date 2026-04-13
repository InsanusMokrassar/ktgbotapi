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
    @SerialName(correctOptionIdsField)
    val correctOptionIds: List<Int>,
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
    @SerialName(allowsRevotingField)
    val allowsRevoting: Boolean = false,
    @SerialName(shuffleOptionsField)
    val shuffleOptions: Boolean = false,
    @SerialName(hideResultsUntilClosesField)
    val hideResultsUntilCloses: Boolean = false,
    @SerialName(explanationField)
    val explanation: String? = null,
    @SerialName(explanationParseModeField)
    val explanationParseMode: ParseMode? = null,
    @SerialName(explanationEntitiesField)
    private val rawExplanationEntities: List<RawMessageEntity>? = null,
    @SerialName(descriptionField)
    val description: String? = null,
    @SerialName(descriptionParseModeField)
    val descriptionParseMode: ParseMode? = null,
    @SerialName(descriptionEntitiesField)
    private val rawDescriptionEntities: List<RawMessageEntity>? = null,
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
    override val suggestedPostParameters: SuggestedPostParameters? = null,
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
        rawExplanationEntities?.asTextSources(text ?: return@lazy null)
    }
    val descriptionTextSources: List<TextSource>? by lazy {
        rawDescriptionEntities?.asTextSources(description ?: return@lazy null)
    }

    constructor(
        chatId: ChatIdentifier,
        question: String,
        options: List<InputPollOption>,
        correctOptionIds: List<Int>,
        explanation: String?,
        questionParseMode: ParseMode? = null,
        explanationParseMode: ParseMode? = null,
        isAnonymous: Boolean = true,
        isClosed: Boolean = false,
        allowMultipleAnswers: Boolean = false,
        allowsRevoting: Boolean = false,
        shuffleOptions: Boolean = false,
            hideResultsUntilCloses: Boolean = false,
        description: String? = null,
        descriptionParseMode: ParseMode? = null,
        openPeriod: LongSeconds? = null,
        closeDate: LongSeconds? = null,
        threadId: MessageThreadId? = chatId.threadId,
        directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
        businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
        disableNotification: Boolean = false,
        protectContent: Boolean = false,
        allowPaidBroadcast: Boolean = false,
        effectId: EffectId? = null,
        suggestedPostParameters: SuggestedPostParameters? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: KeyboardMarkup? = null
    ) : this(
        chatId = chatId,
        question = question,
        options = options,
        correctOptionIds = correctOptionIds,
        questionParseMode = questionParseMode,
        rawQuestionEntities = emptyList(),
        isAnonymous = isAnonymous,
        isClosed = isClosed,
        allowMultipleAnswers = allowMultipleAnswers,
        allowsRevoting = allowsRevoting,
        shuffleOptions = shuffleOptions,
        hideResultsUntilCloses = hideResultsUntilCloses,
        explanation = explanation,
        explanationParseMode = explanationParseMode,
        rawExplanationEntities = emptyList(),
        description = description,
        descriptionParseMode = descriptionParseMode,
        rawDescriptionEntities = null,
        openPeriod = openPeriod,
        closeDate = closeDate,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        suggestedPostParameters = suggestedPostParameters,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    constructor(
        chatId: ChatIdentifier,
        questionEntities: List<TextSource>,
        options: List<InputPollOption>,
        correctOptionIds: List<Int>,
        explanation: String?,
        explanationParseMode: ParseMode? = null,
        isAnonymous: Boolean = true,
        isClosed: Boolean = false,
        allowMultipleAnswers: Boolean = false,
        allowsRevoting: Boolean = false,
        shuffleOptions: Boolean = false,
            hideResultsUntilCloses: Boolean = false,
        description: String? = null,
        descriptionParseMode: ParseMode? = null,
        openPeriod: LongSeconds? = null,
        closeDate: LongSeconds? = null,
        threadId: MessageThreadId? = chatId.threadId,
        directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
        businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
        disableNotification: Boolean = false,
        protectContent: Boolean = false,
        allowPaidBroadcast: Boolean = false,
        effectId: EffectId? = null,
        suggestedPostParameters: SuggestedPostParameters? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: KeyboardMarkup? = null
    ) : this(
        chatId = chatId,
        question = questionEntities.makeSourceString(),
        options = options,
        correctOptionIds = correctOptionIds,
        questionParseMode = null,
        rawQuestionEntities = questionEntities.toRawMessageEntities(),
        isAnonymous = isAnonymous,
        isClosed = isClosed,
        allowMultipleAnswers = allowMultipleAnswers,
        allowsRevoting = allowsRevoting,
        shuffleOptions = shuffleOptions,
        hideResultsUntilCloses = hideResultsUntilCloses,
        explanation = explanation,
        explanationParseMode = explanationParseMode,
        rawExplanationEntities = emptyList(),
        description = description,
        descriptionParseMode = descriptionParseMode,
        rawDescriptionEntities = null,
        openPeriod = openPeriod,
        closeDate = closeDate,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        suggestedPostParameters = suggestedPostParameters,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    constructor(
        chatId: ChatIdentifier,
        question: String,
        options: List<InputPollOption>,
        correctOptionIds: List<Int>,
        questionParseMode: ParseMode? = null,
        explanationTextSources: List<TextSource>? = null,
        isAnonymous: Boolean = true,
        isClosed: Boolean = false,
        allowMultipleAnswers: Boolean = false,
        allowsRevoting: Boolean = false,
        shuffleOptions: Boolean = false,
            hideResultsUntilCloses: Boolean = false,
        descriptionTextSources: List<TextSource>? = null,
        openPeriod: LongSeconds? = null,
        closeDate: LongSeconds? = null,
        threadId: MessageThreadId? = chatId.threadId,
        directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
        businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
        disableNotification: Boolean = false,
        protectContent: Boolean = false,
        allowPaidBroadcast: Boolean = false,
        effectId: EffectId? = null,
        suggestedPostParameters: SuggestedPostParameters? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: KeyboardMarkup? = null
    ) : this(
        chatId = chatId,
        question = question,
        options = options,
        correctOptionIds = correctOptionIds,
        questionParseMode = questionParseMode,
        rawQuestionEntities = emptyList(),
        isAnonymous = isAnonymous,
        isClosed = isClosed,
        allowMultipleAnswers = allowMultipleAnswers,
        allowsRevoting = allowsRevoting,
        shuffleOptions = shuffleOptions,
        hideResultsUntilCloses = hideResultsUntilCloses,
        explanation = explanationTextSources?.makeSourceString(),
        explanationParseMode = null,
        rawExplanationEntities = explanationTextSources?.toRawMessageEntities(),
        description = descriptionTextSources?.makeSourceString(),
        descriptionParseMode = null,
        rawDescriptionEntities = descriptionTextSources?.toRawMessageEntities(),
        openPeriod = openPeriod,
        closeDate = closeDate,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        suggestedPostParameters = suggestedPostParameters,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    constructor(
        chatId: ChatIdentifier,
        questionEntities: List<TextSource>,
        options: List<InputPollOption>,
        correctOptionIds: List<Int>,
        explanationTextSources: List<TextSource>? = null,
        isAnonymous: Boolean = true,
        isClosed: Boolean = false,
        allowMultipleAnswers: Boolean = false,
        allowsRevoting: Boolean = false,
        shuffleOptions: Boolean = false,
            hideResultsUntilCloses: Boolean = false,
        descriptionTextSources: List<TextSource>? = null,
        openPeriod: LongSeconds? = null,
        closeDate: LongSeconds? = null,
        threadId: MessageThreadId? = chatId.threadId,
        directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
        businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
        disableNotification: Boolean = false,
        protectContent: Boolean = false,
        allowPaidBroadcast: Boolean = false,
        effectId: EffectId? = null,
        suggestedPostParameters: SuggestedPostParameters? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: KeyboardMarkup? = null
    ) : this(
        chatId = chatId,
        question = questionEntities.makeSourceString(),
        options = options,
        correctOptionIds = correctOptionIds,
        questionParseMode = null,
        rawQuestionEntities = questionEntities.toRawMessageEntities(),
        isAnonymous = isAnonymous,
        isClosed = isClosed,
        allowMultipleAnswers = allowMultipleAnswers,
        allowsRevoting = allowsRevoting,
        shuffleOptions = shuffleOptions,
        hideResultsUntilCloses = hideResultsUntilCloses,
        explanation = explanationTextSources?.makeSourceString(),
        explanationParseMode = null,
        rawExplanationEntities = explanationTextSources?.toRawMessageEntities(),
        description = descriptionTextSources?.makeSourceString(),
        descriptionParseMode = null,
        rawDescriptionEntities = descriptionTextSources?.toRawMessageEntities(),
        openPeriod = openPeriod,
        closeDate = closeDate,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        suggestedPostParameters = suggestedPostParameters,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    init {
        checkPollInfo(question, options)
        closeInfo?.checkSendData()
        val correctOptionIdsRange = 0 until options.size
        correctOptionIds?.forEach { id ->
            if (id !in correctOptionIdsRange) {
                throw IllegalArgumentException("Correct option id must be in range of $correctOptionIdsRange, but actual " +
                        "value is $id")
            }
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
    correctOptionIds: List<Int>,
    closeInfo: ScheduledCloseInfo?,
    explanation: String?,
    questionParseMode: ParseMode? = null,
    explanationParseMode: ParseMode? = null,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    allowMultipleAnswers: Boolean = false,
    allowsRevoting: Boolean = false,
    shuffleOptions: Boolean = false,
    hideResultsUntilCloses: Boolean = false,
    description: String? = null,
    descriptionParseMode: ParseMode? = null,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = SendQuizPoll(
    chatId = chatId,
    question = question,
    options = options,
    correctOptionIds = correctOptionIds,
    explanation = explanation,
    questionParseMode = questionParseMode,
    explanationParseMode = explanationParseMode,
    isAnonymous = isAnonymous,
    isClosed = isClosed,
    allowMultipleAnswers = allowMultipleAnswers,
    allowsRevoting = allowsRevoting,
    shuffleOptions = shuffleOptions,
    hideResultsUntilCloses = hideResultsUntilCloses,
    description = description,
    descriptionParseMode = descriptionParseMode,
    openPeriod = (closeInfo as? ApproximateScheduledCloseInfo)?.openPeriod,
    closeDate = (closeInfo as? ExactScheduledCloseInfo)?.closeDate,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

fun SendQuizPoll(
    chatId: ChatIdentifier,
    questionEntities: List<TextSource>,
    options: List<InputPollOption>,
    correctOptionIds: List<Int>,
    closeInfo: ScheduledCloseInfo?,
    explanation: String?,
    explanationParseMode: ParseMode? = null,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    allowMultipleAnswers: Boolean = false,
    allowsRevoting: Boolean = false,
    shuffleOptions: Boolean = false,
    hideResultsUntilCloses: Boolean = false,
    description: String? = null,
    descriptionParseMode: ParseMode? = null,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = SendQuizPoll(
    chatId = chatId,
    questionEntities = questionEntities,
    options = options,
    correctOptionIds = correctOptionIds,
    explanation = explanation,
    explanationParseMode = explanationParseMode,
    isAnonymous = isAnonymous,
    isClosed = isClosed,
    allowMultipleAnswers = allowMultipleAnswers,
    allowsRevoting = allowsRevoting,
    shuffleOptions = shuffleOptions,
    hideResultsUntilCloses = hideResultsUntilCloses,
    description = description,
    descriptionParseMode = descriptionParseMode,
    openPeriod = (closeInfo as? ApproximateScheduledCloseInfo)?.openPeriod,
    closeDate = (closeInfo as? ExactScheduledCloseInfo)?.closeDate,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

fun SendQuizPoll(
    chatId: ChatIdentifier,
    question: String,
    options: List<InputPollOption>,
    correctOptionIds: List<Int>,
    closeInfo: ScheduledCloseInfo?,
    questionParseMode: ParseMode? = null,
    explanationTextSources: List<TextSource>? = null,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    allowMultipleAnswers: Boolean = false,
    allowsRevoting: Boolean = false,
    shuffleOptions: Boolean = false,
    hideResultsUntilCloses: Boolean = false,
    descriptionTextSources: List<TextSource>? = null,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = SendQuizPoll(
    chatId = chatId,
    question = question,
    options = options,
    correctOptionIds = correctOptionIds,
    questionParseMode = questionParseMode,
    explanationTextSources = explanationTextSources,
    isAnonymous = isAnonymous,
    isClosed = isClosed,
    allowMultipleAnswers = allowMultipleAnswers,
    allowsRevoting = allowsRevoting,
    shuffleOptions = shuffleOptions,
    hideResultsUntilCloses = hideResultsUntilCloses,
    descriptionTextSources = descriptionTextSources,
    openPeriod = (closeInfo as? ApproximateScheduledCloseInfo)?.openPeriod,
    closeDate = (closeInfo as? ExactScheduledCloseInfo)?.closeDate,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

fun SendQuizPoll(
    chatId: ChatIdentifier,
    questionEntities: List<TextSource>,
    options: List<InputPollOption>,
    correctOptionIds: List<Int>,
    closeInfo: ScheduledCloseInfo?,
    explanationTextSources: List<TextSource>? = null,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    allowMultipleAnswers: Boolean = false,
    allowsRevoting: Boolean = false,
    shuffleOptions: Boolean = false,
    hideResultsUntilCloses: Boolean = false,
    descriptionTextSources: List<TextSource>? = null,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = SendQuizPoll(
    chatId = chatId,
    questionEntities = questionEntities,
    options = options,
    correctOptionIds = correctOptionIds,
    explanationTextSources = explanationTextSources,
    isAnonymous = isAnonymous,
    isClosed = isClosed,
    allowMultipleAnswers = allowMultipleAnswers,
    allowsRevoting = allowsRevoting,
    shuffleOptions = shuffleOptions,
    hideResultsUntilCloses = hideResultsUntilCloses,
    descriptionTextSources = descriptionTextSources,
    openPeriod = (closeInfo as? ApproximateScheduledCloseInfo)?.openPeriod,
    closeDate = (closeInfo as? ExactScheduledCloseInfo)?.closeDate,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)
