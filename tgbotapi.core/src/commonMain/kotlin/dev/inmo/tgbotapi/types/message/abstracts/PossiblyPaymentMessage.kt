package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.message.payments.abstracts.PaymentInfo

interface PossiblyPaymentMessage : AccessibleMessage {
    val paymentInfo: PaymentInfo?
}
