package dev.inmo.tgbotapi.types.message.payments

import dev.inmo.tgbotapi.types.message.payments.abstracts.PaymentInfo
import dev.inmo.tgbotapi.types.payments.SuccessfulPayment

data class SuccessfulPaymentInfo(
    val payment: SuccessfulPayment
) : PaymentInfo
