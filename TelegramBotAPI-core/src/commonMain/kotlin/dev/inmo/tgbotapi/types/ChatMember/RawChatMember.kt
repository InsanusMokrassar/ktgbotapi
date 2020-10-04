package dev.inmo.tgbotapi.types.ChatMember

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.ChatMember.abstracts.ChatMember
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RawChatMember(
    val user: User,
    private val status: String,
    private val until_date: TelegramDate? = null,
    @SerialName(canBeEditedField)
    private val canBeEdited: Boolean = false,
    @SerialName(canChangeInfoField)
    private val canChangeInfo: Boolean = false,
    @SerialName(canPostMessagesField)
    private val canPostMessages: Boolean = false,
    @SerialName(canEditMessagesField)
    private val canEditMessages: Boolean = false,
    @SerialName(canDeleteMessagesField)
    private val canDeleteMessages: Boolean = false,
    @SerialName(canInviteUsersField)
    private val canInviteUsers: Boolean = false,
    @SerialName(canRestrictMembersField)
    private val canRestrictMembers: Boolean = false,
    @SerialName(canPinMessagesField)
    private val canPinMessages: Boolean = false,
    @SerialName(canPromoteMembersField)
    private val canPromoteMembers: Boolean = false,
    @SerialName(isMemberField)
    private val isMember: Boolean = false,
    @SerialName(canSendMessagesField)
    private val canSendMessages: Boolean = false,
    @SerialName(canSendMediaMessagesField)
    private val canSendMediaMessages: Boolean = false,
    @SerialName(canSendPollsField)
    private val canSendPolls: Boolean = false,
    @SerialName(canSendOtherMessagesField)
    private val canSendOtherMessages: Boolean = false,
    @SerialName(canAddWebPagePreviewsField)
    private val canAddWebPagePreviews: Boolean = false,
    @SerialName(customTitleField)
    private val customTitle: String? = null
) {
    val asChatMember: ChatMember by lazy {
        when (status) {
            "creator" -> CreatorChatMember(user, customTitle)
            "administrator" -> AdministratorChatMemberImpl(
                user,
                canBeEdited,
                canChangeInfo,
                canPostMessages,
                canEditMessages,
                canDeleteMessages,
                canInviteUsers,
                canRestrictMembers,
                canPinMessages,
                canPromoteMembers,
                customTitle
            )
            "member" -> MemberChatMember(user)
            "restricted" -> RestrictedChatMember(
                user,
                until_date,
                isMember,
                canSendMessages,
                canSendMediaMessages,
                canSendPolls,
                canSendOtherMessages,
                canAddWebPagePreviews,
                canChangeInfo,
                canInviteUsers,
                canPinMessages
            )
            "left" -> LeftChatMember(user)
            "kicked" -> KickedChatMember(
                user,
                until_date
            )
            else -> throw IllegalStateException("Can't understand type of user: $status")
        }
    }
}
