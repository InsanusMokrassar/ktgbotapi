package dev.inmo.tgbotapi.types.message.ChatEvents.voice

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.VoiceChatEvent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VoiceChatScheduled(
    @SerialName(startDateField)
    val startDate: TelegramDate
) : VoiceChatEvent
