package dev.inmo.tgbotapi.requests.answers.payments

import dev.inmo.tgbotapi.requests.answers.payments.abstracts.AnswerShippingQuery
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.payments.ShippingOption
import dev.inmo.tgbotapi.types.payments.ShippingQuery
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.serialization.*
import kotlinx.serialization.builtins.ListSerializer

@Serializable
data class AnswerShippingQueryOk(
    @SerialName(shippingQueryIdField)
    override val shippingQueryId: ShippingQueryId,
    @Serializable(ShippingOptionsSerializer::class)
    @SerialName(shippingOptionsField)
    val shippingOptions: List<ShippingOption>,
) : AnswerShippingQuery {
    @SerialName(okField)
    override val isOk: Boolean = true
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

@RiskFeature
object ShippingOptionsSerializer : KSerializer<List<ShippingOption>> by ListSerializer(
    ShippingOption.serializer(),
)

@Serializable
data class AnswerShippingQueryError(
    @SerialName(shippingQueryIdField)
    override val shippingQueryId: ShippingQueryId,
    @SerialName(errorMessageField)
    val error: String,
) : AnswerShippingQuery {
    @SerialName(okField)
    override val isOk: Boolean = false
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

fun ShippingQuery.createAnswerOk(shippingOptions: List<ShippingOption>): AnswerShippingQueryOk = AnswerShippingQueryOk(
    id,
    shippingOptions,
)

fun ShippingQuery.createAnswerError(error: String): AnswerShippingQueryError = AnswerShippingQueryError(
    id,
    error,
)
