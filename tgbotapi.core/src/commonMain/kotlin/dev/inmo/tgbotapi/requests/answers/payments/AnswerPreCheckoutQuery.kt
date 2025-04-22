package dev.inmo.tgbotapi.requests.answers.payments

import dev.inmo.tgbotapi.requests.answers.payments.abstracts.AnswerPreCheckoutQuery
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.payments.PreCheckoutQuery
import kotlinx.serialization.*

@Serializable
data class AnswerPreCheckoutQueryOk(
    @SerialName(preCheckoutQueryIdField)
    override val preCheckoutQueryId: PreCheckoutQueryId,
) : AnswerPreCheckoutQuery {
    @SerialName(okField)
    override val isOk: Boolean = true
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

@Serializable
data class AnswerPreCheckoutQueryError(
    @SerialName(preCheckoutQueryIdField)
    override val preCheckoutQueryId: PreCheckoutQueryId,
    @SerialName(errorMessageField)
    val errorMessage: String,
) : AnswerPreCheckoutQuery {
    @SerialName(okField)
    override val isOk: Boolean = false
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

fun PreCheckoutQuery.createAnswerOk(): AnswerPreCheckoutQueryOk =
    AnswerPreCheckoutQueryOk(
        id,
    )

fun PreCheckoutQuery.createAnswerError(error: String): AnswerPreCheckoutQueryError =
    AnswerPreCheckoutQueryError(
        id,
        error,
    )
