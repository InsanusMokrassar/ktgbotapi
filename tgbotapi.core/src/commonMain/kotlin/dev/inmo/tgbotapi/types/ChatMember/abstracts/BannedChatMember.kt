package dev.inmo.tgbotapi.types.ChatMember.abstracts

import dev.inmo.tgbotapi.CommonAbstracts.types.UntilDate
import dev.inmo.tgbotapi.types.ChatMember.ChatMemberSerializer
import kotlinx.serialization.Serializable

@Serializable(ChatMemberSerializer::class)
interface BannedChatMember : ChatMember, UntilDate