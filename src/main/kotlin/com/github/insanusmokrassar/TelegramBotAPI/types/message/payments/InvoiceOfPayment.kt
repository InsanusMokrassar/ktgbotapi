package com.github.insanusmokrassar.TelegramBotAPI.types.message.payments

import com.github.insanusmokrassar.TelegramBotAPI.types.message.payments.abstracts.PaymentInfo
import com.github.insanusmokrassar.TelegramBotAPI.types.payments.Invoice

data class InvoiceOfPayment(
    val invoice: Invoice
) : PaymentInfo
