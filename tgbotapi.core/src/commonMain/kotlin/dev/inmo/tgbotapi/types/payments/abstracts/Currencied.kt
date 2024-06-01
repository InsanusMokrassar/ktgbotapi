package dev.inmo.tgbotapi.types.payments.abstracts

typealias Currency = String

val String.Companion.XTR
    get() = "XTR"

interface Currencied {
    val currency: Currency
}
