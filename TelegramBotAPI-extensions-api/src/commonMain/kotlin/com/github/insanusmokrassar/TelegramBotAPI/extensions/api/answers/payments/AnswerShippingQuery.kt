package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.answers.payments

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.answers.payments.AnswerShippingQueryError
import com.github.insanusmokrassar.TelegramBotAPI.requests.answers.payments.AnswerShippingQueryOk
import com.github.insanusmokrassar.TelegramBotAPI.types.ShippingQueryIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.payments.ShippingOption
import com.github.insanusmokrassar.TelegramBotAPI.types.payments.ShippingQuery

suspend fun RequestsExecutor.answerShippingQueryOk(
    id: ShippingQueryIdentifier,
    shippingOptions: List<ShippingOption>
) = execute(AnswerShippingQueryOk(id, shippingOptions))
suspend fun RequestsExecutor.answerShippingQueryOk(
    shippingQuery: ShippingQuery,
    shippingOptions: List<ShippingOption>
) = answerShippingQueryOk(shippingQuery.id, shippingOptions)

suspend fun RequestsExecutor.answerShippingQueryError(
    id: ShippingQueryIdentifier,
    error: String
) = execute(AnswerShippingQueryError(id, error))
suspend fun RequestsExecutor.answerShippingQueryError(
    shippingQuery: ShippingQuery,
    error: String
) = answerShippingQueryError(shippingQuery.id, error)


