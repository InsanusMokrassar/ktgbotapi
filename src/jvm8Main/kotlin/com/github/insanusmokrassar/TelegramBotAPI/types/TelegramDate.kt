package com.github.insanusmokrassar.TelegramBotAPI.types

import kotlinx.serialization.*

@Serializer(TelegramDate::class)
internal actual class TelegramDateSerializer : KSerializer<TelegramDate> {
    override fun serialize(encoder: Encoder, obj: TelegramDate) {
        encoder.encodeLong(obj.asDate.seconds.toLong())
    }

    override fun deserialize(decoder: Decoder): TelegramDate {
        return TelegramDate(
            decoder.decodeLong()
        )
    }
}