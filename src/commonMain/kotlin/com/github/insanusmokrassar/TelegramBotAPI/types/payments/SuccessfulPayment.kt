package com.github.insanusmokrassar.TelegramBotAPI.types.payments

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.payments.abstracts.*
import kotlinx.serialization.*
import kotlinx.serialization.Optional


@Serializable
data class SuccessfulPayment(
    @SerialName(currencyField)
    override val currency: String,
    @SerialName(totalAmountField)
    override val amount: Long,
    @SerialName(invoicePayloadField)
    val invoicePayload: String,
    @SerialName(telegramPaymentChargeIdField)
    val telegramPaymentChargeId: String,
    @SerialName(providerPaymentChargeIdField)
    val providerPaymentChargeId: String,
    @SerialName(shippingOptionIdField)
    @Optional
    val shippingOptionId: String? = null,
    @SerialName(orderInfoField)
    @Optional
    val orderInfo: OrderInfo? = null
) : Amounted, Currencied
