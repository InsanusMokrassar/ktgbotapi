package dev.inmo.tgbotapi.types.message.payments

import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.CommonEvent
import dev.inmo.tgbotapi.types.message.payments.abstracts.PaymentInfo
import dev.inmo.tgbotapi.types.payments.SuccessfulPayment

data class SuccessfulPaymentEvent(
    val payment: SuccessfulPayment,
) : PaymentInfo, CommonEvent
