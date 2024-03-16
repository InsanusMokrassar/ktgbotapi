package dev.inmo.tgbotapi.extensions.api.answers.payments

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.answers.payments.AnswerShippingQueryError
import dev.inmo.tgbotapi.requests.answers.payments.AnswerShippingQueryOk
import dev.inmo.tgbotapi.types.ShippingQueryId
import dev.inmo.tgbotapi.types.payments.ShippingOption
import dev.inmo.tgbotapi.types.payments.ShippingQuery

suspend fun TelegramBot.answerShippingQueryOk(
    id: ShippingQueryId,
    shippingOptions: List<ShippingOption>
) = execute(AnswerShippingQueryOk(id, shippingOptions))
suspend fun TelegramBot.answerShippingQueryOk(
    shippingQuery: ShippingQuery,
    shippingOptions: List<ShippingOption>
) = answerShippingQueryOk(shippingQuery.id, shippingOptions)

suspend fun TelegramBot.answerShippingQueryError(
    id: ShippingQueryId,
    error: String
) = execute(AnswerShippingQueryError(id, error))
suspend fun TelegramBot.answerShippingQueryError(
    shippingQuery: ShippingQuery,
    error: String
) = answerShippingQueryError(shippingQuery.id, error)


