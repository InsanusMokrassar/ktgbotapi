package dev.inmo.tgbotapi.types.message.ChatEvents.voice

import dev.inmo.tgbotapi.types.User
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.VideoChatEvent
import dev.inmo.tgbotapi.types.usersField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoChatParticipantsInvited(
    @SerialName(usersField)
    val users: List<User> = emptyList()
) : VideoChatEvent

@Deprecated("Renamed", ReplaceWith("VideoChatParticipantsInvited", "dev.inmo.tgbotapi.types.message.ChatEvents.voice.VideoChatParticipantsInvited"))
typealias VoiceChatParticipantsInvited = VideoChatParticipantsInvited
