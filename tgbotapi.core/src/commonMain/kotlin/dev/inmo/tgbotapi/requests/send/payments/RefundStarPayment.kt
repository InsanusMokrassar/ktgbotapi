package dev.inmo.tgbotapi.requests.send.payments

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.payments.abstracts.TelegramPaymentChargeId
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class RefundStarPayment(
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(userIdField)
    val userId: UserId,
    @SerialName(telegramPaymentChargeIdField)
    val telegramPaymentChargeId: TelegramPaymentChargeId
) : SimpleRequest<Unit> {
    override fun method(): String = "refundStarPayment"
    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
