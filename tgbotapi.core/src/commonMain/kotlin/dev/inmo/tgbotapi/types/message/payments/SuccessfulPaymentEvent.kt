package dev.inmo.tgbotapi.types.message.payments

import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.CommonEvent
import dev.inmo.tgbotapi.types.message.payments.abstracts.PaymentInfo
import dev.inmo.tgbotapi.types.payments.SuccessfulPayment

@Deprecated("Renamed", ReplaceWith("SuccessfulPaymentEvent", "dev.inmo.tgbotapi.types.message.payments.SuccessfulPaymentEvent"))
typealias SuccessfulPaymentInfo = SuccessfulPaymentEvent
data class SuccessfulPaymentEvent(
    val payment: SuccessfulPayment
) : PaymentInfo, CommonEvent
