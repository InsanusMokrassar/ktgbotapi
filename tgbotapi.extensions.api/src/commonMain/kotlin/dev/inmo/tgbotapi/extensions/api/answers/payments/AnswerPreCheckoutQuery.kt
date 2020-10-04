package dev.inmo.tgbotapi.extensions.api.answers.payments

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.answers.payments.AnswerPreCheckoutQueryError
import dev.inmo.tgbotapi.requests.answers.payments.AnswerPreCheckoutQueryOk
import dev.inmo.tgbotapi.types.PreCheckoutQueryId
import dev.inmo.tgbotapi.types.payments.PreCheckoutQuery

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
