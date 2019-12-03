package com.github.insanusmokrassar.TelegramBotAPI.types.payments.abstracts

interface Amounted {
    val amount: Long
    val adaptedMajorityTotalAmount: Double
        get() = amount / 100.0
}