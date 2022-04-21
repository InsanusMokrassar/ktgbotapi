package dev.inmo.tgbotapi.types.chat.member

import dev.inmo.tgbotapi.types.*
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
    val canSendMessages: Boolean = false,
    @SerialName(canSendMediaMessagesField)
    val canSendMediaMessages: Boolean = false,
    @SerialName(canSendPollsField)
    val canSendPolls: Boolean = false,
    @SerialName(canSendOtherMessagesField)
    val canSendOtherMessages: Boolean = false,
    @SerialName(canAddWebPagePreviewsField)
    val canAddWebpagePreviews: Boolean = false,
    @SerialName(canChangeInfoField)
    override val canChangeInfo: Boolean = false,
    @SerialName(canInviteUsersField)
    override val canInviteUsers: Boolean = false,
    @SerialName(canPinMessagesField)
    override val canPinMessages: Boolean = false
) : BannedChatMember, SpecialRightsChatMember {
    @SerialName(statusField)
    @Required
    private val type: String = "restricted"
}
