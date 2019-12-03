package com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.message.payments.abstracts.PaymentInfo

interface AbleToBePaymentedMessage : Message {
    val paymentInfo: PaymentInfo?
}