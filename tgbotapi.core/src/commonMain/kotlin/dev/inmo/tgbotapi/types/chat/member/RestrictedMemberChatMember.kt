package dev.inmo.tgbotapi.types.chat.member

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.ChatPermissions
import dev.inmo.tgbotapi.types.chat.PreviewUser
import dev.inmo.tgbotapi.types.chat.User
import kotlinx.serialization.*

/**
 * Represents `ChatMemberRestricted` from telegram bots api and means that member is still member of chat but has been
 * restricted in his rights
 */
@Serializable
data class RestrictedMemberChatMember(
    @SerialName(userField)
    override val user: PreviewUser,
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
) : RestrictedChatMember, SpecialRightsChatMember, MemberChatMember, ChatPermissions {
    @SerialName(statusField)
    @Required
    @EncodeDefault
    override val status: ChatMember.Status = ChatMember.Status.Restricted
}
