package dev.inmo.tgbotapi.types.chat

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Represents any type with common permissions list
 *
 * !!WARNING!! Default serializer of this interface is using [ChatPermissionsImpl] as surrogate and in fact serialized
 * and deserialized as [ChatPermissionsImpl]. In case you wish some custom behaviour you must implement your own
 * [KSerializer] or pass another serializer
 */
@Serializable(ChatPermissions.Companion::class)
interface ChatPermissions {
    val canSendMessages: Boolean?
    val canSendAudios: Boolean?
    val canSendDocuments: Boolean?
    val canSendPhotos: Boolean?
    val canSendVideos: Boolean?
    val canSendVideoNotes: Boolean?
    val canSendVoiceNotes: Boolean?
    val canSendPolls: Boolean?
    val canSendOtherMessages: Boolean?
    val canAddWebPagePreviews: Boolean?
    val canChangeInfo: Boolean?
    val canInviteUsers: Boolean?
    val canPinMessages: Boolean?
    @Transient
    val isGranular
        get() = canSendAudios != null ||
            canSendDocuments != null ||
            canSendVideoNotes != null ||
            canSendPhotos != null ||
            canSendVideos != null ||
            canSendVoiceNotes != null

    companion object : KSerializer<ChatPermissions> {
        operator fun invoke(
            canSendMessages: Boolean? = null,
            canSendAudios: Boolean? = null,
            canSendDocuments: Boolean? = null,
            canSendPhotos: Boolean? = null,
            canSendVideos: Boolean? = null,
            canSendVideoNotes: Boolean? = null,
            canSendVoiceNotes: Boolean? = null,
            canSendPolls: Boolean? = null,
            canSendOtherMessages: Boolean? = null,
            canAddWebPagePreviews: Boolean? = null,
            canChangeInfo: Boolean? = null,
            canInviteUsers: Boolean? = null,
            canPinMessages: Boolean? = null
        ) = ChatPermissionsImpl(
            canSendMessages = canSendMessages,
            canSendAudios = canSendAudios,
            canSendDocuments = canSendDocuments,
            canSendPhotos = canSendPhotos,
            canSendVideos = canSendVideos,
            canSendVideoNotes = canSendVideoNotes,
            canSendVoiceNotes = canSendVoiceNotes,
            canSendPolls = canSendPolls,
            canSendOtherMessages = canSendOtherMessages,
            canAddWebPagePreviews = canAddWebPagePreviews,
            canChangeInfo = canChangeInfo,
            canInviteUsers = canInviteUsers,
            canPinMessages = canPinMessages
        )

        fun Granular(
            canSendMessages: Boolean? = null,
            canSendAudios: Boolean? = null,
            canSendDocuments: Boolean? = null,
            canSendPhotos: Boolean? = null,
            canSendVideos: Boolean? = null,
            canSendVideoNotes: Boolean? = null,
            canSendVoiceNotes: Boolean? = null,
            canSendPolls: Boolean? = null,
            canSendOtherMessages: Boolean? = null,
            canAddWebPagePreviews: Boolean? = null,
            canChangeInfo: Boolean? = null,
            canInviteUsers: Boolean? = null,
            canPinMessages: Boolean? = null
        ) = ChatPermissions(
            canSendMessages = canSendMessages,
            canSendAudios = canSendAudios,
            canSendDocuments = canSendDocuments,
            canSendPhotos = canSendPhotos,
            canSendVideos = canSendVideos,
            canSendVideoNotes = canSendVideoNotes,
            canSendVoiceNotes = canSendVoiceNotes,
            canSendPolls = canSendPolls,
            canSendOtherMessages = canSendOtherMessages,
            canAddWebPagePreviews = canAddWebPagePreviews,
            canChangeInfo = canChangeInfo,
            canInviteUsers = canInviteUsers,
            canPinMessages = canPinMessages
        )

        fun Commonized(
            canSendPolls: Boolean? = null,
            canSendOtherMessages: Boolean? = null,
            canAddWebPagePreviews: Boolean? = null,
            canChangeInfo: Boolean? = null,
            canInviteUsers: Boolean? = null,
            canPinMessages: Boolean? = null
        ) = ChatPermissions(
            canSendMessages = canSendPolls,
            canSendAudios = null,
            canSendDocuments = null,
            canSendPhotos = null,
            canSendVideos = null,
            canSendVideoNotes = null,
            canSendVoiceNotes = null,
            canSendPolls = canSendPolls,
            canSendOtherMessages = canSendOtherMessages,
            canAddWebPagePreviews = canAddWebPagePreviews,
            canChangeInfo = canChangeInfo,
            canInviteUsers = canInviteUsers,
            canPinMessages = canPinMessages
        )

        fun from(
            chatPermissions: ChatPermissions,
            canSendMessages: Boolean? = chatPermissions.canSendMessages,
            canSendAudios: Boolean? = chatPermissions.canSendAudios,
            canSendDocuments: Boolean? = chatPermissions.canSendDocuments,
            canSendPhotos: Boolean? = chatPermissions.canSendPhotos,
            canSendVideos: Boolean? = chatPermissions.canSendVideos,
            canSendVideoNotes: Boolean? = chatPermissions.canSendVideoNotes,
            canSendVoiceNotes: Boolean? = chatPermissions.canSendVoiceNotes,
            canSendPolls: Boolean? = chatPermissions.canSendPolls,
            canSendOtherMessages: Boolean? = chatPermissions.canSendOtherMessages,
            canAddWebPagePreviews: Boolean? = chatPermissions.canAddWebPagePreviews,
            canChangeInfo: Boolean? = chatPermissions.canChangeInfo,
            canInviteUsers: Boolean? = chatPermissions.canInviteUsers,
            canPinMessages: Boolean? = chatPermissions.canPinMessages
        ) = ChatPermissions(
            canSendMessages = canSendMessages,
            canSendAudios = canSendAudios,
            canSendDocuments = canSendDocuments,
            canSendPhotos = canSendPhotos,
            canSendVideos = canSendVideos,
            canSendVideoNotes = canSendVideoNotes,
            canSendVoiceNotes = canSendVoiceNotes,
            canSendPolls = canSendPolls,
            canSendOtherMessages = canSendOtherMessages,
            canAddWebPagePreviews = canAddWebPagePreviews,
            canChangeInfo = canChangeInfo,
            canInviteUsers = canInviteUsers,
            canPinMessages = canPinMessages
        )

        private val realSerializer = ChatPermissionsImpl.serializer()
        override val descriptor: SerialDescriptor
            get() = realSerializer.descriptor

        override fun deserialize(decoder: Decoder): ChatPermissions {
            return realSerializer.deserialize(decoder)
        }

        override fun serialize(encoder: Encoder, value: ChatPermissions) {
            realSerializer.serialize(
                encoder,
                (value as? ChatPermissionsImpl) ?: value.run {
                    ChatPermissionsImpl(
                        canSendMessages = canSendMessages,
                        canSendAudios = canSendAudios,
                        canSendDocuments = canSendDocuments,
                        canSendPhotos = canSendPhotos,
                        canSendVideos = canSendVideos,
                        canSendVideoNotes = canSendVideoNotes,
                        canSendVoiceNotes = canSendVoiceNotes,
                        canSendPolls = canSendPolls,
                        canSendOtherMessages = canSendOtherMessages,
                        canAddWebPagePreviews = canAddWebPagePreviews,
                        canChangeInfo = canChangeInfo,
                        canInviteUsers = canInviteUsers,
                        canPinMessages = canPinMessages
                    )
                }
            )
        }
    }

    /**
     * Copying current instance as [ChatPermissions], but realizations of this interface may differently override this
     * method
     */
    fun defaultCopy(
        canSendMessages: Boolean? = this.canSendMessages,
        canSendAudios: Boolean? = this.canSendAudios,
        canSendDocuments: Boolean? = this.canSendDocuments,
        canSendPhotos: Boolean? = this.canSendPhotos,
        canSendVideos: Boolean? = this.canSendVideos,
        canSendVideoNotes: Boolean? = this.canSendVideoNotes,
        canSendVoiceNotes: Boolean? = this.canSendVoiceNotes,
        canSendPolls: Boolean? = this.canSendPolls,
        canSendOtherMessages: Boolean? = this.canSendOtherMessages,
        canAddWebPagePreviews: Boolean? = this.canAddWebPagePreviews,
        canChangeInfo: Boolean? = this.canChangeInfo,
        canInviteUsers: Boolean? = this.canInviteUsers,
        canPinMessages: Boolean? = this.canPinMessages
    ): ChatPermissions = ChatPermissions(
        canSendMessages = canSendPolls,
        canSendAudios = null,
        canSendDocuments = null,
        canSendPhotos = null,
        canSendVideos = null,
        canSendVideoNotes = null,
        canSendVoiceNotes = null,
        canSendPolls = canSendPolls,
        canSendOtherMessages = canSendOtherMessages,
        canAddWebPagePreviews = canAddWebPagePreviews,
        canChangeInfo = canChangeInfo,
        canInviteUsers = canInviteUsers,
        canPinMessages = canPinMessages
    )
}

val LeftRestrictionsChatPermissions = ChatPermissions(
    canSendMessages = true,
    canSendAudios = true,
    canSendDocuments = true,
    canSendPhotos = true,
    canSendVideos = true,
    canSendVideoNotes = true,
    canSendVoiceNotes = true,
    canSendPolls = true,
    canSendOtherMessages = true,
    canAddWebPagePreviews = true,
    canChangeInfo = true,
    canInviteUsers = true,
    canPinMessages = true,
)

val RestrictionsChatPermissions = ChatPermissions(
    canSendMessages = false,
    canSendAudios = false,
    canSendDocuments = false,
    canSendPhotos = false,
    canSendVideos = false,
    canSendVideoNotes = false,
    canSendVoiceNotes = false,
    canSendPolls = false,
    canSendOtherMessages = false,
    canAddWebPagePreviews = false,
    canChangeInfo = false,
    canInviteUsers = false,
    canPinMessages = false,
)
