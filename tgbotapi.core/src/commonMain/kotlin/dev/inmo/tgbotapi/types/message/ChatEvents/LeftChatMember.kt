package dev.inmo.tgbotapi.types.message.ChatEvents

import dev.inmo.tgbotapi.CommonAbstracts.FromUser
import dev.inmo.tgbotapi.types.User
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.CommonEvent

data class LeftChatMember(
    override val user: User
): CommonEvent, FromUser
