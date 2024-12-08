package dev.inmo.tgbotapi.requests.edit.payments

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.isCanceledField
import dev.inmo.tgbotapi.types.payments.abstracts.TelegramPaymentChargeId
import dev.inmo.tgbotapi.types.telegramPaymentChargeIdField
import dev.inmo.tgbotapi.types.userIdField
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.builtins.serializer

@Serializable
data class EditUserStarSubscription(
    @SerialName(userIdField)
    val userId: UserId,
    @SerialName(telegramPaymentChargeIdField)
    val telegramPaymentChargeId: TelegramPaymentChargeId,
    @SerialName(isCanceledField)
    val isCanceled: Boolean
) : SimpleRequest<Boolean> {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String = "editUserStarSubscription"

    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()

}
