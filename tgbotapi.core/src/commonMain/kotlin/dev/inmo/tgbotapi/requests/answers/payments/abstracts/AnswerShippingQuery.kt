package dev.inmo.tgbotapi.requests.answers.payments.abstracts

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.ShippingQueryId
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.builtins.serializer

interface AnswerShippingQuery : SimpleRequest<Unit> {
    override fun method(): String = "answerShippingQuery"
    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer

    val shippingQueryId: ShippingQueryId
    val isOk: Boolean
}