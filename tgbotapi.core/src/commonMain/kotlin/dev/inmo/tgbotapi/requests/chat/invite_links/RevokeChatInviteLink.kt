package dev.inmo.tgbotapi.requests.chat.invite_links

import dev.inmo.tgbotapi.requests.chat.abstracts.KnownChatInviteLinkRequest
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*

@Serializable
data class RevokeChatInviteLink(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(inviteLinkField)
    override val inviteLink: String,
) : KnownChatInviteLinkRequest<SecondaryChatInviteLink> {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
    override val resultDeserializer: DeserializationStrategy<SecondaryChatInviteLink>
        get() = SecondaryChatInviteLink.serializer()

    override fun method(): String = "revokeChatInviteLink"
}
