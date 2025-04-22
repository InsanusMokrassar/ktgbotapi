package dev.inmo.tgbotapi.types.chat

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessIntro
import dev.inmo.tgbotapi.types.business_connection.BusinessLocation
import dev.inmo.tgbotapi.types.business_connection.BusinessOpeningHours
import dev.inmo.tgbotapi.types.colors.ColorId
import dev.inmo.tgbotapi.types.gifts.AcceptedGiftTypes
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializeOnlySerializer
import dev.inmo.tgbotapi.types.reactions.Reaction
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
@RiskFeature("This class is a subject of changes. It is better to use ExtendedChannelChat due")
data class ExtendedChannelChatImpl(
    @SerialName(idField)
    override val id: ChatId,
    @SerialName(titleField)
    override val title: String,
    @SerialName(acceptedGiftTypesField)
    override val acceptedGiftTypes: AcceptedGiftTypes,
    @SerialName(usernameField)
    override val username: Username? = null,
    @SerialName(activeUsernamesField)
    override val activeUsernames: List<Username> = emptyList(),
    @SerialName(photoField)
    override val chatPhoto: ChatPhoto? = null,
    @SerialName(descriptionField)
    override val description: String = "",
    @SerialName(inviteLinkField)
    override val inviteLink: String? = null,
    @SerialName(pinnedMessageField)
    @Serializable(TelegramBotAPIMessageDeserializeOnlySerializer::class)
    override val pinnedMessage: Message? = null,
    @SerialName(canSendPaidMediaField)
    override val canSendPaidMedia: Boolean = false,
    @SerialName(linkedChatIdField)
    override val linkedGroupChatId: IdChatIdentifier? = null,
    @SerialName(hasHiddenMembersField)
    override val membersHidden: Boolean = false,
    @SerialName(availableReactionsField)
    override val availableReactions: List<Reaction>? = null,
    @SerialName(emojiStatusCustomEmojiIdField)
    override val statusEmojiId: CustomEmojiId? = null,
    @SerialName(emojiStatusExpirationDateField)
    override val statusEmojiExpiration: TelegramDate? = null,
    @SerialName(accentColorIdField)
    override val accentColorId: ColorId = ColorId(0),
    @SerialName(profileAccentColorIdField)
    override val profileAccentColorId: ColorId? = null,
    @SerialName(backgroundCustomEmojiIdField)
    override val backgroundCustomEmojiId: CustomEmojiId? = null,
    @SerialName(profileBackgroundCustomEmojiIdField)
    override val profileBackgroundCustomEmojiId: CustomEmojiId? = null,
    @SerialName(hasVisibleHistoryField)
    override val newMembersSeeHistory: Boolean = false,
    @SerialName(maxReactionCountField)
    override val maxReactionsCount: Int = 3,
) : ExtendedChannelChat

@Serializable
@RiskFeature("This class is a subject of changes. It is better to use ExtendedGroupChat due")
data class ExtendedGroupChatImpl(
    @SerialName(idField)
    override val id: ChatId,
    @SerialName(titleField)
    override val title: String,
    @SerialName(photoField)
    override val chatPhoto: ChatPhoto? = null,
    @SerialName(permissionsField)
    override val permissions: ChatPermissions,
    @SerialName(descriptionField)
    override val description: String = "",
    @SerialName(inviteLinkField)
    override val inviteLink: String? = null,
    @SerialName(pinnedMessageField)
    @Serializable(TelegramBotAPIMessageDeserializeOnlySerializer::class)
    override val pinnedMessage: Message? = null,
    @SerialName(hasHiddenMembersField)
    override val membersHidden: Boolean = false,
    @SerialName(availableReactionsField)
    override val availableReactions: List<Reaction>? = null,
    @SerialName(emojiStatusCustomEmojiIdField)
    override val statusEmojiId: CustomEmojiId? = null,
    @SerialName(emojiStatusExpirationDateField)
    override val statusEmojiExpiration: TelegramDate? = null,
    @SerialName(accentColorIdField)
    override val accentColorId: ColorId = ColorId(0),
    @SerialName(profileAccentColorIdField)
    override val profileAccentColorId: ColorId? = null,
    @SerialName(backgroundCustomEmojiIdField)
    override val backgroundCustomEmojiId: CustomEmojiId? = null,
    @SerialName(profileBackgroundCustomEmojiIdField)
    override val profileBackgroundCustomEmojiId: CustomEmojiId? = null,
    @SerialName(hasVisibleHistoryField)
    override val newMembersSeeHistory: Boolean = false,
    @SerialName(maxReactionCountField)
    override val maxReactionsCount: Int = 3,
    @SerialName(acceptedGiftTypesField)
    override val acceptedGiftTypes: AcceptedGiftTypes = AcceptedGiftTypes(),
) : ExtendedGroupChat

@Serializable
@RiskFeature("This class is a subject of changes. It is better to use ExtendedPrivateChat due")
data class ExtendedPrivateChatImpl(
    @SerialName(idField)
    override val id: UserId,
    @SerialName(photoField)
    override val chatPhoto: ChatPhoto? = null,
    @SerialName(usernameField)
    override val username: Username? = null,
    @SerialName(activeUsernamesField)
    override val activeUsernames: List<Username> = emptyList(),
    @SerialName(firstNameField)
    override val firstName: String = "",
    @SerialName(lastNameField)
    override val lastName: String = "",
    @SerialName(bioField)
    override val bio: String = "",
    @SerialName(hasPrivateForwardsField)
    override val hasPrivateForwards: Boolean = false,
    @SerialName(hasRestrictedVoiceAndVideoMessagesField)
    override val hasRestrictedVoiceAndVideoMessages: Boolean = false,
    @SerialName(emojiStatusCustomEmojiIdField)
    override val statusEmojiId: CustomEmojiId? = null,
    @SerialName(emojiStatusExpirationDateField)
    override val statusEmojiExpiration: TelegramDate? = null,
    @SerialName(accentColorIdField)
    override val accentColorId: ColorId = ColorId(0),
    @SerialName(profileAccentColorIdField)
    override val profileAccentColorId: ColorId? = null,
    @SerialName(backgroundCustomEmojiIdField)
    override val backgroundCustomEmojiId: CustomEmojiId? = null,
    @SerialName(profileBackgroundCustomEmojiIdField)
    override val profileBackgroundCustomEmojiId: CustomEmojiId? = null,
    @SerialName(businessIntroField)
    override val businessIntro: BusinessIntro? = null,
    @SerialName(businessLocationField)
    override val businessLocation: BusinessLocation? = null,
    @SerialName(businessOpeningHoursField)
    override val businessOpeningHours: BusinessOpeningHours? = null,
    @SerialName(birthdateField)
    override val birthdate: Birthdate? = null,
    @SerialName(personalChatField)
    @Serializable(PreviewChatSerializer::class)
    override val personalChat: PreviewChannelChat? = null,
    @SerialName(maxReactionCountField)
    override val maxReactionsCount: Int = 3,
    @SerialName(acceptedGiftTypesField)
    override val acceptedGiftTypes: AcceptedGiftTypes = AcceptedGiftTypes(),
) : ExtendedPrivateChat

typealias ExtendedUser = ExtendedPrivateChatImpl

@Serializable
@RiskFeature("This class is a subject of changes. It is better to use ExtendedSupergroupChat due")
data class ExtendedSupergroupChatImpl(
    @SerialName(idField)
    override val id: ChatId,
    @SerialName(titleField)
    override val title: String,
    @SerialName(usernameField)
    override val username: Username? = null,
    @SerialName(activeUsernamesField)
    override val activeUsernames: List<Username> = emptyList(),
    @SerialName(photoField)
    override val chatPhoto: ChatPhoto? = null,
    @SerialName(permissionsField)
    override val permissions: ChatPermissions,
    @SerialName(descriptionField)
    override val description: String = "",
    @SerialName(inviteLinkField)
    override val inviteLink: String? = null,
    @SerialName(pinnedMessageField)
    @Serializable(TelegramBotAPIMessageDeserializeOnlySerializer::class)
    override val pinnedMessage: Message? = null,
    @SerialName(stickerSetNameFullField)
    override val stickerSetName: StickerSetName? = null,
    @SerialName(slowModeDelayField)
    override val slowModeDelay: Long? = null,
    @SerialName(canSetStickerSetField)
    override val canSetStickerSet: Boolean = false,
    @SerialName(linkedChatIdField)
    override val linkedChannelChatId: IdChatIdentifier? = null,
    @SerialName(locationField)
    override val location: ChatLocation? = null,
    @SerialName(joinToSendMessagesField)
    override val requiresJoinForMessaging: Boolean = false,
    @SerialName(joinByRequestField)
    override val requireAdminApproveToJoin: Boolean = false,
    @SerialName(hasAggressiveAntiSpamEnabledField)
    override val isAggressiveAntiSpamEnabled: Boolean = false,
    @SerialName(hasHiddenMembersField)
    override val membersHidden: Boolean = false,
    @SerialName(availableReactionsField)
    override val availableReactions: List<Reaction>? = null,
    @SerialName(emojiStatusCustomEmojiIdField)
    override val statusEmojiId: CustomEmojiId? = null,
    @SerialName(emojiStatusExpirationDateField)
    override val statusEmojiExpiration: TelegramDate? = null,
    @SerialName(accentColorIdField)
    override val accentColorId: ColorId = ColorId(0),
    @SerialName(profileAccentColorIdField)
    override val profileAccentColorId: ColorId? = null,
    @SerialName(backgroundCustomEmojiIdField)
    override val backgroundCustomEmojiId: CustomEmojiId? = null,
    @SerialName(profileBackgroundCustomEmojiIdField)
    override val profileBackgroundCustomEmojiId: CustomEmojiId? = null,
    @SerialName(hasVisibleHistoryField)
    override val newMembersSeeHistory: Boolean = false,
    @SerialName(unrestrictBoostsCountField)
    override val unrestrictBoostsCount: Int? = null,
    @SerialName(customEmojiStickerSetNameField)
    override val customEmojiStickerSetName: StickerSetName? = null,
    @SerialName(maxReactionCountField)
    override val maxReactionsCount: Int = 3,
    @SerialName(acceptedGiftTypesField)
    override val acceptedGiftTypes: AcceptedGiftTypes = AcceptedGiftTypes(),
) : ExtendedSupergroupChat

@Serializable
@RiskFeature("This class is a subject of changes. It is better to use ExtendedForumChat due")
data class ExtendedForumChatImpl(
    @SerialName(idField)
    override val id: IdChatIdentifier,
    @SerialName(titleField)
    override val title: String,
    @SerialName(usernameField)
    override val username: Username? = null,
    @SerialName(activeUsernamesField)
    override val activeUsernames: List<Username> = emptyList(),
    @SerialName(photoField)
    override val chatPhoto: ChatPhoto? = null,
    @SerialName(permissionsField)
    override val permissions: ChatPermissions,
    @SerialName(descriptionField)
    override val description: String = "",
    @SerialName(inviteLinkField)
    override val inviteLink: String? = null,
    @SerialName(pinnedMessageField)
    @Serializable(TelegramBotAPIMessageDeserializeOnlySerializer::class)
    override val pinnedMessage: Message? = null,
    @SerialName(stickerSetNameFullField)
    override val stickerSetName: StickerSetName? = null,
    @SerialName(slowModeDelayField)
    override val slowModeDelay: Long? = null,
    @SerialName(canSetStickerSetField)
    override val canSetStickerSet: Boolean = false,
    @SerialName(linkedChatIdField)
    override val linkedChannelChatId: IdChatIdentifier? = null,
    @SerialName(locationField)
    override val location: ChatLocation? = null,
    @SerialName(joinToSendMessagesField)
    override val requiresJoinForMessaging: Boolean = false,
    @SerialName(joinByRequestField)
    override val requireAdminApproveToJoin: Boolean = false,
    @SerialName(hasAggressiveAntiSpamEnabledField)
    override val isAggressiveAntiSpamEnabled: Boolean = false,
    @SerialName(hasHiddenMembersField)
    override val membersHidden: Boolean = false,
    @SerialName(availableReactionsField)
    override val availableReactions: List<Reaction>? = null,
    @SerialName(emojiStatusCustomEmojiIdField)
    override val statusEmojiId: CustomEmojiId? = null,
    @SerialName(emojiStatusExpirationDateField)
    override val statusEmojiExpiration: TelegramDate? = null,
    @SerialName(accentColorIdField)
    override val accentColorId: ColorId = ColorId(0),
    @SerialName(profileAccentColorIdField)
    override val profileAccentColorId: ColorId? = null,
    @SerialName(backgroundCustomEmojiIdField)
    override val backgroundCustomEmojiId: CustomEmojiId? = null,
    @SerialName(profileBackgroundCustomEmojiIdField)
    override val profileBackgroundCustomEmojiId: CustomEmojiId? = null,
    @SerialName(hasVisibleHistoryField)
    override val newMembersSeeHistory: Boolean = false,
    @SerialName(unrestrictBoostsCountField)
    override val unrestrictBoostsCount: Int? = null,
    @SerialName(customEmojiStickerSetNameField)
    override val customEmojiStickerSetName: StickerSetName? = null,
    @SerialName(maxReactionCountField)
    override val maxReactionsCount: Int = 3,
    @SerialName(acceptedGiftTypesField)
    override val acceptedGiftTypes: AcceptedGiftTypes = AcceptedGiftTypes(),
) : ExtendedForumChat

@Serializable
data class ExtendedBot(
    @SerialName(idField)
    override val id: UserId,
    @SerialName(firstNameField)
    override val firstName: String,
    @SerialName(lastNameField)
    override val lastName: String = "",
    @SerialName(usernameField)
    override val username: Username? = null,
    @SerialName(canJoinGroupsField)
    val canJoinGroups: Boolean = false,
    @SerialName(canReadAllGroupMessagesField)
    val canReadAllGroupMessages: Boolean = false,
    @SerialName(supportInlineQueriesField)
    val supportsInlineQueries: Boolean = false,
    @SerialName(canConnectToBusinessField)
    val canConnectToBusiness: Boolean = false,
    @SerialName(photoField)
    override val chatPhoto: ChatPhoto? = null,
    @SerialName(accentColorIdField)
    override val accentColorId: ColorId = ColorId(0),
    @SerialName(profileAccentColorIdField)
    override val profileAccentColorId: ColorId? = null,
    @SerialName(backgroundCustomEmojiIdField)
    override val backgroundCustomEmojiId: CustomEmojiId? = null,
    @SerialName(profileBackgroundCustomEmojiIdField)
    override val profileBackgroundCustomEmojiId: CustomEmojiId? = null,
    @SerialName(maxReactionCountField)
    override val maxReactionsCount: Int = 3,
    @SerialName(hasMainWebAppField)
    val hasMainWebApp: Boolean = false,
    @SerialName(acceptedGiftTypesField)
    override val acceptedGiftTypes: AcceptedGiftTypes = AcceptedGiftTypes(),
) : Bot(), ExtendedChat {
    @SerialName(isBotField)
    private val isBot = true
}

@Serializable
data class ExtendedBusinessChatImpl(
    @SerialName(idField)
    override val id: BusinessChatId,
    @SerialName(originField)
    override val original: ExtendedPrivateChat,
) : ExtendedBusinessChat, ExtendedChat by original

data class UnknownExtendedChat(
    override val id: IdChatIdentifier,
    val raw: String,
    val rawJson: JsonObject,
) : ExtendedChat {
    override val chatPhoto: ChatPhoto? = null

    @SerialName(accentColorIdField)
    override val accentColorId: ColorId = ColorId(0)

    @SerialName(profileAccentColorIdField)
    override val profileAccentColorId: ColorId? = null

    @SerialName(backgroundCustomEmojiIdField)
    override val backgroundCustomEmojiId: CustomEmojiId? = null

    @SerialName(profileBackgroundCustomEmojiIdField)
    override val profileBackgroundCustomEmojiId: CustomEmojiId? = null

    @SerialName(maxReactionCountField)
    override val maxReactionsCount: Int = 3

    @SerialName(acceptedGiftTypesField)
    override val acceptedGiftTypes: AcceptedGiftTypes = AcceptedGiftTypes()
}
