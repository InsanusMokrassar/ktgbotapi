package dev.inmo.tgbotapi.extensions.api.answers.payments

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.answers.payments.AnswerShippingQueryError
import dev.inmo.tgbotapi.requests.answers.payments.AnswerShippingQueryOk
import dev.inmo.tgbotapi.types.ShippingQueryId
import dev.inmo.tgbotapi.types.payments.ShippingOption
import dev.inmo.tgbotapi.types.payments.ShippingQuery

public suspend fun TelegramBot.answerShippingQueryOk(
    id: ShippingQueryId,
    shippingOptions: List<ShippingOption>
): Unit = execute(AnswerShippingQueryOk(id, shippingOptions))
public suspend fun TelegramBot.answerShippingQueryOk(
    shippingQuery: ShippingQuery,
    shippingOptions: List<ShippingOption>
): Unit = answerShippingQueryOk(shippingQuery.id, shippingOptions)

public suspend fun TelegramBot.answerShippingQueryError(
    id: ShippingQueryId,
    error: String
): Unit = execute(AnswerShippingQueryError(id, error))
public suspend fun TelegramBot.answerShippingQueryError(
    shippingQuery: ShippingQuery,
    error: String
): Unit = answerShippingQueryError(shippingQuery.id, error)


