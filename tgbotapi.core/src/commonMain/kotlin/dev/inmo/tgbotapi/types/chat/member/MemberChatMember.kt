package dev.inmo.tgbotapi.types.chat.member

import kotlinx.serialization.Serializable

@Serializable(ChatMemberSerializer::class)
sealed interface MemberChatMember : ChatMember
