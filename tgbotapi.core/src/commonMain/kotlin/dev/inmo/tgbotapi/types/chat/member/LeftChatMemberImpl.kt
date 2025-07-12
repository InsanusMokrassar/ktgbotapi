@file:OptIn(ExperimentalSerializationApi::class)

package dev.inmo.tgbotapi.types.chat.member

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.PreviewUser
import dev.inmo.tgbotapi.types.chat.User
import kotlinx.serialization.*

@Serializable
data class LeftChatMemberImpl(
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(userField)
    override val user: PreviewUser
) : LeftChatMember {
    @SerialName(statusField)
    @Required
    @EncodeDefault
    override val status: ChatMember.Status = ChatMember.Status.Left
}
