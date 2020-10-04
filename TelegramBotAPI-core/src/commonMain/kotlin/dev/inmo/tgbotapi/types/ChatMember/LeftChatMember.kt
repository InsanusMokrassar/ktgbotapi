package dev.inmo.tgbotapi.types.ChatMember

import dev.inmo.tgbotapi.types.ChatMember.abstracts.ChatMember
import dev.inmo.tgbotapi.types.User

data class LeftChatMember(override val user: User) :
    ChatMember
