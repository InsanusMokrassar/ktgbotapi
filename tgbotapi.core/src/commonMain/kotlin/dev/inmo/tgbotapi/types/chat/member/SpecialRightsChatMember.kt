package dev.inmo.tgbotapi.types.chat.member

import kotlinx.serialization.Serializable

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(ChatMemberSerializer::class)
sealed interface SpecialRightsChatMember : ChatMember, SpecialChatAdministratorRights
