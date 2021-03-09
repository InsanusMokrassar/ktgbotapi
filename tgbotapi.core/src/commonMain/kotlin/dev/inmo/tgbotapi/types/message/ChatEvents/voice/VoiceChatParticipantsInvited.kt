package dev.inmo.tgbotapi.types.message.ChatEvents.voice

import dev.inmo.tgbotapi.types.User
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.VoiceChatEvent
import dev.inmo.tgbotapi.types.usersField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class VoiceChatParticipantsInvited(
    @SerialName(usersField)
    val users: List<User> = emptyList()
) : VoiceChatEvent
