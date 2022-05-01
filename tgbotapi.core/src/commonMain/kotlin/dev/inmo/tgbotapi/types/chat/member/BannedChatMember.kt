package dev.inmo.tgbotapi.types.chat.member

import dev.inmo.tgbotapi.abstracts.types.UntilDate
import kotlinx.serialization.Serializable

@Serializable(ChatMemberSerializer::class)
sealed interface BannedChatMember : ChatMember, UntilDate
