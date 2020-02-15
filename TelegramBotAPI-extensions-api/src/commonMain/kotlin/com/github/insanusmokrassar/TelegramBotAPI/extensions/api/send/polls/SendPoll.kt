package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.send.polls

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.polls.SendQuizPoll
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.polls.SendRegularPoll
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.polls.QuizPoll
import com.github.insanusmokrassar.TelegramBotAPI.types.polls.RegularPoll

suspend fun RequestsExecutor.sendRegularPoll(
    chatId: ChatIdentifier,
    question: String,
    options: List<String>,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    allowMultipleAnswers: Boolean = false,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendRegularPoll(
        chatId, question, options, isAnonymous, isClosed, allowMultipleAnswers, disableNotification, replyToMessageId, replyMarkup
    )
)
suspend fun RequestsExecutor.sendRegularPoll(
    chatId: ChatIdentifier,
    poll: RegularPoll,
    isClosed: Boolean = false,
    question: String = poll.question,
    options: List<String> = poll.options.map { it.text },
    isAnonymous: Boolean = poll.isAnonymous,
    allowMultipleAnswers: Boolean = poll.allowMultipleAnswers,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendRegularPoll(
        chatId, question, options, isAnonymous, isClosed, allowMultipleAnswers, disableNotification, replyToMessageId, replyMarkup
    )
)

suspend fun RequestsExecutor.sendRegularPoll(
    chat: Chat,
    question: String,
    options: List<String>,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    allowMultipleAnswers: Boolean = false,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendRegularPoll(
    chat.id, question, options, isAnonymous, isClosed, allowMultipleAnswers, disableNotification, replyToMessageId, replyMarkup
)

suspend fun RequestsExecutor.sendRegularPoll(
    chat: Chat,
    poll: RegularPoll,
    isClosed: Boolean = false,
    question: String = poll.question,
    options: List<String> = poll.options.map { it.text },
    isAnonymous: Boolean = poll.isAnonymous,
    allowMultipleAnswers: Boolean = poll.allowMultipleAnswers,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendRegularPoll(
    chat.id, question, options, isAnonymous, isClosed, allowMultipleAnswers, disableNotification, replyToMessageId, replyMarkup
)


suspend fun RequestsExecutor.sendQuizPoll(
    chatId: ChatIdentifier,
    question: String,
    options: List<String>,
    correctOptionId: Int,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendQuizPoll(
        chatId, question, options, correctOptionId, isAnonymous, isClosed, disableNotification, replyToMessageId, replyMarkup
    )
)

suspend fun RequestsExecutor.sendQuizPoll(
    chat: Chat,
    question: String,
    options: List<String>,
    correctOptionId: Int,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(
    chat.id, question, options, correctOptionId, isAnonymous, isClosed, disableNotification, replyToMessageId, replyMarkup
)

suspend fun RequestsExecutor.sendQuizPoll(
    chatId: ChatIdentifier,
    isClosed: Boolean = false,
    quizPoll: QuizPoll,
    question: String = quizPoll.question,
    options: List<String> = quizPoll.options.map { it.text },
    correctOptionId: Int = quizPoll.correctOptionId ?: error("Correct option ID must be provided by income QuizPoll or by developer"),
    isAnonymous: Boolean = quizPoll.isAnonymous,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendQuizPoll(
        chatId, question, options, correctOptionId, isAnonymous, isClosed, disableNotification, replyToMessageId, replyMarkup
    )
)

suspend fun RequestsExecutor.sendQuizPoll(
    chat: Chat,
    isClosed: Boolean = false,
    quizPoll: QuizPoll,
    question: String = quizPoll.question,
    options: List<String> = quizPoll.options.map { it.text },
    correctOptionId: Int = quizPoll.correctOptionId ?: error("Correct option ID must be provided by income QuizPoll or by developer"),
    isAnonymous: Boolean = quizPoll.isAnonymous,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(
    chat.id, question, options, correctOptionId, isAnonymous, isClosed, disableNotification, replyToMessageId, replyMarkup
)
