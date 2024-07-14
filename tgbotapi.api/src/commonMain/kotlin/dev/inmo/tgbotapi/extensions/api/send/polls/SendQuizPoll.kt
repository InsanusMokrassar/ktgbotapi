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
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PollContent> = execute(
    SendQuizPoll(
        chatId,
        question,
        options,
        correctOptionId,
        explanation,
        questionParseMode,
        explanationParseMode,
        isAnonymous,
        isClosed,
        openPeriod,
        closeDate,
        threadId,
        businessConnectionId,
        disableNotification,
        protectContent,
        effectId,
        replyParameters,
        replyMarkup
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
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PollContent> = execute(
    SendQuizPoll(
        chatId,
        questionEntities,
        options,
        correctOptionId,
        explanation,
        explanationParseMode,
        isAnonymous,
        isClosed,
        openPeriod,
        closeDate,
        threadId,
        businessConnectionId,
        disableNotification,
        protectContent,
        effectId,
        replyParameters,
        replyMarkup
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
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PollContent> = execute(
    SendQuizPoll(
        chatId,
        question,
        options,
        correctOptionId,
        questionParseMode,
        explanationTextSources,
        isAnonymous,
        isClosed,
        openPeriod,
        closeDate,
        threadId,
        businessConnectionId,
        disableNotification,
        protectContent,
        effectId,
        replyParameters,
        replyMarkup
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
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PollContent> = execute(
    SendQuizPoll(
        chatId,
        questionEntities,
        options,
        correctOptionId,
        explanationTextSources,
        isAnonymous,
        isClosed,
        openPeriod,
        closeDate,
        threadId,
        businessConnectionId,
        disableNotification,
        protectContent,
        effectId,
        replyParameters,
        replyMarkup
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
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PollContent> = execute(
    SendQuizPoll(
        chatId,
        question,
        options,
        correctOptionId,
        closeInfo,
        explanation,
        questionParseMode,
        explanationParseMode,
        isAnonymous,
        isClosed,
        threadId,
        businessConnectionId,
        disableNotification,
        protectContent,
        effectId,
        replyParameters,
        replyMarkup
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
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PollContent> = execute(
    SendQuizPoll(
        chatId,
        questionEntities,
        options,
        correctOptionId,
        closeInfo,
        explanation,
        explanationParseMode,
        isAnonymous,
        isClosed,
        threadId,
        businessConnectionId,
        disableNotification,
        protectContent,
        effectId,
        replyParameters,
        replyMarkup
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
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PollContent> = execute(
    SendQuizPoll(
        chatId,
        question,
        options,
        correctOptionId,
        closeInfo,
        questionParseMode,
        explanationTextSources,
        isAnonymous,
        isClosed,
        threadId,
        businessConnectionId,
        disableNotification,
        protectContent,
        effectId,
        replyParameters,
        replyMarkup
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
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PollContent> = execute(
    SendQuizPoll(
        chatId,
        questionEntities,
        options,
        correctOptionId,
        closeInfo,
        explanationTextSources,
        isAnonymous,
        isClosed,
        threadId,
        businessConnectionId,
        disableNotification,
        protectContent,
        effectId,
        replyParameters,
        replyMarkup
    )
)
