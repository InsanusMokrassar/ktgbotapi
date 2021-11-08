package dev.inmo.tgbotapi.requests.chat.invite_links

import com.soywiz.klock.DateTime
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.requests.chat.abstracts.*
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*

sealed interface CreateChatInviteLink<R : SecondaryChatInviteLink> : EditChatInviteLinkRequest<R> {
    val expirationUnixTimeStamp: TelegramDate?
    override val expireDate: DateTime?
        get() = expirationUnixTimeStamp ?.asDate
    override fun method(): String = "createChatInviteLink"

    companion object {
        fun unlimited(
            chatId: ChatIdentifier,
            expirationUnixTimeStamp: TelegramDate? = null,
        ) = CreateChatInviteLinkUnlimited(chatId, expirationUnixTimeStamp)
        fun withLimitedMembers(
            chatId: ChatIdentifier,
            membersLimit: MembersLimit,
            expirationUnixTimeStamp: TelegramDate? = null,
        ) = CreateChatInviteLinkWithLimitedMembers(chatId, membersLimit, expirationUnixTimeStamp)
        fun withJoinRequest(
            chatId: ChatIdentifier,
            expirationUnixTimeStamp: TelegramDate? = null,
        ) = CreateChatInviteLinkWithJoinRequest(chatId, expirationUnixTimeStamp)
        fun unlimited(
            chatId: ChatIdentifier,
            expiration: DateTime? = null,
        ) = unlimited(chatId, expiration?.toTelegramDate())
        fun withLimitedMembers(
            chatId: ChatIdentifier,
            membersLimit: MembersLimit,
            expiration: DateTime? = null,
        ) = withLimitedMembers(chatId, membersLimit, expiration?.toTelegramDate())
        fun withJoinRequest(
            chatId: ChatIdentifier,
            expiration: DateTime? = null,
        ) = withJoinRequest(chatId, expiration?.toTelegramDate())
    }
}

/**
 * Represent [https://core.telegram.org/bots/api#createchatinvitelink] request WITHOUT `member_limit`
 * and `creates_join_request`
 *
 * @see CreateChatInviteLink.unlimited
 * @see CreateChatInviteLinkWithLimitedMembers
 * @see CreateChatInviteLinkWithJoinRequest
 */
@Serializable
data class CreateChatInviteLinkUnlimited(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(expireDateField)
    override val expirationUnixTimeStamp: TelegramDate? = null,
) : CreateChatInviteLink<ChatInviteLinkUnlimited> {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
    override val resultDeserializer: DeserializationStrategy<ChatInviteLinkUnlimited>
        get() = ChatInviteLinkUnlimited.serializer()
}

/**
 * Represent [https://core.telegram.org/bots/api#createchatinvitelink] request WITH `member_limit`
 * and WITHOUT `creates_join_request`
 *
 * @see CreateChatInviteLink.withLimitedMembers
 * @see CreateChatInviteLinkUnlimited
 * @see CreateChatInviteLinkWithJoinRequest
 */
@Serializable
data class CreateChatInviteLinkWithLimitedMembers(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(memberLimitField)
    override val membersLimit: MembersLimit,
    @SerialName(expireDateField)
    override val expirationUnixTimeStamp: TelegramDate? = null,
) : CreateChatInviteLink<ChatInviteLinkWithLimitedMembers>, LimitedMembersChatInviteLinkRequest {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

/**
 * Represent [https://core.telegram.org/bots/api#createchatinvitelink] request WITHOUT `member_limit`
 * and WITH `creates_join_request`
 *
 * @see CreateChatInviteLink.withJoinRequest
 * @see CreateChatInviteLinkUnlimited
 * @see CreateChatInviteLinkWithLimitedMembers
 */
@Serializable
data class CreateChatInviteLinkWithJoinRequest(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(expireDateField)
    override val expirationUnixTimeStamp: TelegramDate? = null,
) : CreateChatInviteLink<ChatInviteLinkWithJoinRequest>, WithJoinRequestChatInviteLinkRequest {
    @Required
    @SerialName(createsJoinRequestField)
    private val createsJoinRequest: Boolean = true

    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
