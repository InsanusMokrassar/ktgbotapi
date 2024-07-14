package dev.inmo.tgbotapi.extensions.api.answers.payments

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.answers.payments.AnswerPreCheckoutQueryError
import dev.inmo.tgbotapi.requests.answers.payments.AnswerPreCheckoutQueryOk
import dev.inmo.tgbotapi.types.PreCheckoutQueryId
import dev.inmo.tgbotapi.types.payments.PreCheckoutQuery

public suspend fun TelegramBot.answerPreCheckoutQueryOk(
    id: PreCheckoutQueryId
): Boolean = execute(AnswerPreCheckoutQueryOk(id))
public suspend fun TelegramBot.answerPreCheckoutQueryOk(
    preCheckoutQuery: PreCheckoutQuery
): Boolean = answerPreCheckoutQueryOk(preCheckoutQuery.id)

public suspend fun TelegramBot.answerPreCheckoutQueryError(
    id: PreCheckoutQueryId,
    error: String
): Boolean = execute(AnswerPreCheckoutQueryError(id, error))
public suspend fun TelegramBot.answerPreCheckoutQueryError(
    preCheckoutQuery: PreCheckoutQuery,
    error: String
): Boolean = answerPreCheckoutQueryError(preCheckoutQuery.id, error)
