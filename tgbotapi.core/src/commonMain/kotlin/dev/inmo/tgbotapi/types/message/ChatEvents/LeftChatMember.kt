package dev.inmo.tgbotapi.types.message.ChatEvents

import dev.inmo.tgbotapi.types.User
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.PublicChatEvent

data class LeftChatMember(
    val user: User
) : PublicChatEvent
