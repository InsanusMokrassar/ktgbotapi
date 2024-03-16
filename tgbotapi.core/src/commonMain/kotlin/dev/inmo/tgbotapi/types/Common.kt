package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.utils.BuiltinMimeTypes
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.jvm.JvmInline

typealias Identifier = Long
typealias MessageIdentifier = MessageId
typealias UpdateIdentifier = Long
typealias MediaGroupIdentifier = String
typealias ForwardSignature = String
typealias ForwardSenderName = String
typealias AuthorSignature = ForwardSignature
typealias CallbackQueryIdentifier = String
typealias PaymentQueryIdentifier = String
typealias PreCheckoutQueryId = String
typealias ShippingQueryIdentifier = String
typealias InvoicePayload = String
typealias ShippingOptionIdentifier = String
typealias StartParameter = String
typealias InlineMessageIdentifier = String
typealias PollIdentifier = String
typealias StickerSetName = String
typealias FileUniqueId = String
typealias DiceResult = Int
typealias FoursquareId = String
typealias FoursquareType = String
typealias GooglePlaceId = String
typealias GooglePlaceType = String
typealias MembersLimit = Int
typealias WebAppQueryId = String
@Serializable
@JvmInline
value class CustomEmojiId(
    val string: String
) {
    val appLink
        get() = "${internalTgAppLinksBeginning}emoji?id=$this"
}
@Serializable
@JvmInline
value class StoryId(
    val long: Long
)

typealias Seconds = Int
typealias MilliSeconds = Long
typealias LongSeconds = Long
typealias UnixTimeStamp = LongSeconds

typealias Meters = Float
typealias Degrees = Int

@Serializable(StickerType.Serializer::class)
sealed interface StickerType {
    val type: String

    @Serializable
    object Regular : StickerType { override val type: String = "regular" }
    @Serializable
    object Mask : StickerType { override val type: String = "mask" }
    @Serializable
    object CustomEmoji : StickerType { override val type: String = "custom_emoji" }
    @Serializable
    data class Unknown(override val type: String = "custom_emoji") : StickerType

    object Serializer : KSerializer<StickerType> {
        override val descriptor: SerialDescriptor = String.serializer().descriptor

        override fun deserialize(decoder: Decoder): StickerType {
            return when (val type = decoder.decodeString()) {
                Regular.type -> Regular
                Mask.type -> Mask
                CustomEmoji.type -> CustomEmoji
                else -> Unknown(type)
            }
        }

        override fun serialize(encoder: Encoder, value: StickerType) {
            encoder.encodeString(value.type)
        }

    }
}

@Serializable(StickerFormat.Serializer::class)
sealed interface StickerFormat {
    val type: String

    @Serializable
    object Static : StickerFormat { override val type: String = "static" }
    @Serializable
    object Animated : StickerFormat { override val type: String = "animated" }
    @Serializable
    object Video : StickerFormat { override val type: String = "video" }
    @Serializable
    data class Unknown(override val type: String = "custom_emoji") : StickerFormat

    object Serializer : KSerializer<StickerFormat> {
        override val descriptor: SerialDescriptor = String.serializer().descriptor

        override fun deserialize(decoder: Decoder): StickerFormat {
            return when (val type = decoder.decodeString()) {
                Static.type -> Static
                Animated.type -> Animated
                Video.type -> Video
                else -> Unknown(type)
            }
        }

        override fun serialize(encoder: Encoder, value: StickerFormat) {
            encoder.encodeString(value.type)
        }

    }
}

val usernameRegex = Regex("@[\\w\\d_]+")

val degreesLimit = 1 .. 360
val horizontalAccuracyLimit = 0F .. 1500F

val getUpdatesLimit = 1 .. 100
val callbackQueryAnswerLength = 0 until 200
val captionLength = 0 .. 1024
val textLength = 1 .. 4096
val userProfilePhotosRequestLimit = 0 .. 100
val chatTitleLength = 1 until 255
val threadNameLength = 1 until 128
val chatDescriptionLength = 0 until 256
val inlineResultQueryIdLingth = 1 until 64
val allowedConnectionsLength = 1 .. 100

val invoiceTitleLimit = 1 until 32
val invoiceDescriptionLimit = 1 until 256
val invoicePayloadBytesLimit = 1 until 128

val pollOptionTextLength = 1 .. 100
val pollQuestionTextLength = 1 .. 300
val pollOptionsLimit = 2 .. 10

val livePeriodLimit = 60 .. 86400

val inlineQueryAnswerResultsLimit = 0 .. 50

val customTitleLength = 0 .. 16

val dartsCubeAndBowlingDiceResultLimit = 1 .. 6
val basketballAndFootballDiceResultLimit = 1 .. 5
val slotMachineDiceResultLimit = 1 .. 64

val botCommandLengthLimit = 1 .. 32
val botCommandLimit = botCommandLengthLimit
val botCommandDescriptionLimit = 3 .. 256
val botCommandsLimit = 0 .. 100

val mediaCountInMediaGroup: IntRange = 2 .. 10

val explanationLimit = 0 .. 200

val openPeriodPollSecondsLimit = 5 .. 600

val membersLimit = 1 .. 99999

val suggestedTipAmountsLimit = 1 .. 4

val inputFieldPlaceholderLimit = 1 .. 64

val emojisInStickerLimit = 1 .. 20

val keywordsInStickerLimit = 0 .. 20

val stickerKeywordLengthLimit = 0 .. 64

val keyboardButtonRequestUserLimit = 1 .. 10

val forwardMessagesLimit = 1 .. 100
val copyMessagesLimit = forwardMessagesLimit
val deleteMessagesLimit = forwardMessagesLimit

const val botActionActualityTime: Seconds = 5

val cloudStorageKeyLimit = 1 .. 128
val cloudStorageValueLimit = 0 .. 4096
val cloudStorageKeyRegex = Regex("[A-Za-z0-9_-]{${cloudStorageKeyLimit.first},${cloudStorageKeyLimit.last}}")
val cloudStorageValueRegex = Regex(".{${cloudStorageValueLimit.first},${cloudStorageValueLimit.last}}")

// Made as lazy for correct work in K/JS
val telegramInlineModeGifPermittedMimeTypes by lazy {
    listOf(
        BuiltinMimeTypes.Image.Jpg,
        BuiltinMimeTypes.Image.Gif,
        BuiltinMimeTypes.Video.MP4
    )
}

const val tgWebAppStartParamField = "tgWebAppStartParam"

const val chatIdField = "chat_id"
const val senderChatIdField = "sender_chat_id"
const val senderChatField = "sender_chat"
const val authorSignatureField = "author_signature"
const val senderUserField = "sender_user"
const val senderUserNameField = "sender_user_name"
const val messageIdField = "message_id"
const val giveawayMessageIdField = "giveaway_message_id"
const val messageIdsField = "message_ids"
const val actorChatField = "actor_chat"
const val messageThreadIdField = "message_thread_id"
const val mediaGroupIdField = "media_group_id"
const val updateIdField = "update_id"
const val fromChatIdField = "from_chat_id"
const val disableWebPagePreviewField = "disable_web_page_preview"
const val linkPreviewOptionsField = "link_preview_options"
const val disableNotificationField = "disable_notification"
const val protectContentField = "protect_content"
const val removeCaptionField = "remove_caption"
const val replyToMessageIdField = "reply_to_message_id"
const val replyParametersField = "reply_parameters"
const val allowSendingWithoutReplyField = "allow_sending_without_reply"
const val quoteField = "quote"
const val quoteParseModeField = "quote_parse_mode"
const val quoteEntitiesField = "quote_entities"
const val quotePositionField = "quote_position"
const val replyMarkupField = "reply_markup"
const val disableContentTypeDetectionField = "disable_content_type_detection"
const val supportStreamingField = "support_streaming"
const val livePeriodField = "live_period"
const val proximityAlertRadiusField = "proximity_alert_radius"
const val isBotField = "is_bot"
const val firstNameField = "first_name"
const val lastNameField = "last_name"
const val languageCodeField = "language_code"
const val addedToAttachmentMenuField = "added_to_attachment_menu"
const val allowsWriteToPMField = "allows_write_to_pm"
const val isPremiumField = "is_premium"
const val hasPrivateForwardsField = "has_private_forwards"
const val hasRestrictedVoiceAndVideoMessagesField = "has_restricted_voice_and_video_messages"
const val emojiStatusCustomEmojiIdField = "emoji_status_custom_emoji_id"
const val emojiStatusExpirationDateField = "emoji_status_expiration_date"
const val accentColorIdField = "accent_color_id"
const val profileAccentColorIdField = "profile_accent_color_id"
const val backgroundCustomEmojiIdField = "background_custom_emoji_id"
const val profileBackgroundCustomEmojiIdField = "profile_background_custom_emoji_id"
const val hasVisibleHistoryField = "has_visible_history"
const val unrestrictBoostsCountField = "unrestrict_boost_count"
const val customEmojiStickerSetNameField = "custom_emoji_sticker_set_name"
const val iconCustomEmojiIdField = "icon_custom_emoji_id"
const val canJoinGroupsField = "can_join_groups"
const val canReadAllGroupMessagesField = "can_read_all_group_messages"
const val supportInlineQueriesField = "supports_inline_queries"
const val textEntitiesField = "text_entities"
const val entitiesField = "entities"
const val stickerSetNameField = "set_name"
const val customEmojiIdField = "custom_emoji_id"
const val customEmojiField = "custom_emoji"
const val customEmojiIdsField = "custom_emoji_ids"
const val premiumAnimationField = "premium_animation"
const val stickerSetNameFullField = "sticker_set_name"
const val slowModeDelayField = "slow_mode_delay"
const val maskPositionField = "mask_position"
const val phoneNumberField = "phone_number"
const val userIdField = "user_id"
const val userIdsField = "user_ids"
const val onlyIfBannedField = "only_if_banned"
const val containsMasksField = "contains_masks"
const val resultIdField = "result_id"
const val inlineMessageIdField = "inline_message_id"
const val callbackDataField = "callback_data"
const val callbackGameField = "callback_game"
const val callbackQueryIdField = "callback_query_id"
const val webAppQueryIdField = "web_app_query_id"
const val inlineQueryIdField = "inline_query_id"
const val inlineKeyboardField = "inline_keyboard"
const val showAlertField = "show_alert"
const val cacheTimeField = "cache_time"
const val foursquareIdField = "foursquare_id"
const val foursquareTypeField = "foursquare_type"
const val googlePlaceIdField = "google_place_id"
const val googlePlaceTypeField = "google_place_type"
const val untilDateField = "until_date"
const val errorMessageField = "error_message"
const val messageTextField = "message_text"
const val isPersonalField = "is_personal"
const val nextOffsetField = "next_offset"
const val buttonField = "button"
const val switchPmTextField = "switch_pm_text"
const val switchPmParameterField = "switch_pm_parameter"
const val maxAllowedConnectionsField = "max_connections"
const val allowedUpdatesField = "allowed_updates"
const val allowUserChatsField = "allow_user_chats"
const val allowBotChatsField = "allow_bot_chats"
const val allowGroupChatsField = "allow_group_chats"
const val allowChannelChatsField = "allow_channel_chats"
const val dropPendingUpdatesField = "drop_pending_updates"
const val secretTokenField = "secret_token"
const val hasCustomCertificateField = "has_custom_certificate"
const val pendingUpdateCountField = "pending_update_count"
const val lastErrorDateField = "last_error_date"
const val lastSynchronizationErrorDateField = "last_synchronization_error_date"
const val lastErrorMessageField = "last_error_message"
const val votesCountField = "voter_count"
const val isClosedField = "is_closed"
const val totalVoterCountField = "total_voter_count"
const val correctOptionIdField = "correct_option_id"
const val allowsMultipleAnswersField = "allows_multiple_answers"
const val isAnonymousField = "is_anonymous"
const val canManageTopicsField = "can_manage_topics"
const val canPostStoriesField = "can_post_stories"
const val canEditStoriesField = "can_edit_stories"
const val canDeleteStoriesField = "can_delete_stories"
const val captionEntitiesField = "caption_entities"
const val hasSpoilerField = "has_spoiler"
const val loginUrlField = "login_url"
const val forwardTextField = "forward_text"
const val botUsernameField = "bot_username"
const val switchInlineQueryCurrentChatField = "switch_inline_query_current_chat"
const val switchInlineQueryChosenChatField = "switch_inline_query_chosen_chat"
const val switchInlineQueryField = "switch_inline_query"
const val isAnimatedField = "is_animated"
const val isVideoField = "is_video"
const val inviteLinkField = "invite_link"
const val viaChatFolderInviteLinkField = "via_chat_folder_invite_link"
const val pinnedMessageField = "pinned_message"
const val activeUsernamesField = "active_usernames"
const val customTitleField = "custom_title"
const val optionIdsField = "option_ids"
const val voterChatField = "voter_chat"
const val ipAddressField = "ip_address"
const val linkedChatIdField = "linked_chat_id"
const val hasHiddenMembersField = "has_hidden_members"
const val joinToSendMessagesField = "join_to_send_messages"
const val joinByRequestField = "join_by_request"
const val hasAggressiveAntiSpamEnabledField = "has_aggressive_anti_spam_enabled"
const val horizontalAccuracyField = "horizontal_accuracy"
const val revokeMessagesField = "revoke_messages"
const val messageAutoDeleteTimeField = "message_auto_delete_time"
const val isPrimaryField = "is_primary"
const val isRevokedField = "is_revoked"
const val expireDateField = "expire_date"
const val createsJoinRequestField = "creates_join_request"
const val pendingJoinRequestCountField = "pending_join_request_count"
const val memberLimitField = "member_limit"
const val iconColorField = "icon_color"
const val emojiListField = "emoji_list"

const val requestContactField = "request_contact"
const val requestLocationField = "request_location"
const val requestPollField = "request_poll"
const val requestUserField = "request_user"
const val requestUsersField = "request_users"
const val requestChatField = "request_chat"
const val requestIdField = "request_id"
const val maxQuantityField = "max_quantity"

const val userIsBotField = "user_is_bot"
const val userIsPremiumField = "user_is_premium"
const val chatIsChannelField = "chat_is_channel"
const val chatIsForumField = "chat_is_forum"
const val chatHasUsernameField = "chat_has_username"
const val chatIsCreatedField = "chat_is_created"
const val userAdministratorRightsField = "user_administrator_rights"
const val botAdministratorRightsField = "bot_administrator_rights"
const val botIsMemberField = "bot_is_member"

const val fileNameField = "file_name"
const val mimeTypeField = "mime_type"
const val fileIdField = "file_id"
const val fileSizeField = "file_size"
const val fileDateField = "file_date"
const val filePathField = "file_path"


const val requestWriteAccessField = "request_write_access"


const val photoUrlField = "photo_url"
const val photoSizeField = "photo_size"
const val photoFileIdField = "photo_file_id"
const val photoWidthField = "photo_width"
const val photoHeightField = "photo_height"

const val gifUrlField = "gif_url"
const val gifFileIdField = "gif_file_id"
const val gifWidthField = "gif_width"
const val gifHeightField = "gif_height"
const val gifDurationField = "gif_duration"

const val mpeg4GifUrlField = "mpeg4_url"
const val mpeg4GifFileIdField = "mpeg4_file_id"
const val mpeg4GifWidthField = "mpeg4_width"
const val mpeg4GifHeightField = "mpeg4_height"
const val mpeg4GifDurationField = "mpeg4_duration"

const val videoUrlField = "video_url"
const val videoFileIdField = "video_file_id"
const val videoWidthField = "video_width"
const val videoHeightField = "video_height"
const val videoDurationField = "video_duration"

const val audioUrlField = "audio_url"
const val audioFileIdField = "audio_file_id"
const val audioDurationField = "audio_duration"

const val voiceUrlField = "voice_url"
const val voiceFileIdField = "voice_file_id"
const val voiceDurationField = "voice_duration"

const val documentUrlField = "document_url"
const val documentFileIdField = "document_file_id"

const val stickerFileIdField = "sticker_file_id"

const val gameShortNameField = "game_short_name"

const val thumbnailUrlField = "thumbnail_url"
const val thumbnailMimeTypeField = "thumbnail_mime_type"
const val thumbnailWidthField = "thumbnail_width"
const val thumbnailHeightField = "thumbnail_height"

const val inputMessageContentField = "input_message_content"
const val hideUrlField = "hide_url"

const val botCommandField = "command"
const val botCommandsField = "commands"
const val scopeField = "scope"

const val isMemberField = "is_member"
const val isForumField = "is_forum"
const val canSendMessagesField = "can_send_messages"
const val canSendAudiosField = "can_send_audios"
const val canSendDocumentsField = "can_send_documents"
const val canSendPhotosField = "can_send_photos"
const val canSendVideosField = "can_send_videos"
const val canSendVideoNotesField = "can_send_video_notes"
const val canSendVoiceNotesField = "can_send_voice_notes"
const val canSendOtherMessagesField = "can_send_other_messages"
const val canSendPollsField = "can_send_polls"
const val canAddWebPagePreviewsField = "can_add_web_page_previews"
const val canSetStickerSetField = "can_set_sticker_set"

const val statusField = "status"
const val canBeEditedField = "can_be_edited"
const val canChangeInfoField = "can_change_info"
const val canPostMessagesField = "can_post_messages"
const val canEditMessagesField = "can_edit_messages"
const val canDeleteMessagesField = "can_delete_messages"
const val canInviteUsersField = "can_invite_users"
const val canRestrictMembersField = "can_restrict_members"
const val canPinMessagesField = "can_pin_messages"
const val canPromoteMembersField = "can_promote_members"
const val canManageVoiceChatsField = "can_manage_voice_chats"
const val canManageVideoChatsField = "can_manage_video_chats"
const val useIndependentChatPermissionsField = "use_independent_chat_permissions"
const val rightsField = "rights"
const val forChannelsField = "for_channels"
const val canManageChatField = "can_manage_chat"
const val pngStickerField = "png_sticker"
const val tgsStickerField = "tgs_sticker"
const val webmStickerField = "webm_sticker"
const val oldChatMemberField = "old_chat_member"
const val newChatMemberField = "new_chat_member"
const val stickerTypeField = "sticker_type"
const val stickerFormatField = "sticker_format"
const val needsRepaintingField = "needs_repainting"

const val okField = "ok"
const val captionField = "caption"
const val explanationField = "explanation"
const val idField = "id"
const val pollIdField = "poll_id"
const val textField = "text"
const val thumbnailField = "thumbnail"
const val emojiField = "emoji"
const val emojisField = "emojis"
const val titleField = "title"
const val descriptionField = "description"
const val shortDescriptionField = "short_description"
const val performerField = "performer"
const val durationField = "duration"
const val widthField = "width"
const val heightField = "height"
const val lengthField = "length"
const val latitudeField = "latitude"
const val longitudeField = "longitude"
const val headingField = "heading"
const val fromField = "from"
const val userChatIdField = "user_chat_id"
const val userField = "user"
const val dateField = "date"
const val reactionsField = "reactions"
const val reactionField = "reaction"
const val availableReactionsField = "available_reactions"
const val isBigField = "is_big"
const val oldReactionField = "old_reaction"
const val newReactionField = "new_reaction"
const val chatField = "chat"
const val originField = "origin"
const val chatsField = "chats"
const val usernameField = "username"
const val bioField = "bio"
const val nameField = "name"
const val emailField = "email"
const val locationField = "location"
const val queryField = "query"
const val offsetField = "offset"
const val limitField = "limit"
const val stickersField = "stickers"
const val stickerField = "sticker"
const val keywordsField = "keywords"
const val urlField = "url"
const val addressField = "address"
const val actionField = "action"
const val positionField = "position"
const val labelField = "label"
const val amountField = "amount"
const val pricesField = "prices"
const val payloadField = "payload"
const val vcardField = "vcard"
const val resultsField = "results"
const val resultField = "result"
const val certificateField = "certificate"
const val questionField = "question"
const val optionsField = "options"
const val payField = "pay"
const val permissionsField = "permissions"
const val typeField = "type"
const val valueField = "value"
const val creatorField = "creator"

const val pointField = "point"
const val xShiftField = "x_shift"
const val yShiftField = "y_shift"
const val scaleField = "scale"

const val maxTipAmountField = "max_tip_amount"
const val suggestedTipAmountsField = "suggested_tip_amounts"
const val chatTypeField = "chat_type"

const val explanationEntitiesField = "explanation_entities"
const val explanationParseModeField = "explanation_parse_mode"
const val openPeriodField = "open_period"
const val closeDateField = "close_date"

const val smallFileIdField = "small_file_id"
const val bigFileIdField = "big_file_id"
const val smallFileUniqueIdField = "small_file_unique_id"
const val bigFileUniqueIdField = "big_file_unique_id"

const val fileUniqueIdField = "file_unique_id"


const val currencyField = "currency"
const val startParameterField = "start_parameter"
const val totalAmountField = "total_amount"
const val invoicePayloadField = "invoice_payload"
const val shippingOptionIdField = "shipping_option_id"
const val shippingQueryIdField = "shipping_query_id"
const val preCheckoutQueryIdField = "pre_checkout_query_id"
const val shippingOptionsField = "shipping_options"
const val countryCodeField = "country_code"
const val countryCodesField = "country_codes"
const val totalCountField = "total_count"
const val stateField = "state"
const val cityField = "city"
const val firstStreetLineField = "street_line1"
const val secondStreetLineField = "street_line2"
const val postCodeField = "post_code"
const val shippingAddressField = "shipping_address"
const val orderInfoField = "order_info"
const val telegramPaymentChargeIdField = "telegram_payment_charge_id"
const val providerPaymentChargeIdField = "provider_payment_charge_id"
const val providerTokenField = "provider_token"
const val providerDataField = "provider_data"
const val usersField = "users"
const val startDateField = "start_date"
const val showAboveTextField = "show_above_text"
const val isDisabledField = "is_disabled"
const val preferSmallMediaField = "prefer_small_media"
const val preferLargeMediaField = "prefer_large_media"

const val requireNameField = "need_name"
const val requirePhoneNumberField = "need_phone_number"
const val requireEmailField = "need_email"
const val requireShippingAddressField = "need_shipping_address"

const val shouldSendPhoneNumberToProviderField = "send_phone_number_to_provider"
const val shouldSendEmailToProviderField = "send_email_to_provider"

const val resizeKeyboardField = "resize_keyboard"
const val oneTimeKeyboardField = "one_time_keyboard"
const val inputFieldPlaceholderField = "input_field_placeholder"
const val isPersistentField = "is_persistent"

const val priceDependOnShipAddressField = "is_flexible"

const val documentField = "document"
const val photoField = "photo"
const val audioField = "audio"
const val videoField = "video"
const val animationField = "animation"
const val voiceField = "voice"
const val videoNoteField = "video_note"
const val mediaField = "media"

const val disableEditMessageField = "disable_edit_message"
const val scoreField = "score"
const val forceField = "force"
const val forceReplyField = "force_reply"

const val regularPollType = "regular"
const val quizPollType = "quiz"

const val dataField = "data"
const val credentialsField = "credentials"
const val hashField = "hash"
const val translationField = "translation"
const val translationFileField = "translation_file"
const val fileField = "file"
const val filesField = "files"
const val translationFilesField = "translation_files"
const val frontSideField = "front_side"
const val reverseSideField = "reverse_side"
const val selfieField = "selfie"
const val secretField = "secret"

const val errorsField = "errors"
const val sourceField = "source"
const val isUnclaimedField = "is_unclaimed"
const val fieldNameField = "field_name"
const val dataHashField = "data_hash"
const val fileHashField = "file_hash"
const val fileHashesField = "file_hashes"
const val messageField = "message"
const val unspecifiedField = "unspecified"

const val secureDataField = "secure_data"
const val nonceField = "nonce"

const val personalDetailsField = "personal_details"
const val passportField = "passport"
const val internalPassportField = "internal_passport"
const val driverLicenseField = "driver_license"
const val identityCardField = "identity_card"
const val utilityBillField = "utility_bill"
const val bankStatementField = "bank_statement"
const val rentalAgreementField = "rental_agreement"
const val passportRegistrationField = "passport_registration"
const val temporaryRegistrationField = "temporary_registration"

const val buttonTextField = "button_text"
const val webAppField = "web_app"
const val webAppNameField = "web_app_name"
const val menuButtonField = "menu_button"

const val boostIdField = "boost_id"
const val boostField = "boost"
const val boostCountField = "boost_count"
const val addDateField = "add_date"
const val expirationDateField = "expiration_date"
const val removeDateField = "remove_date"
const val boostsField = "boosts"
const val winnersSelectionDateField = "winners_selection_date"
const val winnersCountField = "winner_count"
const val onlyNewMembersField = "only_new_members"
const val hasPublicWinnersField = "has_public_winners"
const val prizeDescriptionField = "prize_description"
const val premiumSubscriptionMonthCountField = "premium_subscription_month_count"
const val winnersField = "winners"
const val additionalChatCountField = "additional_chat_count"
const val unclaimedPrizeCountField = "unclaimed_prize_count"
const val wasRefundedField = "was_refunded"
const val isManualField = "is_manual"
