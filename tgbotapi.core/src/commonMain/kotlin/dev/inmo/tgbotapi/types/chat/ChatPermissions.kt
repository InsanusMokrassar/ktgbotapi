package dev.inmo.tgbotapi.types.chat

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatPermissions(
    @SerialName(canSendMessagesField)
    val canSendMessages: Boolean = false,
    @SerialName(canSendAudiosField)
    val canSendAudios: Boolean = false,
    @SerialName(canSendDocumentsField)
    val canSendDocuments: Boolean = false,
    @SerialName(canSendPhotosField)
    val canSendPhotos: Boolean = false,
    @SerialName(canSendVideosField)
    val canSendVideos: Boolean = false,
    @SerialName(canSendVideoNotesField)
    val canSendVideoNotes: Boolean = false,
    @SerialName(canSendVoiceNotesField)
    val canSendVoiceNotes: Boolean = false,
    @SerialName(canSendPollsField)
    val canSendPolls: Boolean = false,
    @SerialName(canSendOtherMessagesField)
    val canSendOtherMessages: Boolean = false,
    @SerialName(canAddWebPagePreviewsField)
    val canAddWebPagePreviews: Boolean = false,
    @SerialName(canChangeInfoField)
    val canChangeInfo: Boolean = false,
    @SerialName(canInviteUsersField)
    val canInviteUsers: Boolean = false,
    @SerialName(canPinMessagesField)
    val canPinMessages: Boolean = false
)

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
