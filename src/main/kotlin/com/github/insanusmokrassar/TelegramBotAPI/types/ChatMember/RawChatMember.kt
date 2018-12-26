package com.github.insanusmokrassar.TelegramBotAPI.types.ChatMember

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.*

@Serializable
data class RawChatMember(
    val user: User,
    private val status: String,
    @Optional
    private val until_date: TelegramDate? = null,
    @SerialName(canBeEditedField)
    @Optional
    private val canBeEdited: Boolean = false,
    @SerialName(canChangeInfoField)
    @Optional
    private val canChangeInfo: Boolean = false,
    @SerialName(canPostMessagesField)
    @Optional
    private val canPostMessages: Boolean = false,
    @SerialName(canEditMessagesField)
    @Optional
    private val canEditMessages: Boolean = false,
    @SerialName(canDeleteMessagesField)
    @Optional
    private val canDeleteMessages: Boolean = false,
    @SerialName(canInviteUsersField)
    @Optional
    private val canInviteUsers: Boolean = false,
    @SerialName(canRestrictMembersField)
    @Optional
    private val canRestrictMembers: Boolean = false,
    @SerialName(canPinMessagesField)
    @Optional
    private val canPinMessages: Boolean = false,
    @SerialName(canPromoteMembersField)
    @Optional
    private val canPromoteMembers: Boolean = false,
    @SerialName(canSendMessagesField)
    @Optional
    private val canSendMessages: Boolean = false,
    @SerialName(canSendMediaMessagesField)
    @Optional
    private val canSendMediaMessages: Boolean = false,
    @SerialName(canSendOtherMessagesField)
    @Optional
    private val canSendOtherMessages: Boolean = false,
    @SerialName(canAddWebPagePreviewsField)
    @Optional
    private val canAddWebPagePreviews: Boolean = false
) {
    @Transient
    val asChatMember: ChatMember by lazy {
        when (status) {
            "creator" -> CreatorChatMember(user)
            "administrator" -> AdministratorChatMember(
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
