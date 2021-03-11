package dev.inmo.tgbotapi.requests.chat.invite_links

import com.soywiz.klock.DateTime
import dev.inmo.tgbotapi.requests.chat.abstracts.EditChatInviteLinkRequest
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*

@Serializable
data class CreateChatInviteLink(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(expireDateField)
    private val expirationUnixTimeStamp: TelegramDate? = null,
    @SerialName(memberLimitField)
    override val membersLimit: MembersLimit? = null
) : EditChatInviteLinkRequest {
    override val expireDate: DateTime?
        get() = expirationUnixTimeStamp ?.asDate
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String = "createChatInviteLink"
}

fun CreateChatInviteLink(
    chatId: ChatId,
    expireDate: DateTime,
    membersLimit: MembersLimit? = null
): CreateChatInviteLink = CreateChatInviteLink(
    chatId, expireDate.toTelegramDate(), membersLimit
)
