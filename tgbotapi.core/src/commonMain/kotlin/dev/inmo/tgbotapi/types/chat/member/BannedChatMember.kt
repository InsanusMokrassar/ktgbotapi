package dev.inmo.tgbotapi.types.chat.member

import dev.inmo.tgbotapi.CommonAbstracts.types.UntilDate
import kotlinx.serialization.Serializable

@Serializable(ChatMemberSerializer::class)
sealed interface BannedChatMember : ChatMember, UntilDate
