package dev.inmo.tgbotapi.types.chat.member

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*

@Serializable
data class MemberChatMemberImpl(@SerialName(userField) override val user: User) : MemberChatMember {
    @SerialName(statusField)
    @Required
    private val type: String = "member"
}
