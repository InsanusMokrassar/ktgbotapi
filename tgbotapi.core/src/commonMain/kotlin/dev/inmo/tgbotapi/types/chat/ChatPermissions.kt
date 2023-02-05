package dev.inmo.tgbotapi.types.chat

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class ChatPermissions(
    @SerialName(canSendMessagesField)
    val canSendMessages: Boolean? = null,
    @SerialName(canSendAudiosField)
    val canSendAudios: Boolean? = null,
    @SerialName(canSendDocumentsField)
    val canSendDocuments: Boolean? = null,
    @SerialName(canSendPhotosField)
    val canSendPhotos: Boolean? = null,
    @SerialName(canSendVideosField)
    val canSendVideos: Boolean? = null,
    @SerialName(canSendVideoNotesField)
    val canSendVideoNotes: Boolean? = null,
    @SerialName(canSendVoiceNotesField)
    val canSendVoiceNotes: Boolean? = null,
    @SerialName(canSendPollsField)
    val canSendPolls: Boolean? = null,
    @SerialName(canSendOtherMessagesField)
    val canSendOtherMessages: Boolean? = null,
    @SerialName(canAddWebPagePreviewsField)
    val canAddWebPagePreviews: Boolean? = null,
    @SerialName(canChangeInfoField)
    val canChangeInfo: Boolean? = null,
    @SerialName(canInviteUsersField)
    val canInviteUsers: Boolean? = null,
    @SerialName(canPinMessagesField)
    val canPinMessages: Boolean? = null
) {
    @Transient
    val isGranular
        get() = canSendAudios != null ||
            canSendDocuments != null ||
            canSendVideoNotes != null ||
            canSendPhotos != null ||
            canSendVideos != null ||
            canSendVoiceNotes != null

    companion object {
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
    }
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
