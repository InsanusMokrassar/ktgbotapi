package dev.inmo.tgbotapi.types.chat.member

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.User
import korlibs.time.DateTime
import kotlinx.serialization.*

@Serializable
data class MemberChatMemberImpl(
    @SerialName(userField)
    override val user: User,
    @SerialName(untilDateField)
    @Serializable(TelegramDateSerializer::class)
    override val untilDate: TelegramDate? = null
) : MemberChatMember {
    @SerialName(statusField)
    @Required
    @EncodeDefault
    override val status: ChatMember.Status = ChatMember.Status.Member
}
