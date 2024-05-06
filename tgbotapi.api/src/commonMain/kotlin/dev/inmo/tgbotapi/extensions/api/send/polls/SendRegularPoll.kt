package dev.inmo.tgbotapi.extensions.api.send.polls

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.polls.SendRegularPoll
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.polls.InputPollOption
import dev.inmo.tgbotapi.types.polls.ScheduledCloseInfo


suspend fun TelegramBot.sendRegularPoll(
    chatId: ChatIdentifier,
    question: String,
    options: List<InputPollOption>,
    questionParseMode: ParseMode? = null,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    allowMultipleAnswers: Boolean = false,
    openPeriod: LongSeconds? = null,
    closeDate: LongSeconds? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendRegularPoll(
        chatId,
        question,
        options,
        questionParseMode,
        isAnonymous,
        isClosed,
        allowMultipleAnswers,
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

suspend fun TelegramBot.sendRegularPoll(
    chatId: ChatIdentifier,
    questionEntities: List<TextSource>,
    options: List<InputPollOption>,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    allowMultipleAnswers: Boolean = false,
    openPeriod: LongSeconds? = null,
    closeDate: LongSeconds? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendRegularPoll(
        chatId,
        questionEntities,
        options,
        isAnonymous,
        isClosed,
        allowMultipleAnswers,
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

suspend fun TelegramBot.sendRegularPoll(
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
) = execute(
    SendRegularPoll(
        chatId,
        question,
        options,
        closeInfo,
        questionParseMode,
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
)

suspend fun TelegramBot.sendRegularPoll(
    chatId: ChatIdentifier,
    questionEntities: List<TextSource>,
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
) = execute(
    SendRegularPoll(
        chatId,
        questionEntities,
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
)
