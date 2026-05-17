package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.message.payments.abstracts.PaymentInfo

interface PossiblyPaymentMessage : ChatMessage {
    val paymentInfo: PaymentInfo?
}
