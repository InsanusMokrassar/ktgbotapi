package dev.inmo.tgbotapi.types.message.ChatEvents.voice

import dev.inmo.tgbotapi.types.TelegramDate
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.VideoChatEvent
import dev.inmo.tgbotapi.types.startDateField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoChatScheduled(
    @SerialName(startDateField)
    val startDate: TelegramDate
) : VideoChatEvent

@Deprecated("Renamed", ReplaceWith("VideoChatScheduled", "dev.inmo.tgbotapi.types.message.ChatEvents.voice.VideoChatScheduled"))
typealias VoiceChatScheduled = VideoChatScheduled
