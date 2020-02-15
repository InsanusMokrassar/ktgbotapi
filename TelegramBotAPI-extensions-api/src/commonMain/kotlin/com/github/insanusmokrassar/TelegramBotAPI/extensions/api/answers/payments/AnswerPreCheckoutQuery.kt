package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.answers.payments

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.answers.payments.AnswerPreCheckoutQueryError
import com.github.insanusmokrassar.TelegramBotAPI.requests.answers.payments.AnswerPreCheckoutQueryOk
import com.github.insanusmokrassar.TelegramBotAPI.types.PreCheckoutQueryId
import com.github.insanusmokrassar.TelegramBotAPI.types.payments.PreCheckoutQuery

suspend fun RequestsExecutor.answerPreCheckoutQueryOk(
    id: PreCheckoutQueryId
) = execute(AnswerPreCheckoutQueryOk(id))
suspend fun RequestsExecutor.answerPreCheckoutQueryOk(
    preCheckoutQuery: PreCheckoutQuery
) = answerPreCheckoutQueryOk(preCheckoutQuery.id)

suspend fun RequestsExecutor.answerPreCheckoutQueryError(
    id: PreCheckoutQueryId,
    error: String
) = execute(AnswerPreCheckoutQueryError(id, error))
suspend fun RequestsExecutor.answerPreCheckoutQueryError(
    preCheckoutQuery: PreCheckoutQuery,
    error: String
) = answerPreCheckoutQueryError(preCheckoutQuery.id, error)
