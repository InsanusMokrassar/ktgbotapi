package com.github.insanusmokrassar.TelegramBotAPI.types

import kotlinx.serialization.*
import org.joda.time.DateTime
import java.util.concurrent.TimeUnit


fun DateTime.toTelegramDate(): TelegramDate = TelegramDate(TimeUnit.MILLISECONDS.toSeconds(millis))


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