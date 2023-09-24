package dev.inmo.tgbotapi.types.chat.member

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.User
import kotlinx.serialization.*

@Serializable
data class LeftChatMemberImpl(@SerialName(userField) override val user: User) : LeftChatMember {
    @SerialName(statusField)
    @Required
    override val status: ChatMember.Status
        get() = ChatMember.Status.Left
}
