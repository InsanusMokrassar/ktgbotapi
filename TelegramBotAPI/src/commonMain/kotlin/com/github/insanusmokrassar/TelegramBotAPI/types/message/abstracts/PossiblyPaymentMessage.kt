package com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.message.payments.abstracts.PaymentInfo

interface PossiblyPaymentMessage : Message {
    val paymentInfo: PaymentInfo?
}