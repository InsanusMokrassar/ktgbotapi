package dev.inmo.tgbotapi.types.chat

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatPermissions(
    @SerialName(canSendMessagesField)
    val canSendMessages: Boolean = false,
    @SerialName(canSendMediaMessagesField)
    val canSendMediaMessages: Boolean = false,
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
