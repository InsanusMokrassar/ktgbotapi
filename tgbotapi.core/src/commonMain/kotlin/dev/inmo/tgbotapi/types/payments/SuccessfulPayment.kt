package dev.inmo.tgbotapi.types.payments

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.payments.abstracts.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SuccessfulPayment(
    @SerialName(currencyField)
    override val currency: Currency,
    @SerialName(totalAmountField)
    override val amount: Long,
    @SerialName(invoicePayloadField)
    val invoicePayload: String,
    @SerialName(subscriptionExpirationDateField)
    val subscriptionExpirationDate: TelegramDate? = null,
    @SerialName(isRecurringField)
    val subscriptionPayment: Boolean? = null,
    @SerialName(isFirstRecurringField)
    val isFirstPeriodPayment: Boolean? = null,
    @SerialName(telegramPaymentChargeIdField)
    val telegramPaymentChargeId: TelegramPaymentChargeId,
    @SerialName(providerPaymentChargeIdField)
    val providerPaymentChargeId: String,
    @SerialName(shippingOptionIdField)
    val shippingOptionId: String? = null,
    @SerialName(orderInfoField)
    val orderInfo: OrderInfo? = null
) : Amounted, Currencied {
    val recurringInfo: RecurringInfo? by lazy {
        if (subscriptionPayment == true && subscriptionExpirationDate != null) {
            RecurringInfo(
                subscriptionExpirationDate,
                isFirstPeriodPayment == true,
            )
        } else {
            null
        }
    }
}
