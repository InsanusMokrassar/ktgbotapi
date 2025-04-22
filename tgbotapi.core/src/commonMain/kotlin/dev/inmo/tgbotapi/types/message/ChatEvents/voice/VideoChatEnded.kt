package dev.inmo.tgbotapi.types.message.ChatEvents.voice

import dev.inmo.tgbotapi.types.Seconds
import dev.inmo.tgbotapi.types.durationField
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.VideoChatEvent
import korlibs.time.TimeSpan
import korlibs.time.milliseconds
import korlibs.time.seconds
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoChatEnded(
    @SerialName(durationField)
    val duration: Seconds,
) : VideoChatEvent {
    val timeSpan: TimeSpan
        get() = TimeSpan(duration.seconds.milliseconds)
}
