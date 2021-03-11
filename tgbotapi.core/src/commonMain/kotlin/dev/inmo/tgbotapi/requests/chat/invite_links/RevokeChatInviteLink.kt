package dev.inmo.tgbotapi.requests.chat.invite_links

import com.soywiz.klock.*
import dev.inmo.tgbotapi.requests.chat.abstracts.EditChatInviteLinkRequest
import dev.inmo.tgbotapi.requests.chat.abstracts.KnownChatInviteLinkRequest
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*

@Serializable
data class RevokeChatInviteLink(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(inviteLinkField)
    override val inviteLink: String
) : KnownChatInviteLinkRequest {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String = "revokeChatInviteLink"
}
