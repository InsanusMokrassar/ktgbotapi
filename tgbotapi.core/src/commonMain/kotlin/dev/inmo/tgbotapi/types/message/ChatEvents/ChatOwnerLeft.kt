package dev.inmo.tgbotapi.types.message.ChatEvents

import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.PublicChatEvent
import dev.inmo.tgbotapi.types.newOwnerField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatOwnerLeft(
    @SerialName(newOwnerField)
    val newOwner: CommonUser? = null
) : PublicChatEvent
