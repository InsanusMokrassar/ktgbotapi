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
import kotlinx.serialization.Serializable

@Serializable(ExtendedChatSerializer.Companion::class)
sealed interface ExtendedChat : Chat {
    val chatPhoto: ChatPhoto?
    val accentColorId: ColorId
    val profileAccentColorId: ColorId?
    val backgroundCustomEmojiId: CustomEmojiId?
    val profileBackgroundCustomEmojiId: CustomEmojiId?
    val maxReactionsCount: Int

    val acceptedGiftTypes: AcceptedGiftTypes

    @Deprecated(
        message =
            "Telegram Bot API v9.0 introduced the new field, `acceptedGiftTypes`, to allow granular" +
                " control over which types of gifts user, bot, or chat can accept.",
        replaceWith =
            ReplaceWith(
                "acceptedGiftTypes.uniqueGifts || acceptedGiftTypes.unlimitedGifts || acceptedGiftTypes.limitedGifts || acceptedGiftTypes.premiumSubscription",
            ),
    )
    val canReceiveGifts: Boolean
        get() =
            acceptedGiftTypes.uniqueGifts ||
                acceptedGiftTypes.unlimitedGifts ||
                acceptedGiftTypes.limitedGifts ||
                acceptedGiftTypes.premiumSubscription
}

@Serializable(ExtendedChatSerializer.Companion::class)
sealed interface ExtendedNonBotChat : ExtendedChat {
    val statusEmojiId: CustomEmojiId?
    val statusEmojiExpiration: TelegramDate?
}

@Serializable(ExtendedChatSerializer.Companion::class)
sealed interface ExtendedChannelChat : ChannelChat, ExtendedPublicChat, ExtendedChatWithUsername {
    val linkedGroupChatId: IdChatIdentifier?
    val canSendPaidMedia: Boolean
}

@Serializable(ExtendedChatSerializer.Companion::class)
sealed interface ExtendedGroupChat : GroupChat, ExtendedPublicChat {
    val permissions: ChatPermissions
}

@Serializable(ExtendedChatSerializer.Companion::class)
sealed interface ExtendedPrivateChat : PrivateChat, ExtendedChatWithUsername, ExtendedNonBotChat {
    val bio: String
    val hasPrivateForwards: Boolean
    val hasRestrictedVoiceAndVideoMessages: Boolean

    val businessIntro: BusinessIntro?
    val businessLocation: BusinessLocation?
    val businessOpeningHours: BusinessOpeningHours?

    val birthdate: Birthdate?
    val personalChat: PreviewChannelChat?

    val allowCreateUserIdLink: Boolean
        get() = hasPrivateForwards
}

sealed interface ExtendedPublicChat : ExtendedChat, PublicChat, ExtendedNonBotChat {
    val description: String
    val inviteLink: String?

    @Serializable(TelegramBotAPIMessageDeserializeOnlySerializer::class)
    val pinnedMessage: Message?
    val membersHidden: Boolean
    val availableReactions: List<Reaction>?
    val newMembersSeeHistory: Boolean
}

@Serializable(ExtendedChatSerializer.Companion::class)
sealed interface ExtendedSupergroupChat : SupergroupChat, ExtendedGroupChat, ExtendedChatWithUsername {
    val slowModeDelay: Long?
    val stickerSetName: StickerSetName?
    val canSetStickerSet: Boolean
    val linkedChannelChatId: IdChatIdentifier?
    val unrestrictBoostsCount: Int?
    val location: ChatLocation?
    val customEmojiStickerSetName: StickerSetName?

    /**
     * This field represents field "join_to_send_messages" from API
     */
    val requiresJoinForMessaging: Boolean

    /**
     * This field represents field "join_by_request" from API
     */
    val requireAdminApproveToJoin: Boolean

    /**
     * This field represents field "has_aggressive_anti_spam_enabled" from API
     */
    val isAggressiveAntiSpamEnabled: Boolean
}

@Serializable(ExtendedChatSerializer.Companion::class)
sealed interface ExtendedForumChat : ExtendedSupergroupChat, ForumChat

@Serializable(ExtendedChatSerializer.Companion::class)
sealed interface ExtendedChatWithUsername : UsernameChat, ExtendedChat {
    val activeUsernames: List<Username>
}

@Serializable(ExtendedChatSerializer.Companion::class)
sealed interface ExtendedBusinessChat : BusinessChat, ExtendedChat {
    override val original: ExtendedPrivateChat
}
