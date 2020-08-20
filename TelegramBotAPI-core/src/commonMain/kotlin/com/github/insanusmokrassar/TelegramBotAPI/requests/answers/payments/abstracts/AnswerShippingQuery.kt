package com.github.insanusmokrassar.TelegramBotAPI.requests.answers.payments.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.ShippingQueryIdentifier
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.builtins.serializer

interface AnswerShippingQuery : SimpleRequest<Boolean> {
    override fun method(): String = "answerShippingQuery"
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()

    val shippingQueryId: ShippingQueryIdentifier
    val isOk: Boolean
}