package dev.inmo.tgbotapi.types.message.ChatEvents

import dev.inmo.tgbotapi.abstracts.WithUser
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.PublicChatEvent

data class LeftChatMember(
    override val user: User
) : PublicChatEvent, WithUser
