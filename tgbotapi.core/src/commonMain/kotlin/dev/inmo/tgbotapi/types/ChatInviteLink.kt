package dev.inmo.tgbotapi.types

import com.soywiz.klock.DateTime
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
    @SerialName(expireDateField)
    val expirationDateTime: TelegramDate? = null,
    @SerialName(memberLimitField)
    val membersLimit: MembersLimit ?= null
)

private fun ChatInviteLink.toRawChatInviteLink() = RawChatInviteLink(
    inviteLink,
    creator,
    isPrimary,
    isRevoked,
    expirationDateTime ?.toTelegramDate(),
    membersLimit
)

@Serializable(ChatInviteLinkSerializer::class)
sealed class ChatInviteLink {
    abstract val inviteLink: String
    abstract val creator: User
    abstract val isPrimary: Boolean
    abstract val isRevoked: Boolean
    abstract val expirationDateTime: DateTime?
    abstract val membersLimit: MembersLimit?
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
    @SerialName(memberLimitField)
    override val membersLimit: MembersLimit? = null
) : ChatInviteLink() {
    override val isPrimary: Boolean
        get() = true
    override val expirationDateTime: DateTime?
        get() = expireDate ?.asDate
}

@Serializable
data class CommonInviteLink(
    @SerialName(inviteLinkField)
    override val inviteLink: String,
    @SerialName(creatorField)
    override val creator: User,
    @SerialName(isRevokedField)
    override val isRevoked: Boolean = false,
    @SerialName(expireDateField)
    private val expireDate: TelegramDate? = null,
    @SerialName(memberLimitField)
    override val membersLimit: MembersLimit? = null
) : ChatInviteLink() {
    override val isPrimary: Boolean
        get() = false
    override val expirationDateTime: DateTime?
        get() = expireDate ?.asDate
}

@Serializer(ChatInviteLink::class)
object ChatInviteLinkSerializer : KSerializer<ChatInviteLink> {
    override val descriptor: SerialDescriptor
        get() = RawChatInviteLink.serializer().descriptor

    override fun deserialize(decoder: Decoder): ChatInviteLink {
        val deserializedRaw = RawChatInviteLink.serializer().deserialize(decoder)
        return deserializedRaw.run {
            when {
                deserializedRaw.isPrimary -> PrimaryInviteLink(
                    inviteLink, creator, isRevoked, expirationDateTime, membersLimit
                )
                else -> CommonInviteLink(
                    inviteLink, creator, isRevoked, expirationDateTime, membersLimit
                )
            }
        }
    }

    override fun serialize(encoder: Encoder, value: ChatInviteLink) {
        RawChatInviteLink.serializer().serialize(encoder, value.toRawChatInviteLink())
    }
}
