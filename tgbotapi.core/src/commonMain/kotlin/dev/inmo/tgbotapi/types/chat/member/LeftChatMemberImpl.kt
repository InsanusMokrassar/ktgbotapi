package dev.inmo.tgbotapi.types.chat.member

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.utils.internal.ClassCastsExcluded
import kotlinx.serialization.*

@Serializable
@ClassCastsExcluded
data class LeftChatMemberImpl(@SerialName(userField) override val user: User) : LeftChatMember {
    @SerialName(statusField)
    @Required
    private val type: String = "left"
}
