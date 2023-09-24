package dev.inmo.tgbotapi.types.chat.member

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.ChatPermissions
import dev.inmo.tgbotapi.types.chat.User
import kotlinx.serialization.*

@Serializable
data class RestrictedChatMember(
    @SerialName(userField)
    override val user: User,
    @SerialName(untilDateField)
    override val untilDate: TelegramDate? = null,
    @SerialName(isMemberField)
    val isMember: Boolean = false,
    @SerialName(canSendMessagesField)
    override val canSendMessages: Boolean = false,
    @SerialName(canSendAudiosField)
    override val canSendAudios: Boolean = false,
    @SerialName(canSendDocumentsField)
    override val canSendDocuments: Boolean = false,
    @SerialName(canSendPhotosField)
    override val canSendPhotos: Boolean = false,
    @SerialName(canSendVideosField)
    override val canSendVideos: Boolean = false,
    @SerialName(canSendVideoNotesField)
    override val canSendVideoNotes: Boolean = false,
    @SerialName(canSendVoiceNotesField)
    override val canSendVoiceNotes: Boolean = false,
    @SerialName(canSendPollsField)
    override val canSendPolls: Boolean = false,
    @SerialName(canSendOtherMessagesField)
    override val canSendOtherMessages: Boolean = false,
    @SerialName(canAddWebPagePreviewsField)
    override val canAddWebPagePreviews: Boolean = false,
    @SerialName(canChangeInfoField)
    override val canChangeInfo: Boolean = false,
    @SerialName(canInviteUsersField)
    override val canInviteUsers: Boolean = false,
    @SerialName(canPinMessagesField)
    override val canPinMessages: Boolean = false,
    @SerialName(canManageTopicsField)
    override val canManageTopics: Boolean = false
) : BannedChatMember, SpecialRightsChatMember, ChatPermissions {
    @SerialName(statusField)
    @Required
    override val status: ChatMember.Status
        get() = ChatMember.Status.Restricted
}
