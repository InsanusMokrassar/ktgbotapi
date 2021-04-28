package dev.inmo.tgbotapi.types.message.ChatEvents.voice

import dev.inmo.tgbotapi.types.TelegramDate
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.VoiceChatEvent
import dev.inmo.tgbotapi.types.startDateField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VoiceChatScheduled(
    @SerialName(startDateField)
    val startDate: TelegramDate
) : VoiceChatEvent
