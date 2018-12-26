package com.github.insanusmokrassar.TelegramBotAPI.requests.answers.payments.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.ShippingQueryIdentifier
import kotlinx.serialization.KSerializer
import kotlinx.serialization.serializer

interface AnswerShippingQuery : SimpleRequest<Boolean> {
    override fun method(): String = "answerShippingQuery"
    override fun resultSerializer(): KSerializer<Boolean> = Boolean.serializer()

    val shippingQueryId: ShippingQueryIdentifier
    val isOk: Boolean
}