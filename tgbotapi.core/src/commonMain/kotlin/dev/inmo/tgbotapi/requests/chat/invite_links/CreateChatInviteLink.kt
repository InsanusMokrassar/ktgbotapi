package dev.inmo.tgbotapi.requests.chat.invite_links

import dev.inmo.tgbotapi.abstracts.types.SubscriptionInfo
import dev.inmo.tgbotapi.requests.chat.abstracts.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.TimeSpanAsSecondsSerializer
import korlibs.time.DateTime
import korlibs.time.TimeSpan
import korlibs.time.days
import kotlinx.serialization.*

sealed interface CreateChatInviteLink<R : SecondaryChatInviteLink> : EditChatInviteLinkRequest<R> {
    val expirationUnixTimeStamp: TelegramDate?
    override val expireDate: DateTime?
        get() = expirationUnixTimeStamp ?.asDate

    override fun method(): String = "createChatInviteLink"

    sealed interface Subscription : CreateChatInviteLink<ChatInviteLinkUnlimited>, SubscriptionInfo {
        override val subscriptionPeriod: TimeSpan
        override val subscriptionPrice: UInt

        override fun method(): String = "createChatSubscriptionInviteLink"
    }

    companion object {
        fun unlimited(
            chatId: ChatIdentifier,
            name: String? = null,
            expirationUnixTimeStamp: TelegramDate? = null,
        ) = CreateChatInviteLinkUnlimited(chatId, name, expirationUnixTimeStamp)

        fun withLimitedMembers(
            chatId: ChatIdentifier,
            membersLimit: MembersLimit,
            name: String? = null,
            expirationUnixTimeStamp: TelegramDate? = null,
        ) = CreateChatInviteLinkWithLimitedMembers(chatId, membersLimit, name, expirationUnixTimeStamp)

        fun withJoinRequest(
            chatId: ChatIdentifier,
            name: String? = null,
            expirationUnixTimeStamp: TelegramDate? = null,
        ) = CreateChatInviteLinkWithJoinRequest(chatId, name, expirationUnixTimeStamp)

        fun unlimited(
            chatId: ChatIdentifier,
            expiration: DateTime,
            name: String? = null,
        ) = unlimited(chatId, name, expiration.toTelegramDate())

        fun withLimitedMembers(
            chatId: ChatIdentifier,
            membersLimit: MembersLimit,
            expiration: DateTime,
            name: String? = null,
        ) = withLimitedMembers(chatId, membersLimit, name, expiration.toTelegramDate())

        fun withJoinRequest(
            chatId: ChatIdentifier,
            expiration: DateTime,
            name: String? = null,
        ) = withJoinRequest(chatId, name, expiration.toTelegramDate())

        fun subscription(
            chatId: ChatIdentifier,
            subscriptionPrice: UInt,
            subscriptionPeriod: TimeSpan = 30.days,
            name: String? = null,
            expirationUnixTimeStamp: TelegramDate? = null,
        ) = CreateChatSubscriptionInviteLink(chatId, subscriptionPrice, name, subscriptionPeriod, expirationUnixTimeStamp)
    }
}

/**
 * Represent [request](https://core.telegram.org/bots/api#createchatinvitelink) WITHOUT `member_limit`
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
    @SerialName(nameField)
    override val name: String? = null,
    @SerialName(expireDateField)
    override val expirationUnixTimeStamp: TelegramDate? = null,
) : CreateChatInviteLink<ChatInviteLinkUnlimited> {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
    override val resultDeserializer: DeserializationStrategy<ChatInviteLinkUnlimited>
        get() = ChatInviteLinkUnlimited.serializer()
}

/**
 * Represent [request](https://core.telegram.org/bots/api#createchatsubscriptioninvitelink)
 *
 * @see CreateChatInviteLink.subscription
 */
@Serializable
data class CreateChatSubscriptionInviteLink(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(subscriptionPriceField)
    override val subscriptionPrice: UInt,
    @SerialName(nameField)
    override val name: String? = null,
    @SerialName(subscriptionPeriodField)
    @EncodeDefault
    @Serializable(TimeSpanAsSecondsSerializer::class)
    override val subscriptionPeriod: TimeSpan = 30.days,
    @SerialName(expireDateField)
    override val expirationUnixTimeStamp: TelegramDate? = null,
) : CreateChatInviteLink.Subscription {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
    override val resultDeserializer: DeserializationStrategy<ChatInviteLinkUnlimited>
        get() = ChatInviteLinkUnlimited.serializer()
}

/**
 * Represent [request](https://core.telegram.org/bots/api#createchatinvitelink) WITH `member_limit`
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
    @SerialName(nameField)
    override val name: String? = null,
    @SerialName(expireDateField)
    override val expirationUnixTimeStamp: TelegramDate? = null,
) : CreateChatInviteLink<ChatInviteLinkWithLimitedMembers>, LimitedMembersChatInviteLinkRequest {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

/**
 * Represent [request](https://core.telegram.org/bots/api#createchatinvitelink) WITHOUT `member_limit`
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
    @SerialName(nameField)
    override val name: String? = null,
    @SerialName(expireDateField)
    override val expirationUnixTimeStamp: TelegramDate? = null,
) : CreateChatInviteLink<ChatInviteLinkWithJoinRequest>, WithJoinRequestChatInviteLinkRequest {
    @Required
    @EncodeDefault
    @SerialName(createsJoinRequestField)
    private val createsJoinRequest: Boolean = true

    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
