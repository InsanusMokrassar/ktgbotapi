package dev.inmo.tgbotapi.types.payments.abstracts

typealias Currency = String

interface Currencied {
    val currency: Currency
}
