package dev.inmo.tgbotapi.requests.send.payments

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.payments.abstracts.TelegramPaymentChargeId
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class RefundStarPayment(
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(userIdField)
    val userId: UserId,
    @SerialName(telegramPaymentChargeIdField)
    val telegramPaymentChargeId: TelegramPaymentChargeId
) : SimpleRequest<Boolean> {
    override fun method(): String = "refundStarPayment"
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
