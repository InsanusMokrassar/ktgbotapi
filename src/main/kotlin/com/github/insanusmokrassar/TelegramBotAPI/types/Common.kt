package com.github.insanusmokrassar.TelegramBotAPI.types

typealias Identifier = Long
typealias MessageIdentifier = Long
typealias InlineQueryIdentifier = String
typealias UpdateIdentifier = Long
typealias MediaGroupIdentifier = String
typealias ForwardSignature = String
typealias AuthorSignature = ForwardSignature
typealias CallbackQueryIdentifier = String
typealias PaymentQueryIdentifier = String
typealias PreCheckoutQueryId = String
typealias ShippingQueryIdentifier = String
typealias InvoicePayload = String
typealias ShippingOptionIdentifier = String
typealias StartParameter = String
typealias InlineMessageIdentifier = String

val callbackQueryAnswerLength = 0 until 200
val captionLength = 0 until 1024
val textLength = 0 until 4096
val userProfilePhotosRequestLimit = 0 .. 100
val chatTitleLength = 1 until 255
val chatDescriptionLength = 0 until 256
val inlineResultQueryIdLingth = 1 until 64
val allowedConnectionsLength = 1 .. 100

val invoiceTitleLimit = 1 until 32
val invoiceDescriptionLimit = 1 until 256
val invoicePayloadBytesLimit = 1 until 128

val livePeriodLimit = 60 .. 86400

val inlineQueryAnswerResultsLimit = 0 .. 50

const val chatIdField = "chat_id"
const val messageIdField = "message_id"
const val updateIdField = "update_id"
const val fromChatIdField = "from_chat_id"
const val disableWebPagePreviewField = "disable_web_page_preview"
const val disableNotificationField = "disable_notification"
const val replyToMessageIdField = "reply_to_message_id"
const val replyMarkupField = "reply_markup"
const val supportStreamingField = "support_streaming"
const val livePeriodField = "live_period"
const val isBotField = "is_bot"
const val firstNameField = "first_name"
const val lastNameField = "last_name"
const val languageCodeField = "language_code"
const val textEntitiesField = "text_entities"
const val stickerSetNameField = "set_name"
const val maskPositionField = "mask_position"
const val phoneNumberField = "phone_number"
const val userIdField = "user_id"
const val containsMasksField = "contains_masks"
const val resultIdField = "result_id"
const val inlineMessageIdField = "inline_message_id"
const val callbackDataField = "callback_data"
const val callbackQueryIdField = "callback_query_id"
const val inlineQueryIdField = "inline_query_id"
const val showAlertField = "show_alert"
const val cachedTimeField = "cached_time"
const val foursquareIdField = "foursquare_id"
const val foursquareTypeField = "foursquare_type"
const val untilDateField = "until_date"
const val errorMessageField = "error_message"
const val messageTextField = "message_text"
const val isPersonalField = "is_personal"
const val nextOffsetField = "next_offset"
const val switchPmTextField = "switch_pm_text"
const val switchPmParameterField = "switch_pm_parameter"
const val maxAllowedConnectionsField = "max_connections"
const val allowedUpdatesField = "allowed_updates"


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

const val thumbUrlField = "thumb_url"
const val thumbWidthField = "thumb_width"
const val thumbHeightField = "thumb_height"

const val inputMessageContentField = "input_message_content"
const val hideUrlField = "hide_url"


const val canSendMessagesField = "can_send_messages"
const val canSendMediaMessagesField = "can_send_media_messages"
const val canSendOtherMessagesField = "can_send_other_messages"
const val canAddWebPagePreviewsField = "can_add_web_page_previews"

const val canBeEditedField = "can_be_edited"
const val canChangeInfoField = "can_change_info"
const val canPostMessagesField = "can_post_messages"
const val canEditMessagesField = "can_edit_messages"
const val canDeleteMessagesField = "can_delete_messages"
const val canInviteUsersField = "can_invite_users"
const val canRestrictMembersField = "can_restrict_members"
const val canPinMessagesField = "can_pin_messages"
const val canPromoteMembersField = "can_promote_members"
const val pngStickerField = "png_sticker"

const val okField = "ok"
const val captionField = "caption"
const val idField = "id"
const val textField = "text"
const val thumbField = "thumb"
const val emojiField = "emoji"
const val emojisField = "emojis"
const val titleField = "title"
const val descriptionField = "description"
const val performerField = "performer"
const val durationField = "duration"
const val widthField = "width"
const val heightField = "height"
const val lengthField = "length"
const val latitudeField = "latitude"
const val longitudeField = "longitude"
const val fromField = "from"
const val userField = "user"
const val dateField = "date"
const val chatField = "chat"
const val usernameField = "username"
const val nameField = "name"
const val emailField = "email"
const val locationField = "location"
const val queryField = "query"
const val offsetField = "offset"
const val limitField = "limit"
const val stickersField = "stickers"
const val stickerField = "sticker"
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
const val certificateField = "certificate"

const val pointField = "point"
const val xShiftField = "x_shift"
const val yShiftField = "y_shift"
const val scaleField = "y_shift"


const val currencyField = "currency"
const val startParameterField = "start_parameter"
const val totalAmountField = "total_amount"
const val invoicePayloadField = "invoice_payload"
const val shippingOptionIdField = "shipping_option_id"
const val shippingQueryIdField = "shipping_query_id"
const val preCheckoutQueryIdField = "pre_checkout_query_id"
const val shippingOptionsField = "shipping_options"
const val countryCodeField = "country_code"
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

const val requireNameField = "need_name"
const val requirePhoneNumberField = "need_phone_number"
const val requireEmailField = "need_email"
const val requireShippingAddressField = "need_shipping_address"

const val shouldSendPhoneNumberToProviderField = "send_phone_number_to_provider"
const val shouldSendEmailToProviderField = "send_email_to_provider"

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
