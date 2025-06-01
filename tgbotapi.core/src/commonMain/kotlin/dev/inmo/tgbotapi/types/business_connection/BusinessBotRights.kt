package dev.inmo.tgbotapi.types.business_connection

import dev.inmo.tgbotapi.types.canChangeGiftSettingsField
import dev.inmo.tgbotapi.types.canConvertGiftsToStarsField
import dev.inmo.tgbotapi.types.canDeleteAllMessagesField
import dev.inmo.tgbotapi.types.canDeleteOutgoingMessagesField
import dev.inmo.tgbotapi.types.canEditBioField
import dev.inmo.tgbotapi.types.canEditNameField
import dev.inmo.tgbotapi.types.canEditProfilePhotoField
import dev.inmo.tgbotapi.types.canEditUsernameField
import dev.inmo.tgbotapi.types.canManageStoriesField
import dev.inmo.tgbotapi.types.canReadMessagesField
import dev.inmo.tgbotapi.types.canReplyField
import dev.inmo.tgbotapi.types.canTransferAndUpgradeGiftsField
import dev.inmo.tgbotapi.types.canTransferStarsField
import dev.inmo.tgbotapi.types.canViewGiftsAndStarsField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BusinessBotRights(
    @SerialName(canReplyField)
    val canReply: Boolean = false,
    @SerialName(canReadMessagesField)
    val canMarkMessagesAsRead: Boolean = false,
    @SerialName(canDeleteOutgoingMessagesField)
    val canDeleteOutgoingMessages: Boolean = false,
    @SerialName(canDeleteAllMessagesField)
    val canDeleteAllMessages: Boolean = false,
    @SerialName(canEditNameField)
    val canEditName: Boolean = false,
    @SerialName(canEditBioField)
    val canEditBio: Boolean = false,
    @SerialName(canEditProfilePhotoField)
    val canEditProfilePhoto: Boolean = false,
    @SerialName(canEditUsernameField)
    val canEditUsername: Boolean = false,
    @SerialName(canChangeGiftSettingsField)
    val canChangeGiftSettings: Boolean = false,
    @SerialName(canViewGiftsAndStarsField)
    val canViewGiftsAndStars: Boolean = false,
    @SerialName(canConvertGiftsToStarsField)
    val canConvertGiftsToStars: Boolean = false,
    @SerialName(canTransferAndUpgradeGiftsField)
    val canTransferAndUpgradeGifts: Boolean = false,
    @SerialName(canTransferStarsField)
    val canTransferStars: Boolean = false,
    @SerialName(canManageStoriesField)
    val canManageStories: Boolean = false,
)
