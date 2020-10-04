package dev.inmo.tgbotapi.types.message.ChatEvents

import dev.inmo.tgbotapi.types.User
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.CommonEvent

data class LeftChatMember(
    val user: User
): CommonEvent
