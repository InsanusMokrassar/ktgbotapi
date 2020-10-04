package dev.inmo.tgbotapi.extensions.api.answers.payments

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.answers.payments.AnswerPreCheckoutQueryError
import com.github.insanusmokrassar.TelegramBotAPI.requests.answers.payments.AnswerPreCheckoutQueryOk
import com.github.insanusmokrassar.TelegramBotAPI.types.PreCheckoutQueryId
import com.github.insanusmokrassar.TelegramBotAPI.types.payments.PreCheckoutQuery

suspend fun TelegramBot.answerPreCheckoutQueryOk(
    id: PreCheckoutQueryId
) = execute(AnswerPreCheckoutQueryOk(id))
suspend fun TelegramBot.answerPreCheckoutQueryOk(
    preCheckoutQuery: PreCheckoutQuery
) = answerPreCheckoutQueryOk(preCheckoutQuery.id)

suspend fun TelegramBot.answerPreCheckoutQueryError(
    id: PreCheckoutQueryId,
    error: String
) = execute(AnswerPreCheckoutQueryError(id, error))
suspend fun TelegramBot.answerPreCheckoutQueryError(
    preCheckoutQuery: PreCheckoutQuery,
    error: String
) = answerPreCheckoutQueryError(preCheckoutQuery.id, error)
