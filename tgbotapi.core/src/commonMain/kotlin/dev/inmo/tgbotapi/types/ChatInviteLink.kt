package dev.inmo.tgbotapi.types

import com.soywiz.klock.DateTime
import dev.inmo.tgbotapi.CommonAbstracts.WithUser
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
private data class RawChatInviteLink(
    @SerialName(inviteLinkField)
    val inviteLink: String,
    @SerialName(creatorField)
    val creator: User,
    @SerialName(isPrimaryField)
    val isPrimary: Boolean,
    @SerialName(isRevokedField)
    val isRevoked: Boolean,
    @SerialName(nameField)
    val name: String? = null,
    @SerialName(expireDateField)
    val expirationDateTime: TelegramDate? = null,
    @SerialName(memberLimitField)
    val membersLimit: MembersLimit ?= null,
    @SerialName(createsJoinRequestField)
    val createsJoinRequest: Boolean? = null,
    @SerialName(pendingJoinRequestCountField)
    val pendingJoinRequestCount: MembersLimit ?= null
)

private fun ChatInviteLink.toRawChatInviteLink() = RawChatInviteLink(
    inviteLink,
    creator,
    isPrimary,
    isRevoked,
    (this as? SecondaryChatInviteLink) ?.name,
    expirationDateTime ?.toTelegramDate(),
    (this as? ChatInviteLinkWithLimitedMembers) ?.membersLimit,
    this is ChatInviteLinkWithJoinRequest,
    (this as? ChatInviteLinkWithJoinRequest) ?.leftToReview
)

@Serializable(ChatInviteLinkSerializer::class)
sealed interface ChatInviteLink : WithUser {
    val inviteLink: String
    val creator: User
    val isPrimary: Boolean
        get() = this is PrimaryInviteLink
    val isRevoked: Boolean
    val expirationDateTime: DateTime?
    val name: String?

    override val user: User
        get() = creator

    companion object {
        fun serializer(): KSerializer<ChatInviteLink> = ChatInviteLinkSerializer
    }
}

@Serializable(ChatInviteLinkSerializer::class)
sealed interface SecondaryChatInviteLink : ChatInviteLink {
    override val isPrimary: Boolean
        get() = false

    companion object {
        fun serializer(): KSerializer<SecondaryChatInviteLink> = ChatInviteLinkSerializer as KSerializer<SecondaryChatInviteLink>
    }
}

@Serializable
data class PrimaryInviteLink(
    @SerialName(inviteLinkField)
    override val inviteLink: String,
    @SerialName(creatorField)
    override val creator: User,
    @SerialName(isRevokedField)
    override val isRevoked: Boolean = false,
    @SerialName(expireDateField)
    private val expireDate: TelegramDate? = null,
) : ChatInviteLink {
    override val expirationDateTime: DateTime?
        get() = expireDate ?.asDate
    override val name: String?
        get() = null
}

@Serializable
data class ChatInviteLinkWithJoinRequest(
    @SerialName(inviteLinkField)
    override val inviteLink: String,
    @SerialName(creatorField)
    override val creator: User,
    @SerialName(nameField)
    override val name: String? = null,
    @SerialName(pendingJoinRequestCountField)
    val leftToReview: Int = 0,
    @SerialName(isRevokedField)
    override val isRevoked: Boolean = false,
    @SerialName(expireDateField)
    private val expireDate: TelegramDate? = null
) : SecondaryChatInviteLink {
    override val expirationDateTime: DateTime?
        get() = expireDate ?.asDate
}

@Serializable
data class ChatInviteLinkWithLimitedMembers(
    @SerialName(inviteLinkField)
    override val inviteLink: String,
    @SerialName(creatorField)
    override val creator: User,
    @SerialName(nameField)
    override val name: String? = null,
    @SerialName(memberLimitField)
    val membersLimit: MembersLimit,
    @SerialName(isRevokedField)
    override val isRevoked: Boolean = false,
    @SerialName(expireDateField)
    private val expireDate: TelegramDate? = null,
) : SecondaryChatInviteLink {
    override val expirationDateTime: DateTime?
        get() = expireDate ?.asDate
}

@Serializable
data class ChatInviteLinkUnlimited(
    @SerialName(inviteLinkField)
    override val inviteLink: String,
    @SerialName(creatorField)
    override val creator: User,
    @SerialName(nameField)
    override val name: String? = null,
    @SerialName(isRevokedField)
    override val isRevoked: Boolean = false,
    @SerialName(expireDateField)
    private val expireDate: TelegramDate? = null,
) : SecondaryChatInviteLink {
    override val expirationDateTime: DateTime?
        get() = expireDate ?.asDate
}

@RiskFeature
object ChatInviteLinkSerializer : KSerializer<ChatInviteLink> {
    override val descriptor: SerialDescriptor
        get() = RawChatInviteLink.serializer().descriptor

    override fun deserialize(decoder: Decoder): ChatInviteLink {
        val deserializedRaw = RawChatInviteLink.serializer().deserialize(decoder)
        return deserializedRaw.run {
            when {
                isPrimary -> PrimaryInviteLink(
                    inviteLink, creator, isRevoked, expirationDateTime
                )
                createsJoinRequest == true -> {
                    ChatInviteLinkWithJoinRequest(
                        inviteLink, creator, name, pendingJoinRequestCount ?: 0, isRevoked, expirationDateTime
                    )
                }
                membersLimit != null -> {
                    ChatInviteLinkWithLimitedMembers(
                        inviteLink, creator, name, membersLimit, isRevoked, expirationDateTime
                    )
                }
                else -> ChatInviteLinkUnlimited(
                    inviteLink, creator, name, isRevoked, expirationDateTime
                )
            }
        }
    }

    override fun serialize(encoder: Encoder, value: ChatInviteLink) {
        RawChatInviteLink.serializer().serialize(encoder, value.toRawChatInviteLink())
    }
}
