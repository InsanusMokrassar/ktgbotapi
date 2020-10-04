package dev.inmo.tgbotapi.requests.answers.payments.abstracts

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.ShippingQueryIdentifier
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.builtins.serializer

interface AnswerShippingQuery : SimpleRequest<Boolean> {
    override fun method(): String = "answerShippingQuery"
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()

    val shippingQueryId: ShippingQueryIdentifier
    val isOk: Boolean
}