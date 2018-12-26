package com.github.insanusmokrassar.TelegramBotAPI.types.message.payments

import com.github.insanusmokrassar.TelegramBotAPI.types.message.payments.abstracts.PaymentInfo
import com.github.insanusmokrassar.TelegramBotAPI.types.payments.SuccessfulPayment

data class SuccessfulPaymentInfo(
    val payment: SuccessfulPayment
) : PaymentInfo
