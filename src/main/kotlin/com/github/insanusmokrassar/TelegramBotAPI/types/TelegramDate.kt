package com.github.insanusmokrassar.TelegramBotAPI.types

import kotlinx.serialization.*
import com.soywiz.klock.DateTime
import java.util.concurrent.TimeUnit

@Serializable(TelegramDateSerializer::class)
data class TelegramDate(
    /**
     * Contains UNIX time (seconds)
     */
    private val date: Long
) {
    constructor(dateTime: DateTime) : this(
        TimeUnit.MILLISECONDS.toSeconds(dateTime.unixMillisLong)
    )

    @Transient
    val asDate: DateTime = DateTime(
        TimeUnit.SECONDS.toMillis(date)
    )
}

fun DateTime.toTelegramDate(): TelegramDate = TelegramDate(this)

@Serializer(TelegramDate::class)
internal object TelegramDateSerializer : KSerializer<TelegramDate> {
    override fun serialize(encoder: Encoder, obj: TelegramDate) {
        encoder.encodeLong(
            TimeUnit.MILLISECONDS.toSeconds(obj.asDate.unixMillisLong)
        )
    }

    override fun deserialize(decoder: Decoder): TelegramDate {
        return TelegramDate(
            decoder.decodeLong()
        )
    }
}
