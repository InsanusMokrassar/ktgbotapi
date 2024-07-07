package dev.inmo.tgbotapi.types.payments

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.payments.abstracts.Amounted
import dev.inmo.tgbotapi.types.payments.abstracts.Currencied
import dev.inmo.tgbotapi.types.payments.abstracts.Currency
import dev.inmo.tgbotapi.types.payments.abstracts.TelegramPaymentChargeId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefundedPayment(
    @SerialName(currencyField)
    override val currency: Currency,
    @SerialName(totalAmountField)
    override val amount: Long,
    @SerialName(invoicePayloadField)
    val invoicePayload: String,
    @SerialName(telegramPaymentChargeIdField)
    val telegramPaymentChargeId: TelegramPaymentChargeId,
    @SerialName(providerPaymentChargeIdField)
    val providerPaymentChargeId: String
): Amounted, Currencied
