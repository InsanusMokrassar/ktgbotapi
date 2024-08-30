package dev.inmo.tgbotapi.types.chat.member

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.PreviewUser
import dev.inmo.tgbotapi.types.chat.User
import korlibs.time.DateTime
import kotlinx.serialization.*

@Serializable
data class MemberChatMemberImpl(
    @SerialName(userField)
    override val user: PreviewUser
) : MemberChatMember {
    @SerialName(statusField)
    @Required
    @EncodeDefault
    override val status: ChatMember.Status = ChatMember.Status.Member
}
