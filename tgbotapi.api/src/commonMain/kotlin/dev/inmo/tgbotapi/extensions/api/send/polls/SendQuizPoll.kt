package dev.inmo.tgbotapi.extensions.api.send.polls

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.polls.SendQuizPoll
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.polls.InputPollOption
import dev.inmo.tgbotapi.types.polls.ScheduledCloseInfo


suspend fun TelegramBot.sendQuizPoll(
    chatId: ChatIdentifier,
    question: String,
    options: List<InputPollOption>,
    correctOptionId: Int,
    questionParseMode: ParseMode? = null,
    explanation: String? = null,
    explanationParseMode: ParseMode? = null,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    openPeriod: LongSeconds? = null,
    closeDate: LongSeconds? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendQuizPoll(
        chatId,
        question,
        options,
        correctOptionId,
        questionParseMode,
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
        replyParameters,
        replyMarkup
    )
)

suspend fun TelegramBot.sendQuizPoll(
    chatId: ChatIdentifier,
    questionEntities: List<TextSource>,
    options: List<InputPollOption>,
    correctOptionId: Int,
    explanation: String? = null,
    explanationParseMode: ParseMode? = null,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    openPeriod: LongSeconds? = null,
    closeDate: LongSeconds? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
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
        replyParameters,
        replyMarkup
    )
)

suspend fun TelegramBot.sendQuizPoll(
    chatId: ChatIdentifier,
    question: String,
    options: List<InputPollOption>,
    correctOptionId: Int,
    explanationTextSources: List<TextSource>,
    questionParseMode: ParseMode? = null,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    openPeriod: LongSeconds? = null,
    closeDate: LongSeconds? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendQuizPoll(
        chatId,
        question,
        options,
        correctOptionId,
        explanationTextSources,
        questionParseMode,
        isAnonymous,
        isClosed,
        openPeriod,
        closeDate,
        threadId,
        businessConnectionId,
        disableNotification,
        protectContent,
        replyParameters,
        replyMarkup
    )
)


suspend fun TelegramBot.sendQuizPoll(
    chatId: ChatIdentifier,
    questionEntities: List<TextSource>,
    options: List<InputPollOption>,
    correctOptionId: Int,
    explanationTextSources: List<TextSource>,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    openPeriod: LongSeconds? = null,
    closeDate: LongSeconds? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
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
        replyParameters,
        replyMarkup
    )
)

suspend fun TelegramBot.sendQuizPoll(
    chatId: ChatIdentifier,
    question: String,
    options: List<InputPollOption>,
    correctOptionId: Int,
    closeInfo: ScheduledCloseInfo?,
    questionParseMode: ParseMode? = null,
    explanation: String? = null,
    explanationParseMode: ParseMode? = null,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendQuizPoll(
        chatId,
        question,
        options,
        correctOptionId,
        closeInfo,
        questionParseMode,
        explanation,
        explanationParseMode,
        isAnonymous,
        isClosed,
        threadId,
        businessConnectionId,
        disableNotification,
        protectContent,
        replyParameters,
        replyMarkup
    )
)

suspend fun TelegramBot.sendQuizPoll(
    chatId: ChatIdentifier,
    questionEntities: List<TextSource>,
    options: List<InputPollOption>,
    correctOptionId: Int,
    closeInfo: ScheduledCloseInfo?,
    explanation: String? = null,
    explanationParseMode: ParseMode? = null,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
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
        replyParameters,
        replyMarkup
    )
)

suspend fun TelegramBot.sendQuizPoll(
    chatId: ChatIdentifier,
    question: String,
    options: List<InputPollOption>,
    correctOptionId: Int,
    explanationTextSources: List<TextSource>,
    closeInfo: ScheduledCloseInfo?,
    questionParseMode: ParseMode? = null,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendQuizPoll(
        chatId,
        question,
        options,
        correctOptionId,
        explanationTextSources,
        closeInfo,
        questionParseMode,
        isAnonymous,
        isClosed,
        threadId,
        businessConnectionId,
        disableNotification,
        protectContent,
        replyParameters,
        replyMarkup
    )
)

suspend fun TelegramBot.sendQuizPoll(
    chatId: ChatIdentifier,
    questionEntities: List<TextSource>,
    options: List<InputPollOption>,
    correctOptionId: Int,
    explanationTextSources: List<TextSource>,
    closeInfo: ScheduledCloseInfo?,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendQuizPoll(
        chatId,
        questionEntities,
        options,
        correctOptionId,
        explanationTextSources,
        closeInfo,
        isAnonymous,
        isClosed,
        threadId,
        businessConnectionId,
        disableNotification,
        protectContent,
        replyParameters,
        replyMarkup
    )
)
