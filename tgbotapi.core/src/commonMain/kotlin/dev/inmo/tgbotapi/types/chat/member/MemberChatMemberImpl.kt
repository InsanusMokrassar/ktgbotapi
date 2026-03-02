@file:OptIn(ExperimentalSerializationApi::class)

package dev.inmo.tgbotapi.types.chat.member

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.PreviewUser
import kotlinx.serialization.*

@Serializable
data class MemberChatMemberImpl(
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(userField)
    override val user: PreviewUser,
    @SerialName(tagField)
    override val tag: UserTag? = null
) : MemberChatMember {
    @SerialName(statusField)
    @Required
    @EncodeDefault
    override val status: ChatMember.Status = ChatMember.Status.Member
}
