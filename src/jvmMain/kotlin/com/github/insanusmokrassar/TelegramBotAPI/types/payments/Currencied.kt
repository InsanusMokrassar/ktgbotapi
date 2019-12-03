package com.github.insanusmokrassar.TelegramBotAPI.types.payments

import com.github.insanusmokrassar.TelegramBotAPI.types.payments.abstracts.Currencied
import java.util.*

fun Currencied.javaCurrency(): Currency = Currency.getInstance(currency)
