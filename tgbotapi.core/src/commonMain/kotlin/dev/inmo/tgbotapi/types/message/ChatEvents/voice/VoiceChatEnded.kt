package dev.inmo.tgbotapi.types.message.ChatEvents.voice

import com.soywiz.klock.TimeSpan
import com.soywiz.klock.seconds
import dev.inmo.tgbotapi.types.Seconds
import dev.inmo.tgbotapi.types.durationField
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.VoiceChatEvent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VoiceChatEnded(
    @SerialName(durationField)
    val duration: Seconds
) : VoiceChatEvent {
    val timeSpan: TimeSpan
        get() = TimeSpan(duration.seconds.milliseconds)
}
