package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.utils.RiskFeature
import korlibs.time.DateTime
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(TelegramDateSerializer::class)
data class TelegramDate(
    /**
     * Contains UNIX time (seconds)
     */
    internal val date: Long,
) {
    constructor(dateTime: DateTime) : this(
        dateTime.unixMillisLong / 1000,
    )

    @Transient
    val asDate: DateTime =
        DateTime(
            date * 1000,
        )
}

fun DateTime.toTelegramDate(): TelegramDate = TelegramDate(this)

@RiskFeature
object TelegramDateSerializer : KSerializer<TelegramDate> {
    override val descriptor: SerialDescriptor = Long.serializer().descriptor

    override fun serialize(
        encoder: Encoder,
        value: TelegramDate,
    ) {
        encoder.encodeLong(
            value.date,
        )
    }

    override fun deserialize(decoder: Decoder): TelegramDate {
        return TelegramDate(
            decoder.decodeLong(),
        )
    }
}
