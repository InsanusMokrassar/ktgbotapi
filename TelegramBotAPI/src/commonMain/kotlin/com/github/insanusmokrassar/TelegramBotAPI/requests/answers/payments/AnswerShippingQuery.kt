package com.github.insanusmokrassar.TelegramBotAPI.requests.answers.payments

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.answers.payments.abstracts.AnswerShippingQuery
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.payments.ShippingOption
import com.github.insanusmokrassar.TelegramBotAPI.types.payments.ShippingQuery
import kotlinx.serialization.*
import kotlinx.serialization.internal.ArrayListSerializer

@Serializable
data class AnswerShippingQueryOk(
    @SerialName(shippingQueryIdField)
    override val shippingQueryId: ShippingQueryIdentifier,
    @Serializable(ShippingOptionsSerializer::class)
    @SerialName(shippingOptionsField)
    val shippingOptions: List<ShippingOption>
) : AnswerShippingQuery {
    @SerialName(okField)
    override val isOk: Boolean = true
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

internal object ShippingOptionsSerializer : KSerializer<List<ShippingOption>> by ArrayListSerializer(
    ShippingOption.serializer()
)

@Serializable
data class AnswerShippingQueryError(
    @SerialName(shippingQueryIdField)
    override val shippingQueryId: ShippingQueryIdentifier,
    @SerialName(errorMessageField)
    val error: String
) : AnswerShippingQuery {
    @SerialName(okField)
    override val isOk: Boolean = false
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

fun ShippingQuery.createAnswerOk(
    shippingOptions: List<ShippingOption>
): AnswerShippingQueryOk = AnswerShippingQueryOk(
    id,
    shippingOptions
)

fun ShippingQuery.createAnswerError(
    error: String
): AnswerShippingQueryError = AnswerShippingQueryError(
    id,
    error
)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.answerShippingQueryOk(
    id: ShippingQueryIdentifier,
    shippingOptions: List<ShippingOption>
) = execute(AnswerShippingQueryOk(id, shippingOptions))
@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.answerShippingQueryOk(
    shippingQuery: ShippingQuery,
    shippingOptions: List<ShippingOption>
) = answerShippingQueryOk(shippingQuery.id, shippingOptions)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.answerShippingQueryError(
    id: ShippingQueryIdentifier,
    error: String
) = execute(AnswerShippingQueryError(id, error))
@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.answerShippingQueryError(
    shippingQuery: ShippingQuery,
    error: String
) = answerShippingQueryError(shippingQuery.id, error)


