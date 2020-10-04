package dev.inmo.tgbotapi.types.ChatMember

import dev.inmo.tgbotapi.types.ChatMember.abstracts.ChatMember
import dev.inmo.tgbotapi.types.User

data class MemberChatMember(override val user: User) :
    ChatMember
