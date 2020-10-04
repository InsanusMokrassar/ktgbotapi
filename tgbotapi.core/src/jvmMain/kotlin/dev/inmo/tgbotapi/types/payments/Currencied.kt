package dev.inmo.tgbotapi.types.payments

import dev.inmo.tgbotapi.types.payments.abstracts.Currencied
import java.util.*

fun Currencied.javaCurrency(): Currency = Currency.getInstance(currency)
