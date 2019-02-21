package com.github.insanusmokrassar.TelegramBotAPI.types.payments.abstracts

import kotlinx.serialization.*
import java.util.*

interface Currencied {
    val currency: Currency
}

@Serializer(Currency::class)
object CurrencySerializer : KSerializer<Currency> {
    override fun serialize(encoder: Encoder, obj: Currency) {
        encoder.encodeString(obj.currencyCode)
    }

    override fun deserialize(decoder: Decoder): Currency {
        return decoder.decodeString().let {
            Currency.getInstance(it)
        }
    }
}
