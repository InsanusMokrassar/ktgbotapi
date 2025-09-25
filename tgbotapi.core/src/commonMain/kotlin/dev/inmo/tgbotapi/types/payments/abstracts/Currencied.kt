package dev.inmo.tgbotapi.types.payments.abstracts

typealias Currency = String

val String.Companion.XTR
    get() = "XTR"

val String.Companion.TON
    get() = "TOM"

interface Currencied {
    val currency: Currency
}
