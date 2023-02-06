package dev.inmo.tgbotapi.types.chat

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Represents any type with common permissions list
 *
 * !!WARNING!! Default serializer of this interface is using [Granular] as surrogate and in fact serialized
 * and deserialized as [Granular]. In case you wish some custom behaviour you must implement your own
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
    val canSendStickers: Boolean?
        get() = canSendOtherMessages
    val canSendGifs: Boolean?
        get() = canSendStickers

    @Serializable
    data class Granular(
        @SerialName(canSendMessagesField)
        override val canSendMessages: Boolean? = null,
        @SerialName(canSendAudiosField)
        override val canSendAudios: Boolean? = null,
        @SerialName(canSendDocumentsField)
        override val canSendDocuments: Boolean? = null,
        @SerialName(canSendPhotosField)
        override val canSendPhotos: Boolean? = null,
        @SerialName(canSendVideosField)
        override val canSendVideos: Boolean? = null,
        @SerialName(canSendVideoNotesField)
        override val canSendVideoNotes: Boolean? = null,
        @SerialName(canSendVoiceNotesField)
        override val canSendVoiceNotes: Boolean? = null,
        @SerialName(canSendPollsField)
        override val canSendPolls: Boolean? = null,
        @SerialName(canSendOtherMessagesField)
        override val canSendOtherMessages: Boolean? = null,
        @SerialName(canAddWebPagePreviewsField)
        override val canAddWebPagePreviews: Boolean? = null,
        @SerialName(canChangeInfoField)
        override val canChangeInfo: Boolean? = null,
        @SerialName(canInviteUsersField)
        override val canInviteUsers: Boolean? = null,
        @SerialName(canPinMessagesField)
        override val canPinMessages: Boolean? = null
    ) : ChatPermissions {
        @Transient
        override val isGranular: Boolean
            get() = true
    }

    @Serializable
    data class Common(
        @SerialName(canSendPollsField)
        override val canSendPolls: Boolean? = null,
        @SerialName(canSendOtherMessagesField)
        override val canSendOtherMessages: Boolean? = null,
        @SerialName(canAddWebPagePreviewsField)
        override val canAddWebPagePreviews: Boolean? = null,
        @SerialName(canChangeInfoField)
        override val canChangeInfo: Boolean? = null,
        @SerialName(canInviteUsersField)
        override val canInviteUsers: Boolean? = null,
        @SerialName(canPinMessagesField)
        override val canPinMessages: Boolean? = null
    ) : ChatPermissions {
        @Transient
        override val isGranular: Boolean
            get() = false
        @Transient
        override val canSendMessages: Boolean? = canSendOtherMessages ?.let {
            it && (canAddWebPagePreviews ?: return@let null)
        }
        @Transient
        override val canSendAudios: Boolean?
            get() = canSendMessages
        @Transient
        override val canSendDocuments: Boolean?
            get() = canSendMessages
        @Transient
        override val canSendPhotos: Boolean?
            get() = canSendMessages
        @Transient
        override val canSendVideos: Boolean?
            get() = canSendMessages
        @Transient
        override val canSendVideoNotes: Boolean?
            get() = canSendMessages
        @Transient
        override val canSendVoiceNotes: Boolean?
            get() = canSendMessages
    }

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
        ) = Granular(
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

        private val realSerializer = Granular.serializer()
        private val commonSerializer = Common.serializer()
        override val descriptor: SerialDescriptor
            get() = realSerializer.descriptor

        override fun deserialize(decoder: Decoder): ChatPermissions {
            return realSerializer.deserialize(decoder)
        }

        override fun serialize(encoder: Encoder, value: ChatPermissions) {
            realSerializer.serialize(
                encoder,
                (value as? Granular) ?: value.run {
                    Granular(
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
    fun copyGranular(
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

    /**
     * Copying current instance as [ChatPermissions], but realizations of this interface may differently override this
     * method
     */
    fun copyCommon(
        canSendPolls: Boolean? = this.canSendPolls,
        canSendOtherMessages: Boolean? = this.canSendOtherMessages,
        canAddWebPagePreviews: Boolean? = this.canAddWebPagePreviews,
        canChangeInfo: Boolean? = this.canChangeInfo,
        canInviteUsers: Boolean? = this.canInviteUsers,
        canPinMessages: Boolean? = this.canPinMessages
    ): ChatPermissions = ChatPermissions(
        canSendMessages = null,
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
