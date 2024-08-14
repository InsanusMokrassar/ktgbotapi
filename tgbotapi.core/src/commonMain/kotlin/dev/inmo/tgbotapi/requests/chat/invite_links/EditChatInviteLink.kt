package dev.inmo.tgbotapi.requests.chat.invite_links

import korlibs.time.DateTime
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.requests.chat.abstracts.*
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*

sealed interface EditChatInviteLink<R : SecondaryChatInviteLink> : EditChatInviteLinkRequest<R>, KnownChatInviteLinkRequest<R> {
    val expirationUnixTimeStamp: TelegramDate?
    override val expireDate: DateTime?
        get() = expirationUnixTimeStamp ?.asDate
    override fun method(): String = "editChatInviteLink"

    sealed interface Subscription : EditChatInviteLink<ChatInviteLinkUnlimited> {
        override fun method(): String = "editChatSubscriptionInviteLink"
    }

    companion object {
        fun unlimited(
            chatId: ChatIdentifier,
            inviteLink: String,
            name: String? = null,
            expirationUnixTimeStamp: TelegramDate? = null,
        ) = EditChatInviteLinkUnlimited(chatId, inviteLink, name, expirationUnixTimeStamp)
        fun withLimitedMembers(
            chatId: ChatIdentifier,
            inviteLink: String,
            membersLimit: MembersLimit,
            name: String? = null,
            expirationUnixTimeStamp: TelegramDate? = null,
        ) = EditChatInviteLinkWithLimitedMembers(chatId, inviteLink, membersLimit, name, expirationUnixTimeStamp)
        fun withJoinRequest(
            chatId: ChatIdentifier,
            inviteLink: String,
            name: String? = null,
            expirationUnixTimeStamp: TelegramDate? = null,
        ) = EditChatInviteLinkWithJoinRequest(chatId, inviteLink, name, expirationUnixTimeStamp)
        fun unlimited(
            chatId: ChatIdentifier,
            inviteLink: String,
            expiration: DateTime,
            name: String? = null,
        ) = unlimited(chatId, inviteLink, name, expiration.toTelegramDate())
        fun withLimitedMembers(
            chatId: ChatIdentifier,
            inviteLink: String,
            membersLimit: MembersLimit,
            expiration: DateTime,
            name: String? = null,
        ) = withLimitedMembers(chatId, inviteLink, membersLimit, name, expiration.toTelegramDate())
        fun withJoinRequest(
            chatId: ChatIdentifier,
            inviteLink: String,
            expiration: DateTime,
            name: String? = null,
        ) = withJoinRequest(chatId, inviteLink, name, expiration.toTelegramDate())
        fun subscription(
            chatId: ChatIdentifier,
            inviteLink: String,
            name: String,
        ) = EditChatSubscriptionInviteLink(chatId, inviteLink, name)
    }
}

/**
 * Represent [https://core.telegram.org/bots/api#editchatinvitelink] request WITHOUT `member_limit`
 * and `creates_join_request`
 *
 * @see EditChatInviteLink.unlimited
 * @see EditChatInviteLinkWithLimitedMembers
 * @see EditChatInviteLinkWithJoinRequest
 */
@Serializable
data class EditChatInviteLinkUnlimited(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(inviteLinkField)
    override val inviteLink: String,
    @SerialName(nameField)
    override val name: String? = null,
    @SerialName(expireDateField)
    override val expirationUnixTimeStamp: TelegramDate? = null,
) : EditChatInviteLink<ChatInviteLinkUnlimited> {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
    override val resultDeserializer: DeserializationStrategy<ChatInviteLinkUnlimited>
        get() = ChatInviteLinkUnlimited.serializer()
}

/**
 * Represent [request](https://core.telegram.org/bots/api#editchatsubscriptioninvitelink)
 *
 * @see EditChatInviteLink.subscription
 */
@Serializable
data class EditChatSubscriptionInviteLink(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(inviteLinkField)
    override val inviteLink: String,
    @SerialName(nameField)
    override val name: String? = null,
) : EditChatInviteLink.Subscription {
    override val expirationUnixTimeStamp: TelegramDate?
        get() = null
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
    override val resultDeserializer: DeserializationStrategy<ChatInviteLinkUnlimited>
        get() = ChatInviteLinkUnlimited.serializer()
}

/**
 * Represent [https://core.telegram.org/bots/api#editchatinvitelink] request WITH `member_limit`
 * and WITHOUT `creates_join_request`
 *
 * @see EditChatInviteLink.withLimitedMembers
 * @see EditChatInviteLinkUnlimited
 * @see EditChatInviteLinkWithJoinRequest
 */
@Serializable
data class EditChatInviteLinkWithLimitedMembers(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(inviteLinkField)
    override val inviteLink: String,
    @SerialName(memberLimitField)
    override val membersLimit: MembersLimit,
    @SerialName(nameField)
    override val name: String? = null,
    @SerialName(expireDateField)
    override val expirationUnixTimeStamp: TelegramDate? = null,
) : EditChatInviteLink<ChatInviteLinkWithLimitedMembers>,
    LimitedMembersChatInviteLinkRequest {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

/**
 * Represent [https://core.telegram.org/bots/api#editchatinvitelink] request WITHOUT `member_limit`
 * and WITH `creates_join_request`
 *
 * @see EditChatInviteLink.withJoinRequest
 * @see EditChatInviteLinkUnlimited
 * @see EditChatInviteLinkWithLimitedMembers
 */
@Serializable
data class EditChatInviteLinkWithJoinRequest(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(inviteLinkField)
    override val inviteLink: String,
    @SerialName(nameField)
    override val name: String? = null,
    @SerialName(expireDateField)
    override val expirationUnixTimeStamp: TelegramDate? = null,
) : EditChatInviteLink<ChatInviteLinkWithJoinRequest>,
    WithJoinRequestChatInviteLinkRequest {
    @Required
    @EncodeDefault
    @SerialName(createsJoinRequestField)
    private val createsJoinRequest: Boolean = true

    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
