package dev.inmo.tgbotapi.types.chat

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class ChatPermissionsImpl(
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
) : ChatPermissions
