package com.github.insanusmokrassar.TelegramBotAPI.types.payments

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.payments.abstracts.*
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
    @SerialName(telegramPaymentChargeIdField)
    val telegramPaymentChargeId: String,
    @SerialName(providerPaymentChargeIdField)
    val providerPaymentChargeId: String,
    @SerialName(shippingOptionIdField)
    val shippingOptionId: String? = null,
    @SerialName(orderInfoField)
    val orderInfo: OrderInfo? = null
) : Amounted, Currencied
