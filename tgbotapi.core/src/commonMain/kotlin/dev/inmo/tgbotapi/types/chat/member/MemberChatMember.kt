package dev.inmo.tgbotapi.types.chat.member

import dev.inmo.tgbotapi.abstracts.OptionallyTagged
import kotlinx.serialization.Serializable

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(ChatMemberSerializer::class)
sealed interface MemberChatMember : ChatMember, OptionallyTagged
