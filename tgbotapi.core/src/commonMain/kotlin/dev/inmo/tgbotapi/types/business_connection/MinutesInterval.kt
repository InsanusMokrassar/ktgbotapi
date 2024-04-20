package dev.inmo.tgbotapi.types.business_connection

import korlibs.time.TimeSpan
import korlibs.time.minutes
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class MinutesInterval(
    val int: Int
) {
    val dayTimeSpan: TimeSpan
        get() = int.minutes
    val weekDay0: Int
        get() = dayTimeSpan.inWholeDays.toInt()
}
