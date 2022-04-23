package dev.inmo.tgbotapi.types.ChatMember.abstracts

import kotlinx.serialization.Serializable

@Serializable(ChatMemberSerializer::class)
interface SpecialRightsChatMember : ChatMember, SpecialChatAdministratorRights
