@file:Suppress(
  "unused",
  "RemoveRedundantQualifierName",
  "RedundantVisibilityModifier",
  "NOTHING_TO_INLINE",
  "UNCHECKED_CAST",
  "OPT_IN_USAGE",
)

package dev.inmo.tgbotapi.extensions.utils

import dev.inmo.tgbotapi.requests.answers.InlineQueryResultsButton
import dev.inmo.tgbotapi.requests.stickers.InputSticker
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.ChatIdWithThreadId
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.IdChatIdentifier
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputContactMessageContent
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputInvoiceMessageContent
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputLocationMessageContent
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputMessageContent
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputTextMessageContent
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputVenueMessageContent
import dev.inmo.tgbotapi.types.Username
import dev.inmo.tgbotapi.types.actions.BotAction
import dev.inmo.tgbotapi.types.actions.ChooseStickerAction
import dev.inmo.tgbotapi.types.actions.CustomBotAction
import dev.inmo.tgbotapi.types.actions.FindLocationAction
import dev.inmo.tgbotapi.types.actions.RecordVideoAction
import dev.inmo.tgbotapi.types.actions.RecordVideoNoteAction
import dev.inmo.tgbotapi.types.actions.RecordVoiceAction
import dev.inmo.tgbotapi.types.actions.TypingAction
import dev.inmo.tgbotapi.types.actions.UploadDocumentAction
import dev.inmo.tgbotapi.types.actions.UploadPhotoAction
import dev.inmo.tgbotapi.types.actions.UploadVideoAction
import dev.inmo.tgbotapi.types.actions.UploadVideoNoteAction
import dev.inmo.tgbotapi.types.actions.UploadVoiceAction
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.CallbackDataInlineKeyboardButton
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.CallbackGameInlineKeyboardButton
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.InlineKeyboardButton
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.LoginURLInlineKeyboardButton
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.PayInlineKeyboardButton
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.SwitchInlineQueryChosenChatInlineKeyboardButton
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.SwitchInlineQueryCurrentChatInlineKeyboardButton
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.SwitchInlineQueryInlineKeyboardButton
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.URLInlineKeyboardButton
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.UnknownInlineKeyboardButton
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.WebAppInlineKeyboardButton
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.buttons.KeyboardButtonRequestUser
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.buttons.ReplyForce
import dev.inmo.tgbotapi.types.buttons.ReplyKeyboardMarkup
import dev.inmo.tgbotapi.types.buttons.ReplyKeyboardRemove
import dev.inmo.tgbotapi.types.chat.AbleToAddInAttachmentMenuChat
import dev.inmo.tgbotapi.types.chat.Bot
import dev.inmo.tgbotapi.types.chat.ChannelChat
import dev.inmo.tgbotapi.types.chat.ChannelChatImpl
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.chat.CommonBot
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.chat.ExtendedBot
import dev.inmo.tgbotapi.types.chat.ExtendedChannelChat
import dev.inmo.tgbotapi.types.chat.ExtendedChannelChatImpl
import dev.inmo.tgbotapi.types.chat.ExtendedChat
import dev.inmo.tgbotapi.types.chat.ExtendedChatWithUsername
import dev.inmo.tgbotapi.types.chat.ExtendedForumChat
import dev.inmo.tgbotapi.types.chat.ExtendedForumChatImpl
import dev.inmo.tgbotapi.types.chat.ExtendedGroupChat
import dev.inmo.tgbotapi.types.chat.ExtendedGroupChatImpl
import dev.inmo.tgbotapi.types.chat.ExtendedPrivateChat
import dev.inmo.tgbotapi.types.chat.ExtendedPrivateChatImpl
import dev.inmo.tgbotapi.types.chat.ExtendedPublicChat
import dev.inmo.tgbotapi.types.chat.ExtendedSupergroupChat
import dev.inmo.tgbotapi.types.chat.ExtendedSupergroupChatImpl
import dev.inmo.tgbotapi.types.chat.ForumChat
import dev.inmo.tgbotapi.types.chat.ForumChatImpl
import dev.inmo.tgbotapi.types.chat.GroupChat
import dev.inmo.tgbotapi.types.chat.GroupChatImpl
import dev.inmo.tgbotapi.types.chat.PossiblyPremiumChat
import dev.inmo.tgbotapi.types.chat.PrivateChat
import dev.inmo.tgbotapi.types.chat.PrivateChatImpl
import dev.inmo.tgbotapi.types.chat.PublicChat
import dev.inmo.tgbotapi.types.chat.SuperPublicChat
import dev.inmo.tgbotapi.types.chat.SupergroupChat
import dev.inmo.tgbotapi.types.chat.SupergroupChatImpl
import dev.inmo.tgbotapi.types.chat.UnknownChatType
import dev.inmo.tgbotapi.types.chat.UnknownExtendedChat
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.chat.UsernameChat
import dev.inmo.tgbotapi.types.dice.BasketballDiceAnimationType
import dev.inmo.tgbotapi.types.dice.BowlingDiceAnimationType
import dev.inmo.tgbotapi.types.dice.CubeDiceAnimationType
import dev.inmo.tgbotapi.types.dice.CustomDiceAnimationType
import dev.inmo.tgbotapi.types.dice.DartsDiceAnimationType
import dev.inmo.tgbotapi.types.dice.DiceAnimationType
import dev.inmo.tgbotapi.types.dice.FootballDiceAnimationType
import dev.inmo.tgbotapi.types.dice.SlotMachineDiceAnimationType
import dev.inmo.tgbotapi.types.files.AnimatedSticker
import dev.inmo.tgbotapi.types.files.AnimationFile
import dev.inmo.tgbotapi.types.files.AudioFile
import dev.inmo.tgbotapi.types.files.CustomEmojiAnimatedSticker
import dev.inmo.tgbotapi.types.files.CustomEmojiSimpleSticker
import dev.inmo.tgbotapi.types.files.CustomEmojiSticker
import dev.inmo.tgbotapi.types.files.CustomEmojiVideoSticker
import dev.inmo.tgbotapi.types.files.DocumentFile
import dev.inmo.tgbotapi.types.files.File
import dev.inmo.tgbotapi.types.files.MaskAnimatedSticker
import dev.inmo.tgbotapi.types.files.MaskSimpleSticker
import dev.inmo.tgbotapi.types.files.MaskSticker
import dev.inmo.tgbotapi.types.files.MaskVideoSticker
import dev.inmo.tgbotapi.types.files.MimedMediaFile
import dev.inmo.tgbotapi.types.files.PassportFile
import dev.inmo.tgbotapi.types.files.PathedFile
import dev.inmo.tgbotapi.types.files.PhotoSize
import dev.inmo.tgbotapi.types.files.PlayableMediaFile
import dev.inmo.tgbotapi.types.files.RegularAnimatedSticker
import dev.inmo.tgbotapi.types.files.RegularSimpleSticker
import dev.inmo.tgbotapi.types.files.RegularSticker
import dev.inmo.tgbotapi.types.files.RegularVideoSticker
import dev.inmo.tgbotapi.types.files.SizedMediaFile
import dev.inmo.tgbotapi.types.files.Sticker
import dev.inmo.tgbotapi.types.files.TelegramMediaFile
import dev.inmo.tgbotapi.types.files.ThumbedMediaFile
import dev.inmo.tgbotapi.types.files.UnknownSticker
import dev.inmo.tgbotapi.types.files.VideoFile
import dev.inmo.tgbotapi.types.files.VideoNoteFile
import dev.inmo.tgbotapi.types.files.VideoSticker
import dev.inmo.tgbotapi.types.files.VoiceFile
import dev.inmo.tgbotapi.types.location.LiveLocation
import dev.inmo.tgbotapi.types.location.Location
import dev.inmo.tgbotapi.types.location.StaticLocation
import dev.inmo.tgbotapi.types.media.AudioMediaGroupMemberTelegramMedia
import dev.inmo.tgbotapi.types.media.DocumentMediaGroupMemberTelegramMedia
import dev.inmo.tgbotapi.types.media.DuratedTelegramMedia
import dev.inmo.tgbotapi.types.media.MediaGroupMemberTelegramMedia
import dev.inmo.tgbotapi.types.media.SizedTelegramMedia
import dev.inmo.tgbotapi.types.media.SpoilerableTelegramMedia
import dev.inmo.tgbotapi.types.media.TelegramMedia
import dev.inmo.tgbotapi.types.media.TelegramMediaAnimation
import dev.inmo.tgbotapi.types.media.TelegramMediaAudio
import dev.inmo.tgbotapi.types.media.TelegramMediaDocument
import dev.inmo.tgbotapi.types.media.TelegramMediaPhoto
import dev.inmo.tgbotapi.types.media.TelegramMediaVideo
import dev.inmo.tgbotapi.types.media.ThumbedTelegramMedia
import dev.inmo.tgbotapi.types.media.TitledTelegramMedia
import dev.inmo.tgbotapi.types.media.VisualMediaGroupMemberTelegramMedia
import dev.inmo.tgbotapi.types.message.ForwardInfo
import dev.inmo.tgbotapi.types.message.content.AnimationContent
import dev.inmo.tgbotapi.types.message.content.AudioContent
import dev.inmo.tgbotapi.types.message.content.AudioMediaGroupPartContent
import dev.inmo.tgbotapi.types.message.content.ContactContent
import dev.inmo.tgbotapi.types.message.content.DiceContent
import dev.inmo.tgbotapi.types.message.content.DocumentContent
import dev.inmo.tgbotapi.types.message.content.DocumentMediaGroupPartContent
import dev.inmo.tgbotapi.types.message.content.GameContent
import dev.inmo.tgbotapi.types.message.content.InvoiceContent
import dev.inmo.tgbotapi.types.message.content.LiveLocationContent
import dev.inmo.tgbotapi.types.message.content.LocationContent
import dev.inmo.tgbotapi.types.message.content.MediaCollectionContent
import dev.inmo.tgbotapi.types.message.content.MediaContent
import dev.inmo.tgbotapi.types.message.content.MediaGroupCollectionContent
import dev.inmo.tgbotapi.types.message.content.MediaGroupContent
import dev.inmo.tgbotapi.types.message.content.MediaGroupPartContent
import dev.inmo.tgbotapi.types.message.content.MessageContent
import dev.inmo.tgbotapi.types.message.content.PhotoContent
import dev.inmo.tgbotapi.types.message.content.PollContent
import dev.inmo.tgbotapi.types.message.content.ResendableContent
import dev.inmo.tgbotapi.types.message.content.SpoilerableMediaContent
import dev.inmo.tgbotapi.types.message.content.StaticLocationContent
import dev.inmo.tgbotapi.types.message.content.StickerContent
import dev.inmo.tgbotapi.types.message.content.TextContent
import dev.inmo.tgbotapi.types.message.content.TextedContent
import dev.inmo.tgbotapi.types.message.content.TextedMediaContent
import dev.inmo.tgbotapi.types.message.content.VenueContent
import dev.inmo.tgbotapi.types.message.content.VideoContent
import dev.inmo.tgbotapi.types.message.content.VideoNoteContent
import dev.inmo.tgbotapi.types.message.content.VisualMediaGroupPartContent
import dev.inmo.tgbotapi.types.message.content.VoiceContent
import dev.inmo.tgbotapi.types.message.textsources.BoldTextSource
import dev.inmo.tgbotapi.types.message.textsources.BotCommandTextSource
import dev.inmo.tgbotapi.types.message.textsources.CashTagTextSource
import dev.inmo.tgbotapi.types.message.textsources.CodeTextSource
import dev.inmo.tgbotapi.types.message.textsources.CustomEmojiTextSource
import dev.inmo.tgbotapi.types.message.textsources.EMailTextSource
import dev.inmo.tgbotapi.types.message.textsources.HashTagTextSource
import dev.inmo.tgbotapi.types.message.textsources.ItalicTextSource
import dev.inmo.tgbotapi.types.message.textsources.MentionTextSource
import dev.inmo.tgbotapi.types.message.textsources.MultilevelTextSource
import dev.inmo.tgbotapi.types.message.textsources.PhoneNumberTextSource
import dev.inmo.tgbotapi.types.message.textsources.PreTextSource
import dev.inmo.tgbotapi.types.message.textsources.RegularTextSource
import dev.inmo.tgbotapi.types.message.textsources.SpoilerTextSource
import dev.inmo.tgbotapi.types.message.textsources.StrikethroughTextSource
import dev.inmo.tgbotapi.types.message.textsources.TextLinkTextSource
import dev.inmo.tgbotapi.types.message.textsources.TextMentionTextSource
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.textsources.URLTextSource
import dev.inmo.tgbotapi.types.message.textsources.UnderlineTextSource
import dev.inmo.tgbotapi.types.passport.PassportElementError
import dev.inmo.tgbotapi.types.passport.PassportElementErrorDataField
import dev.inmo.tgbotapi.types.passport.PassportElementErrorFile
import dev.inmo.tgbotapi.types.passport.PassportElementErrorFiles
import dev.inmo.tgbotapi.types.passport.PassportElementErrorFrontSide
import dev.inmo.tgbotapi.types.passport.PassportElementErrorReverseSide
import dev.inmo.tgbotapi.types.passport.PassportElementErrorSelfie
import dev.inmo.tgbotapi.types.passport.PassportElementErrorTranslationFile
import dev.inmo.tgbotapi.types.passport.PassportElementErrorTranslationFiles
import dev.inmo.tgbotapi.types.passport.PassportElementErrorUnspecified
import dev.inmo.tgbotapi.types.passport.PassportElementFileError
import dev.inmo.tgbotapi.types.passport.PassportElementFilesError
import dev.inmo.tgbotapi.types.passport.PassportMultipleElementsError
import dev.inmo.tgbotapi.types.passport.PassportSingleElementError
import dev.inmo.tgbotapi.types.passport.UnknownPassportElementError
import dev.inmo.tgbotapi.types.polls.ApproximateScheduledCloseInfo
import dev.inmo.tgbotapi.types.polls.ExactScheduledCloseInfo
import dev.inmo.tgbotapi.types.polls.MultipleAnswersPoll
import dev.inmo.tgbotapi.types.polls.Poll
import dev.inmo.tgbotapi.types.polls.QuizPoll
import dev.inmo.tgbotapi.types.polls.RegularPoll
import dev.inmo.tgbotapi.types.polls.ScheduledCloseInfo
import dev.inmo.tgbotapi.types.polls.UnknownPollType
import dev.inmo.tgbotapi.types.request.ChatShared
import dev.inmo.tgbotapi.types.request.ChatSharedRequest
import dev.inmo.tgbotapi.types.request.RequestResponse
import dev.inmo.tgbotapi.types.request.UserShared
import kotlin.Suppress

public inline fun InlineQueryResultsButton.startOrNull(): InlineQueryResultsButton.Start? = this as?
    dev.inmo.tgbotapi.requests.answers.InlineQueryResultsButton.Start

public inline fun InlineQueryResultsButton.startOrThrow(): InlineQueryResultsButton.Start = this as
    dev.inmo.tgbotapi.requests.answers.InlineQueryResultsButton.Start

public inline fun <T>
    InlineQueryResultsButton.ifStart(block: (InlineQueryResultsButton.Start) -> T): T? =
    startOrNull() ?.let(block)

public inline fun InlineQueryResultsButton.unknownOrNull(): InlineQueryResultsButton.Unknown? = this
    as? dev.inmo.tgbotapi.requests.answers.InlineQueryResultsButton.Unknown

public inline fun InlineQueryResultsButton.unknownOrThrow(): InlineQueryResultsButton.Unknown = this
    as dev.inmo.tgbotapi.requests.answers.InlineQueryResultsButton.Unknown

public inline fun <T>
    InlineQueryResultsButton.ifUnknown(block: (InlineQueryResultsButton.Unknown) -> T): T? =
    unknownOrNull() ?.let(block)

public inline fun InlineQueryResultsButton.webAppOrNull(): InlineQueryResultsButton.WebApp? = this
    as? dev.inmo.tgbotapi.requests.answers.InlineQueryResultsButton.WebApp

public inline fun InlineQueryResultsButton.webAppOrThrow(): InlineQueryResultsButton.WebApp = this
    as dev.inmo.tgbotapi.requests.answers.InlineQueryResultsButton.WebApp

public inline fun <T>
    InlineQueryResultsButton.ifWebApp(block: (InlineQueryResultsButton.WebApp) -> T): T? =
    webAppOrNull() ?.let(block)

public inline fun InputSticker.maskOrNull(): InputSticker.Mask? = this as?
    dev.inmo.tgbotapi.requests.stickers.InputSticker.Mask

public inline fun InputSticker.maskOrThrow(): InputSticker.Mask = this as
    dev.inmo.tgbotapi.requests.stickers.InputSticker.Mask

public inline fun <T> InputSticker.ifMask(block: (InputSticker.Mask) -> T): T? = maskOrNull()
    ?.let(block)

public inline fun InputSticker.withKeywordsOrNull(): InputSticker.WithKeywords? = this as?
    dev.inmo.tgbotapi.requests.stickers.InputSticker.WithKeywords

public inline fun InputSticker.withKeywordsOrThrow(): InputSticker.WithKeywords = this as
    dev.inmo.tgbotapi.requests.stickers.InputSticker.WithKeywords

public inline fun <T> InputSticker.ifWithKeywords(block: (InputSticker.WithKeywords) -> T): T? =
    withKeywordsOrNull() ?.let(block)

public inline fun InputSticker.customEmojiOrNull(): InputSticker.WithKeywords.CustomEmoji? = this
    as? dev.inmo.tgbotapi.requests.stickers.InputSticker.WithKeywords.CustomEmoji

public inline fun InputSticker.customEmojiOrThrow(): InputSticker.WithKeywords.CustomEmoji = this as
    dev.inmo.tgbotapi.requests.stickers.InputSticker.WithKeywords.CustomEmoji

public inline fun <T>
    InputSticker.ifCustomEmoji(block: (InputSticker.WithKeywords.CustomEmoji) -> T): T? =
    customEmojiOrNull() ?.let(block)

public inline fun InputSticker.regularOrNull(): InputSticker.WithKeywords.Regular? = this as?
    dev.inmo.tgbotapi.requests.stickers.InputSticker.WithKeywords.Regular

public inline fun InputSticker.regularOrThrow(): InputSticker.WithKeywords.Regular = this as
    dev.inmo.tgbotapi.requests.stickers.InputSticker.WithKeywords.Regular

public inline fun <T> InputSticker.ifRegular(block: (InputSticker.WithKeywords.Regular) -> T): T? =
    regularOrNull() ?.let(block)

public inline fun ChatIdentifier.idChatIdentifierOrNull(): IdChatIdentifier? = this as?
    dev.inmo.tgbotapi.types.IdChatIdentifier

public inline fun ChatIdentifier.idChatIdentifierOrThrow(): IdChatIdentifier = this as
    dev.inmo.tgbotapi.types.IdChatIdentifier

public inline fun <T> ChatIdentifier.ifIdChatIdentifier(block: (IdChatIdentifier) -> T): T? =
    idChatIdentifierOrNull() ?.let(block)

public inline fun ChatIdentifier.chatIdOrNull(): ChatId? = this as? dev.inmo.tgbotapi.types.ChatId

public inline fun ChatIdentifier.chatIdOrThrow(): ChatId = this as dev.inmo.tgbotapi.types.ChatId

public inline fun <T> ChatIdentifier.ifChatId(block: (ChatId) -> T): T? = chatIdOrNull()
    ?.let(block)

public inline fun ChatIdentifier.chatIdWithThreadIdOrNull(): ChatIdWithThreadId? = this as?
    dev.inmo.tgbotapi.types.ChatIdWithThreadId

public inline fun ChatIdentifier.chatIdWithThreadIdOrThrow(): ChatIdWithThreadId = this as
    dev.inmo.tgbotapi.types.ChatIdWithThreadId

public inline fun <T> ChatIdentifier.ifChatIdWithThreadId(block: (ChatIdWithThreadId) -> T): T? =
    chatIdWithThreadIdOrNull() ?.let(block)

public inline fun ChatIdentifier.usernameOrNull(): Username? = this as?
    dev.inmo.tgbotapi.types.Username

public inline fun ChatIdentifier.usernameOrThrow(): Username = this as
    dev.inmo.tgbotapi.types.Username

public inline fun <T> ChatIdentifier.ifUsername(block: (Username) -> T): T? = usernameOrNull()
    ?.let(block)

public inline fun InputMessageContent.inputContactMessageContentOrNull():
    InputContactMessageContent? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputContactMessageContent

public inline fun InputMessageContent.inputContactMessageContentOrThrow():
    InputContactMessageContent = this as
    dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputContactMessageContent

public inline fun <T>
    InputMessageContent.ifInputContactMessageContent(block: (InputContactMessageContent) -> T): T? =
    inputContactMessageContentOrNull() ?.let(block)

public inline fun InputMessageContent.inputInvoiceMessageContentOrNull():
    InputInvoiceMessageContent? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputInvoiceMessageContent

public inline fun InputMessageContent.inputInvoiceMessageContentOrThrow():
    InputInvoiceMessageContent = this as
    dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputInvoiceMessageContent

public inline fun <T>
    InputMessageContent.ifInputInvoiceMessageContent(block: (InputInvoiceMessageContent) -> T): T? =
    inputInvoiceMessageContentOrNull() ?.let(block)

public inline fun InputMessageContent.inputLocationMessageContentOrNull():
    InputLocationMessageContent? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputLocationMessageContent

public inline fun InputMessageContent.inputLocationMessageContentOrThrow():
    InputLocationMessageContent = this as
    dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputLocationMessageContent

public inline fun <T>
    InputMessageContent.ifInputLocationMessageContent(block: (InputLocationMessageContent) -> T): T?
    = inputLocationMessageContentOrNull() ?.let(block)

public inline fun InputMessageContent.inputTextMessageContentOrNull(): InputTextMessageContent? =
    this as? dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputTextMessageContent

public inline fun InputMessageContent.inputTextMessageContentOrThrow(): InputTextMessageContent =
    this as dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputTextMessageContent

public inline fun <T>
    InputMessageContent.ifInputTextMessageContent(block: (InputTextMessageContent) -> T): T? =
    inputTextMessageContentOrNull() ?.let(block)

public inline fun InputMessageContent.inputVenueMessageContentOrNull(): InputVenueMessageContent? =
    this as? dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputVenueMessageContent

public inline fun InputMessageContent.inputVenueMessageContentOrThrow(): InputVenueMessageContent =
    this as dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputVenueMessageContent

public inline fun <T>
    InputMessageContent.ifInputVenueMessageContent(block: (InputVenueMessageContent) -> T): T? =
    inputVenueMessageContentOrNull() ?.let(block)

public inline fun BotAction.chooseStickerActionOrNull(): ChooseStickerAction? = this as?
    dev.inmo.tgbotapi.types.actions.ChooseStickerAction

public inline fun BotAction.chooseStickerActionOrThrow(): ChooseStickerAction = this as
    dev.inmo.tgbotapi.types.actions.ChooseStickerAction

public inline fun <T> BotAction.ifChooseStickerAction(block: (ChooseStickerAction) -> T): T? =
    chooseStickerActionOrNull() ?.let(block)

public inline fun BotAction.customBotActionOrNull(): CustomBotAction? = this as?
    dev.inmo.tgbotapi.types.actions.CustomBotAction

public inline fun BotAction.customBotActionOrThrow(): CustomBotAction = this as
    dev.inmo.tgbotapi.types.actions.CustomBotAction

public inline fun <T> BotAction.ifCustomBotAction(block: (CustomBotAction) -> T): T? =
    customBotActionOrNull() ?.let(block)

public inline fun BotAction.findLocationActionOrNull(): FindLocationAction? = this as?
    dev.inmo.tgbotapi.types.actions.FindLocationAction

public inline fun BotAction.findLocationActionOrThrow(): FindLocationAction = this as
    dev.inmo.tgbotapi.types.actions.FindLocationAction

public inline fun <T> BotAction.ifFindLocationAction(block: (FindLocationAction) -> T): T? =
    findLocationActionOrNull() ?.let(block)

public inline fun BotAction.recordVideoActionOrNull(): RecordVideoAction? = this as?
    dev.inmo.tgbotapi.types.actions.RecordVideoAction

public inline fun BotAction.recordVideoActionOrThrow(): RecordVideoAction = this as
    dev.inmo.tgbotapi.types.actions.RecordVideoAction

public inline fun <T> BotAction.ifRecordVideoAction(block: (RecordVideoAction) -> T): T? =
    recordVideoActionOrNull() ?.let(block)

public inline fun BotAction.recordVideoNoteActionOrNull(): RecordVideoNoteAction? = this as?
    dev.inmo.tgbotapi.types.actions.RecordVideoNoteAction

public inline fun BotAction.recordVideoNoteActionOrThrow(): RecordVideoNoteAction = this as
    dev.inmo.tgbotapi.types.actions.RecordVideoNoteAction

public inline fun <T> BotAction.ifRecordVideoNoteAction(block: (RecordVideoNoteAction) -> T): T? =
    recordVideoNoteActionOrNull() ?.let(block)

public inline fun BotAction.recordVoiceActionOrNull(): RecordVoiceAction? = this as?
    dev.inmo.tgbotapi.types.actions.RecordVoiceAction

public inline fun BotAction.recordVoiceActionOrThrow(): RecordVoiceAction = this as
    dev.inmo.tgbotapi.types.actions.RecordVoiceAction

public inline fun <T> BotAction.ifRecordVoiceAction(block: (RecordVoiceAction) -> T): T? =
    recordVoiceActionOrNull() ?.let(block)

public inline fun BotAction.typingActionOrNull(): TypingAction? = this as?
    dev.inmo.tgbotapi.types.actions.TypingAction

public inline fun BotAction.typingActionOrThrow(): TypingAction = this as
    dev.inmo.tgbotapi.types.actions.TypingAction

public inline fun <T> BotAction.ifTypingAction(block: (TypingAction) -> T): T? =
    typingActionOrNull() ?.let(block)

public inline fun BotAction.uploadDocumentActionOrNull(): UploadDocumentAction? = this as?
    dev.inmo.tgbotapi.types.actions.UploadDocumentAction

public inline fun BotAction.uploadDocumentActionOrThrow(): UploadDocumentAction = this as
    dev.inmo.tgbotapi.types.actions.UploadDocumentAction

public inline fun <T> BotAction.ifUploadDocumentAction(block: (UploadDocumentAction) -> T): T? =
    uploadDocumentActionOrNull() ?.let(block)

public inline fun BotAction.uploadPhotoActionOrNull(): UploadPhotoAction? = this as?
    dev.inmo.tgbotapi.types.actions.UploadPhotoAction

public inline fun BotAction.uploadPhotoActionOrThrow(): UploadPhotoAction = this as
    dev.inmo.tgbotapi.types.actions.UploadPhotoAction

public inline fun <T> BotAction.ifUploadPhotoAction(block: (UploadPhotoAction) -> T): T? =
    uploadPhotoActionOrNull() ?.let(block)

public inline fun BotAction.uploadVideoActionOrNull(): UploadVideoAction? = this as?
    dev.inmo.tgbotapi.types.actions.UploadVideoAction

public inline fun BotAction.uploadVideoActionOrThrow(): UploadVideoAction = this as
    dev.inmo.tgbotapi.types.actions.UploadVideoAction

public inline fun <T> BotAction.ifUploadVideoAction(block: (UploadVideoAction) -> T): T? =
    uploadVideoActionOrNull() ?.let(block)

public inline fun BotAction.uploadVideoNoteActionOrNull(): UploadVideoNoteAction? = this as?
    dev.inmo.tgbotapi.types.actions.UploadVideoNoteAction

public inline fun BotAction.uploadVideoNoteActionOrThrow(): UploadVideoNoteAction = this as
    dev.inmo.tgbotapi.types.actions.UploadVideoNoteAction

public inline fun <T> BotAction.ifUploadVideoNoteAction(block: (UploadVideoNoteAction) -> T): T? =
    uploadVideoNoteActionOrNull() ?.let(block)

public inline fun BotAction.uploadVoiceActionOrNull(): UploadVoiceAction? = this as?
    dev.inmo.tgbotapi.types.actions.UploadVoiceAction

public inline fun BotAction.uploadVoiceActionOrThrow(): UploadVoiceAction = this as
    dev.inmo.tgbotapi.types.actions.UploadVoiceAction

public inline fun <T> BotAction.ifUploadVoiceAction(block: (UploadVoiceAction) -> T): T? =
    uploadVoiceActionOrNull() ?.let(block)

public inline fun InlineKeyboardButton.callbackDataInlineKeyboardButtonOrNull():
    CallbackDataInlineKeyboardButton? = this as?
    dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.CallbackDataInlineKeyboardButton

public inline fun InlineKeyboardButton.callbackDataInlineKeyboardButtonOrThrow():
    CallbackDataInlineKeyboardButton = this as
    dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.CallbackDataInlineKeyboardButton

public inline fun <T>
    InlineKeyboardButton.ifCallbackDataInlineKeyboardButton(block: (CallbackDataInlineKeyboardButton) -> T):
    T? = callbackDataInlineKeyboardButtonOrNull() ?.let(block)

public inline fun InlineKeyboardButton.callbackGameInlineKeyboardButtonOrNull():
    CallbackGameInlineKeyboardButton? = this as?
    dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.CallbackGameInlineKeyboardButton

public inline fun InlineKeyboardButton.callbackGameInlineKeyboardButtonOrThrow():
    CallbackGameInlineKeyboardButton = this as
    dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.CallbackGameInlineKeyboardButton

public inline fun <T>
    InlineKeyboardButton.ifCallbackGameInlineKeyboardButton(block: (CallbackGameInlineKeyboardButton) -> T):
    T? = callbackGameInlineKeyboardButtonOrNull() ?.let(block)

public inline fun InlineKeyboardButton.loginURLInlineKeyboardButtonOrNull():
    LoginURLInlineKeyboardButton? = this as?
    dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.LoginURLInlineKeyboardButton

public inline fun InlineKeyboardButton.loginURLInlineKeyboardButtonOrThrow():
    LoginURLInlineKeyboardButton = this as
    dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.LoginURLInlineKeyboardButton

public inline fun <T>
    InlineKeyboardButton.ifLoginURLInlineKeyboardButton(block: (LoginURLInlineKeyboardButton) -> T):
    T? = loginURLInlineKeyboardButtonOrNull() ?.let(block)

public inline fun InlineKeyboardButton.payInlineKeyboardButtonOrNull(): PayInlineKeyboardButton? =
    this as? dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.PayInlineKeyboardButton

public inline fun InlineKeyboardButton.payInlineKeyboardButtonOrThrow(): PayInlineKeyboardButton =
    this as dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.PayInlineKeyboardButton

public inline fun <T>
    InlineKeyboardButton.ifPayInlineKeyboardButton(block: (PayInlineKeyboardButton) -> T): T? =
    payInlineKeyboardButtonOrNull() ?.let(block)

public inline fun InlineKeyboardButton.switchInlineQueryChosenChatInlineKeyboardButtonOrNull():
    SwitchInlineQueryChosenChatInlineKeyboardButton? = this as?
    dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.SwitchInlineQueryChosenChatInlineKeyboardButton

public inline fun InlineKeyboardButton.switchInlineQueryChosenChatInlineKeyboardButtonOrThrow():
    SwitchInlineQueryChosenChatInlineKeyboardButton = this as
    dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.SwitchInlineQueryChosenChatInlineKeyboardButton

public inline fun <T>
    InlineKeyboardButton.ifSwitchInlineQueryChosenChatInlineKeyboardButton(block: (SwitchInlineQueryChosenChatInlineKeyboardButton) -> T):
    T? = switchInlineQueryChosenChatInlineKeyboardButtonOrNull() ?.let(block)

public inline fun InlineKeyboardButton.switchInlineQueryCurrentChatInlineKeyboardButtonOrNull():
    SwitchInlineQueryCurrentChatInlineKeyboardButton? = this as?
    dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.SwitchInlineQueryCurrentChatInlineKeyboardButton

public inline fun InlineKeyboardButton.switchInlineQueryCurrentChatInlineKeyboardButtonOrThrow():
    SwitchInlineQueryCurrentChatInlineKeyboardButton = this as
    dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.SwitchInlineQueryCurrentChatInlineKeyboardButton

public inline fun <T>
    InlineKeyboardButton.ifSwitchInlineQueryCurrentChatInlineKeyboardButton(block: (SwitchInlineQueryCurrentChatInlineKeyboardButton) -> T):
    T? = switchInlineQueryCurrentChatInlineKeyboardButtonOrNull() ?.let(block)

public inline fun InlineKeyboardButton.switchInlineQueryInlineKeyboardButtonOrNull():
    SwitchInlineQueryInlineKeyboardButton? = this as?
    dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.SwitchInlineQueryInlineKeyboardButton

public inline fun InlineKeyboardButton.switchInlineQueryInlineKeyboardButtonOrThrow():
    SwitchInlineQueryInlineKeyboardButton = this as
    dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.SwitchInlineQueryInlineKeyboardButton

public inline fun <T>
    InlineKeyboardButton.ifSwitchInlineQueryInlineKeyboardButton(block: (SwitchInlineQueryInlineKeyboardButton) -> T):
    T? = switchInlineQueryInlineKeyboardButtonOrNull() ?.let(block)

public inline fun InlineKeyboardButton.uRLInlineKeyboardButtonOrNull(): URLInlineKeyboardButton? =
    this as? dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.URLInlineKeyboardButton

public inline fun InlineKeyboardButton.uRLInlineKeyboardButtonOrThrow(): URLInlineKeyboardButton =
    this as dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.URLInlineKeyboardButton

public inline fun <T>
    InlineKeyboardButton.ifURLInlineKeyboardButton(block: (URLInlineKeyboardButton) -> T): T? =
    uRLInlineKeyboardButtonOrNull() ?.let(block)

public inline fun InlineKeyboardButton.unknownInlineKeyboardButtonOrNull():
    UnknownInlineKeyboardButton? = this as?
    dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.UnknownInlineKeyboardButton

public inline fun InlineKeyboardButton.unknownInlineKeyboardButtonOrThrow():
    UnknownInlineKeyboardButton = this as
    dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.UnknownInlineKeyboardButton

public inline fun <T>
    InlineKeyboardButton.ifUnknownInlineKeyboardButton(block: (UnknownInlineKeyboardButton) -> T):
    T? = unknownInlineKeyboardButtonOrNull() ?.let(block)

public inline fun InlineKeyboardButton.webAppInlineKeyboardButtonOrNull():
    WebAppInlineKeyboardButton? = this as?
    dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.WebAppInlineKeyboardButton

public inline fun InlineKeyboardButton.webAppInlineKeyboardButtonOrThrow():
    WebAppInlineKeyboardButton = this as
    dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.WebAppInlineKeyboardButton

public inline fun <T>
    InlineKeyboardButton.ifWebAppInlineKeyboardButton(block: (WebAppInlineKeyboardButton) -> T): T?
    = webAppInlineKeyboardButtonOrNull() ?.let(block)

public inline fun KeyboardButtonRequestUser.anyOrNull(): KeyboardButtonRequestUser.Any? = this as?
    dev.inmo.tgbotapi.types.buttons.KeyboardButtonRequestUser.Any

public inline fun KeyboardButtonRequestUser.anyOrThrow(): KeyboardButtonRequestUser.Any = this as
    dev.inmo.tgbotapi.types.buttons.KeyboardButtonRequestUser.Any

public inline fun <T> KeyboardButtonRequestUser.ifAny(block: (KeyboardButtonRequestUser.Any) -> T):
    T? = anyOrNull() ?.let(block)

public inline fun KeyboardButtonRequestUser.botOrNull(): KeyboardButtonRequestUser.Bot? = this as?
    dev.inmo.tgbotapi.types.buttons.KeyboardButtonRequestUser.Bot

public inline fun KeyboardButtonRequestUser.botOrThrow(): KeyboardButtonRequestUser.Bot = this as
    dev.inmo.tgbotapi.types.buttons.KeyboardButtonRequestUser.Bot

public inline fun <T> KeyboardButtonRequestUser.ifBot(block: (KeyboardButtonRequestUser.Bot) -> T):
    T? = botOrNull() ?.let(block)

public inline fun KeyboardButtonRequestUser.commonOrNull(): KeyboardButtonRequestUser.Common? = this
    as? dev.inmo.tgbotapi.types.buttons.KeyboardButtonRequestUser.Common

public inline fun KeyboardButtonRequestUser.commonOrThrow(): KeyboardButtonRequestUser.Common = this
    as dev.inmo.tgbotapi.types.buttons.KeyboardButtonRequestUser.Common

public inline fun <T>
    KeyboardButtonRequestUser.ifCommon(block: (KeyboardButtonRequestUser.Common) -> T): T? =
    commonOrNull() ?.let(block)

public inline fun KeyboardMarkup.inlineKeyboardMarkupOrNull(): InlineKeyboardMarkup? = this as?
    dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup

public inline fun KeyboardMarkup.inlineKeyboardMarkupOrThrow(): InlineKeyboardMarkup = this as
    dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup

public inline fun <T> KeyboardMarkup.ifInlineKeyboardMarkup(block: (InlineKeyboardMarkup) -> T): T?
    = inlineKeyboardMarkupOrNull() ?.let(block)

public inline fun KeyboardMarkup.replyForceOrNull(): ReplyForce? = this as?
    dev.inmo.tgbotapi.types.buttons.ReplyForce

public inline fun KeyboardMarkup.replyForceOrThrow(): ReplyForce = this as
    dev.inmo.tgbotapi.types.buttons.ReplyForce

public inline fun <T> KeyboardMarkup.ifReplyForce(block: (ReplyForce) -> T): T? = replyForceOrNull()
    ?.let(block)

public inline fun KeyboardMarkup.replyKeyboardMarkupOrNull(): ReplyKeyboardMarkup? = this as?
    dev.inmo.tgbotapi.types.buttons.ReplyKeyboardMarkup

public inline fun KeyboardMarkup.replyKeyboardMarkupOrThrow(): ReplyKeyboardMarkup = this as
    dev.inmo.tgbotapi.types.buttons.ReplyKeyboardMarkup

public inline fun <T> KeyboardMarkup.ifReplyKeyboardMarkup(block: (ReplyKeyboardMarkup) -> T): T? =
    replyKeyboardMarkupOrNull() ?.let(block)

public inline fun KeyboardMarkup.replyKeyboardRemoveOrNull(): ReplyKeyboardRemove? = this as?
    dev.inmo.tgbotapi.types.buttons.ReplyKeyboardRemove

public inline fun KeyboardMarkup.replyKeyboardRemoveOrThrow(): ReplyKeyboardRemove = this as
    dev.inmo.tgbotapi.types.buttons.ReplyKeyboardRemove

public inline fun <T> KeyboardMarkup.ifReplyKeyboardRemove(block: (ReplyKeyboardRemove) -> T): T? =
    replyKeyboardRemoveOrNull() ?.let(block)

public inline fun Chat.ableToAddInAttachmentMenuChatOrNull(): AbleToAddInAttachmentMenuChat? = this
    as? dev.inmo.tgbotapi.types.chat.AbleToAddInAttachmentMenuChat

public inline fun Chat.ableToAddInAttachmentMenuChatOrThrow(): AbleToAddInAttachmentMenuChat = this
    as dev.inmo.tgbotapi.types.chat.AbleToAddInAttachmentMenuChat

public inline fun <T>
    Chat.ifAbleToAddInAttachmentMenuChat(block: (AbleToAddInAttachmentMenuChat) -> T): T? =
    ableToAddInAttachmentMenuChatOrNull() ?.let(block)

public inline fun Chat.commonUserOrNull(): CommonUser? = this as?
    dev.inmo.tgbotapi.types.chat.CommonUser

public inline fun Chat.commonUserOrThrow(): CommonUser = this as
    dev.inmo.tgbotapi.types.chat.CommonUser

public inline fun <T> Chat.ifCommonUser(block: (CommonUser) -> T): T? = commonUserOrNull()
    ?.let(block)

public inline fun Chat.extendedChatOrNull(): ExtendedChat? = this as?
    dev.inmo.tgbotapi.types.chat.ExtendedChat

public inline fun Chat.extendedChatOrThrow(): ExtendedChat = this as
    dev.inmo.tgbotapi.types.chat.ExtendedChat

public inline fun <T> Chat.ifExtendedChat(block: (ExtendedChat) -> T): T? = extendedChatOrNull()
    ?.let(block)

public inline fun Chat.extendedChatWithUsernameOrNull(): ExtendedChatWithUsername? = this as?
    dev.inmo.tgbotapi.types.chat.ExtendedChatWithUsername

public inline fun Chat.extendedChatWithUsernameOrThrow(): ExtendedChatWithUsername = this as
    dev.inmo.tgbotapi.types.chat.ExtendedChatWithUsername

public inline fun <T> Chat.ifExtendedChatWithUsername(block: (ExtendedChatWithUsername) -> T): T? =
    extendedChatWithUsernameOrNull() ?.let(block)

public inline fun Chat.extendedChannelChatOrNull(): ExtendedChannelChat? = this as?
    dev.inmo.tgbotapi.types.chat.ExtendedChannelChat

public inline fun Chat.extendedChannelChatOrThrow(): ExtendedChannelChat = this as
    dev.inmo.tgbotapi.types.chat.ExtendedChannelChat

public inline fun <T> Chat.ifExtendedChannelChat(block: (ExtendedChannelChat) -> T): T? =
    extendedChannelChatOrNull() ?.let(block)

public inline fun Chat.extendedChannelChatImplOrNull(): ExtendedChannelChatImpl? = this as?
    dev.inmo.tgbotapi.types.chat.ExtendedChannelChatImpl

public inline fun Chat.extendedChannelChatImplOrThrow(): ExtendedChannelChatImpl = this as
    dev.inmo.tgbotapi.types.chat.ExtendedChannelChatImpl

public inline fun <T> Chat.ifExtendedChannelChatImpl(block: (ExtendedChannelChatImpl) -> T): T? =
    extendedChannelChatImplOrNull() ?.let(block)

public inline fun Chat.extendedPrivateChatOrNull(): ExtendedPrivateChat? = this as?
    dev.inmo.tgbotapi.types.chat.ExtendedPrivateChat

public inline fun Chat.extendedPrivateChatOrThrow(): ExtendedPrivateChat = this as
    dev.inmo.tgbotapi.types.chat.ExtendedPrivateChat

public inline fun <T> Chat.ifExtendedPrivateChat(block: (ExtendedPrivateChat) -> T): T? =
    extendedPrivateChatOrNull() ?.let(block)

public inline fun Chat.extendedPrivateChatImplOrNull(): ExtendedPrivateChatImpl? = this as?
    dev.inmo.tgbotapi.types.chat.ExtendedPrivateChatImpl

public inline fun Chat.extendedPrivateChatImplOrThrow(): ExtendedPrivateChatImpl = this as
    dev.inmo.tgbotapi.types.chat.ExtendedPrivateChatImpl

public inline fun <T> Chat.ifExtendedPrivateChatImpl(block: (ExtendedPrivateChatImpl) -> T): T? =
    extendedPrivateChatImplOrNull() ?.let(block)

public inline fun Chat.extendedSupergroupChatOrNull(): ExtendedSupergroupChat? = this as?
    dev.inmo.tgbotapi.types.chat.ExtendedSupergroupChat

public inline fun Chat.extendedSupergroupChatOrThrow(): ExtendedSupergroupChat = this as
    dev.inmo.tgbotapi.types.chat.ExtendedSupergroupChat

public inline fun <T> Chat.ifExtendedSupergroupChat(block: (ExtendedSupergroupChat) -> T): T? =
    extendedSupergroupChatOrNull() ?.let(block)

public inline fun Chat.extendedForumChatOrNull(): ExtendedForumChat? = this as?
    dev.inmo.tgbotapi.types.chat.ExtendedForumChat

public inline fun Chat.extendedForumChatOrThrow(): ExtendedForumChat = this as
    dev.inmo.tgbotapi.types.chat.ExtendedForumChat

public inline fun <T> Chat.ifExtendedForumChat(block: (ExtendedForumChat) -> T): T? =
    extendedForumChatOrNull() ?.let(block)

public inline fun Chat.extendedForumChatImplOrNull(): ExtendedForumChatImpl? = this as?
    dev.inmo.tgbotapi.types.chat.ExtendedForumChatImpl

public inline fun Chat.extendedForumChatImplOrThrow(): ExtendedForumChatImpl = this as
    dev.inmo.tgbotapi.types.chat.ExtendedForumChatImpl

public inline fun <T> Chat.ifExtendedForumChatImpl(block: (ExtendedForumChatImpl) -> T): T? =
    extendedForumChatImplOrNull() ?.let(block)

public inline fun Chat.extendedSupergroupChatImplOrNull(): ExtendedSupergroupChatImpl? = this as?
    dev.inmo.tgbotapi.types.chat.ExtendedSupergroupChatImpl

public inline fun Chat.extendedSupergroupChatImplOrThrow(): ExtendedSupergroupChatImpl = this as
    dev.inmo.tgbotapi.types.chat.ExtendedSupergroupChatImpl

public inline fun <T> Chat.ifExtendedSupergroupChatImpl(block: (ExtendedSupergroupChatImpl) -> T):
    T? = extendedSupergroupChatImplOrNull() ?.let(block)

public inline fun Chat.extendedPublicChatOrNull(): ExtendedPublicChat? = this as?
    dev.inmo.tgbotapi.types.chat.ExtendedPublicChat

public inline fun Chat.extendedPublicChatOrThrow(): ExtendedPublicChat = this as
    dev.inmo.tgbotapi.types.chat.ExtendedPublicChat

public inline fun <T> Chat.ifExtendedPublicChat(block: (ExtendedPublicChat) -> T): T? =
    extendedPublicChatOrNull() ?.let(block)

public inline fun Chat.extendedGroupChatOrNull(): ExtendedGroupChat? = this as?
    dev.inmo.tgbotapi.types.chat.ExtendedGroupChat

public inline fun Chat.extendedGroupChatOrThrow(): ExtendedGroupChat = this as
    dev.inmo.tgbotapi.types.chat.ExtendedGroupChat

public inline fun <T> Chat.ifExtendedGroupChat(block: (ExtendedGroupChat) -> T): T? =
    extendedGroupChatOrNull() ?.let(block)

public inline fun Chat.extendedGroupChatImplOrNull(): ExtendedGroupChatImpl? = this as?
    dev.inmo.tgbotapi.types.chat.ExtendedGroupChatImpl

public inline fun Chat.extendedGroupChatImplOrThrow(): ExtendedGroupChatImpl = this as
    dev.inmo.tgbotapi.types.chat.ExtendedGroupChatImpl

public inline fun <T> Chat.ifExtendedGroupChatImpl(block: (ExtendedGroupChatImpl) -> T): T? =
    extendedGroupChatImplOrNull() ?.let(block)

public inline fun Chat.unknownExtendedChatOrNull(): UnknownExtendedChat? = this as?
    dev.inmo.tgbotapi.types.chat.UnknownExtendedChat

public inline fun Chat.unknownExtendedChatOrThrow(): UnknownExtendedChat = this as
    dev.inmo.tgbotapi.types.chat.UnknownExtendedChat

public inline fun <T> Chat.ifUnknownExtendedChat(block: (UnknownExtendedChat) -> T): T? =
    unknownExtendedChatOrNull() ?.let(block)

public inline fun Chat.possiblyPremiumChatOrNull(): PossiblyPremiumChat? = this as?
    dev.inmo.tgbotapi.types.chat.PossiblyPremiumChat

public inline fun Chat.possiblyPremiumChatOrThrow(): PossiblyPremiumChat = this as
    dev.inmo.tgbotapi.types.chat.PossiblyPremiumChat

public inline fun <T> Chat.ifPossiblyPremiumChat(block: (PossiblyPremiumChat) -> T): T? =
    possiblyPremiumChatOrNull() ?.let(block)

public inline fun Chat.privateChatOrNull(): PrivateChat? = this as?
    dev.inmo.tgbotapi.types.chat.PrivateChat

public inline fun Chat.privateChatOrThrow(): PrivateChat = this as
    dev.inmo.tgbotapi.types.chat.PrivateChat

public inline fun <T> Chat.ifPrivateChat(block: (PrivateChat) -> T): T? = privateChatOrNull()
    ?.let(block)

public inline fun Chat.privateChatImplOrNull(): PrivateChatImpl? = this as?
    dev.inmo.tgbotapi.types.chat.PrivateChatImpl

public inline fun Chat.privateChatImplOrThrow(): PrivateChatImpl = this as
    dev.inmo.tgbotapi.types.chat.PrivateChatImpl

public inline fun <T> Chat.ifPrivateChatImpl(block: (PrivateChatImpl) -> T): T? =
    privateChatImplOrNull() ?.let(block)

public inline fun Chat.userOrNull(): User? = this as? dev.inmo.tgbotapi.types.chat.User

public inline fun Chat.userOrThrow(): User = this as dev.inmo.tgbotapi.types.chat.User

public inline fun <T> Chat.ifUser(block: (User) -> T): T? = userOrNull() ?.let(block)

public inline fun Chat.botOrNull(): Bot? = this as? dev.inmo.tgbotapi.types.chat.Bot

public inline fun Chat.botOrThrow(): Bot = this as dev.inmo.tgbotapi.types.chat.Bot

public inline fun <T> Chat.ifBot(block: (Bot) -> T): T? = botOrNull() ?.let(block)

public inline fun Chat.commonBotOrNull(): CommonBot? = this as?
    dev.inmo.tgbotapi.types.chat.CommonBot

public inline fun Chat.commonBotOrThrow(): CommonBot = this as
    dev.inmo.tgbotapi.types.chat.CommonBot

public inline fun <T> Chat.ifCommonBot(block: (CommonBot) -> T): T? = commonBotOrNull() ?.let(block)

public inline fun Chat.extendedBotOrNull(): ExtendedBot? = this as?
    dev.inmo.tgbotapi.types.chat.ExtendedBot

public inline fun Chat.extendedBotOrThrow(): ExtendedBot = this as
    dev.inmo.tgbotapi.types.chat.ExtendedBot

public inline fun <T> Chat.ifExtendedBot(block: (ExtendedBot) -> T): T? = extendedBotOrNull()
    ?.let(block)

public inline fun Chat.publicChatOrNull(): PublicChat? = this as?
    dev.inmo.tgbotapi.types.chat.PublicChat

public inline fun Chat.publicChatOrThrow(): PublicChat = this as
    dev.inmo.tgbotapi.types.chat.PublicChat

public inline fun <T> Chat.ifPublicChat(block: (PublicChat) -> T): T? = publicChatOrNull()
    ?.let(block)

public inline fun Chat.groupChatOrNull(): GroupChat? = this as?
    dev.inmo.tgbotapi.types.chat.GroupChat

public inline fun Chat.groupChatOrThrow(): GroupChat = this as
    dev.inmo.tgbotapi.types.chat.GroupChat

public inline fun <T> Chat.ifGroupChat(block: (GroupChat) -> T): T? = groupChatOrNull() ?.let(block)

public inline fun Chat.groupChatImplOrNull(): GroupChatImpl? = this as?
    dev.inmo.tgbotapi.types.chat.GroupChatImpl

public inline fun Chat.groupChatImplOrThrow(): GroupChatImpl = this as
    dev.inmo.tgbotapi.types.chat.GroupChatImpl

public inline fun <T> Chat.ifGroupChatImpl(block: (GroupChatImpl) -> T): T? = groupChatImplOrNull()
    ?.let(block)

public inline fun Chat.supergroupChatOrNull(): SupergroupChat? = this as?
    dev.inmo.tgbotapi.types.chat.SupergroupChat

public inline fun Chat.supergroupChatOrThrow(): SupergroupChat = this as
    dev.inmo.tgbotapi.types.chat.SupergroupChat

public inline fun <T> Chat.ifSupergroupChat(block: (SupergroupChat) -> T): T? =
    supergroupChatOrNull() ?.let(block)

public inline fun Chat.forumChatOrNull(): ForumChat? = this as?
    dev.inmo.tgbotapi.types.chat.ForumChat

public inline fun Chat.forumChatOrThrow(): ForumChat = this as
    dev.inmo.tgbotapi.types.chat.ForumChat

public inline fun <T> Chat.ifForumChat(block: (ForumChat) -> T): T? = forumChatOrNull() ?.let(block)

public inline fun Chat.forumChatImplOrNull(): ForumChatImpl? = this as?
    dev.inmo.tgbotapi.types.chat.ForumChatImpl

public inline fun Chat.forumChatImplOrThrow(): ForumChatImpl = this as
    dev.inmo.tgbotapi.types.chat.ForumChatImpl

public inline fun <T> Chat.ifForumChatImpl(block: (ForumChatImpl) -> T): T? = forumChatImplOrNull()
    ?.let(block)

public inline fun Chat.supergroupChatImplOrNull(): SupergroupChatImpl? = this as?
    dev.inmo.tgbotapi.types.chat.SupergroupChatImpl

public inline fun Chat.supergroupChatImplOrThrow(): SupergroupChatImpl = this as
    dev.inmo.tgbotapi.types.chat.SupergroupChatImpl

public inline fun <T> Chat.ifSupergroupChatImpl(block: (SupergroupChatImpl) -> T): T? =
    supergroupChatImplOrNull() ?.let(block)

public inline fun Chat.superPublicChatOrNull(): SuperPublicChat? = this as?
    dev.inmo.tgbotapi.types.chat.SuperPublicChat

public inline fun Chat.superPublicChatOrThrow(): SuperPublicChat = this as
    dev.inmo.tgbotapi.types.chat.SuperPublicChat

public inline fun <T> Chat.ifSuperPublicChat(block: (SuperPublicChat) -> T): T? =
    superPublicChatOrNull() ?.let(block)

public inline fun Chat.channelChatOrNull(): ChannelChat? = this as?
    dev.inmo.tgbotapi.types.chat.ChannelChat

public inline fun Chat.channelChatOrThrow(): ChannelChat = this as
    dev.inmo.tgbotapi.types.chat.ChannelChat

public inline fun <T> Chat.ifChannelChat(block: (ChannelChat) -> T): T? = channelChatOrNull()
    ?.let(block)

public inline fun Chat.channelChatImplOrNull(): ChannelChatImpl? = this as?
    dev.inmo.tgbotapi.types.chat.ChannelChatImpl

public inline fun Chat.channelChatImplOrThrow(): ChannelChatImpl = this as
    dev.inmo.tgbotapi.types.chat.ChannelChatImpl

public inline fun <T> Chat.ifChannelChatImpl(block: (ChannelChatImpl) -> T): T? =
    channelChatImplOrNull() ?.let(block)

public inline fun Chat.unknownChatTypeOrNull(): UnknownChatType? = this as?
    dev.inmo.tgbotapi.types.chat.UnknownChatType

public inline fun Chat.unknownChatTypeOrThrow(): UnknownChatType = this as
    dev.inmo.tgbotapi.types.chat.UnknownChatType

public inline fun <T> Chat.ifUnknownChatType(block: (UnknownChatType) -> T): T? =
    unknownChatTypeOrNull() ?.let(block)

public inline fun Chat.usernameChatOrNull(): UsernameChat? = this as?
    dev.inmo.tgbotapi.types.chat.UsernameChat

public inline fun Chat.usernameChatOrThrow(): UsernameChat = this as
    dev.inmo.tgbotapi.types.chat.UsernameChat

public inline fun <T> Chat.ifUsernameChat(block: (UsernameChat) -> T): T? = usernameChatOrNull()
    ?.let(block)

public inline fun DiceAnimationType.basketballDiceAnimationTypeOrNull():
    BasketballDiceAnimationType? = this as? dev.inmo.tgbotapi.types.dice.BasketballDiceAnimationType

public inline fun DiceAnimationType.basketballDiceAnimationTypeOrThrow():
    BasketballDiceAnimationType = this as dev.inmo.tgbotapi.types.dice.BasketballDiceAnimationType

public inline fun <T>
    DiceAnimationType.ifBasketballDiceAnimationType(block: (BasketballDiceAnimationType) -> T): T? =
    basketballDiceAnimationTypeOrNull() ?.let(block)

public inline fun DiceAnimationType.bowlingDiceAnimationTypeOrNull(): BowlingDiceAnimationType? =
    this as? dev.inmo.tgbotapi.types.dice.BowlingDiceAnimationType

public inline fun DiceAnimationType.bowlingDiceAnimationTypeOrThrow(): BowlingDiceAnimationType =
    this as dev.inmo.tgbotapi.types.dice.BowlingDiceAnimationType

public inline fun <T>
    DiceAnimationType.ifBowlingDiceAnimationType(block: (BowlingDiceAnimationType) -> T): T? =
    bowlingDiceAnimationTypeOrNull() ?.let(block)

public inline fun DiceAnimationType.cubeDiceAnimationTypeOrNull(): CubeDiceAnimationType? = this as?
    dev.inmo.tgbotapi.types.dice.CubeDiceAnimationType

public inline fun DiceAnimationType.cubeDiceAnimationTypeOrThrow(): CubeDiceAnimationType = this as
    dev.inmo.tgbotapi.types.dice.CubeDiceAnimationType

public inline fun <T>
    DiceAnimationType.ifCubeDiceAnimationType(block: (CubeDiceAnimationType) -> T): T? =
    cubeDiceAnimationTypeOrNull() ?.let(block)

public inline fun DiceAnimationType.customDiceAnimationTypeOrNull(): CustomDiceAnimationType? = this
    as? dev.inmo.tgbotapi.types.dice.CustomDiceAnimationType

public inline fun DiceAnimationType.customDiceAnimationTypeOrThrow(): CustomDiceAnimationType = this
    as dev.inmo.tgbotapi.types.dice.CustomDiceAnimationType

public inline fun <T>
    DiceAnimationType.ifCustomDiceAnimationType(block: (CustomDiceAnimationType) -> T): T? =
    customDiceAnimationTypeOrNull() ?.let(block)

public inline fun DiceAnimationType.dartsDiceAnimationTypeOrNull(): DartsDiceAnimationType? = this
    as? dev.inmo.tgbotapi.types.dice.DartsDiceAnimationType

public inline fun DiceAnimationType.dartsDiceAnimationTypeOrThrow(): DartsDiceAnimationType = this
    as dev.inmo.tgbotapi.types.dice.DartsDiceAnimationType

public inline fun <T>
    DiceAnimationType.ifDartsDiceAnimationType(block: (DartsDiceAnimationType) -> T): T? =
    dartsDiceAnimationTypeOrNull() ?.let(block)

public inline fun DiceAnimationType.footballDiceAnimationTypeOrNull(): FootballDiceAnimationType? =
    this as? dev.inmo.tgbotapi.types.dice.FootballDiceAnimationType

public inline fun DiceAnimationType.footballDiceAnimationTypeOrThrow(): FootballDiceAnimationType =
    this as dev.inmo.tgbotapi.types.dice.FootballDiceAnimationType

public inline fun <T>
    DiceAnimationType.ifFootballDiceAnimationType(block: (FootballDiceAnimationType) -> T): T? =
    footballDiceAnimationTypeOrNull() ?.let(block)

public inline fun DiceAnimationType.slotMachineDiceAnimationTypeOrNull():
    SlotMachineDiceAnimationType? = this as?
    dev.inmo.tgbotapi.types.dice.SlotMachineDiceAnimationType

public inline fun DiceAnimationType.slotMachineDiceAnimationTypeOrThrow():
    SlotMachineDiceAnimationType = this as dev.inmo.tgbotapi.types.dice.SlotMachineDiceAnimationType

public inline fun <T>
    DiceAnimationType.ifSlotMachineDiceAnimationType(block: (SlotMachineDiceAnimationType) -> T): T?
    = slotMachineDiceAnimationTypeOrNull() ?.let(block)

public inline fun TelegramMediaFile.animationFileOrNull(): AnimationFile? = this as?
    dev.inmo.tgbotapi.types.files.AnimationFile

public inline fun TelegramMediaFile.animationFileOrThrow(): AnimationFile = this as
    dev.inmo.tgbotapi.types.files.AnimationFile

public inline fun <T> TelegramMediaFile.ifAnimationFile(block: (AnimationFile) -> T): T? =
    animationFileOrNull() ?.let(block)

public inline fun TelegramMediaFile.audioFileOrNull(): AudioFile? = this as?
    dev.inmo.tgbotapi.types.files.AudioFile

public inline fun TelegramMediaFile.audioFileOrThrow(): AudioFile = this as
    dev.inmo.tgbotapi.types.files.AudioFile

public inline fun <T> TelegramMediaFile.ifAudioFile(block: (AudioFile) -> T): T? = audioFileOrNull()
    ?.let(block)

public inline fun TelegramMediaFile.documentFileOrNull(): DocumentFile? = this as?
    dev.inmo.tgbotapi.types.files.DocumentFile

public inline fun TelegramMediaFile.documentFileOrThrow(): DocumentFile = this as
    dev.inmo.tgbotapi.types.files.DocumentFile

public inline fun <T> TelegramMediaFile.ifDocumentFile(block: (DocumentFile) -> T): T? =
    documentFileOrNull() ?.let(block)

public inline fun TelegramMediaFile.fileOrNull(): File? = this as?
    dev.inmo.tgbotapi.types.files.File

public inline fun TelegramMediaFile.fileOrThrow(): File = this as dev.inmo.tgbotapi.types.files.File

public inline fun <T> TelegramMediaFile.ifFile(block: (File) -> T): T? = fileOrNull() ?.let(block)

public inline fun TelegramMediaFile.mimedMediaFileOrNull(): MimedMediaFile? = this as?
    dev.inmo.tgbotapi.types.files.MimedMediaFile

public inline fun TelegramMediaFile.mimedMediaFileOrThrow(): MimedMediaFile = this as
    dev.inmo.tgbotapi.types.files.MimedMediaFile

public inline fun <T> TelegramMediaFile.ifMimedMediaFile(block: (MimedMediaFile) -> T): T? =
    mimedMediaFileOrNull() ?.let(block)

public inline fun TelegramMediaFile.videoFileOrNull(): VideoFile? = this as?
    dev.inmo.tgbotapi.types.files.VideoFile

public inline fun TelegramMediaFile.videoFileOrThrow(): VideoFile = this as
    dev.inmo.tgbotapi.types.files.VideoFile

public inline fun <T> TelegramMediaFile.ifVideoFile(block: (VideoFile) -> T): T? = videoFileOrNull()
    ?.let(block)

public inline fun TelegramMediaFile.voiceFileOrNull(): VoiceFile? = this as?
    dev.inmo.tgbotapi.types.files.VoiceFile

public inline fun TelegramMediaFile.voiceFileOrThrow(): VoiceFile = this as
    dev.inmo.tgbotapi.types.files.VoiceFile

public inline fun <T> TelegramMediaFile.ifVoiceFile(block: (VoiceFile) -> T): T? = voiceFileOrNull()
    ?.let(block)

public inline fun TelegramMediaFile.passportFileOrNull(): PassportFile? = this as?
    dev.inmo.tgbotapi.types.files.PassportFile

public inline fun TelegramMediaFile.passportFileOrThrow(): PassportFile = this as
    dev.inmo.tgbotapi.types.files.PassportFile

public inline fun <T> TelegramMediaFile.ifPassportFile(block: (PassportFile) -> T): T? =
    passportFileOrNull() ?.let(block)

public inline fun TelegramMediaFile.pathedFileOrNull(): PathedFile? = this as?
    dev.inmo.tgbotapi.types.files.PathedFile

public inline fun TelegramMediaFile.pathedFileOrThrow(): PathedFile = this as
    dev.inmo.tgbotapi.types.files.PathedFile

public inline fun <T> TelegramMediaFile.ifPathedFile(block: (PathedFile) -> T): T? =
    pathedFileOrNull() ?.let(block)

public inline fun TelegramMediaFile.playableMediaFileOrNull(): PlayableMediaFile? = this as?
    dev.inmo.tgbotapi.types.files.PlayableMediaFile

public inline fun TelegramMediaFile.playableMediaFileOrThrow(): PlayableMediaFile = this as
    dev.inmo.tgbotapi.types.files.PlayableMediaFile

public inline fun <T> TelegramMediaFile.ifPlayableMediaFile(block: (PlayableMediaFile) -> T): T? =
    playableMediaFileOrNull() ?.let(block)

public inline fun TelegramMediaFile.videoNoteFileOrNull(): VideoNoteFile? = this as?
    dev.inmo.tgbotapi.types.files.VideoNoteFile

public inline fun TelegramMediaFile.videoNoteFileOrThrow(): VideoNoteFile = this as
    dev.inmo.tgbotapi.types.files.VideoNoteFile

public inline fun <T> TelegramMediaFile.ifVideoNoteFile(block: (VideoNoteFile) -> T): T? =
    videoNoteFileOrNull() ?.let(block)

public inline fun TelegramMediaFile.sizedMediaFileOrNull(): SizedMediaFile? = this as?
    dev.inmo.tgbotapi.types.files.SizedMediaFile

public inline fun TelegramMediaFile.sizedMediaFileOrThrow(): SizedMediaFile = this as
    dev.inmo.tgbotapi.types.files.SizedMediaFile

public inline fun <T> TelegramMediaFile.ifSizedMediaFile(block: (SizedMediaFile) -> T): T? =
    sizedMediaFileOrNull() ?.let(block)

public inline fun TelegramMediaFile.photoSizeOrNull(): PhotoSize? = this as?
    dev.inmo.tgbotapi.types.files.PhotoSize

public inline fun TelegramMediaFile.photoSizeOrThrow(): PhotoSize = this as
    dev.inmo.tgbotapi.types.files.PhotoSize

public inline fun <T> TelegramMediaFile.ifPhotoSize(block: (PhotoSize) -> T): T? = photoSizeOrNull()
    ?.let(block)

public inline fun TelegramMediaFile.stickerOrNull(): Sticker? = this as?
    dev.inmo.tgbotapi.types.files.Sticker

public inline fun TelegramMediaFile.stickerOrThrow(): Sticker = this as
    dev.inmo.tgbotapi.types.files.Sticker

public inline fun <T> TelegramMediaFile.ifSticker(block: (Sticker) -> T): T? = stickerOrNull()
    ?.let(block)

public inline fun TelegramMediaFile.animatedStickerOrNull(): AnimatedSticker? = this as?
    dev.inmo.tgbotapi.types.files.AnimatedSticker

public inline fun TelegramMediaFile.animatedStickerOrThrow(): AnimatedSticker = this as
    dev.inmo.tgbotapi.types.files.AnimatedSticker

public inline fun <T> TelegramMediaFile.ifAnimatedSticker(block: (AnimatedSticker) -> T): T? =
    animatedStickerOrNull() ?.let(block)

public inline fun TelegramMediaFile.customEmojiAnimatedStickerOrNull(): CustomEmojiAnimatedSticker?
    = this as? dev.inmo.tgbotapi.types.files.CustomEmojiAnimatedSticker

public inline fun TelegramMediaFile.customEmojiAnimatedStickerOrThrow(): CustomEmojiAnimatedSticker
    = this as dev.inmo.tgbotapi.types.files.CustomEmojiAnimatedSticker

public inline fun <T>
    TelegramMediaFile.ifCustomEmojiAnimatedSticker(block: (CustomEmojiAnimatedSticker) -> T): T? =
    customEmojiAnimatedStickerOrNull() ?.let(block)

public inline fun TelegramMediaFile.maskAnimatedStickerOrNull(): MaskAnimatedSticker? = this as?
    dev.inmo.tgbotapi.types.files.MaskAnimatedSticker

public inline fun TelegramMediaFile.maskAnimatedStickerOrThrow(): MaskAnimatedSticker = this as
    dev.inmo.tgbotapi.types.files.MaskAnimatedSticker

public inline fun <T> TelegramMediaFile.ifMaskAnimatedSticker(block: (MaskAnimatedSticker) -> T): T?
    = maskAnimatedStickerOrNull() ?.let(block)

public inline fun TelegramMediaFile.regularAnimatedStickerOrNull(): RegularAnimatedSticker? = this
    as? dev.inmo.tgbotapi.types.files.RegularAnimatedSticker

public inline fun TelegramMediaFile.regularAnimatedStickerOrThrow(): RegularAnimatedSticker = this
    as dev.inmo.tgbotapi.types.files.RegularAnimatedSticker

public inline fun <T>
    TelegramMediaFile.ifRegularAnimatedSticker(block: (RegularAnimatedSticker) -> T): T? =
    regularAnimatedStickerOrNull() ?.let(block)

public inline fun TelegramMediaFile.customEmojiStickerOrNull(): CustomEmojiSticker? = this as?
    dev.inmo.tgbotapi.types.files.CustomEmojiSticker

public inline fun TelegramMediaFile.customEmojiStickerOrThrow(): CustomEmojiSticker = this as
    dev.inmo.tgbotapi.types.files.CustomEmojiSticker

public inline fun <T> TelegramMediaFile.ifCustomEmojiSticker(block: (CustomEmojiSticker) -> T): T? =
    customEmojiStickerOrNull() ?.let(block)

public inline fun TelegramMediaFile.customEmojiSimpleStickerOrNull(): CustomEmojiSimpleSticker? =
    this as? dev.inmo.tgbotapi.types.files.CustomEmojiSimpleSticker

public inline fun TelegramMediaFile.customEmojiSimpleStickerOrThrow(): CustomEmojiSimpleSticker =
    this as dev.inmo.tgbotapi.types.files.CustomEmojiSimpleSticker

public inline fun <T>
    TelegramMediaFile.ifCustomEmojiSimpleSticker(block: (CustomEmojiSimpleSticker) -> T): T? =
    customEmojiSimpleStickerOrNull() ?.let(block)

public inline fun TelegramMediaFile.customEmojiVideoStickerOrNull(): CustomEmojiVideoSticker? = this
    as? dev.inmo.tgbotapi.types.files.CustomEmojiVideoSticker

public inline fun TelegramMediaFile.customEmojiVideoStickerOrThrow(): CustomEmojiVideoSticker = this
    as dev.inmo.tgbotapi.types.files.CustomEmojiVideoSticker

public inline fun <T>
    TelegramMediaFile.ifCustomEmojiVideoSticker(block: (CustomEmojiVideoSticker) -> T): T? =
    customEmojiVideoStickerOrNull() ?.let(block)

public inline fun TelegramMediaFile.maskStickerOrNull(): MaskSticker? = this as?
    dev.inmo.tgbotapi.types.files.MaskSticker

public inline fun TelegramMediaFile.maskStickerOrThrow(): MaskSticker = this as
    dev.inmo.tgbotapi.types.files.MaskSticker

public inline fun <T> TelegramMediaFile.ifMaskSticker(block: (MaskSticker) -> T): T? =
    maskStickerOrNull() ?.let(block)

public inline fun TelegramMediaFile.maskSimpleStickerOrNull(): MaskSimpleSticker? = this as?
    dev.inmo.tgbotapi.types.files.MaskSimpleSticker

public inline fun TelegramMediaFile.maskSimpleStickerOrThrow(): MaskSimpleSticker = this as
    dev.inmo.tgbotapi.types.files.MaskSimpleSticker

public inline fun <T> TelegramMediaFile.ifMaskSimpleSticker(block: (MaskSimpleSticker) -> T): T? =
    maskSimpleStickerOrNull() ?.let(block)

public inline fun TelegramMediaFile.maskVideoStickerOrNull(): MaskVideoSticker? = this as?
    dev.inmo.tgbotapi.types.files.MaskVideoSticker

public inline fun TelegramMediaFile.maskVideoStickerOrThrow(): MaskVideoSticker = this as
    dev.inmo.tgbotapi.types.files.MaskVideoSticker

public inline fun <T> TelegramMediaFile.ifMaskVideoSticker(block: (MaskVideoSticker) -> T): T? =
    maskVideoStickerOrNull() ?.let(block)

public inline fun TelegramMediaFile.regularStickerOrNull(): RegularSticker? = this as?
    dev.inmo.tgbotapi.types.files.RegularSticker

public inline fun TelegramMediaFile.regularStickerOrThrow(): RegularSticker = this as
    dev.inmo.tgbotapi.types.files.RegularSticker

public inline fun <T> TelegramMediaFile.ifRegularSticker(block: (RegularSticker) -> T): T? =
    regularStickerOrNull() ?.let(block)

public inline fun TelegramMediaFile.regularSimpleStickerOrNull(): RegularSimpleSticker? = this as?
    dev.inmo.tgbotapi.types.files.RegularSimpleSticker

public inline fun TelegramMediaFile.regularSimpleStickerOrThrow(): RegularSimpleSticker = this as
    dev.inmo.tgbotapi.types.files.RegularSimpleSticker

public inline fun <T> TelegramMediaFile.ifRegularSimpleSticker(block: (RegularSimpleSticker) -> T):
    T? = regularSimpleStickerOrNull() ?.let(block)

public inline fun TelegramMediaFile.regularVideoStickerOrNull(): RegularVideoSticker? = this as?
    dev.inmo.tgbotapi.types.files.RegularVideoSticker

public inline fun TelegramMediaFile.regularVideoStickerOrThrow(): RegularVideoSticker = this as
    dev.inmo.tgbotapi.types.files.RegularVideoSticker

public inline fun <T> TelegramMediaFile.ifRegularVideoSticker(block: (RegularVideoSticker) -> T): T?
    = regularVideoStickerOrNull() ?.let(block)

public inline fun TelegramMediaFile.unknownStickerOrNull(): UnknownSticker? = this as?
    dev.inmo.tgbotapi.types.files.UnknownSticker

public inline fun TelegramMediaFile.unknownStickerOrThrow(): UnknownSticker = this as
    dev.inmo.tgbotapi.types.files.UnknownSticker

public inline fun <T> TelegramMediaFile.ifUnknownSticker(block: (UnknownSticker) -> T): T? =
    unknownStickerOrNull() ?.let(block)

public inline fun TelegramMediaFile.videoStickerOrNull(): VideoSticker? = this as?
    dev.inmo.tgbotapi.types.files.VideoSticker

public inline fun TelegramMediaFile.videoStickerOrThrow(): VideoSticker = this as
    dev.inmo.tgbotapi.types.files.VideoSticker

public inline fun <T> TelegramMediaFile.ifVideoSticker(block: (VideoSticker) -> T): T? =
    videoStickerOrNull() ?.let(block)

public inline fun TelegramMediaFile.thumbedMediaFileOrNull(): ThumbedMediaFile? = this as?
    dev.inmo.tgbotapi.types.files.ThumbedMediaFile

public inline fun TelegramMediaFile.thumbedMediaFileOrThrow(): ThumbedMediaFile = this as
    dev.inmo.tgbotapi.types.files.ThumbedMediaFile

public inline fun <T> TelegramMediaFile.ifThumbedMediaFile(block: (ThumbedMediaFile) -> T): T? =
    thumbedMediaFileOrNull() ?.let(block)

public inline fun Location.liveLocationOrNull(): LiveLocation? = this as?
    dev.inmo.tgbotapi.types.location.LiveLocation

public inline fun Location.liveLocationOrThrow(): LiveLocation = this as
    dev.inmo.tgbotapi.types.location.LiveLocation

public inline fun <T> Location.ifLiveLocation(block: (LiveLocation) -> T): T? = liveLocationOrNull()
    ?.let(block)

public inline fun Location.staticLocationOrNull(): StaticLocation? = this as?
    dev.inmo.tgbotapi.types.location.StaticLocation

public inline fun Location.staticLocationOrThrow(): StaticLocation = this as
    dev.inmo.tgbotapi.types.location.StaticLocation

public inline fun <T> Location.ifStaticLocation(block: (StaticLocation) -> T): T? =
    staticLocationOrNull() ?.let(block)

public inline fun TelegramMedia.duratedTelegramMediaOrNull(): DuratedTelegramMedia? = this as?
    dev.inmo.tgbotapi.types.media.DuratedTelegramMedia

public inline fun TelegramMedia.duratedTelegramMediaOrThrow(): DuratedTelegramMedia = this as
    dev.inmo.tgbotapi.types.media.DuratedTelegramMedia

public inline fun <T> TelegramMedia.ifDuratedTelegramMedia(block: (DuratedTelegramMedia) -> T): T? =
    duratedTelegramMediaOrNull() ?.let(block)

public inline fun TelegramMedia.telegramMediaAnimationOrNull(): TelegramMediaAnimation? = this as?
    dev.inmo.tgbotapi.types.media.TelegramMediaAnimation

public inline fun TelegramMedia.telegramMediaAnimationOrThrow(): TelegramMediaAnimation = this as
    dev.inmo.tgbotapi.types.media.TelegramMediaAnimation

public inline fun <T> TelegramMedia.ifTelegramMediaAnimation(block: (TelegramMediaAnimation) -> T):
    T? = telegramMediaAnimationOrNull() ?.let(block)

public inline fun TelegramMedia.telegramMediaAudioOrNull(): TelegramMediaAudio? = this as?
    dev.inmo.tgbotapi.types.media.TelegramMediaAudio

public inline fun TelegramMedia.telegramMediaAudioOrThrow(): TelegramMediaAudio = this as
    dev.inmo.tgbotapi.types.media.TelegramMediaAudio

public inline fun <T> TelegramMedia.ifTelegramMediaAudio(block: (TelegramMediaAudio) -> T): T? =
    telegramMediaAudioOrNull() ?.let(block)

public inline fun TelegramMedia.telegramMediaVideoOrNull(): TelegramMediaVideo? = this as?
    dev.inmo.tgbotapi.types.media.TelegramMediaVideo

public inline fun TelegramMedia.telegramMediaVideoOrThrow(): TelegramMediaVideo = this as
    dev.inmo.tgbotapi.types.media.TelegramMediaVideo

public inline fun <T> TelegramMedia.ifTelegramMediaVideo(block: (TelegramMediaVideo) -> T): T? =
    telegramMediaVideoOrNull() ?.let(block)

public inline fun TelegramMedia.mediaGroupMemberTelegramMediaOrNull():
    MediaGroupMemberTelegramMedia? = this as?
    dev.inmo.tgbotapi.types.media.MediaGroupMemberTelegramMedia

public inline fun TelegramMedia.mediaGroupMemberTelegramMediaOrThrow():
    MediaGroupMemberTelegramMedia = this as
    dev.inmo.tgbotapi.types.media.MediaGroupMemberTelegramMedia

public inline fun <T>
    TelegramMedia.ifMediaGroupMemberTelegramMedia(block: (MediaGroupMemberTelegramMedia) -> T): T? =
    mediaGroupMemberTelegramMediaOrNull() ?.let(block)

public inline fun TelegramMedia.audioMediaGroupMemberTelegramMediaOrNull():
    AudioMediaGroupMemberTelegramMedia? = this as?
    dev.inmo.tgbotapi.types.media.AudioMediaGroupMemberTelegramMedia

public inline fun TelegramMedia.audioMediaGroupMemberTelegramMediaOrThrow():
    AudioMediaGroupMemberTelegramMedia = this as
    dev.inmo.tgbotapi.types.media.AudioMediaGroupMemberTelegramMedia

public inline fun <T>
    TelegramMedia.ifAudioMediaGroupMemberTelegramMedia(block: (AudioMediaGroupMemberTelegramMedia) -> T):
    T? = audioMediaGroupMemberTelegramMediaOrNull() ?.let(block)

public inline fun TelegramMedia.documentMediaGroupMemberTelegramMediaOrNull():
    DocumentMediaGroupMemberTelegramMedia? = this as?
    dev.inmo.tgbotapi.types.media.DocumentMediaGroupMemberTelegramMedia

public inline fun TelegramMedia.documentMediaGroupMemberTelegramMediaOrThrow():
    DocumentMediaGroupMemberTelegramMedia = this as
    dev.inmo.tgbotapi.types.media.DocumentMediaGroupMemberTelegramMedia

public inline fun <T>
    TelegramMedia.ifDocumentMediaGroupMemberTelegramMedia(block: (DocumentMediaGroupMemberTelegramMedia) -> T):
    T? = documentMediaGroupMemberTelegramMediaOrNull() ?.let(block)

public inline fun TelegramMedia.telegramMediaDocumentOrNull(): TelegramMediaDocument? = this as?
    dev.inmo.tgbotapi.types.media.TelegramMediaDocument

public inline fun TelegramMedia.telegramMediaDocumentOrThrow(): TelegramMediaDocument = this as
    dev.inmo.tgbotapi.types.media.TelegramMediaDocument

public inline fun <T> TelegramMedia.ifTelegramMediaDocument(block: (TelegramMediaDocument) -> T): T?
    = telegramMediaDocumentOrNull() ?.let(block)

public inline fun TelegramMedia.visualMediaGroupMemberTelegramMediaOrNull():
    VisualMediaGroupMemberTelegramMedia? = this as?
    dev.inmo.tgbotapi.types.media.VisualMediaGroupMemberTelegramMedia

public inline fun TelegramMedia.visualMediaGroupMemberTelegramMediaOrThrow():
    VisualMediaGroupMemberTelegramMedia = this as
    dev.inmo.tgbotapi.types.media.VisualMediaGroupMemberTelegramMedia

public inline fun <T>
    TelegramMedia.ifVisualMediaGroupMemberTelegramMedia(block: (VisualMediaGroupMemberTelegramMedia) -> T):
    T? = visualMediaGroupMemberTelegramMediaOrNull() ?.let(block)

public inline fun TelegramMedia.telegramMediaPhotoOrNull(): TelegramMediaPhoto? = this as?
    dev.inmo.tgbotapi.types.media.TelegramMediaPhoto

public inline fun TelegramMedia.telegramMediaPhotoOrThrow(): TelegramMediaPhoto = this as
    dev.inmo.tgbotapi.types.media.TelegramMediaPhoto

public inline fun <T> TelegramMedia.ifTelegramMediaPhoto(block: (TelegramMediaPhoto) -> T): T? =
    telegramMediaPhotoOrNull() ?.let(block)

public inline fun TelegramMedia.sizedTelegramMediaOrNull(): SizedTelegramMedia? = this as?
    dev.inmo.tgbotapi.types.media.SizedTelegramMedia

public inline fun TelegramMedia.sizedTelegramMediaOrThrow(): SizedTelegramMedia = this as
    dev.inmo.tgbotapi.types.media.SizedTelegramMedia

public inline fun <T> TelegramMedia.ifSizedTelegramMedia(block: (SizedTelegramMedia) -> T): T? =
    sizedTelegramMediaOrNull() ?.let(block)

public inline fun TelegramMedia.spoilerableTelegramMediaOrNull(): SpoilerableTelegramMedia? = this
    as? dev.inmo.tgbotapi.types.media.SpoilerableTelegramMedia

public inline fun TelegramMedia.spoilerableTelegramMediaOrThrow(): SpoilerableTelegramMedia = this
    as dev.inmo.tgbotapi.types.media.SpoilerableTelegramMedia

public inline fun <T>
    TelegramMedia.ifSpoilerableTelegramMedia(block: (SpoilerableTelegramMedia) -> T): T? =
    spoilerableTelegramMediaOrNull() ?.let(block)

public inline fun TelegramMedia.thumbedTelegramMediaOrNull(): ThumbedTelegramMedia? = this as?
    dev.inmo.tgbotapi.types.media.ThumbedTelegramMedia

public inline fun TelegramMedia.thumbedTelegramMediaOrThrow(): ThumbedTelegramMedia = this as
    dev.inmo.tgbotapi.types.media.ThumbedTelegramMedia

public inline fun <T> TelegramMedia.ifThumbedTelegramMedia(block: (ThumbedTelegramMedia) -> T): T? =
    thumbedTelegramMediaOrNull() ?.let(block)

public inline fun TelegramMedia.titledTelegramMediaOrNull(): TitledTelegramMedia? = this as?
    dev.inmo.tgbotapi.types.media.TitledTelegramMedia

public inline fun TelegramMedia.titledTelegramMediaOrThrow(): TitledTelegramMedia = this as
    dev.inmo.tgbotapi.types.media.TitledTelegramMedia

public inline fun <T> TelegramMedia.ifTitledTelegramMedia(block: (TitledTelegramMedia) -> T): T? =
    titledTelegramMediaOrNull() ?.let(block)

public inline fun ForwardInfo.byAnonymousOrNull(): ForwardInfo.ByAnonymous? = this as?
    dev.inmo.tgbotapi.types.message.ForwardInfo.ByAnonymous

public inline fun ForwardInfo.byAnonymousOrThrow(): ForwardInfo.ByAnonymous = this as
    dev.inmo.tgbotapi.types.message.ForwardInfo.ByAnonymous

public inline fun <T> ForwardInfo.ifByAnonymous(block: (ForwardInfo.ByAnonymous) -> T): T? =
    byAnonymousOrNull() ?.let(block)

public inline fun ForwardInfo.byUserOrNull(): ForwardInfo.ByUser? = this as?
    dev.inmo.tgbotapi.types.message.ForwardInfo.ByUser

public inline fun ForwardInfo.byUserOrThrow(): ForwardInfo.ByUser = this as
    dev.inmo.tgbotapi.types.message.ForwardInfo.ByUser

public inline fun <T> ForwardInfo.ifByUser(block: (ForwardInfo.ByUser) -> T): T? = byUserOrNull()
    ?.let(block)

public inline fun ForwardInfo.publicChatOrNull(): ForwardInfo.PublicChat? = this as?
    dev.inmo.tgbotapi.types.message.ForwardInfo.PublicChat

public inline fun ForwardInfo.publicChatOrThrow(): ForwardInfo.PublicChat = this as
    dev.inmo.tgbotapi.types.message.ForwardInfo.PublicChat

public inline fun <T> ForwardInfo.ifPublicChat(block: (ForwardInfo.PublicChat) -> T): T? =
    publicChatOrNull() ?.let(block)

public inline fun ForwardInfo.fromChannelOrNull(): ForwardInfo.PublicChat.FromChannel? = this as?
    dev.inmo.tgbotapi.types.message.ForwardInfo.PublicChat.FromChannel

public inline fun ForwardInfo.fromChannelOrThrow(): ForwardInfo.PublicChat.FromChannel = this as
    dev.inmo.tgbotapi.types.message.ForwardInfo.PublicChat.FromChannel

public inline fun <T> ForwardInfo.ifFromChannel(block: (ForwardInfo.PublicChat.FromChannel) -> T):
    T? = fromChannelOrNull() ?.let(block)

public inline fun ForwardInfo.fromSupergroupOrNull(): ForwardInfo.PublicChat.FromSupergroup? = this
    as? dev.inmo.tgbotapi.types.message.ForwardInfo.PublicChat.FromSupergroup

public inline fun ForwardInfo.fromSupergroupOrThrow(): ForwardInfo.PublicChat.FromSupergroup = this
    as dev.inmo.tgbotapi.types.message.ForwardInfo.PublicChat.FromSupergroup

public inline fun <T>
    ForwardInfo.ifFromSupergroup(block: (ForwardInfo.PublicChat.FromSupergroup) -> T): T? =
    fromSupergroupOrNull() ?.let(block)

public inline fun ForwardInfo.sentByChannelOrNull(): ForwardInfo.PublicChat.SentByChannel? = this
    as? dev.inmo.tgbotapi.types.message.ForwardInfo.PublicChat.SentByChannel

public inline fun ForwardInfo.sentByChannelOrThrow(): ForwardInfo.PublicChat.SentByChannel = this as
    dev.inmo.tgbotapi.types.message.ForwardInfo.PublicChat.SentByChannel

public inline fun <T>
    ForwardInfo.ifSentByChannel(block: (ForwardInfo.PublicChat.SentByChannel) -> T): T? =
    sentByChannelOrNull() ?.let(block)

public inline fun ResendableContent.messageContentOrNull(): MessageContent? = this as?
    dev.inmo.tgbotapi.types.message.content.MessageContent

public inline fun ResendableContent.messageContentOrThrow(): MessageContent = this as
    dev.inmo.tgbotapi.types.message.content.MessageContent

public inline fun <T> ResendableContent.ifMessageContent(block: (MessageContent) -> T): T? =
    messageContentOrNull() ?.let(block)

public inline fun ResendableContent.contactContentOrNull(): ContactContent? = this as?
    dev.inmo.tgbotapi.types.message.content.ContactContent

public inline fun ResendableContent.contactContentOrThrow(): ContactContent = this as
    dev.inmo.tgbotapi.types.message.content.ContactContent

public inline fun <T> ResendableContent.ifContactContent(block: (ContactContent) -> T): T? =
    contactContentOrNull() ?.let(block)

public inline fun ResendableContent.diceContentOrNull(): DiceContent? = this as?
    dev.inmo.tgbotapi.types.message.content.DiceContent

public inline fun ResendableContent.diceContentOrThrow(): DiceContent = this as
    dev.inmo.tgbotapi.types.message.content.DiceContent

public inline fun <T> ResendableContent.ifDiceContent(block: (DiceContent) -> T): T? =
    diceContentOrNull() ?.let(block)

public inline fun ResendableContent.gameContentOrNull(): GameContent? = this as?
    dev.inmo.tgbotapi.types.message.content.GameContent

public inline fun ResendableContent.gameContentOrThrow(): GameContent = this as
    dev.inmo.tgbotapi.types.message.content.GameContent

public inline fun <T> ResendableContent.ifGameContent(block: (GameContent) -> T): T? =
    gameContentOrNull() ?.let(block)

public inline fun ResendableContent.invoiceContentOrNull(): InvoiceContent? = this as?
    dev.inmo.tgbotapi.types.message.content.InvoiceContent

public inline fun ResendableContent.invoiceContentOrThrow(): InvoiceContent = this as
    dev.inmo.tgbotapi.types.message.content.InvoiceContent

public inline fun <T> ResendableContent.ifInvoiceContent(block: (InvoiceContent) -> T): T? =
    invoiceContentOrNull() ?.let(block)

public inline fun ResendableContent.locationContentOrNull(): LocationContent? = this as?
    dev.inmo.tgbotapi.types.message.content.LocationContent

public inline fun ResendableContent.locationContentOrThrow(): LocationContent = this as
    dev.inmo.tgbotapi.types.message.content.LocationContent

public inline fun <T> ResendableContent.ifLocationContent(block: (LocationContent) -> T): T? =
    locationContentOrNull() ?.let(block)

public inline fun ResendableContent.liveLocationContentOrNull(): LiveLocationContent? = this as?
    dev.inmo.tgbotapi.types.message.content.LiveLocationContent

public inline fun ResendableContent.liveLocationContentOrThrow(): LiveLocationContent = this as
    dev.inmo.tgbotapi.types.message.content.LiveLocationContent

public inline fun <T> ResendableContent.ifLiveLocationContent(block: (LiveLocationContent) -> T): T?
    = liveLocationContentOrNull() ?.let(block)

public inline fun ResendableContent.staticLocationContentOrNull(): StaticLocationContent? = this as?
    dev.inmo.tgbotapi.types.message.content.StaticLocationContent

public inline fun ResendableContent.staticLocationContentOrThrow(): StaticLocationContent = this as
    dev.inmo.tgbotapi.types.message.content.StaticLocationContent

public inline fun <T>
    ResendableContent.ifStaticLocationContent(block: (StaticLocationContent) -> T): T? =
    staticLocationContentOrNull() ?.let(block)

public inline fun ResendableContent.mediaCollectionContentOrNull():
    MediaCollectionContent<TelegramMediaFile>? = this as?
    dev.inmo.tgbotapi.types.message.content.MediaCollectionContent<dev.inmo.tgbotapi.types.files.TelegramMediaFile>

public inline fun ResendableContent.mediaCollectionContentOrThrow():
    MediaCollectionContent<TelegramMediaFile> = this as
    dev.inmo.tgbotapi.types.message.content.MediaCollectionContent<dev.inmo.tgbotapi.types.files.TelegramMediaFile>

public inline fun <T>
    ResendableContent.ifMediaCollectionContent(block: (MediaCollectionContent<TelegramMediaFile>) -> T):
    T? = mediaCollectionContentOrNull() ?.let(block)

public inline fun ResendableContent.photoContentOrNull(): PhotoContent? = this as?
    dev.inmo.tgbotapi.types.message.content.PhotoContent

public inline fun ResendableContent.photoContentOrThrow(): PhotoContent = this as
    dev.inmo.tgbotapi.types.message.content.PhotoContent

public inline fun <T> ResendableContent.ifPhotoContent(block: (PhotoContent) -> T): T? =
    photoContentOrNull() ?.let(block)

public inline fun ResendableContent.mediaContentOrNull(): MediaContent? = this as?
    dev.inmo.tgbotapi.types.message.content.MediaContent

public inline fun ResendableContent.mediaContentOrThrow(): MediaContent = this as
    dev.inmo.tgbotapi.types.message.content.MediaContent

public inline fun <T> ResendableContent.ifMediaContent(block: (MediaContent) -> T): T? =
    mediaContentOrNull() ?.let(block)

public inline fun ResendableContent.spoilerableMediaContentOrNull(): SpoilerableMediaContent? = this
    as? dev.inmo.tgbotapi.types.message.content.SpoilerableMediaContent

public inline fun ResendableContent.spoilerableMediaContentOrThrow(): SpoilerableMediaContent = this
    as dev.inmo.tgbotapi.types.message.content.SpoilerableMediaContent

public inline fun <T>
    ResendableContent.ifSpoilerableMediaContent(block: (SpoilerableMediaContent) -> T): T? =
    spoilerableMediaContentOrNull() ?.let(block)

public inline fun ResendableContent.animationContentOrNull(): AnimationContent? = this as?
    dev.inmo.tgbotapi.types.message.content.AnimationContent

public inline fun ResendableContent.animationContentOrThrow(): AnimationContent = this as
    dev.inmo.tgbotapi.types.message.content.AnimationContent

public inline fun <T> ResendableContent.ifAnimationContent(block: (AnimationContent) -> T): T? =
    animationContentOrNull() ?.let(block)

public inline fun ResendableContent.visualMediaGroupPartContentOrNull():
    VisualMediaGroupPartContent? = this as?
    dev.inmo.tgbotapi.types.message.content.VisualMediaGroupPartContent

public inline fun ResendableContent.visualMediaGroupPartContentOrThrow():
    VisualMediaGroupPartContent = this as
    dev.inmo.tgbotapi.types.message.content.VisualMediaGroupPartContent

public inline fun <T>
    ResendableContent.ifVisualMediaGroupPartContent(block: (VisualMediaGroupPartContent) -> T): T? =
    visualMediaGroupPartContentOrNull() ?.let(block)

public inline fun ResendableContent.videoContentOrNull(): VideoContent? = this as?
    dev.inmo.tgbotapi.types.message.content.VideoContent

public inline fun ResendableContent.videoContentOrThrow(): VideoContent = this as
    dev.inmo.tgbotapi.types.message.content.VideoContent

public inline fun <T> ResendableContent.ifVideoContent(block: (VideoContent) -> T): T? =
    videoContentOrNull() ?.let(block)

public inline fun ResendableContent.stickerContentOrNull(): StickerContent? = this as?
    dev.inmo.tgbotapi.types.message.content.StickerContent

public inline fun ResendableContent.stickerContentOrThrow(): StickerContent = this as
    dev.inmo.tgbotapi.types.message.content.StickerContent

public inline fun <T> ResendableContent.ifStickerContent(block: (StickerContent) -> T): T? =
    stickerContentOrNull() ?.let(block)

public inline fun ResendableContent.textedMediaContentOrNull(): TextedMediaContent? = this as?
    dev.inmo.tgbotapi.types.message.content.TextedMediaContent

public inline fun ResendableContent.textedMediaContentOrThrow(): TextedMediaContent = this as
    dev.inmo.tgbotapi.types.message.content.TextedMediaContent

public inline fun <T> ResendableContent.ifTextedMediaContent(block: (TextedMediaContent) -> T): T? =
    textedMediaContentOrNull() ?.let(block)

public inline fun ResendableContent.mediaGroupCollectionContentOrNull():
    MediaGroupCollectionContent<MediaGroupPartContent>? = this as?
    dev.inmo.tgbotapi.types.message.content.MediaGroupCollectionContent<dev.inmo.tgbotapi.types.message.content.MediaGroupPartContent>

public inline fun ResendableContent.mediaGroupCollectionContentOrThrow():
    MediaGroupCollectionContent<MediaGroupPartContent> = this as
    dev.inmo.tgbotapi.types.message.content.MediaGroupCollectionContent<dev.inmo.tgbotapi.types.message.content.MediaGroupPartContent>

public inline fun <T>
    ResendableContent.ifMediaGroupCollectionContent(block: (MediaGroupCollectionContent<MediaGroupPartContent>) -> T):
    T? = mediaGroupCollectionContentOrNull() ?.let(block)

public inline fun ResendableContent.mediaGroupContentOrNull():
    MediaGroupContent<MediaGroupPartContent>? = this as?
    dev.inmo.tgbotapi.types.message.content.MediaGroupContent<dev.inmo.tgbotapi.types.message.content.MediaGroupPartContent>

public inline fun ResendableContent.mediaGroupContentOrThrow():
    MediaGroupContent<MediaGroupPartContent> = this as
    dev.inmo.tgbotapi.types.message.content.MediaGroupContent<dev.inmo.tgbotapi.types.message.content.MediaGroupPartContent>

public inline fun <T>
    ResendableContent.ifMediaGroupContent(block: (MediaGroupContent<MediaGroupPartContent>) -> T):
    T? = mediaGroupContentOrNull() ?.let(block)

public inline fun ResendableContent.mediaGroupPartContentOrNull(): MediaGroupPartContent? = this as?
    dev.inmo.tgbotapi.types.message.content.MediaGroupPartContent

public inline fun ResendableContent.mediaGroupPartContentOrThrow(): MediaGroupPartContent = this as
    dev.inmo.tgbotapi.types.message.content.MediaGroupPartContent

public inline fun <T>
    ResendableContent.ifMediaGroupPartContent(block: (MediaGroupPartContent) -> T): T? =
    mediaGroupPartContentOrNull() ?.let(block)

public inline fun ResendableContent.audioMediaGroupPartContentOrNull(): AudioMediaGroupPartContent?
    = this as? dev.inmo.tgbotapi.types.message.content.AudioMediaGroupPartContent

public inline fun ResendableContent.audioMediaGroupPartContentOrThrow(): AudioMediaGroupPartContent
    = this as dev.inmo.tgbotapi.types.message.content.AudioMediaGroupPartContent

public inline fun <T>
    ResendableContent.ifAudioMediaGroupPartContent(block: (AudioMediaGroupPartContent) -> T): T? =
    audioMediaGroupPartContentOrNull() ?.let(block)

public inline fun ResendableContent.audioContentOrNull(): AudioContent? = this as?
    dev.inmo.tgbotapi.types.message.content.AudioContent

public inline fun ResendableContent.audioContentOrThrow(): AudioContent = this as
    dev.inmo.tgbotapi.types.message.content.AudioContent

public inline fun <T> ResendableContent.ifAudioContent(block: (AudioContent) -> T): T? =
    audioContentOrNull() ?.let(block)

public inline fun ResendableContent.documentMediaGroupPartContentOrNull():
    DocumentMediaGroupPartContent? = this as?
    dev.inmo.tgbotapi.types.message.content.DocumentMediaGroupPartContent

public inline fun ResendableContent.documentMediaGroupPartContentOrThrow():
    DocumentMediaGroupPartContent = this as
    dev.inmo.tgbotapi.types.message.content.DocumentMediaGroupPartContent

public inline fun <T>
    ResendableContent.ifDocumentMediaGroupPartContent(block: (DocumentMediaGroupPartContent) -> T):
    T? = documentMediaGroupPartContentOrNull() ?.let(block)

public inline fun ResendableContent.documentContentOrNull(): DocumentContent? = this as?
    dev.inmo.tgbotapi.types.message.content.DocumentContent

public inline fun ResendableContent.documentContentOrThrow(): DocumentContent = this as
    dev.inmo.tgbotapi.types.message.content.DocumentContent

public inline fun <T> ResendableContent.ifDocumentContent(block: (DocumentContent) -> T): T? =
    documentContentOrNull() ?.let(block)

public inline fun ResendableContent.voiceContentOrNull(): VoiceContent? = this as?
    dev.inmo.tgbotapi.types.message.content.VoiceContent

public inline fun ResendableContent.voiceContentOrThrow(): VoiceContent = this as
    dev.inmo.tgbotapi.types.message.content.VoiceContent

public inline fun <T> ResendableContent.ifVoiceContent(block: (VoiceContent) -> T): T? =
    voiceContentOrNull() ?.let(block)

public inline fun ResendableContent.videoNoteContentOrNull(): VideoNoteContent? = this as?
    dev.inmo.tgbotapi.types.message.content.VideoNoteContent

public inline fun ResendableContent.videoNoteContentOrThrow(): VideoNoteContent = this as
    dev.inmo.tgbotapi.types.message.content.VideoNoteContent

public inline fun <T> ResendableContent.ifVideoNoteContent(block: (VideoNoteContent) -> T): T? =
    videoNoteContentOrNull() ?.let(block)

public inline fun ResendableContent.pollContentOrNull(): PollContent? = this as?
    dev.inmo.tgbotapi.types.message.content.PollContent

public inline fun ResendableContent.pollContentOrThrow(): PollContent = this as
    dev.inmo.tgbotapi.types.message.content.PollContent

public inline fun <T> ResendableContent.ifPollContent(block: (PollContent) -> T): T? =
    pollContentOrNull() ?.let(block)

public inline fun ResendableContent.textedContentOrNull(): TextedContent? = this as?
    dev.inmo.tgbotapi.types.message.content.TextedContent

public inline fun ResendableContent.textedContentOrThrow(): TextedContent = this as
    dev.inmo.tgbotapi.types.message.content.TextedContent

public inline fun <T> ResendableContent.ifTextedContent(block: (TextedContent) -> T): T? =
    textedContentOrNull() ?.let(block)

public inline fun ResendableContent.textContentOrNull(): TextContent? = this as?
    dev.inmo.tgbotapi.types.message.content.TextContent

public inline fun ResendableContent.textContentOrThrow(): TextContent = this as
    dev.inmo.tgbotapi.types.message.content.TextContent

public inline fun <T> ResendableContent.ifTextContent(block: (TextContent) -> T): T? =
    textContentOrNull() ?.let(block)

public inline fun ResendableContent.venueContentOrNull(): VenueContent? = this as?
    dev.inmo.tgbotapi.types.message.content.VenueContent

public inline fun ResendableContent.venueContentOrThrow(): VenueContent = this as
    dev.inmo.tgbotapi.types.message.content.VenueContent

public inline fun <T> ResendableContent.ifVenueContent(block: (VenueContent) -> T): T? =
    venueContentOrNull() ?.let(block)

public inline fun TextSource.botCommandTextSourceOrNull(): BotCommandTextSource? = this as?
    dev.inmo.tgbotapi.types.message.textsources.BotCommandTextSource

public inline fun TextSource.botCommandTextSourceOrThrow(): BotCommandTextSource = this as
    dev.inmo.tgbotapi.types.message.textsources.BotCommandTextSource

public inline fun <T> TextSource.ifBotCommandTextSource(block: (BotCommandTextSource) -> T): T? =
    botCommandTextSourceOrNull() ?.let(block)

public inline fun TextSource.codeTextSourceOrNull(): CodeTextSource? = this as?
    dev.inmo.tgbotapi.types.message.textsources.CodeTextSource

public inline fun TextSource.codeTextSourceOrThrow(): CodeTextSource = this as
    dev.inmo.tgbotapi.types.message.textsources.CodeTextSource

public inline fun <T> TextSource.ifCodeTextSource(block: (CodeTextSource) -> T): T? =
    codeTextSourceOrNull() ?.let(block)

public inline fun TextSource.multilevelTextSourceOrNull(): MultilevelTextSource? = this as?
    dev.inmo.tgbotapi.types.message.textsources.MultilevelTextSource

public inline fun TextSource.multilevelTextSourceOrThrow(): MultilevelTextSource = this as
    dev.inmo.tgbotapi.types.message.textsources.MultilevelTextSource

public inline fun <T> TextSource.ifMultilevelTextSource(block: (MultilevelTextSource) -> T): T? =
    multilevelTextSourceOrNull() ?.let(block)

public inline fun TextSource.boldTextSourceOrNull(): BoldTextSource? = this as?
    dev.inmo.tgbotapi.types.message.textsources.BoldTextSource

public inline fun TextSource.boldTextSourceOrThrow(): BoldTextSource = this as
    dev.inmo.tgbotapi.types.message.textsources.BoldTextSource

public inline fun <T> TextSource.ifBoldTextSource(block: (BoldTextSource) -> T): T? =
    boldTextSourceOrNull() ?.let(block)

public inline fun TextSource.cashTagTextSourceOrNull(): CashTagTextSource? = this as?
    dev.inmo.tgbotapi.types.message.textsources.CashTagTextSource

public inline fun TextSource.cashTagTextSourceOrThrow(): CashTagTextSource = this as
    dev.inmo.tgbotapi.types.message.textsources.CashTagTextSource

public inline fun <T> TextSource.ifCashTagTextSource(block: (CashTagTextSource) -> T): T? =
    cashTagTextSourceOrNull() ?.let(block)

public inline fun TextSource.customEmojiTextSourceOrNull(): CustomEmojiTextSource? = this as?
    dev.inmo.tgbotapi.types.message.textsources.CustomEmojiTextSource

public inline fun TextSource.customEmojiTextSourceOrThrow(): CustomEmojiTextSource = this as
    dev.inmo.tgbotapi.types.message.textsources.CustomEmojiTextSource

public inline fun <T> TextSource.ifCustomEmojiTextSource(block: (CustomEmojiTextSource) -> T): T? =
    customEmojiTextSourceOrNull() ?.let(block)

public inline fun TextSource.eMailTextSourceOrNull(): EMailTextSource? = this as?
    dev.inmo.tgbotapi.types.message.textsources.EMailTextSource

public inline fun TextSource.eMailTextSourceOrThrow(): EMailTextSource = this as
    dev.inmo.tgbotapi.types.message.textsources.EMailTextSource

public inline fun <T> TextSource.ifEMailTextSource(block: (EMailTextSource) -> T): T? =
    eMailTextSourceOrNull() ?.let(block)

public inline fun TextSource.hashTagTextSourceOrNull(): HashTagTextSource? = this as?
    dev.inmo.tgbotapi.types.message.textsources.HashTagTextSource

public inline fun TextSource.hashTagTextSourceOrThrow(): HashTagTextSource = this as
    dev.inmo.tgbotapi.types.message.textsources.HashTagTextSource

public inline fun <T> TextSource.ifHashTagTextSource(block: (HashTagTextSource) -> T): T? =
    hashTagTextSourceOrNull() ?.let(block)

public inline fun TextSource.italicTextSourceOrNull(): ItalicTextSource? = this as?
    dev.inmo.tgbotapi.types.message.textsources.ItalicTextSource

public inline fun TextSource.italicTextSourceOrThrow(): ItalicTextSource = this as
    dev.inmo.tgbotapi.types.message.textsources.ItalicTextSource

public inline fun <T> TextSource.ifItalicTextSource(block: (ItalicTextSource) -> T): T? =
    italicTextSourceOrNull() ?.let(block)

public inline fun TextSource.mentionTextSourceOrNull(): MentionTextSource? = this as?
    dev.inmo.tgbotapi.types.message.textsources.MentionTextSource

public inline fun TextSource.mentionTextSourceOrThrow(): MentionTextSource = this as
    dev.inmo.tgbotapi.types.message.textsources.MentionTextSource

public inline fun <T> TextSource.ifMentionTextSource(block: (MentionTextSource) -> T): T? =
    mentionTextSourceOrNull() ?.let(block)

public inline fun TextSource.phoneNumberTextSourceOrNull(): PhoneNumberTextSource? = this as?
    dev.inmo.tgbotapi.types.message.textsources.PhoneNumberTextSource

public inline fun TextSource.phoneNumberTextSourceOrThrow(): PhoneNumberTextSource = this as
    dev.inmo.tgbotapi.types.message.textsources.PhoneNumberTextSource

public inline fun <T> TextSource.ifPhoneNumberTextSource(block: (PhoneNumberTextSource) -> T): T? =
    phoneNumberTextSourceOrNull() ?.let(block)

public inline fun TextSource.spoilerTextSourceOrNull(): SpoilerTextSource? = this as?
    dev.inmo.tgbotapi.types.message.textsources.SpoilerTextSource

public inline fun TextSource.spoilerTextSourceOrThrow(): SpoilerTextSource = this as
    dev.inmo.tgbotapi.types.message.textsources.SpoilerTextSource

public inline fun <T> TextSource.ifSpoilerTextSource(block: (SpoilerTextSource) -> T): T? =
    spoilerTextSourceOrNull() ?.let(block)

public inline fun TextSource.strikethroughTextSourceOrNull(): StrikethroughTextSource? = this as?
    dev.inmo.tgbotapi.types.message.textsources.StrikethroughTextSource

public inline fun TextSource.strikethroughTextSourceOrThrow(): StrikethroughTextSource = this as
    dev.inmo.tgbotapi.types.message.textsources.StrikethroughTextSource

public inline fun <T> TextSource.ifStrikethroughTextSource(block: (StrikethroughTextSource) -> T):
    T? = strikethroughTextSourceOrNull() ?.let(block)

public inline fun TextSource.textMentionTextSourceOrNull(): TextMentionTextSource? = this as?
    dev.inmo.tgbotapi.types.message.textsources.TextMentionTextSource

public inline fun TextSource.textMentionTextSourceOrThrow(): TextMentionTextSource = this as
    dev.inmo.tgbotapi.types.message.textsources.TextMentionTextSource

public inline fun <T> TextSource.ifTextMentionTextSource(block: (TextMentionTextSource) -> T): T? =
    textMentionTextSourceOrNull() ?.let(block)

public inline fun TextSource.underlineTextSourceOrNull(): UnderlineTextSource? = this as?
    dev.inmo.tgbotapi.types.message.textsources.UnderlineTextSource

public inline fun TextSource.underlineTextSourceOrThrow(): UnderlineTextSource = this as
    dev.inmo.tgbotapi.types.message.textsources.UnderlineTextSource

public inline fun <T> TextSource.ifUnderlineTextSource(block: (UnderlineTextSource) -> T): T? =
    underlineTextSourceOrNull() ?.let(block)

public inline fun TextSource.preTextSourceOrNull(): PreTextSource? = this as?
    dev.inmo.tgbotapi.types.message.textsources.PreTextSource

public inline fun TextSource.preTextSourceOrThrow(): PreTextSource = this as
    dev.inmo.tgbotapi.types.message.textsources.PreTextSource

public inline fun <T> TextSource.ifPreTextSource(block: (PreTextSource) -> T): T? =
    preTextSourceOrNull() ?.let(block)

public inline fun TextSource.regularTextSourceOrNull(): RegularTextSource? = this as?
    dev.inmo.tgbotapi.types.message.textsources.RegularTextSource

public inline fun TextSource.regularTextSourceOrThrow(): RegularTextSource = this as
    dev.inmo.tgbotapi.types.message.textsources.RegularTextSource

public inline fun <T> TextSource.ifRegularTextSource(block: (RegularTextSource) -> T): T? =
    regularTextSourceOrNull() ?.let(block)

public inline fun TextSource.textLinkTextSourceOrNull(): TextLinkTextSource? = this as?
    dev.inmo.tgbotapi.types.message.textsources.TextLinkTextSource

public inline fun TextSource.textLinkTextSourceOrThrow(): TextLinkTextSource = this as
    dev.inmo.tgbotapi.types.message.textsources.TextLinkTextSource

public inline fun <T> TextSource.ifTextLinkTextSource(block: (TextLinkTextSource) -> T): T? =
    textLinkTextSourceOrNull() ?.let(block)

public inline fun TextSource.uRLTextSourceOrNull(): URLTextSource? = this as?
    dev.inmo.tgbotapi.types.message.textsources.URLTextSource

public inline fun TextSource.uRLTextSourceOrThrow(): URLTextSource = this as
    dev.inmo.tgbotapi.types.message.textsources.URLTextSource

public inline fun <T> TextSource.ifURLTextSource(block: (URLTextSource) -> T): T? =
    uRLTextSourceOrNull() ?.let(block)

public inline fun PassportElementError.passportMultipleElementsErrorOrNull():
    PassportMultipleElementsError? = this as?
    dev.inmo.tgbotapi.types.passport.PassportMultipleElementsError

public inline fun PassportElementError.passportMultipleElementsErrorOrThrow():
    PassportMultipleElementsError = this as
    dev.inmo.tgbotapi.types.passport.PassportMultipleElementsError

public inline fun <T>
    PassportElementError.ifPassportMultipleElementsError(block: (PassportMultipleElementsError) -> T):
    T? = passportMultipleElementsErrorOrNull() ?.let(block)

public inline fun PassportElementError.passportElementFilesErrorOrNull(): PassportElementFilesError?
    = this as? dev.inmo.tgbotapi.types.passport.PassportElementFilesError

public inline fun PassportElementError.passportElementFilesErrorOrThrow(): PassportElementFilesError
    = this as dev.inmo.tgbotapi.types.passport.PassportElementFilesError

public inline fun <T>
    PassportElementError.ifPassportElementFilesError(block: (PassportElementFilesError) -> T): T? =
    passportElementFilesErrorOrNull() ?.let(block)

public inline fun PassportElementError.passportElementErrorFilesOrNull(): PassportElementErrorFiles?
    = this as? dev.inmo.tgbotapi.types.passport.PassportElementErrorFiles

public inline fun PassportElementError.passportElementErrorFilesOrThrow(): PassportElementErrorFiles
    = this as dev.inmo.tgbotapi.types.passport.PassportElementErrorFiles

public inline fun <T>
    PassportElementError.ifPassportElementErrorFiles(block: (PassportElementErrorFiles) -> T): T? =
    passportElementErrorFilesOrNull() ?.let(block)

public inline fun PassportElementError.passportElementErrorTranslationFilesOrNull():
    PassportElementErrorTranslationFiles? = this as?
    dev.inmo.tgbotapi.types.passport.PassportElementErrorTranslationFiles

public inline fun PassportElementError.passportElementErrorTranslationFilesOrThrow():
    PassportElementErrorTranslationFiles = this as
    dev.inmo.tgbotapi.types.passport.PassportElementErrorTranslationFiles

public inline fun <T>
    PassportElementError.ifPassportElementErrorTranslationFiles(block: (PassportElementErrorTranslationFiles) -> T):
    T? = passportElementErrorTranslationFilesOrNull() ?.let(block)

public inline fun PassportElementError.passportSingleElementErrorOrNull():
    PassportSingleElementError? = this as?
    dev.inmo.tgbotapi.types.passport.PassportSingleElementError

public inline fun PassportElementError.passportSingleElementErrorOrThrow():
    PassportSingleElementError = this as dev.inmo.tgbotapi.types.passport.PassportSingleElementError

public inline fun <T>
    PassportElementError.ifPassportSingleElementError(block: (PassportSingleElementError) -> T): T?
    = passportSingleElementErrorOrNull() ?.let(block)

public inline fun PassportElementError.passportElementErrorDataFieldOrNull():
    PassportElementErrorDataField? = this as?
    dev.inmo.tgbotapi.types.passport.PassportElementErrorDataField

public inline fun PassportElementError.passportElementErrorDataFieldOrThrow():
    PassportElementErrorDataField = this as
    dev.inmo.tgbotapi.types.passport.PassportElementErrorDataField

public inline fun <T>
    PassportElementError.ifPassportElementErrorDataField(block: (PassportElementErrorDataField) -> T):
    T? = passportElementErrorDataFieldOrNull() ?.let(block)

public inline fun PassportElementError.passportElementFileErrorOrNull(): PassportElementFileError? =
    this as? dev.inmo.tgbotapi.types.passport.PassportElementFileError

public inline fun PassportElementError.passportElementFileErrorOrThrow(): PassportElementFileError =
    this as dev.inmo.tgbotapi.types.passport.PassportElementFileError

public inline fun <T>
    PassportElementError.ifPassportElementFileError(block: (PassportElementFileError) -> T): T? =
    passportElementFileErrorOrNull() ?.let(block)

public inline fun PassportElementError.passportElementErrorFileOrNull(): PassportElementErrorFile? =
    this as? dev.inmo.tgbotapi.types.passport.PassportElementErrorFile

public inline fun PassportElementError.passportElementErrorFileOrThrow(): PassportElementErrorFile =
    this as dev.inmo.tgbotapi.types.passport.PassportElementErrorFile

public inline fun <T>
    PassportElementError.ifPassportElementErrorFile(block: (PassportElementErrorFile) -> T): T? =
    passportElementErrorFileOrNull() ?.let(block)

public inline fun PassportElementError.passportElementErrorFrontSideOrNull():
    PassportElementErrorFrontSide? = this as?
    dev.inmo.tgbotapi.types.passport.PassportElementErrorFrontSide

public inline fun PassportElementError.passportElementErrorFrontSideOrThrow():
    PassportElementErrorFrontSide = this as
    dev.inmo.tgbotapi.types.passport.PassportElementErrorFrontSide

public inline fun <T>
    PassportElementError.ifPassportElementErrorFrontSide(block: (PassportElementErrorFrontSide) -> T):
    T? = passportElementErrorFrontSideOrNull() ?.let(block)

public inline fun PassportElementError.passportElementErrorReverseSideOrNull():
    PassportElementErrorReverseSide? = this as?
    dev.inmo.tgbotapi.types.passport.PassportElementErrorReverseSide

public inline fun PassportElementError.passportElementErrorReverseSideOrThrow():
    PassportElementErrorReverseSide = this as
    dev.inmo.tgbotapi.types.passport.PassportElementErrorReverseSide

public inline fun <T>
    PassportElementError.ifPassportElementErrorReverseSide(block: (PassportElementErrorReverseSide) -> T):
    T? = passportElementErrorReverseSideOrNull() ?.let(block)

public inline fun PassportElementError.passportElementErrorSelfieOrNull():
    PassportElementErrorSelfie? = this as?
    dev.inmo.tgbotapi.types.passport.PassportElementErrorSelfie

public inline fun PassportElementError.passportElementErrorSelfieOrThrow():
    PassportElementErrorSelfie = this as dev.inmo.tgbotapi.types.passport.PassportElementErrorSelfie

public inline fun <T>
    PassportElementError.ifPassportElementErrorSelfie(block: (PassportElementErrorSelfie) -> T): T?
    = passportElementErrorSelfieOrNull() ?.let(block)

public inline fun PassportElementError.passportElementErrorTranslationFileOrNull():
    PassportElementErrorTranslationFile? = this as?
    dev.inmo.tgbotapi.types.passport.PassportElementErrorTranslationFile

public inline fun PassportElementError.passportElementErrorTranslationFileOrThrow():
    PassportElementErrorTranslationFile = this as
    dev.inmo.tgbotapi.types.passport.PassportElementErrorTranslationFile

public inline fun <T>
    PassportElementError.ifPassportElementErrorTranslationFile(block: (PassportElementErrorTranslationFile) -> T):
    T? = passportElementErrorTranslationFileOrNull() ?.let(block)

public inline fun PassportElementError.passportElementErrorUnspecifiedOrNull():
    PassportElementErrorUnspecified? = this as?
    dev.inmo.tgbotapi.types.passport.PassportElementErrorUnspecified

public inline fun PassportElementError.passportElementErrorUnspecifiedOrThrow():
    PassportElementErrorUnspecified = this as
    dev.inmo.tgbotapi.types.passport.PassportElementErrorUnspecified

public inline fun <T>
    PassportElementError.ifPassportElementErrorUnspecified(block: (PassportElementErrorUnspecified) -> T):
    T? = passportElementErrorUnspecifiedOrNull() ?.let(block)

public inline fun PassportElementError.unknownPassportElementErrorOrNull():
    UnknownPassportElementError? = this as?
    dev.inmo.tgbotapi.types.passport.UnknownPassportElementError

public inline fun PassportElementError.unknownPassportElementErrorOrThrow():
    UnknownPassportElementError = this as
    dev.inmo.tgbotapi.types.passport.UnknownPassportElementError

public inline fun <T>
    PassportElementError.ifUnknownPassportElementError(block: (UnknownPassportElementError) -> T):
    T? = unknownPassportElementErrorOrNull() ?.let(block)

public inline fun ScheduledCloseInfo.approximateScheduledCloseInfoOrNull():
    ApproximateScheduledCloseInfo? = this as?
    dev.inmo.tgbotapi.types.polls.ApproximateScheduledCloseInfo

public inline fun ScheduledCloseInfo.approximateScheduledCloseInfoOrThrow():
    ApproximateScheduledCloseInfo = this as
    dev.inmo.tgbotapi.types.polls.ApproximateScheduledCloseInfo

public inline fun <T>
    ScheduledCloseInfo.ifApproximateScheduledCloseInfo(block: (ApproximateScheduledCloseInfo) -> T):
    T? = approximateScheduledCloseInfoOrNull() ?.let(block)

public inline fun ScheduledCloseInfo.exactScheduledCloseInfoOrNull(): ExactScheduledCloseInfo? =
    this as? dev.inmo.tgbotapi.types.polls.ExactScheduledCloseInfo

public inline fun ScheduledCloseInfo.exactScheduledCloseInfoOrThrow(): ExactScheduledCloseInfo =
    this as dev.inmo.tgbotapi.types.polls.ExactScheduledCloseInfo

public inline fun <T>
    ScheduledCloseInfo.ifExactScheduledCloseInfo(block: (ExactScheduledCloseInfo) -> T): T? =
    exactScheduledCloseInfoOrNull() ?.let(block)

public inline fun Poll.multipleAnswersPollOrNull(): MultipleAnswersPoll? = this as?
    dev.inmo.tgbotapi.types.polls.MultipleAnswersPoll

public inline fun Poll.multipleAnswersPollOrThrow(): MultipleAnswersPoll = this as
    dev.inmo.tgbotapi.types.polls.MultipleAnswersPoll

public inline fun <T> Poll.ifMultipleAnswersPoll(block: (MultipleAnswersPoll) -> T): T? =
    multipleAnswersPollOrNull() ?.let(block)

public inline fun Poll.regularPollOrNull(): RegularPoll? = this as?
    dev.inmo.tgbotapi.types.polls.RegularPoll

public inline fun Poll.regularPollOrThrow(): RegularPoll = this as
    dev.inmo.tgbotapi.types.polls.RegularPoll

public inline fun <T> Poll.ifRegularPoll(block: (RegularPoll) -> T): T? = regularPollOrNull()
    ?.let(block)

public inline fun Poll.quizPollOrNull(): QuizPoll? = this as? dev.inmo.tgbotapi.types.polls.QuizPoll

public inline fun Poll.quizPollOrThrow(): QuizPoll = this as dev.inmo.tgbotapi.types.polls.QuizPoll

public inline fun <T> Poll.ifQuizPoll(block: (QuizPoll) -> T): T? = quizPollOrNull() ?.let(block)

public inline fun Poll.unknownPollTypeOrNull(): UnknownPollType? = this as?
    dev.inmo.tgbotapi.types.polls.UnknownPollType

public inline fun Poll.unknownPollTypeOrThrow(): UnknownPollType = this as
    dev.inmo.tgbotapi.types.polls.UnknownPollType

public inline fun <T> Poll.ifUnknownPollType(block: (UnknownPollType) -> T): T? =
    unknownPollTypeOrNull() ?.let(block)

public inline fun RequestResponse.chatSharedRequestOrNull(): ChatSharedRequest? = this as?
    dev.inmo.tgbotapi.types.request.ChatSharedRequest

public inline fun RequestResponse.chatSharedRequestOrThrow(): ChatSharedRequest = this as
    dev.inmo.tgbotapi.types.request.ChatSharedRequest

public inline fun <T> RequestResponse.ifChatSharedRequest(block: (ChatSharedRequest) -> T): T? =
    chatSharedRequestOrNull() ?.let(block)

public inline fun RequestResponse.chatSharedOrNull(): ChatShared? = this as?
    dev.inmo.tgbotapi.types.request.ChatShared

public inline fun RequestResponse.chatSharedOrThrow(): ChatShared = this as
    dev.inmo.tgbotapi.types.request.ChatShared

public inline fun <T> RequestResponse.ifChatShared(block: (ChatShared) -> T): T? =
    chatSharedOrNull() ?.let(block)

public inline fun RequestResponse.userSharedOrNull(): UserShared? = this as?
    dev.inmo.tgbotapi.types.request.UserShared

public inline fun RequestResponse.userSharedOrThrow(): UserShared = this as
    dev.inmo.tgbotapi.types.request.UserShared

public inline fun <T> RequestResponse.ifUserShared(block: (UserShared) -> T): T? =
    userSharedOrNull() ?.let(block)
