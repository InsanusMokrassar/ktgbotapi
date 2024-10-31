package dev.inmo.tgbotapi.extensions.api.send.polls

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.polls.SendQuizPoll
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.PollContent
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.polls.InputPollOption
import dev.inmo.tgbotapi.types.polls.ScheduledCloseInfo


public suspend fun TelegramBot.sendQuizPoll(
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
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PollContent> = execute(
    SendQuizPoll(
        chatId = chatId,
        question = question,
        options = options,
        correctOptionId = correctOptionId,
        explanation = explanation,
        questionParseMode = questionParseMode,
        explanationParseMode = explanationParseMode,
        isAnonymous = isAnonymous,
        isClosed = isClosed,
        openPeriod = openPeriod,
        closeDate = closeDate,
        threadId = threadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )
)

public suspend fun TelegramBot.sendQuizPoll(
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
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PollContent> = execute(
    SendQuizPoll(
        chatId = chatId,
        questionEntities = questionEntities,
        options = options,
        correctOptionId = correctOptionId,
        explanation = explanation,
        explanationParseMode = explanationParseMode,
        isAnonymous = isAnonymous,
        isClosed = isClosed,
        openPeriod = openPeriod,
        closeDate = closeDate,
        threadId = threadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )
)

public suspend fun TelegramBot.sendQuizPoll(
    chatId: ChatIdentifier,
    question: String,
    options: List<InputPollOption>,
    correctOptionId: Int,
    explanationTextSources: List<TextSource>? = null,
    questionParseMode: ParseMode? = null,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    openPeriod: LongSeconds? = null,
    closeDate: LongSeconds? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PollContent> = execute(
    SendQuizPoll(
        chatId = chatId,
        question = question,
        options = options,
        correctOptionId = correctOptionId,
        questionParseMode = questionParseMode,
        explanationTextSources = explanationTextSources,
        isAnonymous = isAnonymous,
        isClosed = isClosed,
        openPeriod = openPeriod,
        closeDate = closeDate,
        threadId = threadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )
)


public suspend fun TelegramBot.sendQuizPoll(
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
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PollContent> = execute(
    SendQuizPoll(
        chatId = chatId,
        questionEntities = questionEntities,
        options = options,
        correctOptionId = correctOptionId,
        explanationTextSources = explanationTextSources,
        isAnonymous = isAnonymous,
        isClosed = isClosed,
        openPeriod = openPeriod,
        closeDate = closeDate,
        threadId = threadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )
)

public suspend fun TelegramBot.sendQuizPoll(
    chatId: ChatIdentifier,
    question: String,
    options: List<InputPollOption>,
    correctOptionId: Int,
    closeInfo: ScheduledCloseInfo?,
    questionParseMode: ParseMode? = null,
    explanation: String?,
    explanationParseMode: ParseMode? = null,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PollContent> = execute(
    SendQuizPoll(
        chatId = chatId,
        question = question,
        options = options,
        correctOptionId = correctOptionId,
        closeInfo = closeInfo,
        explanation = explanation,
        questionParseMode = questionParseMode,
        explanationParseMode = explanationParseMode,
        isAnonymous = isAnonymous,
        isClosed = isClosed,
        threadId = threadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )
)

public suspend fun TelegramBot.sendQuizPoll(
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
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PollContent> = execute(
    SendQuizPoll(
        chatId = chatId,
        questionEntities = questionEntities,
        options = options,
        correctOptionId = correctOptionId,
        closeInfo = closeInfo,
        explanation = explanation,
        explanationParseMode = explanationParseMode,
        isAnonymous = isAnonymous,
        isClosed = isClosed,
        threadId = threadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )
)

public suspend fun TelegramBot.sendQuizPoll(
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
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PollContent> = execute(
    SendQuizPoll(
        chatId = chatId,
        question = question,
        options = options,
        correctOptionId = correctOptionId,
        closeInfo = closeInfo,
        questionParseMode = questionParseMode,
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
        replyMarkup = replyMarkup
    )
)

public suspend fun TelegramBot.sendQuizPoll(
    chatId: ChatIdentifier,
    questionEntities: List<TextSource>,
    options: List<InputPollOption>,
    correctOptionId: Int,
    closeInfo: ScheduledCloseInfo?,
    explanationTextSources: List<TextSource>? = null,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PollContent> = execute(
    SendQuizPoll(
        chatId = chatId,
        questionEntities = questionEntities,
        options = options,
        correctOptionId = correctOptionId,
        closeInfo = closeInfo,
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
        replyMarkup = replyMarkup
    )
)
