package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.message.payments.abstracts.PaymentInfo

interface PossiblyPaymentMessage : Message {
    val paymentInfo: PaymentInfo?
}