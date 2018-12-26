package com.github.insanusmokrassar.TelegramBotAPI.types.payments.abstracts

import kotlinx.serialization.*
import java.util.*

interface Currencied {
    val currency: Currency
}

@Serializer(Currency::class)
object CurrencySerializer : KSerializer<Currency> {
    override fun serialize(output: Encoder, obj: Currency) {
        output.encodeString(obj.currencyCode)
    }

    override fun deserialize(input: Decoder): Currency {
        return input.decodeString().let {
            Currency.getInstance(it)
        }
    }
}
