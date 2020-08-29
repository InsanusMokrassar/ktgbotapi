package com.github.insanusmokrassar.TelegramBotAPI.types.payments.abstracts

typealias Currency = String

interface Currencied {
    val currency: Currency
}
