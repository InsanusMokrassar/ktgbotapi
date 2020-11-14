package dev.inmo.tgbotapi.types.ChatMember

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.ChatMember.abstracts.ChatMember
import kotlinx.serialization.*

@Serializable
data class MemberChatMember(@SerialName(userField) override val user: User) : ChatMember {
    @SerialName(statusField)
    @Required
    private val type: String = "member"
}
