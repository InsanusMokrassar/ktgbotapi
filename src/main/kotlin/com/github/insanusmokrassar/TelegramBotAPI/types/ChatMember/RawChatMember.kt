package com.github.insanusmokrassar.TelegramBotAPI.types.ChatMember

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.*

@Serializable
data class RawChatMember(
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
    @SerialName(canSendMessagesField)
    private val canSendMessages: Boolean = false,
    @SerialName(canSendMediaMessagesField)
    private val canSendMediaMessages: Boolean = false,
    @SerialName(canSendOtherMessagesField)
    private val canSendOtherMessages: Boolean = false,
    @SerialName(canAddWebPagePreviewsField)
    private val canAddWebPagePreviews: Boolean = false
) {
    @Transient
    val asChatMember: ChatMember by lazy {
        when (status) {
            "creator" -> CreatorChatMember(user)
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
                canPromoteMembers
            )
            "member" -> MemberChatMember(user)
            "restricted" -> RestrictedChatMember(
                user,
                until_date,
                canSendMessages,
                canSendMediaMessages,
                canSendOtherMessages,
                canAddWebPagePreviews
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
