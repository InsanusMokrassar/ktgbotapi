package dev.inmo.tgbotapi.requests.chat.invite_links

import com.soywiz.klock.*
import dev.inmo.tgbotapi.requests.chat.abstracts.EditChatInviteLinkRequest
import dev.inmo.tgbotapi.requests.chat.abstracts.KnownChatInviteLinkRequest
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*

@Serializable
data class EditChatInviteLink(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(inviteLinkField)
    override val inviteLink: String,
    @SerialName(expireDateField)
    private val expirationUnixTimeStamp: TelegramDate? = null,
    @SerialName(memberLimitField)
    override val membersLimit: MembersLimit? = null
) : EditChatInviteLinkRequest, KnownChatInviteLinkRequest {
    override val expireDate: DateTime?
        get() = expirationUnixTimeStamp ?.asDate
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String = "editChatInviteLink"
}

fun EditChatInviteLink(
    chatId: ChatIdentifier,
    inviteLink: String,
    expireDate: DateTime,
    membersLimit: MembersLimit? = null
): EditChatInviteLink = EditChatInviteLink(
    chatId, inviteLink, expireDate.toTelegramDate(), membersLimit
)
