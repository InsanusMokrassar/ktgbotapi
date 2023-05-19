@file:Suppress(
  "unused",
  "RemoveRedundantQualifierName",
  "RedundantVisibilityModifier",
  "NOTHING_TO_INLINE",
  "UNCHECKED_CAST",
  "OPT_IN_USAGE",
)

package dev.inmo.tgbotapi.extensions.utils

import dev.inmo.tgbotapi.abstracts.CommonSendInvoiceData
import dev.inmo.tgbotapi.abstracts.FromUser
import dev.inmo.tgbotapi.abstracts.WithUser
import dev.inmo.tgbotapi.requests.answers.InlineQueryResultsButton
import dev.inmo.tgbotapi.requests.send.payments.CreateInvoiceLink
import dev.inmo.tgbotapi.requests.send.payments.SendInvoice
import dev.inmo.tgbotapi.requests.stickers.InputSticker
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.ChatIdWithThreadId
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.ChatInviteLink
import dev.inmo.tgbotapi.types.ChatInviteLinkUnlimited
import dev.inmo.tgbotapi.types.ChatInviteLinkWithJoinRequest
import dev.inmo.tgbotapi.types.ChatInviteLinkWithLimitedMembers
import dev.inmo.tgbotapi.types.IdChatIdentifier
import dev.inmo.tgbotapi.types.InlineQueries.ChosenInlineResult.BaseChosenInlineResult
import dev.inmo.tgbotapi.types.InlineQueries.ChosenInlineResult.ChosenInlineResult
import dev.inmo.tgbotapi.types.InlineQueries.ChosenInlineResult.LocationChosenInlineResult
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultArticle
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultAudioCachedImpl
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultAudioImpl
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultContact
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultDocumentCachedImpl
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultDocumentImpl
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultGame
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultGifCachedImpl
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultGifImpl
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultLocation
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultMpeg4GifCachedImpl
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultMpeg4GifImpl
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultPhotoCachedImpl
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultPhotoImpl
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultStickerCached
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultVenue
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultVideoCachedImpl
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultVideoImpl
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultVoiceCachedImpl
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultVoiceImpl
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.DescribedInlineQueryResult
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.FileInlineQueryResult
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.InlineQueryResult
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.OptionallyTitledInlineQueryResult
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.SizedInlineQueryResult
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.ThumbSizedInlineQueryResult
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.ThumbedInlineQueryResult
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.ThumbedWithMimeTypeInlineQueryResult
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.TitledInlineQueryResult
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.UrlInlineQueryResult
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.WithInputMessageContentInlineQueryResult
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.audio.InlineQueryResultAudio
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.audio.InlineQueryResultAudioCached
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.audio.InlineQueryResultAudioCommon
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.document.InlineQueryResultDocument
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.document.InlineQueryResultDocumentCached
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.document.InlineQueryResultDocumentCommon
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.gif.InlineQueryResultGif
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.gif.InlineQueryResultGifCached
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.gif.InlineQueryResultGifCommon
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.mpeg4gif.InlineQueryResultMpeg4Gif
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.mpeg4gif.InlineQueryResultMpeg4GifCached
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.mpeg4gif.InlineQueryResultMpeg4GifCommon
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.photo.InlineQueryResultPhoto
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.photo.InlineQueryResultPhotoCached
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.photo.InlineQueryResultPhotoCommon
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.video.InlineQueryResultVideo
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.video.InlineQueryResultVideoCached
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.video.InlineQueryResultVideoCommon
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.voice.InlineQueryResultVoice
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.voice.InlineQueryResultVoiceCached
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.voice.InlineQueryResultVoiceCommon
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputContactMessageContent
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputInvoiceMessageContent
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputLocationMessageContent
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputMessageContent
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputTextMessageContent
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputVenueMessageContent
import dev.inmo.tgbotapi.types.InlineQueries.query.BaseInlineQuery
import dev.inmo.tgbotapi.types.InlineQueries.query.InlineQuery
import dev.inmo.tgbotapi.types.InlineQueries.query.LocationInlineQuery
import dev.inmo.tgbotapi.types.PrimaryInviteLink
import dev.inmo.tgbotapi.types.SecondaryChatInviteLink
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
import dev.inmo.tgbotapi.types.chat.ChatJoinRequest
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
import dev.inmo.tgbotapi.types.chat.member.AdministratorChatMember
import dev.inmo.tgbotapi.types.chat.member.AdministratorChatMemberImpl
import dev.inmo.tgbotapi.types.chat.member.BannedChatMember
import dev.inmo.tgbotapi.types.chat.member.ChatMember
import dev.inmo.tgbotapi.types.chat.member.ChatMemberUpdated
import dev.inmo.tgbotapi.types.chat.member.KickedChatMember
import dev.inmo.tgbotapi.types.chat.member.LeftChatMember
import dev.inmo.tgbotapi.types.chat.member.LeftChatMemberImpl
import dev.inmo.tgbotapi.types.chat.member.MemberChatMember
import dev.inmo.tgbotapi.types.chat.member.MemberChatMemberImpl
import dev.inmo.tgbotapi.types.chat.member.OwnerChatMember
import dev.inmo.tgbotapi.types.chat.member.RestrictedChatMember
import dev.inmo.tgbotapi.types.chat.member.SpecialRightsChatMember
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
import dev.inmo.tgbotapi.types.message.AnonymousForumContentMessageImpl
import dev.inmo.tgbotapi.types.message.AnonymousGroupContentMessageImpl
import dev.inmo.tgbotapi.types.message.ChannelContentMessageImpl
import dev.inmo.tgbotapi.types.message.ChannelEventMessage
import dev.inmo.tgbotapi.types.message.ChatEvents.ChannelChatCreated
import dev.inmo.tgbotapi.types.message.ChatEvents.DeleteChatPhoto
import dev.inmo.tgbotapi.types.message.ChatEvents.GroupChatCreated
import dev.inmo.tgbotapi.types.message.ChatEvents.LeftChatMemberEvent
import dev.inmo.tgbotapi.types.message.ChatEvents.MessageAutoDeleteTimerChanged
import dev.inmo.tgbotapi.types.message.ChatEvents.MigratedToSupergroup
import dev.inmo.tgbotapi.types.message.ChatEvents.NewChatMembers
import dev.inmo.tgbotapi.types.message.ChatEvents.NewChatPhoto
import dev.inmo.tgbotapi.types.message.ChatEvents.NewChatTitle
import dev.inmo.tgbotapi.types.message.ChatEvents.PinnedMessage
import dev.inmo.tgbotapi.types.message.ChatEvents.ProximityAlertTriggered
import dev.inmo.tgbotapi.types.message.ChatEvents.SupergroupChatCreated
import dev.inmo.tgbotapi.types.message.ChatEvents.UserLoggedIn
import dev.inmo.tgbotapi.types.message.ChatEvents.WebAppData
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ChannelEvent
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ChatEvent
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.CommonEvent
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ForumEvent
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.GroupEvent
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.PrivateEvent
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.PublicChatEvent
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.SupergroupEvent
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.VideoChatEvent
import dev.inmo.tgbotapi.types.message.ChatEvents.forum.ForumTopicClosed
import dev.inmo.tgbotapi.types.message.ChatEvents.forum.ForumTopicCreated
import dev.inmo.tgbotapi.types.message.ChatEvents.forum.ForumTopicEdited
import dev.inmo.tgbotapi.types.message.ChatEvents.forum.ForumTopicReopened
import dev.inmo.tgbotapi.types.message.ChatEvents.forum.GeneralForumTopicHidden
import dev.inmo.tgbotapi.types.message.ChatEvents.forum.GeneralForumTopicUnhidden
import dev.inmo.tgbotapi.types.message.ChatEvents.forum.WriteAccessAllowed
import dev.inmo.tgbotapi.types.message.ChatEvents.voice.VideoChatEnded
import dev.inmo.tgbotapi.types.message.ChatEvents.voice.VideoChatParticipantsInvited
import dev.inmo.tgbotapi.types.message.ChatEvents.voice.VideoChatScheduled
import dev.inmo.tgbotapi.types.message.ChatEvents.voice.VideoChatStarted
import dev.inmo.tgbotapi.types.message.CommonForumContentMessageImpl
import dev.inmo.tgbotapi.types.message.CommonGroupContentMessageImpl
import dev.inmo.tgbotapi.types.message.CommonGroupEventMessage
import dev.inmo.tgbotapi.types.message.CommonSupergroupEventMessage
import dev.inmo.tgbotapi.types.message.ConnectedFromChannelGroupContentMessageImpl
import dev.inmo.tgbotapi.types.message.ForwardInfo
import dev.inmo.tgbotapi.types.message.FromChannelForumContentMessageImpl
import dev.inmo.tgbotapi.types.message.PassportMessage
import dev.inmo.tgbotapi.types.message.PrivateContentMessageImpl
import dev.inmo.tgbotapi.types.message.PrivateEventMessage
import dev.inmo.tgbotapi.types.message.UnconnectedFromChannelGroupContentMessageImpl
import dev.inmo.tgbotapi.types.message.abstracts.AnonymousForumContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.AnonymousGroupContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.ChannelContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.ChatEventMessage
import dev.inmo.tgbotapi.types.message.abstracts.CommonForumContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.CommonGroupContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.abstracts.ConnectedFromChannelGroupContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.ForumContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.FromChannelForumContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.FromChannelGroupContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.FromUserMessage
import dev.inmo.tgbotapi.types.message.abstracts.GroupContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.GroupEventMessage
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.message.abstracts.PossiblyEditedMessage
import dev.inmo.tgbotapi.types.message.abstracts.PossiblyForwardedMessage
import dev.inmo.tgbotapi.types.message.abstracts.PossiblyMediaGroupMessage
import dev.inmo.tgbotapi.types.message.abstracts.PossiblyPaymentMessage
import dev.inmo.tgbotapi.types.message.abstracts.PossiblySentViaBotCommonMessage
import dev.inmo.tgbotapi.types.message.abstracts.PossiblyTopicMessage
import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.PublicContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.SignedMessage
import dev.inmo.tgbotapi.types.message.abstracts.SupergroupEventMessage
import dev.inmo.tgbotapi.types.message.abstracts.UnconnectedFromChannelGroupContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.UnknownMessageType
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
import dev.inmo.tgbotapi.types.message.payments.SuccessfulPaymentEvent
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
import dev.inmo.tgbotapi.types.passport.decrypted.AddressSecureValue
import dev.inmo.tgbotapi.types.passport.decrypted.BankStatementSecureValue
import dev.inmo.tgbotapi.types.passport.decrypted.CommonPassportSecureValue
import dev.inmo.tgbotapi.types.passport.decrypted.DriverLicenseSecureValue
import dev.inmo.tgbotapi.types.passport.decrypted.IdentityCardSecureValue
import dev.inmo.tgbotapi.types.passport.decrypted.IdentityWithReverseSideSecureValue
import dev.inmo.tgbotapi.types.passport.decrypted.InternalPassportSecureValue
import dev.inmo.tgbotapi.types.passport.decrypted.OtherDocumentsSecureValue
import dev.inmo.tgbotapi.types.passport.decrypted.PassportRegistrationSecureValue
import dev.inmo.tgbotapi.types.passport.decrypted.PassportSecureValue
import dev.inmo.tgbotapi.types.passport.decrypted.PersonalDetailsSecureValue
import dev.inmo.tgbotapi.types.passport.decrypted.RentalAgreementSecureValue
import dev.inmo.tgbotapi.types.passport.decrypted.TemporalRegistrationSecureValue
import dev.inmo.tgbotapi.types.passport.decrypted.UtilityBillSecureValue
import dev.inmo.tgbotapi.types.passport.decrypted.abstracts.SecureValue
import dev.inmo.tgbotapi.types.passport.decrypted.abstracts.SecureValueIdentity
import dev.inmo.tgbotapi.types.passport.decrypted.abstracts.SecureValueWithData
import dev.inmo.tgbotapi.types.passport.decrypted.abstracts.SecureValueWithFiles
import dev.inmo.tgbotapi.types.passport.decrypted.abstracts.SecureValueWithReverseSide
import dev.inmo.tgbotapi.types.passport.decrypted.abstracts.SecureValueWithTranslations
import dev.inmo.tgbotapi.types.passport.encrypted.BankStatement
import dev.inmo.tgbotapi.types.passport.encrypted.CommonPassport
import dev.inmo.tgbotapi.types.passport.encrypted.DriverLicense
import dev.inmo.tgbotapi.types.passport.encrypted.Email
import dev.inmo.tgbotapi.types.passport.encrypted.EncryptedAddress
import dev.inmo.tgbotapi.types.passport.encrypted.EncryptedPassportElementWithTranslatableFilesCollection
import dev.inmo.tgbotapi.types.passport.encrypted.EncryptedPassportElementWithTranslatableIDDocument
import dev.inmo.tgbotapi.types.passport.encrypted.EncryptedPersonalDetails
import dev.inmo.tgbotapi.types.passport.encrypted.IdentityCard
import dev.inmo.tgbotapi.types.passport.encrypted.InternalPassport
import dev.inmo.tgbotapi.types.passport.encrypted.Passport
import dev.inmo.tgbotapi.types.passport.encrypted.PassportRegistration
import dev.inmo.tgbotapi.types.passport.encrypted.PhoneNumber
import dev.inmo.tgbotapi.types.passport.encrypted.RentalAgreement
import dev.inmo.tgbotapi.types.passport.encrypted.TemporaryRegistration
import dev.inmo.tgbotapi.types.passport.encrypted.UtilityBill
import dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElement
import dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementTranslatable
import dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementWithData
import dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementWithEmail
import dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementWithFilesCollection
import dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementWithFrontSide
import dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementWithPhoneNumber
import dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementWithReverseSide
import dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementWithSelfie
import dev.inmo.tgbotapi.types.passport.encrypted.abstracts.UnknownEncryptedPassportElement
import dev.inmo.tgbotapi.types.payments.PreCheckoutQuery
import dev.inmo.tgbotapi.types.payments.ShippingQuery
import dev.inmo.tgbotapi.types.polls.ApproximateScheduledCloseInfo
import dev.inmo.tgbotapi.types.polls.ExactScheduledCloseInfo
import dev.inmo.tgbotapi.types.polls.MultipleAnswersPoll
import dev.inmo.tgbotapi.types.polls.Poll
import dev.inmo.tgbotapi.types.polls.PollAnswer
import dev.inmo.tgbotapi.types.polls.QuizPoll
import dev.inmo.tgbotapi.types.polls.RegularPoll
import dev.inmo.tgbotapi.types.polls.ScheduledCloseInfo
import dev.inmo.tgbotapi.types.polls.UnknownPollType
import dev.inmo.tgbotapi.types.queries.callback.CallbackQuery
import dev.inmo.tgbotapi.types.queries.callback.DataCallbackQuery
import dev.inmo.tgbotapi.types.queries.callback.GameShortNameCallbackQuery
import dev.inmo.tgbotapi.types.queries.callback.InlineMessageIdCallbackQuery
import dev.inmo.tgbotapi.types.queries.callback.InlineMessageIdDataCallbackQuery
import dev.inmo.tgbotapi.types.queries.callback.InlineMessageIdGameShortNameCallbackQuery
import dev.inmo.tgbotapi.types.queries.callback.MessageCallbackQuery
import dev.inmo.tgbotapi.types.queries.callback.MessageDataCallbackQuery
import dev.inmo.tgbotapi.types.queries.callback.MessageGameShortNameCallbackQuery
import dev.inmo.tgbotapi.types.queries.callback.UnknownCallbackQueryType
import dev.inmo.tgbotapi.types.request.ChatShared
import dev.inmo.tgbotapi.types.request.ChatSharedRequest
import dev.inmo.tgbotapi.types.request.RequestResponse
import dev.inmo.tgbotapi.types.request.UserShared
import dev.inmo.tgbotapi.types.update.CallbackQueryUpdate
import dev.inmo.tgbotapi.types.update.ChannelPostUpdate
import dev.inmo.tgbotapi.types.update.ChatJoinRequestUpdate
import dev.inmo.tgbotapi.types.update.ChosenInlineResultUpdate
import dev.inmo.tgbotapi.types.update.CommonChatMemberUpdatedUpdate
import dev.inmo.tgbotapi.types.update.EditChannelPostUpdate
import dev.inmo.tgbotapi.types.update.EditMessageUpdate
import dev.inmo.tgbotapi.types.update.InlineQueryUpdate
import dev.inmo.tgbotapi.types.update.MessageUpdate
import dev.inmo.tgbotapi.types.update.MyChatMemberUpdatedUpdate
import dev.inmo.tgbotapi.types.update.PollAnswerUpdate
import dev.inmo.tgbotapi.types.update.PollUpdate
import dev.inmo.tgbotapi.types.update.PreCheckoutQueryUpdate
import dev.inmo.tgbotapi.types.update.ShippingQueryUpdate
import dev.inmo.tgbotapi.types.update.abstracts.BaseEditMessageUpdate
import dev.inmo.tgbotapi.types.update.abstracts.BaseMessageUpdate
import dev.inmo.tgbotapi.types.update.abstracts.BaseSentMessageUpdate
import dev.inmo.tgbotapi.types.update.abstracts.ChatMemberUpdatedUpdate
import dev.inmo.tgbotapi.types.update.abstracts.UnknownUpdate
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlin.Suppress

public inline fun CommonSendInvoiceData.createInvoiceLinkOrNull(): CreateInvoiceLink? = this as?
    dev.inmo.tgbotapi.requests.send.payments.CreateInvoiceLink

public inline fun CommonSendInvoiceData.createInvoiceLinkOrThrow(): CreateInvoiceLink = this as
    dev.inmo.tgbotapi.requests.send.payments.CreateInvoiceLink

public inline fun <T> CommonSendInvoiceData.ifCreateInvoiceLink(block: (CreateInvoiceLink) -> T): T?
    = createInvoiceLinkOrNull() ?.let(block)

public inline fun CommonSendInvoiceData.sendInvoiceOrNull(): SendInvoice? = this as?
    dev.inmo.tgbotapi.requests.send.payments.SendInvoice

public inline fun CommonSendInvoiceData.sendInvoiceOrThrow(): SendInvoice = this as
    dev.inmo.tgbotapi.requests.send.payments.SendInvoice

public inline fun <T> CommonSendInvoiceData.ifSendInvoice(block: (SendInvoice) -> T): T? =
    sendInvoiceOrNull() ?.let(block)

public inline fun CommonSendInvoiceData.inputInvoiceMessageContentOrNull():
    InputInvoiceMessageContent? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputInvoiceMessageContent

public inline fun CommonSendInvoiceData.inputInvoiceMessageContentOrThrow():
    InputInvoiceMessageContent = this as
    dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputInvoiceMessageContent

public inline fun <T>
    CommonSendInvoiceData.ifInputInvoiceMessageContent(block: (InputInvoiceMessageContent) -> T): T?
    = inputInvoiceMessageContentOrNull() ?.let(block)

public inline fun WithUser.fromUserOrNull(): FromUser? = this as?
    dev.inmo.tgbotapi.abstracts.FromUser

public inline fun WithUser.fromUserOrThrow(): FromUser = this as
    dev.inmo.tgbotapi.abstracts.FromUser

public inline fun <T> WithUser.ifFromUser(block: (FromUser) -> T): T? = fromUserOrNull()
    ?.let(block)

public inline fun WithUser.chatInviteLinkOrNull(): ChatInviteLink? = this as?
    dev.inmo.tgbotapi.types.ChatInviteLink

public inline fun WithUser.chatInviteLinkOrThrow(): ChatInviteLink = this as
    dev.inmo.tgbotapi.types.ChatInviteLink

public inline fun <T> WithUser.ifChatInviteLink(block: (ChatInviteLink) -> T): T? =
    chatInviteLinkOrNull() ?.let(block)

public inline fun WithUser.secondaryChatInviteLinkOrNull(): SecondaryChatInviteLink? = this as?
    dev.inmo.tgbotapi.types.SecondaryChatInviteLink

public inline fun WithUser.secondaryChatInviteLinkOrThrow(): SecondaryChatInviteLink = this as
    dev.inmo.tgbotapi.types.SecondaryChatInviteLink

public inline fun <T> WithUser.ifSecondaryChatInviteLink(block: (SecondaryChatInviteLink) -> T): T?
    = secondaryChatInviteLinkOrNull() ?.let(block)

public inline fun WithUser.primaryInviteLinkOrNull(): PrimaryInviteLink? = this as?
    dev.inmo.tgbotapi.types.PrimaryInviteLink

public inline fun WithUser.primaryInviteLinkOrThrow(): PrimaryInviteLink = this as
    dev.inmo.tgbotapi.types.PrimaryInviteLink

public inline fun <T> WithUser.ifPrimaryInviteLink(block: (PrimaryInviteLink) -> T): T? =
    primaryInviteLinkOrNull() ?.let(block)

public inline fun WithUser.chatInviteLinkWithJoinRequestOrNull(): ChatInviteLinkWithJoinRequest? =
    this as? dev.inmo.tgbotapi.types.ChatInviteLinkWithJoinRequest

public inline fun WithUser.chatInviteLinkWithJoinRequestOrThrow(): ChatInviteLinkWithJoinRequest =
    this as dev.inmo.tgbotapi.types.ChatInviteLinkWithJoinRequest

public inline fun <T>
    WithUser.ifChatInviteLinkWithJoinRequest(block: (ChatInviteLinkWithJoinRequest) -> T): T? =
    chatInviteLinkWithJoinRequestOrNull() ?.let(block)

public inline fun WithUser.chatInviteLinkWithLimitedMembersOrNull():
    ChatInviteLinkWithLimitedMembers? = this as?
    dev.inmo.tgbotapi.types.ChatInviteLinkWithLimitedMembers

public inline fun WithUser.chatInviteLinkWithLimitedMembersOrThrow():
    ChatInviteLinkWithLimitedMembers = this as
    dev.inmo.tgbotapi.types.ChatInviteLinkWithLimitedMembers

public inline fun <T>
    WithUser.ifChatInviteLinkWithLimitedMembers(block: (ChatInviteLinkWithLimitedMembers) -> T): T?
    = chatInviteLinkWithLimitedMembersOrNull() ?.let(block)

public inline fun WithUser.chatInviteLinkUnlimitedOrNull(): ChatInviteLinkUnlimited? = this as?
    dev.inmo.tgbotapi.types.ChatInviteLinkUnlimited

public inline fun WithUser.chatInviteLinkUnlimitedOrThrow(): ChatInviteLinkUnlimited = this as
    dev.inmo.tgbotapi.types.ChatInviteLinkUnlimited

public inline fun <T> WithUser.ifChatInviteLinkUnlimited(block: (ChatInviteLinkUnlimited) -> T): T?
    = chatInviteLinkUnlimitedOrNull() ?.let(block)

public inline fun WithUser.baseChosenInlineResultOrNull(): BaseChosenInlineResult? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.ChosenInlineResult.BaseChosenInlineResult

public inline fun WithUser.baseChosenInlineResultOrThrow(): BaseChosenInlineResult = this as
    dev.inmo.tgbotapi.types.InlineQueries.ChosenInlineResult.BaseChosenInlineResult

public inline fun <T> WithUser.ifBaseChosenInlineResult(block: (BaseChosenInlineResult) -> T): T? =
    baseChosenInlineResultOrNull() ?.let(block)

public inline fun WithUser.chosenInlineResultOrNull(): ChosenInlineResult? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.ChosenInlineResult.ChosenInlineResult

public inline fun WithUser.chosenInlineResultOrThrow(): ChosenInlineResult = this as
    dev.inmo.tgbotapi.types.InlineQueries.ChosenInlineResult.ChosenInlineResult

public inline fun <T> WithUser.ifChosenInlineResult(block: (ChosenInlineResult) -> T): T? =
    chosenInlineResultOrNull() ?.let(block)

public inline fun WithUser.locationChosenInlineResultOrNull(): LocationChosenInlineResult? = this
    as? dev.inmo.tgbotapi.types.InlineQueries.ChosenInlineResult.LocationChosenInlineResult

public inline fun WithUser.locationChosenInlineResultOrThrow(): LocationChosenInlineResult = this as
    dev.inmo.tgbotapi.types.InlineQueries.ChosenInlineResult.LocationChosenInlineResult

public inline fun <T>
    WithUser.ifLocationChosenInlineResult(block: (LocationChosenInlineResult) -> T): T? =
    locationChosenInlineResultOrNull() ?.let(block)

public inline fun WithUser.baseInlineQueryOrNull(): BaseInlineQuery? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.query.BaseInlineQuery

public inline fun WithUser.baseInlineQueryOrThrow(): BaseInlineQuery = this as
    dev.inmo.tgbotapi.types.InlineQueries.query.BaseInlineQuery

public inline fun <T> WithUser.ifBaseInlineQuery(block: (BaseInlineQuery) -> T): T? =
    baseInlineQueryOrNull() ?.let(block)

public inline fun WithUser.inlineQueryOrNull(): InlineQuery? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.query.InlineQuery

public inline fun WithUser.inlineQueryOrThrow(): InlineQuery = this as
    dev.inmo.tgbotapi.types.InlineQueries.query.InlineQuery

public inline fun <T> WithUser.ifInlineQuery(block: (InlineQuery) -> T): T? = inlineQueryOrNull()
    ?.let(block)

public inline fun WithUser.locationInlineQueryOrNull(): LocationInlineQuery? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.query.LocationInlineQuery

public inline fun WithUser.locationInlineQueryOrThrow(): LocationInlineQuery = this as
    dev.inmo.tgbotapi.types.InlineQueries.query.LocationInlineQuery

public inline fun <T> WithUser.ifLocationInlineQuery(block: (LocationInlineQuery) -> T): T? =
    locationInlineQueryOrNull() ?.let(block)

public inline fun WithUser.chatJoinRequestOrNull(): ChatJoinRequest? = this as?
    dev.inmo.tgbotapi.types.chat.ChatJoinRequest

public inline fun WithUser.chatJoinRequestOrThrow(): ChatJoinRequest = this as
    dev.inmo.tgbotapi.types.chat.ChatJoinRequest

public inline fun <T> WithUser.ifChatJoinRequest(block: (ChatJoinRequest) -> T): T? =
    chatJoinRequestOrNull() ?.let(block)

public inline fun WithUser.administratorChatMemberOrNull(): AdministratorChatMember? = this as?
    dev.inmo.tgbotapi.types.chat.member.AdministratorChatMember

public inline fun WithUser.administratorChatMemberOrThrow(): AdministratorChatMember = this as
    dev.inmo.tgbotapi.types.chat.member.AdministratorChatMember

public inline fun <T> WithUser.ifAdministratorChatMember(block: (AdministratorChatMember) -> T): T?
    = administratorChatMemberOrNull() ?.let(block)

public inline fun WithUser.administratorChatMemberImplOrNull(): AdministratorChatMemberImpl? = this
    as? dev.inmo.tgbotapi.types.chat.member.AdministratorChatMemberImpl

public inline fun WithUser.administratorChatMemberImplOrThrow(): AdministratorChatMemberImpl = this
    as dev.inmo.tgbotapi.types.chat.member.AdministratorChatMemberImpl

public inline fun <T>
    WithUser.ifAdministratorChatMemberImpl(block: (AdministratorChatMemberImpl) -> T): T? =
    administratorChatMemberImplOrNull() ?.let(block)

public inline fun WithUser.bannedChatMemberOrNull(): BannedChatMember? = this as?
    dev.inmo.tgbotapi.types.chat.member.BannedChatMember

public inline fun WithUser.bannedChatMemberOrThrow(): BannedChatMember = this as
    dev.inmo.tgbotapi.types.chat.member.BannedChatMember

public inline fun <T> WithUser.ifBannedChatMember(block: (BannedChatMember) -> T): T? =
    bannedChatMemberOrNull() ?.let(block)

public inline fun WithUser.chatMemberOrNull(): ChatMember? = this as?
    dev.inmo.tgbotapi.types.chat.member.ChatMember

public inline fun WithUser.chatMemberOrThrow(): ChatMember = this as
    dev.inmo.tgbotapi.types.chat.member.ChatMember

public inline fun <T> WithUser.ifChatMember(block: (ChatMember) -> T): T? = chatMemberOrNull()
    ?.let(block)

public inline fun WithUser.chatMemberUpdatedOrNull(): ChatMemberUpdated? = this as?
    dev.inmo.tgbotapi.types.chat.member.ChatMemberUpdated

public inline fun WithUser.chatMemberUpdatedOrThrow(): ChatMemberUpdated = this as
    dev.inmo.tgbotapi.types.chat.member.ChatMemberUpdated

public inline fun <T> WithUser.ifChatMemberUpdated(block: (ChatMemberUpdated) -> T): T? =
    chatMemberUpdatedOrNull() ?.let(block)

public inline fun WithUser.kickedChatMemberOrNull(): KickedChatMember? = this as?
    dev.inmo.tgbotapi.types.chat.member.KickedChatMember

public inline fun WithUser.kickedChatMemberOrThrow(): KickedChatMember = this as
    dev.inmo.tgbotapi.types.chat.member.KickedChatMember

public inline fun <T> WithUser.ifKickedChatMember(block: (KickedChatMember) -> T): T? =
    kickedChatMemberOrNull() ?.let(block)

public inline fun WithUser.leftChatMemberOrNull(): LeftChatMember? = this as?
    dev.inmo.tgbotapi.types.chat.member.LeftChatMember

public inline fun WithUser.leftChatMemberOrThrow(): LeftChatMember = this as
    dev.inmo.tgbotapi.types.chat.member.LeftChatMember

public inline fun <T> WithUser.ifLeftChatMember(block: (LeftChatMember) -> T): T? =
    leftChatMemberOrNull() ?.let(block)

public inline fun WithUser.leftChatMemberImplOrNull(): LeftChatMemberImpl? = this as?
    dev.inmo.tgbotapi.types.chat.member.LeftChatMemberImpl

public inline fun WithUser.leftChatMemberImplOrThrow(): LeftChatMemberImpl = this as
    dev.inmo.tgbotapi.types.chat.member.LeftChatMemberImpl

public inline fun <T> WithUser.ifLeftChatMemberImpl(block: (LeftChatMemberImpl) -> T): T? =
    leftChatMemberImplOrNull() ?.let(block)

public inline fun WithUser.memberChatMemberOrNull(): MemberChatMember? = this as?
    dev.inmo.tgbotapi.types.chat.member.MemberChatMember

public inline fun WithUser.memberChatMemberOrThrow(): MemberChatMember = this as
    dev.inmo.tgbotapi.types.chat.member.MemberChatMember

public inline fun <T> WithUser.ifMemberChatMember(block: (MemberChatMember) -> T): T? =
    memberChatMemberOrNull() ?.let(block)

public inline fun WithUser.memberChatMemberImplOrNull(): MemberChatMemberImpl? = this as?
    dev.inmo.tgbotapi.types.chat.member.MemberChatMemberImpl

public inline fun WithUser.memberChatMemberImplOrThrow(): MemberChatMemberImpl = this as
    dev.inmo.tgbotapi.types.chat.member.MemberChatMemberImpl

public inline fun <T> WithUser.ifMemberChatMemberImpl(block: (MemberChatMemberImpl) -> T): T? =
    memberChatMemberImplOrNull() ?.let(block)

public inline fun WithUser.ownerChatMemberOrNull(): OwnerChatMember? = this as?
    dev.inmo.tgbotapi.types.chat.member.OwnerChatMember

public inline fun WithUser.ownerChatMemberOrThrow(): OwnerChatMember = this as
    dev.inmo.tgbotapi.types.chat.member.OwnerChatMember

public inline fun <T> WithUser.ifOwnerChatMember(block: (OwnerChatMember) -> T): T? =
    ownerChatMemberOrNull() ?.let(block)

public inline fun WithUser.restrictedChatMemberOrNull(): RestrictedChatMember? = this as?
    dev.inmo.tgbotapi.types.chat.member.RestrictedChatMember

public inline fun WithUser.restrictedChatMemberOrThrow(): RestrictedChatMember = this as
    dev.inmo.tgbotapi.types.chat.member.RestrictedChatMember

public inline fun <T> WithUser.ifRestrictedChatMember(block: (RestrictedChatMember) -> T): T? =
    restrictedChatMemberOrNull() ?.let(block)

public inline fun WithUser.specialRightsChatMemberOrNull(): SpecialRightsChatMember? = this as?
    dev.inmo.tgbotapi.types.chat.member.SpecialRightsChatMember

public inline fun WithUser.specialRightsChatMemberOrThrow(): SpecialRightsChatMember = this as
    dev.inmo.tgbotapi.types.chat.member.SpecialRightsChatMember

public inline fun <T> WithUser.ifSpecialRightsChatMember(block: (SpecialRightsChatMember) -> T): T?
    = specialRightsChatMemberOrNull() ?.let(block)

public inline fun WithUser.leftChatMemberEventOrNull(): LeftChatMemberEvent? = this as?
    dev.inmo.tgbotapi.types.message.ChatEvents.LeftChatMemberEvent

public inline fun WithUser.leftChatMemberEventOrThrow(): LeftChatMemberEvent = this as
    dev.inmo.tgbotapi.types.message.ChatEvents.LeftChatMemberEvent

public inline fun <T> WithUser.ifLeftChatMemberEvent(block: (LeftChatMemberEvent) -> T): T? =
    leftChatMemberEventOrNull() ?.let(block)

public inline fun WithUser.commonGroupEventMessageOrNull(): CommonGroupEventMessage<GroupEvent>? =
    this as?
    dev.inmo.tgbotapi.types.message.CommonGroupEventMessage<dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.GroupEvent>

public inline fun WithUser.commonGroupEventMessageOrThrow(): CommonGroupEventMessage<GroupEvent> =
    this as
    dev.inmo.tgbotapi.types.message.CommonGroupEventMessage<dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.GroupEvent>

public inline fun <T>
    WithUser.ifCommonGroupEventMessage(block: (CommonGroupEventMessage<GroupEvent>) -> T): T? =
    commonGroupEventMessageOrNull() ?.let(block)

public inline fun WithUser.commonSupergroupEventMessageOrNull():
    CommonSupergroupEventMessage<SupergroupEvent>? = this as?
    dev.inmo.tgbotapi.types.message.CommonSupergroupEventMessage<dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.SupergroupEvent>

public inline fun WithUser.commonSupergroupEventMessageOrThrow():
    CommonSupergroupEventMessage<SupergroupEvent> = this as
    dev.inmo.tgbotapi.types.message.CommonSupergroupEventMessage<dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.SupergroupEvent>

public inline fun <T>
    WithUser.ifCommonSupergroupEventMessage(block: (CommonSupergroupEventMessage<SupergroupEvent>) -> T):
    T? = commonSupergroupEventMessageOrNull() ?.let(block)

public inline fun WithUser.commonGroupContentMessageImplOrNull():
    CommonGroupContentMessageImpl<MessageContent>? = this as?
    dev.inmo.tgbotapi.types.message.CommonGroupContentMessageImpl<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun WithUser.commonGroupContentMessageImplOrThrow():
    CommonGroupContentMessageImpl<MessageContent> = this as
    dev.inmo.tgbotapi.types.message.CommonGroupContentMessageImpl<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun <T>
    WithUser.ifCommonGroupContentMessageImpl(block: (CommonGroupContentMessageImpl<MessageContent>) -> T):
    T? = commonGroupContentMessageImplOrNull() ?.let(block)

public inline fun WithUser.commonForumContentMessageImplOrNull():
    CommonForumContentMessageImpl<MessageContent>? = this as?
    dev.inmo.tgbotapi.types.message.CommonForumContentMessageImpl<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun WithUser.commonForumContentMessageImplOrThrow():
    CommonForumContentMessageImpl<MessageContent> = this as
    dev.inmo.tgbotapi.types.message.CommonForumContentMessageImpl<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun <T>
    WithUser.ifCommonForumContentMessageImpl(block: (CommonForumContentMessageImpl<MessageContent>) -> T):
    T? = commonForumContentMessageImplOrNull() ?.let(block)

public inline fun WithUser.passportMessageOrNull(): PassportMessage? = this as?
    dev.inmo.tgbotapi.types.message.PassportMessage

public inline fun WithUser.passportMessageOrThrow(): PassportMessage = this as
    dev.inmo.tgbotapi.types.message.PassportMessage

public inline fun <T> WithUser.ifPassportMessage(block: (PassportMessage) -> T): T? =
    passportMessageOrNull() ?.let(block)

public inline fun WithUser.privateContentMessageImplOrNull():
    PrivateContentMessageImpl<MessageContent>? = this as?
    dev.inmo.tgbotapi.types.message.PrivateContentMessageImpl<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun WithUser.privateContentMessageImplOrThrow():
    PrivateContentMessageImpl<MessageContent> = this as
    dev.inmo.tgbotapi.types.message.PrivateContentMessageImpl<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun <T>
    WithUser.ifPrivateContentMessageImpl(block: (PrivateContentMessageImpl<MessageContent>) -> T):
    T? = privateContentMessageImplOrNull() ?.let(block)

public inline fun WithUser.fromUserMessageOrNull(): FromUserMessage? = this as?
    dev.inmo.tgbotapi.types.message.abstracts.FromUserMessage

public inline fun WithUser.fromUserMessageOrThrow(): FromUserMessage = this as
    dev.inmo.tgbotapi.types.message.abstracts.FromUserMessage

public inline fun <T> WithUser.ifFromUserMessage(block: (FromUserMessage) -> T): T? =
    fromUserMessageOrNull() ?.let(block)

public inline fun WithUser.groupEventMessageOrNull(): GroupEventMessage<GroupEvent>? = this as?
    dev.inmo.tgbotapi.types.message.abstracts.GroupEventMessage<dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.GroupEvent>

public inline fun WithUser.groupEventMessageOrThrow(): GroupEventMessage<GroupEvent> = this as
    dev.inmo.tgbotapi.types.message.abstracts.GroupEventMessage<dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.GroupEvent>

public inline fun <T> WithUser.ifGroupEventMessage(block: (GroupEventMessage<GroupEvent>) -> T): T?
    = groupEventMessageOrNull() ?.let(block)

public inline fun WithUser.commonGroupContentMessageOrNull():
    CommonGroupContentMessage<MessageContent>? = this as?
    dev.inmo.tgbotapi.types.message.abstracts.CommonGroupContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun WithUser.commonGroupContentMessageOrThrow():
    CommonGroupContentMessage<MessageContent> = this as
    dev.inmo.tgbotapi.types.message.abstracts.CommonGroupContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun <T>
    WithUser.ifCommonGroupContentMessage(block: (CommonGroupContentMessage<MessageContent>) -> T):
    T? = commonGroupContentMessageOrNull() ?.let(block)

public inline fun WithUser.commonForumContentMessageOrNull():
    CommonForumContentMessage<MessageContent>? = this as?
    dev.inmo.tgbotapi.types.message.abstracts.CommonForumContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun WithUser.commonForumContentMessageOrThrow():
    CommonForumContentMessage<MessageContent> = this as
    dev.inmo.tgbotapi.types.message.abstracts.CommonForumContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun <T>
    WithUser.ifCommonForumContentMessage(block: (CommonForumContentMessage<MessageContent>) -> T):
    T? = commonForumContentMessageOrNull() ?.let(block)

public inline fun WithUser.privateContentMessageOrNull(): PrivateContentMessage<MessageContent>? =
    this as?
    dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun WithUser.privateContentMessageOrThrow(): PrivateContentMessage<MessageContent> =
    this as
    dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun <T>
    WithUser.ifPrivateContentMessage(block: (PrivateContentMessage<MessageContent>) -> T): T? =
    privateContentMessageOrNull() ?.let(block)

public inline fun WithUser.supergroupEventMessageOrNull(): SupergroupEventMessage<SupergroupEvent>?
    = this as?
    dev.inmo.tgbotapi.types.message.abstracts.SupergroupEventMessage<dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.SupergroupEvent>

public inline fun WithUser.supergroupEventMessageOrThrow(): SupergroupEventMessage<SupergroupEvent>
    = this as
    dev.inmo.tgbotapi.types.message.abstracts.SupergroupEventMessage<dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.SupergroupEvent>

public inline fun <T>
    WithUser.ifSupergroupEventMessage(block: (SupergroupEventMessage<SupergroupEvent>) -> T): T? =
    supergroupEventMessageOrNull() ?.let(block)

public inline fun WithUser.preCheckoutQueryOrNull(): PreCheckoutQuery? = this as?
    dev.inmo.tgbotapi.types.payments.PreCheckoutQuery

public inline fun WithUser.preCheckoutQueryOrThrow(): PreCheckoutQuery = this as
    dev.inmo.tgbotapi.types.payments.PreCheckoutQuery

public inline fun <T> WithUser.ifPreCheckoutQuery(block: (PreCheckoutQuery) -> T): T? =
    preCheckoutQueryOrNull() ?.let(block)

public inline fun WithUser.shippingQueryOrNull(): ShippingQuery? = this as?
    dev.inmo.tgbotapi.types.payments.ShippingQuery

public inline fun WithUser.shippingQueryOrThrow(): ShippingQuery = this as
    dev.inmo.tgbotapi.types.payments.ShippingQuery

public inline fun <T> WithUser.ifShippingQuery(block: (ShippingQuery) -> T): T? =
    shippingQueryOrNull() ?.let(block)

public inline fun WithUser.pollAnswerOrNull(): PollAnswer? = this as?
    dev.inmo.tgbotapi.types.polls.PollAnswer

public inline fun WithUser.pollAnswerOrThrow(): PollAnswer = this as
    dev.inmo.tgbotapi.types.polls.PollAnswer

public inline fun <T> WithUser.ifPollAnswer(block: (PollAnswer) -> T): T? = pollAnswerOrNull()
    ?.let(block)

public inline fun WithUser.callbackQueryOrNull(): CallbackQuery? = this as?
    dev.inmo.tgbotapi.types.queries.callback.CallbackQuery

public inline fun WithUser.callbackQueryOrThrow(): CallbackQuery = this as
    dev.inmo.tgbotapi.types.queries.callback.CallbackQuery

public inline fun <T> WithUser.ifCallbackQuery(block: (CallbackQuery) -> T): T? =
    callbackQueryOrNull() ?.let(block)

public inline fun WithUser.unknownCallbackQueryTypeOrNull(): UnknownCallbackQueryType? = this as?
    dev.inmo.tgbotapi.types.queries.callback.UnknownCallbackQueryType

public inline fun WithUser.unknownCallbackQueryTypeOrThrow(): UnknownCallbackQueryType = this as
    dev.inmo.tgbotapi.types.queries.callback.UnknownCallbackQueryType

public inline fun <T> WithUser.ifUnknownCallbackQueryType(block: (UnknownCallbackQueryType) -> T):
    T? = unknownCallbackQueryTypeOrNull() ?.let(block)

public inline fun WithUser.dataCallbackQueryOrNull(): DataCallbackQuery? = this as?
    dev.inmo.tgbotapi.types.queries.callback.DataCallbackQuery

public inline fun WithUser.dataCallbackQueryOrThrow(): DataCallbackQuery = this as
    dev.inmo.tgbotapi.types.queries.callback.DataCallbackQuery

public inline fun <T> WithUser.ifDataCallbackQuery(block: (DataCallbackQuery) -> T): T? =
    dataCallbackQueryOrNull() ?.let(block)

public inline fun WithUser.gameShortNameCallbackQueryOrNull(): GameShortNameCallbackQuery? = this
    as? dev.inmo.tgbotapi.types.queries.callback.GameShortNameCallbackQuery

public inline fun WithUser.gameShortNameCallbackQueryOrThrow(): GameShortNameCallbackQuery = this as
    dev.inmo.tgbotapi.types.queries.callback.GameShortNameCallbackQuery

public inline fun <T>
    WithUser.ifGameShortNameCallbackQuery(block: (GameShortNameCallbackQuery) -> T): T? =
    gameShortNameCallbackQueryOrNull() ?.let(block)

public inline fun WithUser.inlineMessageIdCallbackQueryOrNull(): InlineMessageIdCallbackQuery? =
    this as? dev.inmo.tgbotapi.types.queries.callback.InlineMessageIdCallbackQuery

public inline fun WithUser.inlineMessageIdCallbackQueryOrThrow(): InlineMessageIdCallbackQuery =
    this as dev.inmo.tgbotapi.types.queries.callback.InlineMessageIdCallbackQuery

public inline fun <T>
    WithUser.ifInlineMessageIdCallbackQuery(block: (InlineMessageIdCallbackQuery) -> T): T? =
    inlineMessageIdCallbackQueryOrNull() ?.let(block)

public inline fun WithUser.inlineMessageIdDataCallbackQueryOrNull():
    InlineMessageIdDataCallbackQuery? = this as?
    dev.inmo.tgbotapi.types.queries.callback.InlineMessageIdDataCallbackQuery

public inline fun WithUser.inlineMessageIdDataCallbackQueryOrThrow():
    InlineMessageIdDataCallbackQuery = this as
    dev.inmo.tgbotapi.types.queries.callback.InlineMessageIdDataCallbackQuery

public inline fun <T>
    WithUser.ifInlineMessageIdDataCallbackQuery(block: (InlineMessageIdDataCallbackQuery) -> T): T?
    = inlineMessageIdDataCallbackQueryOrNull() ?.let(block)

public inline fun WithUser.inlineMessageIdGameShortNameCallbackQueryOrNull():
    InlineMessageIdGameShortNameCallbackQuery? = this as?
    dev.inmo.tgbotapi.types.queries.callback.InlineMessageIdGameShortNameCallbackQuery

public inline fun WithUser.inlineMessageIdGameShortNameCallbackQueryOrThrow():
    InlineMessageIdGameShortNameCallbackQuery = this as
    dev.inmo.tgbotapi.types.queries.callback.InlineMessageIdGameShortNameCallbackQuery

public inline fun <T>
    WithUser.ifInlineMessageIdGameShortNameCallbackQuery(block: (InlineMessageIdGameShortNameCallbackQuery) -> T):
    T? = inlineMessageIdGameShortNameCallbackQueryOrNull() ?.let(block)

public inline fun WithUser.messageCallbackQueryOrNull(): MessageCallbackQuery? = this as?
    dev.inmo.tgbotapi.types.queries.callback.MessageCallbackQuery

public inline fun WithUser.messageCallbackQueryOrThrow(): MessageCallbackQuery = this as
    dev.inmo.tgbotapi.types.queries.callback.MessageCallbackQuery

public inline fun <T> WithUser.ifMessageCallbackQuery(block: (MessageCallbackQuery) -> T): T? =
    messageCallbackQueryOrNull() ?.let(block)

public inline fun WithUser.messageDataCallbackQueryOrNull(): MessageDataCallbackQuery? = this as?
    dev.inmo.tgbotapi.types.queries.callback.MessageDataCallbackQuery

public inline fun WithUser.messageDataCallbackQueryOrThrow(): MessageDataCallbackQuery = this as
    dev.inmo.tgbotapi.types.queries.callback.MessageDataCallbackQuery

public inline fun <T> WithUser.ifMessageDataCallbackQuery(block: (MessageDataCallbackQuery) -> T):
    T? = messageDataCallbackQueryOrNull() ?.let(block)

public inline fun WithUser.messageGameShortNameCallbackQueryOrNull():
    MessageGameShortNameCallbackQuery? = this as?
    dev.inmo.tgbotapi.types.queries.callback.MessageGameShortNameCallbackQuery

public inline fun WithUser.messageGameShortNameCallbackQueryOrThrow():
    MessageGameShortNameCallbackQuery = this as
    dev.inmo.tgbotapi.types.queries.callback.MessageGameShortNameCallbackQuery

public inline fun <T>
    WithUser.ifMessageGameShortNameCallbackQuery(block: (MessageGameShortNameCallbackQuery) -> T):
    T? = messageGameShortNameCallbackQueryOrNull() ?.let(block)

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

public inline fun InlineQueryResult.inlineQueryResultArticleOrNull(): InlineQueryResultArticle? =
    this as? dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultArticle

public inline fun InlineQueryResult.inlineQueryResultArticleOrThrow(): InlineQueryResultArticle =
    this as dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultArticle

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultArticle(block: (InlineQueryResultArticle) -> T): T? =
    inlineQueryResultArticleOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultAudioCachedImplOrNull():
    InlineQueryResultAudioCachedImpl? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultAudioCachedImpl

public inline fun InlineQueryResult.inlineQueryResultAudioCachedImplOrThrow():
    InlineQueryResultAudioCachedImpl = this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultAudioCachedImpl

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultAudioCachedImpl(block: (InlineQueryResultAudioCachedImpl) -> T):
    T? = inlineQueryResultAudioCachedImplOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultAudioImplOrNull(): InlineQueryResultAudioImpl?
    = this as? dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultAudioImpl

public inline fun InlineQueryResult.inlineQueryResultAudioImplOrThrow(): InlineQueryResultAudioImpl
    = this as dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultAudioImpl

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultAudioImpl(block: (InlineQueryResultAudioImpl) -> T): T? =
    inlineQueryResultAudioImplOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultContactOrNull(): InlineQueryResultContact? =
    this as? dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultContact

public inline fun InlineQueryResult.inlineQueryResultContactOrThrow(): InlineQueryResultContact =
    this as dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultContact

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultContact(block: (InlineQueryResultContact) -> T): T? =
    inlineQueryResultContactOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultDocumentCachedImplOrNull():
    InlineQueryResultDocumentCachedImpl? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultDocumentCachedImpl

public inline fun InlineQueryResult.inlineQueryResultDocumentCachedImplOrThrow():
    InlineQueryResultDocumentCachedImpl = this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultDocumentCachedImpl

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultDocumentCachedImpl(block: (InlineQueryResultDocumentCachedImpl) -> T):
    T? = inlineQueryResultDocumentCachedImplOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultDocumentImplOrNull():
    InlineQueryResultDocumentImpl? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultDocumentImpl

public inline fun InlineQueryResult.inlineQueryResultDocumentImplOrThrow():
    InlineQueryResultDocumentImpl = this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultDocumentImpl

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultDocumentImpl(block: (InlineQueryResultDocumentImpl) -> T):
    T? = inlineQueryResultDocumentImplOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultGameOrNull(): InlineQueryResultGame? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultGame

public inline fun InlineQueryResult.inlineQueryResultGameOrThrow(): InlineQueryResultGame = this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultGame

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultGame(block: (InlineQueryResultGame) -> T): T? =
    inlineQueryResultGameOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultGifCachedImplOrNull():
    InlineQueryResultGifCachedImpl? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultGifCachedImpl

public inline fun InlineQueryResult.inlineQueryResultGifCachedImplOrThrow():
    InlineQueryResultGifCachedImpl = this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultGifCachedImpl

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultGifCachedImpl(block: (InlineQueryResultGifCachedImpl) -> T):
    T? = inlineQueryResultGifCachedImplOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultGifImplOrNull(): InlineQueryResultGifImpl? =
    this as? dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultGifImpl

public inline fun InlineQueryResult.inlineQueryResultGifImplOrThrow(): InlineQueryResultGifImpl =
    this as dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultGifImpl

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultGifImpl(block: (InlineQueryResultGifImpl) -> T): T? =
    inlineQueryResultGifImplOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultLocationOrNull(): InlineQueryResultLocation? =
    this as? dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultLocation

public inline fun InlineQueryResult.inlineQueryResultLocationOrThrow(): InlineQueryResultLocation =
    this as dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultLocation

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultLocation(block: (InlineQueryResultLocation) -> T): T? =
    inlineQueryResultLocationOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultMpeg4GifCachedImplOrNull():
    InlineQueryResultMpeg4GifCachedImpl? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultMpeg4GifCachedImpl

public inline fun InlineQueryResult.inlineQueryResultMpeg4GifCachedImplOrThrow():
    InlineQueryResultMpeg4GifCachedImpl = this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultMpeg4GifCachedImpl

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultMpeg4GifCachedImpl(block: (InlineQueryResultMpeg4GifCachedImpl) -> T):
    T? = inlineQueryResultMpeg4GifCachedImplOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultMpeg4GifImplOrNull():
    InlineQueryResultMpeg4GifImpl? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultMpeg4GifImpl

public inline fun InlineQueryResult.inlineQueryResultMpeg4GifImplOrThrow():
    InlineQueryResultMpeg4GifImpl = this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultMpeg4GifImpl

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultMpeg4GifImpl(block: (InlineQueryResultMpeg4GifImpl) -> T):
    T? = inlineQueryResultMpeg4GifImplOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultPhotoCachedImplOrNull():
    InlineQueryResultPhotoCachedImpl? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultPhotoCachedImpl

public inline fun InlineQueryResult.inlineQueryResultPhotoCachedImplOrThrow():
    InlineQueryResultPhotoCachedImpl = this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultPhotoCachedImpl

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultPhotoCachedImpl(block: (InlineQueryResultPhotoCachedImpl) -> T):
    T? = inlineQueryResultPhotoCachedImplOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultPhotoImplOrNull(): InlineQueryResultPhotoImpl?
    = this as? dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultPhotoImpl

public inline fun InlineQueryResult.inlineQueryResultPhotoImplOrThrow(): InlineQueryResultPhotoImpl
    = this as dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultPhotoImpl

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultPhotoImpl(block: (InlineQueryResultPhotoImpl) -> T): T? =
    inlineQueryResultPhotoImplOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultStickerCachedOrNull():
    InlineQueryResultStickerCached? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultStickerCached

public inline fun InlineQueryResult.inlineQueryResultStickerCachedOrThrow():
    InlineQueryResultStickerCached = this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultStickerCached

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultStickerCached(block: (InlineQueryResultStickerCached) -> T):
    T? = inlineQueryResultStickerCachedOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultVenueOrNull(): InlineQueryResultVenue? = this
    as? dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultVenue

public inline fun InlineQueryResult.inlineQueryResultVenueOrThrow(): InlineQueryResultVenue = this
    as dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultVenue

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultVenue(block: (InlineQueryResultVenue) -> T): T? =
    inlineQueryResultVenueOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultVideoCachedImplOrNull():
    InlineQueryResultVideoCachedImpl? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultVideoCachedImpl

public inline fun InlineQueryResult.inlineQueryResultVideoCachedImplOrThrow():
    InlineQueryResultVideoCachedImpl = this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultVideoCachedImpl

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultVideoCachedImpl(block: (InlineQueryResultVideoCachedImpl) -> T):
    T? = inlineQueryResultVideoCachedImplOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultVideoImplOrNull(): InlineQueryResultVideoImpl?
    = this as? dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultVideoImpl

public inline fun InlineQueryResult.inlineQueryResultVideoImplOrThrow(): InlineQueryResultVideoImpl
    = this as dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultVideoImpl

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultVideoImpl(block: (InlineQueryResultVideoImpl) -> T): T? =
    inlineQueryResultVideoImplOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultVoiceCachedImplOrNull():
    InlineQueryResultVoiceCachedImpl? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultVoiceCachedImpl

public inline fun InlineQueryResult.inlineQueryResultVoiceCachedImplOrThrow():
    InlineQueryResultVoiceCachedImpl = this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultVoiceCachedImpl

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultVoiceCachedImpl(block: (InlineQueryResultVoiceCachedImpl) -> T):
    T? = inlineQueryResultVoiceCachedImplOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultVoiceImplOrNull(): InlineQueryResultVoiceImpl?
    = this as? dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultVoiceImpl

public inline fun InlineQueryResult.inlineQueryResultVoiceImplOrThrow(): InlineQueryResultVoiceImpl
    = this as dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultVoiceImpl

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultVoiceImpl(block: (InlineQueryResultVoiceImpl) -> T): T? =
    inlineQueryResultVoiceImplOrNull() ?.let(block)

public inline fun InlineQueryResult.describedInlineQueryResultOrNull(): DescribedInlineQueryResult?
    = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.DescribedInlineQueryResult

public inline fun InlineQueryResult.describedInlineQueryResultOrThrow(): DescribedInlineQueryResult
    = this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.DescribedInlineQueryResult

public inline fun <T>
    InlineQueryResult.ifDescribedInlineQueryResult(block: (DescribedInlineQueryResult) -> T): T? =
    describedInlineQueryResultOrNull() ?.let(block)

public inline fun InlineQueryResult.fileInlineQueryResultOrNull(): FileInlineQueryResult? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.FileInlineQueryResult

public inline fun InlineQueryResult.fileInlineQueryResultOrThrow(): FileInlineQueryResult = this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.FileInlineQueryResult

public inline fun <T>
    InlineQueryResult.ifFileInlineQueryResult(block: (FileInlineQueryResult) -> T): T? =
    fileInlineQueryResultOrNull() ?.let(block)

public inline fun InlineQueryResult.optionallyTitledInlineQueryResultOrNull():
    OptionallyTitledInlineQueryResult? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.OptionallyTitledInlineQueryResult

public inline fun InlineQueryResult.optionallyTitledInlineQueryResultOrThrow():
    OptionallyTitledInlineQueryResult = this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.OptionallyTitledInlineQueryResult

public inline fun <T>
    InlineQueryResult.ifOptionallyTitledInlineQueryResult(block: (OptionallyTitledInlineQueryResult) -> T):
    T? = optionallyTitledInlineQueryResultOrNull() ?.let(block)

public inline fun InlineQueryResult.sizedInlineQueryResultOrNull(): SizedInlineQueryResult? = this
    as? dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.SizedInlineQueryResult

public inline fun InlineQueryResult.sizedInlineQueryResultOrThrow(): SizedInlineQueryResult = this
    as dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.SizedInlineQueryResult

public inline fun <T>
    InlineQueryResult.ifSizedInlineQueryResult(block: (SizedInlineQueryResult) -> T): T? =
    sizedInlineQueryResultOrNull() ?.let(block)

public inline fun InlineQueryResult.thumbSizedInlineQueryResultOrNull():
    ThumbSizedInlineQueryResult? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.ThumbSizedInlineQueryResult

public inline fun InlineQueryResult.thumbSizedInlineQueryResultOrThrow():
    ThumbSizedInlineQueryResult = this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.ThumbSizedInlineQueryResult

public inline fun <T>
    InlineQueryResult.ifThumbSizedInlineQueryResult(block: (ThumbSizedInlineQueryResult) -> T): T? =
    thumbSizedInlineQueryResultOrNull() ?.let(block)

public inline fun InlineQueryResult.thumbedInlineQueryResultOrNull(): ThumbedInlineQueryResult? =
    this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.ThumbedInlineQueryResult

public inline fun InlineQueryResult.thumbedInlineQueryResultOrThrow(): ThumbedInlineQueryResult =
    this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.ThumbedInlineQueryResult

public inline fun <T>
    InlineQueryResult.ifThumbedInlineQueryResult(block: (ThumbedInlineQueryResult) -> T): T? =
    thumbedInlineQueryResultOrNull() ?.let(block)

public inline fun InlineQueryResult.thumbedWithMimeTypeInlineQueryResultOrNull():
    ThumbedWithMimeTypeInlineQueryResult? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.ThumbedWithMimeTypeInlineQueryResult

public inline fun InlineQueryResult.thumbedWithMimeTypeInlineQueryResultOrThrow():
    ThumbedWithMimeTypeInlineQueryResult = this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.ThumbedWithMimeTypeInlineQueryResult

public inline fun <T>
    InlineQueryResult.ifThumbedWithMimeTypeInlineQueryResult(block: (ThumbedWithMimeTypeInlineQueryResult) -> T):
    T? = thumbedWithMimeTypeInlineQueryResultOrNull() ?.let(block)

public inline fun InlineQueryResult.titledInlineQueryResultOrNull(): TitledInlineQueryResult? = this
    as? dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.TitledInlineQueryResult

public inline fun InlineQueryResult.titledInlineQueryResultOrThrow(): TitledInlineQueryResult = this
    as dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.TitledInlineQueryResult

public inline fun <T>
    InlineQueryResult.ifTitledInlineQueryResult(block: (TitledInlineQueryResult) -> T): T? =
    titledInlineQueryResultOrNull() ?.let(block)

public inline fun InlineQueryResult.urlInlineQueryResultOrNull(): UrlInlineQueryResult? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.UrlInlineQueryResult

public inline fun InlineQueryResult.urlInlineQueryResultOrThrow(): UrlInlineQueryResult = this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.UrlInlineQueryResult

public inline fun <T> InlineQueryResult.ifUrlInlineQueryResult(block: (UrlInlineQueryResult) -> T):
    T? = urlInlineQueryResultOrNull() ?.let(block)

public inline fun InlineQueryResult.withInputMessageContentInlineQueryResultOrNull():
    WithInputMessageContentInlineQueryResult? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.WithInputMessageContentInlineQueryResult

public inline fun InlineQueryResult.withInputMessageContentInlineQueryResultOrThrow():
    WithInputMessageContentInlineQueryResult = this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.WithInputMessageContentInlineQueryResult

public inline fun <T>
    InlineQueryResult.ifWithInputMessageContentInlineQueryResult(block: (WithInputMessageContentInlineQueryResult) -> T):
    T? = withInputMessageContentInlineQueryResultOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultAudioOrNull(): InlineQueryResultAudio? = this
    as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.audio.InlineQueryResultAudio

public inline fun InlineQueryResult.inlineQueryResultAudioOrThrow(): InlineQueryResultAudio = this
    as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.audio.InlineQueryResultAudio

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultAudio(block: (InlineQueryResultAudio) -> T): T? =
    inlineQueryResultAudioOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultAudioCachedOrNull():
    InlineQueryResultAudioCached? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.audio.InlineQueryResultAudioCached

public inline fun InlineQueryResult.inlineQueryResultAudioCachedOrThrow():
    InlineQueryResultAudioCached = this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.audio.InlineQueryResultAudioCached

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultAudioCached(block: (InlineQueryResultAudioCached) -> T): T?
    = inlineQueryResultAudioCachedOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultAudioCommonOrNull():
    InlineQueryResultAudioCommon? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.audio.InlineQueryResultAudioCommon

public inline fun InlineQueryResult.inlineQueryResultAudioCommonOrThrow():
    InlineQueryResultAudioCommon = this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.audio.InlineQueryResultAudioCommon

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultAudioCommon(block: (InlineQueryResultAudioCommon) -> T): T?
    = inlineQueryResultAudioCommonOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultDocumentOrNull(): InlineQueryResultDocument? =
    this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.document.InlineQueryResultDocument

public inline fun InlineQueryResult.inlineQueryResultDocumentOrThrow(): InlineQueryResultDocument =
    this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.document.InlineQueryResultDocument

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultDocument(block: (InlineQueryResultDocument) -> T): T? =
    inlineQueryResultDocumentOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultDocumentCachedOrNull():
    InlineQueryResultDocumentCached? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.document.InlineQueryResultDocumentCached

public inline fun InlineQueryResult.inlineQueryResultDocumentCachedOrThrow():
    InlineQueryResultDocumentCached = this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.document.InlineQueryResultDocumentCached

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultDocumentCached(block: (InlineQueryResultDocumentCached) -> T):
    T? = inlineQueryResultDocumentCachedOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultDocumentCommonOrNull():
    InlineQueryResultDocumentCommon? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.document.InlineQueryResultDocumentCommon

public inline fun InlineQueryResult.inlineQueryResultDocumentCommonOrThrow():
    InlineQueryResultDocumentCommon = this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.document.InlineQueryResultDocumentCommon

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultDocumentCommon(block: (InlineQueryResultDocumentCommon) -> T):
    T? = inlineQueryResultDocumentCommonOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultGifOrNull(): InlineQueryResultGif? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.gif.InlineQueryResultGif

public inline fun InlineQueryResult.inlineQueryResultGifOrThrow(): InlineQueryResultGif = this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.gif.InlineQueryResultGif

public inline fun <T> InlineQueryResult.ifInlineQueryResultGif(block: (InlineQueryResultGif) -> T):
    T? = inlineQueryResultGifOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultGifCachedOrNull(): InlineQueryResultGifCached?
    = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.gif.InlineQueryResultGifCached

public inline fun InlineQueryResult.inlineQueryResultGifCachedOrThrow(): InlineQueryResultGifCached
    = this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.gif.InlineQueryResultGifCached

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultGifCached(block: (InlineQueryResultGifCached) -> T): T? =
    inlineQueryResultGifCachedOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultGifCommonOrNull(): InlineQueryResultGifCommon?
    = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.gif.InlineQueryResultGifCommon

public inline fun InlineQueryResult.inlineQueryResultGifCommonOrThrow(): InlineQueryResultGifCommon
    = this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.gif.InlineQueryResultGifCommon

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultGifCommon(block: (InlineQueryResultGifCommon) -> T): T? =
    inlineQueryResultGifCommonOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultMpeg4GifOrNull(): InlineQueryResultMpeg4Gif? =
    this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.mpeg4gif.InlineQueryResultMpeg4Gif

public inline fun InlineQueryResult.inlineQueryResultMpeg4GifOrThrow(): InlineQueryResultMpeg4Gif =
    this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.mpeg4gif.InlineQueryResultMpeg4Gif

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultMpeg4Gif(block: (InlineQueryResultMpeg4Gif) -> T): T? =
    inlineQueryResultMpeg4GifOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultMpeg4GifCachedOrNull():
    InlineQueryResultMpeg4GifCached? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.mpeg4gif.InlineQueryResultMpeg4GifCached

public inline fun InlineQueryResult.inlineQueryResultMpeg4GifCachedOrThrow():
    InlineQueryResultMpeg4GifCached = this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.mpeg4gif.InlineQueryResultMpeg4GifCached

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultMpeg4GifCached(block: (InlineQueryResultMpeg4GifCached) -> T):
    T? = inlineQueryResultMpeg4GifCachedOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultMpeg4GifCommonOrNull():
    InlineQueryResultMpeg4GifCommon? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.mpeg4gif.InlineQueryResultMpeg4GifCommon

public inline fun InlineQueryResult.inlineQueryResultMpeg4GifCommonOrThrow():
    InlineQueryResultMpeg4GifCommon = this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.mpeg4gif.InlineQueryResultMpeg4GifCommon

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultMpeg4GifCommon(block: (InlineQueryResultMpeg4GifCommon) -> T):
    T? = inlineQueryResultMpeg4GifCommonOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultPhotoOrNull(): InlineQueryResultPhoto? = this
    as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.photo.InlineQueryResultPhoto

public inline fun InlineQueryResult.inlineQueryResultPhotoOrThrow(): InlineQueryResultPhoto = this
    as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.photo.InlineQueryResultPhoto

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultPhoto(block: (InlineQueryResultPhoto) -> T): T? =
    inlineQueryResultPhotoOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultPhotoCachedOrNull():
    InlineQueryResultPhotoCached? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.photo.InlineQueryResultPhotoCached

public inline fun InlineQueryResult.inlineQueryResultPhotoCachedOrThrow():
    InlineQueryResultPhotoCached = this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.photo.InlineQueryResultPhotoCached

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultPhotoCached(block: (InlineQueryResultPhotoCached) -> T): T?
    = inlineQueryResultPhotoCachedOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultPhotoCommonOrNull():
    InlineQueryResultPhotoCommon? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.photo.InlineQueryResultPhotoCommon

public inline fun InlineQueryResult.inlineQueryResultPhotoCommonOrThrow():
    InlineQueryResultPhotoCommon = this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.photo.InlineQueryResultPhotoCommon

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultPhotoCommon(block: (InlineQueryResultPhotoCommon) -> T): T?
    = inlineQueryResultPhotoCommonOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultVideoOrNull(): InlineQueryResultVideo? = this
    as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.video.InlineQueryResultVideo

public inline fun InlineQueryResult.inlineQueryResultVideoOrThrow(): InlineQueryResultVideo = this
    as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.video.InlineQueryResultVideo

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultVideo(block: (InlineQueryResultVideo) -> T): T? =
    inlineQueryResultVideoOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultVideoCachedOrNull():
    InlineQueryResultVideoCached? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.video.InlineQueryResultVideoCached

public inline fun InlineQueryResult.inlineQueryResultVideoCachedOrThrow():
    InlineQueryResultVideoCached = this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.video.InlineQueryResultVideoCached

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultVideoCached(block: (InlineQueryResultVideoCached) -> T): T?
    = inlineQueryResultVideoCachedOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultVideoCommonOrNull():
    InlineQueryResultVideoCommon? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.video.InlineQueryResultVideoCommon

public inline fun InlineQueryResult.inlineQueryResultVideoCommonOrThrow():
    InlineQueryResultVideoCommon = this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.video.InlineQueryResultVideoCommon

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultVideoCommon(block: (InlineQueryResultVideoCommon) -> T): T?
    = inlineQueryResultVideoCommonOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultVoiceOrNull(): InlineQueryResultVoice? = this
    as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.voice.InlineQueryResultVoice

public inline fun InlineQueryResult.inlineQueryResultVoiceOrThrow(): InlineQueryResultVoice = this
    as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.voice.InlineQueryResultVoice

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultVoice(block: (InlineQueryResultVoice) -> T): T? =
    inlineQueryResultVoiceOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultVoiceCachedOrNull():
    InlineQueryResultVoiceCached? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.voice.InlineQueryResultVoiceCached

public inline fun InlineQueryResult.inlineQueryResultVoiceCachedOrThrow():
    InlineQueryResultVoiceCached = this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.voice.InlineQueryResultVoiceCached

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultVoiceCached(block: (InlineQueryResultVoiceCached) -> T): T?
    = inlineQueryResultVoiceCachedOrNull() ?.let(block)

public inline fun InlineQueryResult.inlineQueryResultVoiceCommonOrNull():
    InlineQueryResultVoiceCommon? = this as?
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.voice.InlineQueryResultVoiceCommon

public inline fun InlineQueryResult.inlineQueryResultVoiceCommonOrThrow():
    InlineQueryResultVoiceCommon = this as
    dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.voice.InlineQueryResultVoiceCommon

public inline fun <T>
    InlineQueryResult.ifInlineQueryResultVoiceCommon(block: (InlineQueryResultVoiceCommon) -> T): T?
    = inlineQueryResultVoiceCommonOrNull() ?.let(block)

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

public inline fun BotAction.typingActionOrNull(): TypingAction? = this as?
    dev.inmo.tgbotapi.types.actions.TypingAction

public inline fun BotAction.typingActionOrThrow(): TypingAction = this as
    dev.inmo.tgbotapi.types.actions.TypingAction

public inline fun <T> BotAction.ifTypingAction(block: (TypingAction) -> T): T? =
    typingActionOrNull() ?.let(block)

public inline fun BotAction.uploadPhotoActionOrNull(): UploadPhotoAction? = this as?
    dev.inmo.tgbotapi.types.actions.UploadPhotoAction

public inline fun BotAction.uploadPhotoActionOrThrow(): UploadPhotoAction = this as
    dev.inmo.tgbotapi.types.actions.UploadPhotoAction

public inline fun <T> BotAction.ifUploadPhotoAction(block: (UploadPhotoAction) -> T): T? =
    uploadPhotoActionOrNull() ?.let(block)

public inline fun BotAction.recordVideoActionOrNull(): RecordVideoAction? = this as?
    dev.inmo.tgbotapi.types.actions.RecordVideoAction

public inline fun BotAction.recordVideoActionOrThrow(): RecordVideoAction = this as
    dev.inmo.tgbotapi.types.actions.RecordVideoAction

public inline fun <T> BotAction.ifRecordVideoAction(block: (RecordVideoAction) -> T): T? =
    recordVideoActionOrNull() ?.let(block)

public inline fun BotAction.uploadVideoActionOrNull(): UploadVideoAction? = this as?
    dev.inmo.tgbotapi.types.actions.UploadVideoAction

public inline fun BotAction.uploadVideoActionOrThrow(): UploadVideoAction = this as
    dev.inmo.tgbotapi.types.actions.UploadVideoAction

public inline fun <T> BotAction.ifUploadVideoAction(block: (UploadVideoAction) -> T): T? =
    uploadVideoActionOrNull() ?.let(block)

public inline fun BotAction.recordVoiceActionOrNull(): RecordVoiceAction? = this as?
    dev.inmo.tgbotapi.types.actions.RecordVoiceAction

public inline fun BotAction.recordVoiceActionOrThrow(): RecordVoiceAction = this as
    dev.inmo.tgbotapi.types.actions.RecordVoiceAction

public inline fun <T> BotAction.ifRecordVoiceAction(block: (RecordVoiceAction) -> T): T? =
    recordVoiceActionOrNull() ?.let(block)

public inline fun BotAction.uploadVoiceActionOrNull(): UploadVoiceAction? = this as?
    dev.inmo.tgbotapi.types.actions.UploadVoiceAction

public inline fun BotAction.uploadVoiceActionOrThrow(): UploadVoiceAction = this as
    dev.inmo.tgbotapi.types.actions.UploadVoiceAction

public inline fun <T> BotAction.ifUploadVoiceAction(block: (UploadVoiceAction) -> T): T? =
    uploadVoiceActionOrNull() ?.let(block)

public inline fun BotAction.uploadDocumentActionOrNull(): UploadDocumentAction? = this as?
    dev.inmo.tgbotapi.types.actions.UploadDocumentAction

public inline fun BotAction.uploadDocumentActionOrThrow(): UploadDocumentAction = this as
    dev.inmo.tgbotapi.types.actions.UploadDocumentAction

public inline fun <T> BotAction.ifUploadDocumentAction(block: (UploadDocumentAction) -> T): T? =
    uploadDocumentActionOrNull() ?.let(block)

public inline fun BotAction.findLocationActionOrNull(): FindLocationAction? = this as?
    dev.inmo.tgbotapi.types.actions.FindLocationAction

public inline fun BotAction.findLocationActionOrThrow(): FindLocationAction = this as
    dev.inmo.tgbotapi.types.actions.FindLocationAction

public inline fun <T> BotAction.ifFindLocationAction(block: (FindLocationAction) -> T): T? =
    findLocationActionOrNull() ?.let(block)

public inline fun BotAction.recordVideoNoteActionOrNull(): RecordVideoNoteAction? = this as?
    dev.inmo.tgbotapi.types.actions.RecordVideoNoteAction

public inline fun BotAction.recordVideoNoteActionOrThrow(): RecordVideoNoteAction = this as
    dev.inmo.tgbotapi.types.actions.RecordVideoNoteAction

public inline fun <T> BotAction.ifRecordVideoNoteAction(block: (RecordVideoNoteAction) -> T): T? =
    recordVideoNoteActionOrNull() ?.let(block)

public inline fun BotAction.uploadVideoNoteActionOrNull(): UploadVideoNoteAction? = this as?
    dev.inmo.tgbotapi.types.actions.UploadVideoNoteAction

public inline fun BotAction.uploadVideoNoteActionOrThrow(): UploadVideoNoteAction = this as
    dev.inmo.tgbotapi.types.actions.UploadVideoNoteAction

public inline fun <T> BotAction.ifUploadVideoNoteAction(block: (UploadVideoNoteAction) -> T): T? =
    uploadVideoNoteActionOrNull() ?.let(block)

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

public inline fun InlineKeyboardButton.unknownInlineKeyboardButtonOrNull():
    UnknownInlineKeyboardButton? = this as?
    dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.UnknownInlineKeyboardButton

public inline fun InlineKeyboardButton.unknownInlineKeyboardButtonOrThrow():
    UnknownInlineKeyboardButton = this as
    dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.UnknownInlineKeyboardButton

public inline fun <T>
    InlineKeyboardButton.ifUnknownInlineKeyboardButton(block: (UnknownInlineKeyboardButton) -> T):
    T? = unknownInlineKeyboardButtonOrNull() ?.let(block)

public inline fun InlineKeyboardButton.payInlineKeyboardButtonOrNull(): PayInlineKeyboardButton? =
    this as? dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.PayInlineKeyboardButton

public inline fun InlineKeyboardButton.payInlineKeyboardButtonOrThrow(): PayInlineKeyboardButton =
    this as dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.PayInlineKeyboardButton

public inline fun <T>
    InlineKeyboardButton.ifPayInlineKeyboardButton(block: (PayInlineKeyboardButton) -> T): T? =
    payInlineKeyboardButtonOrNull() ?.let(block)

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

public inline fun InlineKeyboardButton.switchInlineQueryCurrentChatInlineKeyboardButtonOrNull():
    SwitchInlineQueryCurrentChatInlineKeyboardButton? = this as?
    dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.SwitchInlineQueryCurrentChatInlineKeyboardButton

public inline fun InlineKeyboardButton.switchInlineQueryCurrentChatInlineKeyboardButtonOrThrow():
    SwitchInlineQueryCurrentChatInlineKeyboardButton = this as
    dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.SwitchInlineQueryCurrentChatInlineKeyboardButton

public inline fun <T>
    InlineKeyboardButton.ifSwitchInlineQueryCurrentChatInlineKeyboardButton(block: (SwitchInlineQueryCurrentChatInlineKeyboardButton) -> T):
    T? = switchInlineQueryCurrentChatInlineKeyboardButtonOrNull() ?.let(block)

public inline fun InlineKeyboardButton.switchInlineQueryChosenChatInlineKeyboardButtonOrNull():
    SwitchInlineQueryChosenChatInlineKeyboardButton? = this as?
    dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.SwitchInlineQueryChosenChatInlineKeyboardButton

public inline fun InlineKeyboardButton.switchInlineQueryChosenChatInlineKeyboardButtonOrThrow():
    SwitchInlineQueryChosenChatInlineKeyboardButton = this as
    dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.SwitchInlineQueryChosenChatInlineKeyboardButton

public inline fun <T>
    InlineKeyboardButton.ifSwitchInlineQueryChosenChatInlineKeyboardButton(block: (SwitchInlineQueryChosenChatInlineKeyboardButton) -> T):
    T? = switchInlineQueryChosenChatInlineKeyboardButtonOrNull() ?.let(block)

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

public inline fun Chat.usernameChatOrNull(): UsernameChat? = this as?
    dev.inmo.tgbotapi.types.chat.UsernameChat

public inline fun Chat.usernameChatOrThrow(): UsernameChat = this as
    dev.inmo.tgbotapi.types.chat.UsernameChat

public inline fun <T> Chat.ifUsernameChat(block: (UsernameChat) -> T): T? = usernameChatOrNull()
    ?.let(block)

public inline fun Chat.privateChatOrNull(): PrivateChat? = this as?
    dev.inmo.tgbotapi.types.chat.PrivateChat

public inline fun Chat.privateChatOrThrow(): PrivateChat = this as
    dev.inmo.tgbotapi.types.chat.PrivateChat

public inline fun <T> Chat.ifPrivateChat(block: (PrivateChat) -> T): T? = privateChatOrNull()
    ?.let(block)

public inline fun Chat.publicChatOrNull(): PublicChat? = this as?
    dev.inmo.tgbotapi.types.chat.PublicChat

public inline fun Chat.publicChatOrThrow(): PublicChat = this as
    dev.inmo.tgbotapi.types.chat.PublicChat

public inline fun <T> Chat.ifPublicChat(block: (PublicChat) -> T): T? = publicChatOrNull()
    ?.let(block)

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

public inline fun Chat.groupChatOrNull(): GroupChat? = this as?
    dev.inmo.tgbotapi.types.chat.GroupChat

public inline fun Chat.groupChatOrThrow(): GroupChat = this as
    dev.inmo.tgbotapi.types.chat.GroupChat

public inline fun <T> Chat.ifGroupChat(block: (GroupChat) -> T): T? = groupChatOrNull() ?.let(block)

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

public inline fun Chat.possiblyPremiumChatOrNull(): PossiblyPremiumChat? = this as?
    dev.inmo.tgbotapi.types.chat.PossiblyPremiumChat

public inline fun Chat.possiblyPremiumChatOrThrow(): PossiblyPremiumChat = this as
    dev.inmo.tgbotapi.types.chat.PossiblyPremiumChat

public inline fun <T> Chat.ifPossiblyPremiumChat(block: (PossiblyPremiumChat) -> T): T? =
    possiblyPremiumChatOrNull() ?.let(block)

public inline fun Chat.ableToAddInAttachmentMenuChatOrNull(): AbleToAddInAttachmentMenuChat? = this
    as? dev.inmo.tgbotapi.types.chat.AbleToAddInAttachmentMenuChat

public inline fun Chat.ableToAddInAttachmentMenuChatOrThrow(): AbleToAddInAttachmentMenuChat = this
    as dev.inmo.tgbotapi.types.chat.AbleToAddInAttachmentMenuChat

public inline fun <T>
    Chat.ifAbleToAddInAttachmentMenuChat(block: (AbleToAddInAttachmentMenuChat) -> T): T? =
    ableToAddInAttachmentMenuChatOrNull() ?.let(block)

public inline fun Chat.extendedChannelChatImplOrNull(): ExtendedChannelChatImpl? = this as?
    dev.inmo.tgbotapi.types.chat.ExtendedChannelChatImpl

public inline fun Chat.extendedChannelChatImplOrThrow(): ExtendedChannelChatImpl = this as
    dev.inmo.tgbotapi.types.chat.ExtendedChannelChatImpl

public inline fun <T> Chat.ifExtendedChannelChatImpl(block: (ExtendedChannelChatImpl) -> T): T? =
    extendedChannelChatImplOrNull() ?.let(block)

public inline fun Chat.extendedGroupChatImplOrNull(): ExtendedGroupChatImpl? = this as?
    dev.inmo.tgbotapi.types.chat.ExtendedGroupChatImpl

public inline fun Chat.extendedGroupChatImplOrThrow(): ExtendedGroupChatImpl = this as
    dev.inmo.tgbotapi.types.chat.ExtendedGroupChatImpl

public inline fun <T> Chat.ifExtendedGroupChatImpl(block: (ExtendedGroupChatImpl) -> T): T? =
    extendedGroupChatImplOrNull() ?.let(block)

public inline fun Chat.extendedPrivateChatImplOrNull(): ExtendedPrivateChatImpl? = this as?
    dev.inmo.tgbotapi.types.chat.ExtendedPrivateChatImpl

public inline fun Chat.extendedPrivateChatImplOrThrow(): ExtendedPrivateChatImpl = this as
    dev.inmo.tgbotapi.types.chat.ExtendedPrivateChatImpl

public inline fun <T> Chat.ifExtendedPrivateChatImpl(block: (ExtendedPrivateChatImpl) -> T): T? =
    extendedPrivateChatImplOrNull() ?.let(block)

public inline fun Chat.extendedSupergroupChatImplOrNull(): ExtendedSupergroupChatImpl? = this as?
    dev.inmo.tgbotapi.types.chat.ExtendedSupergroupChatImpl

public inline fun Chat.extendedSupergroupChatImplOrThrow(): ExtendedSupergroupChatImpl = this as
    dev.inmo.tgbotapi.types.chat.ExtendedSupergroupChatImpl

public inline fun <T> Chat.ifExtendedSupergroupChatImpl(block: (ExtendedSupergroupChatImpl) -> T):
    T? = extendedSupergroupChatImplOrNull() ?.let(block)

public inline fun Chat.extendedForumChatImplOrNull(): ExtendedForumChatImpl? = this as?
    dev.inmo.tgbotapi.types.chat.ExtendedForumChatImpl

public inline fun Chat.extendedForumChatImplOrThrow(): ExtendedForumChatImpl = this as
    dev.inmo.tgbotapi.types.chat.ExtendedForumChatImpl

public inline fun <T> Chat.ifExtendedForumChatImpl(block: (ExtendedForumChatImpl) -> T): T? =
    extendedForumChatImplOrNull() ?.let(block)

public inline fun Chat.extendedBotOrNull(): ExtendedBot? = this as?
    dev.inmo.tgbotapi.types.chat.ExtendedBot

public inline fun Chat.extendedBotOrThrow(): ExtendedBot = this as
    dev.inmo.tgbotapi.types.chat.ExtendedBot

public inline fun <T> Chat.ifExtendedBot(block: (ExtendedBot) -> T): T? = extendedBotOrNull()
    ?.let(block)

public inline fun Chat.unknownExtendedChatOrNull(): UnknownExtendedChat? = this as?
    dev.inmo.tgbotapi.types.chat.UnknownExtendedChat

public inline fun Chat.unknownExtendedChatOrThrow(): UnknownExtendedChat = this as
    dev.inmo.tgbotapi.types.chat.UnknownExtendedChat

public inline fun <T> Chat.ifUnknownExtendedChat(block: (UnknownExtendedChat) -> T): T? =
    unknownExtendedChatOrNull() ?.let(block)

public inline fun Chat.extendedChannelChatOrNull(): ExtendedChannelChat? = this as?
    dev.inmo.tgbotapi.types.chat.ExtendedChannelChat

public inline fun Chat.extendedChannelChatOrThrow(): ExtendedChannelChat = this as
    dev.inmo.tgbotapi.types.chat.ExtendedChannelChat

public inline fun <T> Chat.ifExtendedChannelChat(block: (ExtendedChannelChat) -> T): T? =
    extendedChannelChatOrNull() ?.let(block)

public inline fun Chat.extendedGroupChatOrNull(): ExtendedGroupChat? = this as?
    dev.inmo.tgbotapi.types.chat.ExtendedGroupChat

public inline fun Chat.extendedGroupChatOrThrow(): ExtendedGroupChat = this as
    dev.inmo.tgbotapi.types.chat.ExtendedGroupChat

public inline fun <T> Chat.ifExtendedGroupChat(block: (ExtendedGroupChat) -> T): T? =
    extendedGroupChatOrNull() ?.let(block)

public inline fun Chat.extendedPrivateChatOrNull(): ExtendedPrivateChat? = this as?
    dev.inmo.tgbotapi.types.chat.ExtendedPrivateChat

public inline fun Chat.extendedPrivateChatOrThrow(): ExtendedPrivateChat = this as
    dev.inmo.tgbotapi.types.chat.ExtendedPrivateChat

public inline fun <T> Chat.ifExtendedPrivateChat(block: (ExtendedPrivateChat) -> T): T? =
    extendedPrivateChatOrNull() ?.let(block)

public inline fun Chat.extendedPublicChatOrNull(): ExtendedPublicChat? = this as?
    dev.inmo.tgbotapi.types.chat.ExtendedPublicChat

public inline fun Chat.extendedPublicChatOrThrow(): ExtendedPublicChat = this as
    dev.inmo.tgbotapi.types.chat.ExtendedPublicChat

public inline fun <T> Chat.ifExtendedPublicChat(block: (ExtendedPublicChat) -> T): T? =
    extendedPublicChatOrNull() ?.let(block)

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

public inline fun Chat.groupChatImplOrNull(): GroupChatImpl? = this as?
    dev.inmo.tgbotapi.types.chat.GroupChatImpl

public inline fun Chat.groupChatImplOrThrow(): GroupChatImpl = this as
    dev.inmo.tgbotapi.types.chat.GroupChatImpl

public inline fun <T> Chat.ifGroupChatImpl(block: (GroupChatImpl) -> T): T? = groupChatImplOrNull()
    ?.let(block)

public inline fun Chat.privateChatImplOrNull(): PrivateChatImpl? = this as?
    dev.inmo.tgbotapi.types.chat.PrivateChatImpl

public inline fun Chat.privateChatImplOrThrow(): PrivateChatImpl = this as
    dev.inmo.tgbotapi.types.chat.PrivateChatImpl

public inline fun <T> Chat.ifPrivateChatImpl(block: (PrivateChatImpl) -> T): T? =
    privateChatImplOrNull() ?.let(block)

public inline fun Chat.supergroupChatImplOrNull(): SupergroupChatImpl? = this as?
    dev.inmo.tgbotapi.types.chat.SupergroupChatImpl

public inline fun Chat.supergroupChatImplOrThrow(): SupergroupChatImpl = this as
    dev.inmo.tgbotapi.types.chat.SupergroupChatImpl

public inline fun <T> Chat.ifSupergroupChatImpl(block: (SupergroupChatImpl) -> T): T? =
    supergroupChatImplOrNull() ?.let(block)

public inline fun Chat.forumChatImplOrNull(): ForumChatImpl? = this as?
    dev.inmo.tgbotapi.types.chat.ForumChatImpl

public inline fun Chat.forumChatImplOrThrow(): ForumChatImpl = this as
    dev.inmo.tgbotapi.types.chat.ForumChatImpl

public inline fun <T> Chat.ifForumChatImpl(block: (ForumChatImpl) -> T): T? = forumChatImplOrNull()
    ?.let(block)

public inline fun Chat.channelChatImplOrNull(): ChannelChatImpl? = this as?
    dev.inmo.tgbotapi.types.chat.ChannelChatImpl

public inline fun Chat.channelChatImplOrThrow(): ChannelChatImpl = this as
    dev.inmo.tgbotapi.types.chat.ChannelChatImpl

public inline fun <T> Chat.ifChannelChatImpl(block: (ChannelChatImpl) -> T): T? =
    channelChatImplOrNull() ?.let(block)

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

public inline fun Chat.commonUserOrNull(): CommonUser? = this as?
    dev.inmo.tgbotapi.types.chat.CommonUser

public inline fun Chat.commonUserOrThrow(): CommonUser = this as
    dev.inmo.tgbotapi.types.chat.CommonUser

public inline fun <T> Chat.ifCommonUser(block: (CommonUser) -> T): T? = commonUserOrNull()
    ?.let(block)

public inline fun Chat.unknownChatTypeOrNull(): UnknownChatType? = this as?
    dev.inmo.tgbotapi.types.chat.UnknownChatType

public inline fun Chat.unknownChatTypeOrThrow(): UnknownChatType = this as
    dev.inmo.tgbotapi.types.chat.UnknownChatType

public inline fun <T> Chat.ifUnknownChatType(block: (UnknownChatType) -> T): T? =
    unknownChatTypeOrNull() ?.let(block)

public inline fun DiceAnimationType.cubeDiceAnimationTypeOrNull(): CubeDiceAnimationType? = this as?
    dev.inmo.tgbotapi.types.dice.CubeDiceAnimationType

public inline fun DiceAnimationType.cubeDiceAnimationTypeOrThrow(): CubeDiceAnimationType = this as
    dev.inmo.tgbotapi.types.dice.CubeDiceAnimationType

public inline fun <T>
    DiceAnimationType.ifCubeDiceAnimationType(block: (CubeDiceAnimationType) -> T): T? =
    cubeDiceAnimationTypeOrNull() ?.let(block)

public inline fun DiceAnimationType.dartsDiceAnimationTypeOrNull(): DartsDiceAnimationType? = this
    as? dev.inmo.tgbotapi.types.dice.DartsDiceAnimationType

public inline fun DiceAnimationType.dartsDiceAnimationTypeOrThrow(): DartsDiceAnimationType = this
    as dev.inmo.tgbotapi.types.dice.DartsDiceAnimationType

public inline fun <T>
    DiceAnimationType.ifDartsDiceAnimationType(block: (DartsDiceAnimationType) -> T): T? =
    dartsDiceAnimationTypeOrNull() ?.let(block)

public inline fun DiceAnimationType.basketballDiceAnimationTypeOrNull():
    BasketballDiceAnimationType? = this as? dev.inmo.tgbotapi.types.dice.BasketballDiceAnimationType

public inline fun DiceAnimationType.basketballDiceAnimationTypeOrThrow():
    BasketballDiceAnimationType = this as dev.inmo.tgbotapi.types.dice.BasketballDiceAnimationType

public inline fun <T>
    DiceAnimationType.ifBasketballDiceAnimationType(block: (BasketballDiceAnimationType) -> T): T? =
    basketballDiceAnimationTypeOrNull() ?.let(block)

public inline fun DiceAnimationType.footballDiceAnimationTypeOrNull(): FootballDiceAnimationType? =
    this as? dev.inmo.tgbotapi.types.dice.FootballDiceAnimationType

public inline fun DiceAnimationType.footballDiceAnimationTypeOrThrow(): FootballDiceAnimationType =
    this as dev.inmo.tgbotapi.types.dice.FootballDiceAnimationType

public inline fun <T>
    DiceAnimationType.ifFootballDiceAnimationType(block: (FootballDiceAnimationType) -> T): T? =
    footballDiceAnimationTypeOrNull() ?.let(block)

public inline fun DiceAnimationType.bowlingDiceAnimationTypeOrNull(): BowlingDiceAnimationType? =
    this as? dev.inmo.tgbotapi.types.dice.BowlingDiceAnimationType

public inline fun DiceAnimationType.bowlingDiceAnimationTypeOrThrow(): BowlingDiceAnimationType =
    this as dev.inmo.tgbotapi.types.dice.BowlingDiceAnimationType

public inline fun <T>
    DiceAnimationType.ifBowlingDiceAnimationType(block: (BowlingDiceAnimationType) -> T): T? =
    bowlingDiceAnimationTypeOrNull() ?.let(block)

public inline fun DiceAnimationType.slotMachineDiceAnimationTypeOrNull():
    SlotMachineDiceAnimationType? = this as?
    dev.inmo.tgbotapi.types.dice.SlotMachineDiceAnimationType

public inline fun DiceAnimationType.slotMachineDiceAnimationTypeOrThrow():
    SlotMachineDiceAnimationType = this as dev.inmo.tgbotapi.types.dice.SlotMachineDiceAnimationType

public inline fun <T>
    DiceAnimationType.ifSlotMachineDiceAnimationType(block: (SlotMachineDiceAnimationType) -> T): T?
    = slotMachineDiceAnimationTypeOrNull() ?.let(block)

public inline fun DiceAnimationType.customDiceAnimationTypeOrNull(): CustomDiceAnimationType? = this
    as? dev.inmo.tgbotapi.types.dice.CustomDiceAnimationType

public inline fun DiceAnimationType.customDiceAnimationTypeOrThrow(): CustomDiceAnimationType = this
    as dev.inmo.tgbotapi.types.dice.CustomDiceAnimationType

public inline fun <T>
    DiceAnimationType.ifCustomDiceAnimationType(block: (CustomDiceAnimationType) -> T): T? =
    customDiceAnimationTypeOrNull() ?.let(block)

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

public inline fun TelegramMediaFile.photoSizeOrNull(): PhotoSize? = this as?
    dev.inmo.tgbotapi.types.files.PhotoSize

public inline fun TelegramMediaFile.photoSizeOrThrow(): PhotoSize = this as
    dev.inmo.tgbotapi.types.files.PhotoSize

public inline fun <T> TelegramMediaFile.ifPhotoSize(block: (PhotoSize) -> T): T? = photoSizeOrNull()
    ?.let(block)

public inline fun TelegramMediaFile.playableMediaFileOrNull(): PlayableMediaFile? = this as?
    dev.inmo.tgbotapi.types.files.PlayableMediaFile

public inline fun TelegramMediaFile.playableMediaFileOrThrow(): PlayableMediaFile = this as
    dev.inmo.tgbotapi.types.files.PlayableMediaFile

public inline fun <T> TelegramMediaFile.ifPlayableMediaFile(block: (PlayableMediaFile) -> T): T? =
    playableMediaFileOrNull() ?.let(block)

public inline fun TelegramMediaFile.sizedMediaFileOrNull(): SizedMediaFile? = this as?
    dev.inmo.tgbotapi.types.files.SizedMediaFile

public inline fun TelegramMediaFile.sizedMediaFileOrThrow(): SizedMediaFile = this as
    dev.inmo.tgbotapi.types.files.SizedMediaFile

public inline fun <T> TelegramMediaFile.ifSizedMediaFile(block: (SizedMediaFile) -> T): T? =
    sizedMediaFileOrNull() ?.let(block)

public inline fun TelegramMediaFile.stickerOrNull(): Sticker? = this as?
    dev.inmo.tgbotapi.types.files.Sticker

public inline fun TelegramMediaFile.stickerOrThrow(): Sticker = this as
    dev.inmo.tgbotapi.types.files.Sticker

public inline fun <T> TelegramMediaFile.ifSticker(block: (Sticker) -> T): T? = stickerOrNull()
    ?.let(block)

public inline fun TelegramMediaFile.videoStickerOrNull(): VideoSticker? = this as?
    dev.inmo.tgbotapi.types.files.VideoSticker

public inline fun TelegramMediaFile.videoStickerOrThrow(): VideoSticker = this as
    dev.inmo.tgbotapi.types.files.VideoSticker

public inline fun <T> TelegramMediaFile.ifVideoSticker(block: (VideoSticker) -> T): T? =
    videoStickerOrNull() ?.let(block)

public inline fun TelegramMediaFile.animatedStickerOrNull(): AnimatedSticker? = this as?
    dev.inmo.tgbotapi.types.files.AnimatedSticker

public inline fun TelegramMediaFile.animatedStickerOrThrow(): AnimatedSticker = this as
    dev.inmo.tgbotapi.types.files.AnimatedSticker

public inline fun <T> TelegramMediaFile.ifAnimatedSticker(block: (AnimatedSticker) -> T): T? =
    animatedStickerOrNull() ?.let(block)

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

public inline fun TelegramMediaFile.regularAnimatedStickerOrNull(): RegularAnimatedSticker? = this
    as? dev.inmo.tgbotapi.types.files.RegularAnimatedSticker

public inline fun TelegramMediaFile.regularAnimatedStickerOrThrow(): RegularAnimatedSticker = this
    as dev.inmo.tgbotapi.types.files.RegularAnimatedSticker

public inline fun <T>
    TelegramMediaFile.ifRegularAnimatedSticker(block: (RegularAnimatedSticker) -> T): T? =
    regularAnimatedStickerOrNull() ?.let(block)

public inline fun TelegramMediaFile.regularVideoStickerOrNull(): RegularVideoSticker? = this as?
    dev.inmo.tgbotapi.types.files.RegularVideoSticker

public inline fun TelegramMediaFile.regularVideoStickerOrThrow(): RegularVideoSticker = this as
    dev.inmo.tgbotapi.types.files.RegularVideoSticker

public inline fun <T> TelegramMediaFile.ifRegularVideoSticker(block: (RegularVideoSticker) -> T): T?
    = regularVideoStickerOrNull() ?.let(block)

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

public inline fun TelegramMediaFile.maskAnimatedStickerOrNull(): MaskAnimatedSticker? = this as?
    dev.inmo.tgbotapi.types.files.MaskAnimatedSticker

public inline fun TelegramMediaFile.maskAnimatedStickerOrThrow(): MaskAnimatedSticker = this as
    dev.inmo.tgbotapi.types.files.MaskAnimatedSticker

public inline fun <T> TelegramMediaFile.ifMaskAnimatedSticker(block: (MaskAnimatedSticker) -> T): T?
    = maskAnimatedStickerOrNull() ?.let(block)

public inline fun TelegramMediaFile.maskVideoStickerOrNull(): MaskVideoSticker? = this as?
    dev.inmo.tgbotapi.types.files.MaskVideoSticker

public inline fun TelegramMediaFile.maskVideoStickerOrThrow(): MaskVideoSticker = this as
    dev.inmo.tgbotapi.types.files.MaskVideoSticker

public inline fun <T> TelegramMediaFile.ifMaskVideoSticker(block: (MaskVideoSticker) -> T): T? =
    maskVideoStickerOrNull() ?.let(block)

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

public inline fun TelegramMediaFile.customEmojiAnimatedStickerOrNull(): CustomEmojiAnimatedSticker?
    = this as? dev.inmo.tgbotapi.types.files.CustomEmojiAnimatedSticker

public inline fun TelegramMediaFile.customEmojiAnimatedStickerOrThrow(): CustomEmojiAnimatedSticker
    = this as dev.inmo.tgbotapi.types.files.CustomEmojiAnimatedSticker

public inline fun <T>
    TelegramMediaFile.ifCustomEmojiAnimatedSticker(block: (CustomEmojiAnimatedSticker) -> T): T? =
    customEmojiAnimatedStickerOrNull() ?.let(block)

public inline fun TelegramMediaFile.customEmojiVideoStickerOrNull(): CustomEmojiVideoSticker? = this
    as? dev.inmo.tgbotapi.types.files.CustomEmojiVideoSticker

public inline fun TelegramMediaFile.customEmojiVideoStickerOrThrow(): CustomEmojiVideoSticker = this
    as dev.inmo.tgbotapi.types.files.CustomEmojiVideoSticker

public inline fun <T>
    TelegramMediaFile.ifCustomEmojiVideoSticker(block: (CustomEmojiVideoSticker) -> T): T? =
    customEmojiVideoStickerOrNull() ?.let(block)

public inline fun TelegramMediaFile.unknownStickerOrNull(): UnknownSticker? = this as?
    dev.inmo.tgbotapi.types.files.UnknownSticker

public inline fun TelegramMediaFile.unknownStickerOrThrow(): UnknownSticker = this as
    dev.inmo.tgbotapi.types.files.UnknownSticker

public inline fun <T> TelegramMediaFile.ifUnknownSticker(block: (UnknownSticker) -> T): T? =
    unknownStickerOrNull() ?.let(block)

public inline fun TelegramMediaFile.thumbedMediaFileOrNull(): ThumbedMediaFile? = this as?
    dev.inmo.tgbotapi.types.files.ThumbedMediaFile

public inline fun TelegramMediaFile.thumbedMediaFileOrThrow(): ThumbedMediaFile = this as
    dev.inmo.tgbotapi.types.files.ThumbedMediaFile

public inline fun <T> TelegramMediaFile.ifThumbedMediaFile(block: (ThumbedMediaFile) -> T): T? =
    thumbedMediaFileOrNull() ?.let(block)

public inline fun TelegramMediaFile.videoFileOrNull(): VideoFile? = this as?
    dev.inmo.tgbotapi.types.files.VideoFile

public inline fun TelegramMediaFile.videoFileOrThrow(): VideoFile = this as
    dev.inmo.tgbotapi.types.files.VideoFile

public inline fun <T> TelegramMediaFile.ifVideoFile(block: (VideoFile) -> T): T? = videoFileOrNull()
    ?.let(block)

public inline fun TelegramMediaFile.videoNoteFileOrNull(): VideoNoteFile? = this as?
    dev.inmo.tgbotapi.types.files.VideoNoteFile

public inline fun TelegramMediaFile.videoNoteFileOrThrow(): VideoNoteFile = this as
    dev.inmo.tgbotapi.types.files.VideoNoteFile

public inline fun <T> TelegramMediaFile.ifVideoNoteFile(block: (VideoNoteFile) -> T): T? =
    videoNoteFileOrNull() ?.let(block)

public inline fun TelegramMediaFile.voiceFileOrNull(): VoiceFile? = this as?
    dev.inmo.tgbotapi.types.files.VoiceFile

public inline fun TelegramMediaFile.voiceFileOrThrow(): VoiceFile = this as
    dev.inmo.tgbotapi.types.files.VoiceFile

public inline fun <T> TelegramMediaFile.ifVoiceFile(block: (VoiceFile) -> T): T? = voiceFileOrNull()
    ?.let(block)

public inline fun Location.staticLocationOrNull(): StaticLocation? = this as?
    dev.inmo.tgbotapi.types.location.StaticLocation

public inline fun Location.staticLocationOrThrow(): StaticLocation = this as
    dev.inmo.tgbotapi.types.location.StaticLocation

public inline fun <T> Location.ifStaticLocation(block: (StaticLocation) -> T): T? =
    staticLocationOrNull() ?.let(block)

public inline fun Location.liveLocationOrNull(): LiveLocation? = this as?
    dev.inmo.tgbotapi.types.location.LiveLocation

public inline fun Location.liveLocationOrThrow(): LiveLocation = this as
    dev.inmo.tgbotapi.types.location.LiveLocation

public inline fun <T> Location.ifLiveLocation(block: (LiveLocation) -> T): T? = liveLocationOrNull()
    ?.let(block)

public inline fun TelegramMedia.duratedTelegramMediaOrNull(): DuratedTelegramMedia? = this as?
    dev.inmo.tgbotapi.types.media.DuratedTelegramMedia

public inline fun TelegramMedia.duratedTelegramMediaOrThrow(): DuratedTelegramMedia = this as
    dev.inmo.tgbotapi.types.media.DuratedTelegramMedia

public inline fun <T> TelegramMedia.ifDuratedTelegramMedia(block: (DuratedTelegramMedia) -> T): T? =
    duratedTelegramMediaOrNull() ?.let(block)

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

public inline fun TelegramMedia.visualMediaGroupMemberTelegramMediaOrNull():
    VisualMediaGroupMemberTelegramMedia? = this as?
    dev.inmo.tgbotapi.types.media.VisualMediaGroupMemberTelegramMedia

public inline fun TelegramMedia.visualMediaGroupMemberTelegramMediaOrThrow():
    VisualMediaGroupMemberTelegramMedia = this as
    dev.inmo.tgbotapi.types.media.VisualMediaGroupMemberTelegramMedia

public inline fun <T>
    TelegramMedia.ifVisualMediaGroupMemberTelegramMedia(block: (VisualMediaGroupMemberTelegramMedia) -> T):
    T? = visualMediaGroupMemberTelegramMediaOrNull() ?.let(block)

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

public inline fun TelegramMedia.telegramMediaDocumentOrNull(): TelegramMediaDocument? = this as?
    dev.inmo.tgbotapi.types.media.TelegramMediaDocument

public inline fun TelegramMedia.telegramMediaDocumentOrThrow(): TelegramMediaDocument = this as
    dev.inmo.tgbotapi.types.media.TelegramMediaDocument

public inline fun <T> TelegramMedia.ifTelegramMediaDocument(block: (TelegramMediaDocument) -> T): T?
    = telegramMediaDocumentOrNull() ?.let(block)

public inline fun TelegramMedia.telegramMediaPhotoOrNull(): TelegramMediaPhoto? = this as?
    dev.inmo.tgbotapi.types.media.TelegramMediaPhoto

public inline fun TelegramMedia.telegramMediaPhotoOrThrow(): TelegramMediaPhoto = this as
    dev.inmo.tgbotapi.types.media.TelegramMediaPhoto

public inline fun <T> TelegramMedia.ifTelegramMediaPhoto(block: (TelegramMediaPhoto) -> T): T? =
    telegramMediaPhotoOrNull() ?.let(block)

public inline fun TelegramMedia.telegramMediaVideoOrNull(): TelegramMediaVideo? = this as?
    dev.inmo.tgbotapi.types.media.TelegramMediaVideo

public inline fun TelegramMedia.telegramMediaVideoOrThrow(): TelegramMediaVideo = this as
    dev.inmo.tgbotapi.types.media.TelegramMediaVideo

public inline fun <T> TelegramMedia.ifTelegramMediaVideo(block: (TelegramMediaVideo) -> T): T? =
    telegramMediaVideoOrNull() ?.let(block)

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

public inline fun ChatEvent.channelChatCreatedOrNull(): ChannelChatCreated? = this as?
    dev.inmo.tgbotapi.types.message.ChatEvents.ChannelChatCreated

public inline fun ChatEvent.channelChatCreatedOrThrow(): ChannelChatCreated = this as
    dev.inmo.tgbotapi.types.message.ChatEvents.ChannelChatCreated

public inline fun <T> ChatEvent.ifChannelChatCreated(block: (ChannelChatCreated) -> T): T? =
    channelChatCreatedOrNull() ?.let(block)

public inline fun ChatEvent.deleteChatPhotoOrNull(): DeleteChatPhoto? = this as?
    dev.inmo.tgbotapi.types.message.ChatEvents.DeleteChatPhoto

public inline fun ChatEvent.deleteChatPhotoOrThrow(): DeleteChatPhoto = this as
    dev.inmo.tgbotapi.types.message.ChatEvents.DeleteChatPhoto

public inline fun <T> ChatEvent.ifDeleteChatPhoto(block: (DeleteChatPhoto) -> T): T? =
    deleteChatPhotoOrNull() ?.let(block)

public inline fun ChatEvent.groupChatCreatedOrNull(): GroupChatCreated? = this as?
    dev.inmo.tgbotapi.types.message.ChatEvents.GroupChatCreated

public inline fun ChatEvent.groupChatCreatedOrThrow(): GroupChatCreated = this as
    dev.inmo.tgbotapi.types.message.ChatEvents.GroupChatCreated

public inline fun <T> ChatEvent.ifGroupChatCreated(block: (GroupChatCreated) -> T): T? =
    groupChatCreatedOrNull() ?.let(block)

public inline fun ChatEvent.leftChatMemberEventOrNull(): LeftChatMemberEvent? = this as?
    dev.inmo.tgbotapi.types.message.ChatEvents.LeftChatMemberEvent

public inline fun ChatEvent.leftChatMemberEventOrThrow(): LeftChatMemberEvent = this as
    dev.inmo.tgbotapi.types.message.ChatEvents.LeftChatMemberEvent

public inline fun <T> ChatEvent.ifLeftChatMemberEvent(block: (LeftChatMemberEvent) -> T): T? =
    leftChatMemberEventOrNull() ?.let(block)

public inline fun ChatEvent.messageAutoDeleteTimerChangedOrNull(): MessageAutoDeleteTimerChanged? =
    this as? dev.inmo.tgbotapi.types.message.ChatEvents.MessageAutoDeleteTimerChanged

public inline fun ChatEvent.messageAutoDeleteTimerChangedOrThrow(): MessageAutoDeleteTimerChanged =
    this as dev.inmo.tgbotapi.types.message.ChatEvents.MessageAutoDeleteTimerChanged

public inline fun <T>
    ChatEvent.ifMessageAutoDeleteTimerChanged(block: (MessageAutoDeleteTimerChanged) -> T): T? =
    messageAutoDeleteTimerChangedOrNull() ?.let(block)

public inline fun ChatEvent.migratedToSupergroupOrNull(): MigratedToSupergroup? = this as?
    dev.inmo.tgbotapi.types.message.ChatEvents.MigratedToSupergroup

public inline fun ChatEvent.migratedToSupergroupOrThrow(): MigratedToSupergroup = this as
    dev.inmo.tgbotapi.types.message.ChatEvents.MigratedToSupergroup

public inline fun <T> ChatEvent.ifMigratedToSupergroup(block: (MigratedToSupergroup) -> T): T? =
    migratedToSupergroupOrNull() ?.let(block)

public inline fun ChatEvent.newChatMembersOrNull(): NewChatMembers? = this as?
    dev.inmo.tgbotapi.types.message.ChatEvents.NewChatMembers

public inline fun ChatEvent.newChatMembersOrThrow(): NewChatMembers = this as
    dev.inmo.tgbotapi.types.message.ChatEvents.NewChatMembers

public inline fun <T> ChatEvent.ifNewChatMembers(block: (NewChatMembers) -> T): T? =
    newChatMembersOrNull() ?.let(block)

public inline fun ChatEvent.newChatPhotoOrNull(): NewChatPhoto? = this as?
    dev.inmo.tgbotapi.types.message.ChatEvents.NewChatPhoto

public inline fun ChatEvent.newChatPhotoOrThrow(): NewChatPhoto = this as
    dev.inmo.tgbotapi.types.message.ChatEvents.NewChatPhoto

public inline fun <T> ChatEvent.ifNewChatPhoto(block: (NewChatPhoto) -> T): T? =
    newChatPhotoOrNull() ?.let(block)

public inline fun ChatEvent.newChatTitleOrNull(): NewChatTitle? = this as?
    dev.inmo.tgbotapi.types.message.ChatEvents.NewChatTitle

public inline fun ChatEvent.newChatTitleOrThrow(): NewChatTitle = this as
    dev.inmo.tgbotapi.types.message.ChatEvents.NewChatTitle

public inline fun <T> ChatEvent.ifNewChatTitle(block: (NewChatTitle) -> T): T? =
    newChatTitleOrNull() ?.let(block)

public inline fun ChatEvent.pinnedMessageOrNull(): PinnedMessage? = this as?
    dev.inmo.tgbotapi.types.message.ChatEvents.PinnedMessage

public inline fun ChatEvent.pinnedMessageOrThrow(): PinnedMessage = this as
    dev.inmo.tgbotapi.types.message.ChatEvents.PinnedMessage

public inline fun <T> ChatEvent.ifPinnedMessage(block: (PinnedMessage) -> T): T? =
    pinnedMessageOrNull() ?.let(block)

public inline fun ChatEvent.proximityAlertTriggeredOrNull(): ProximityAlertTriggered? = this as?
    dev.inmo.tgbotapi.types.message.ChatEvents.ProximityAlertTriggered

public inline fun ChatEvent.proximityAlertTriggeredOrThrow(): ProximityAlertTriggered = this as
    dev.inmo.tgbotapi.types.message.ChatEvents.ProximityAlertTriggered

public inline fun <T> ChatEvent.ifProximityAlertTriggered(block: (ProximityAlertTriggered) -> T): T?
    = proximityAlertTriggeredOrNull() ?.let(block)

public inline fun ChatEvent.supergroupChatCreatedOrNull(): SupergroupChatCreated? = this as?
    dev.inmo.tgbotapi.types.message.ChatEvents.SupergroupChatCreated

public inline fun ChatEvent.supergroupChatCreatedOrThrow(): SupergroupChatCreated = this as
    dev.inmo.tgbotapi.types.message.ChatEvents.SupergroupChatCreated

public inline fun <T> ChatEvent.ifSupergroupChatCreated(block: (SupergroupChatCreated) -> T): T? =
    supergroupChatCreatedOrNull() ?.let(block)

public inline fun ChatEvent.userLoggedInOrNull(): UserLoggedIn? = this as?
    dev.inmo.tgbotapi.types.message.ChatEvents.UserLoggedIn

public inline fun ChatEvent.userLoggedInOrThrow(): UserLoggedIn = this as
    dev.inmo.tgbotapi.types.message.ChatEvents.UserLoggedIn

public inline fun <T> ChatEvent.ifUserLoggedIn(block: (UserLoggedIn) -> T): T? =
    userLoggedInOrNull() ?.let(block)

public inline fun ChatEvent.webAppDataOrNull(): WebAppData? = this as?
    dev.inmo.tgbotapi.types.message.ChatEvents.WebAppData

public inline fun ChatEvent.webAppDataOrThrow(): WebAppData = this as
    dev.inmo.tgbotapi.types.message.ChatEvents.WebAppData

public inline fun <T> ChatEvent.ifWebAppData(block: (WebAppData) -> T): T? = webAppDataOrNull()
    ?.let(block)

public inline fun ChatEvent.channelEventOrNull(): ChannelEvent? = this as?
    dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ChannelEvent

public inline fun ChatEvent.channelEventOrThrow(): ChannelEvent = this as
    dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ChannelEvent

public inline fun <T> ChatEvent.ifChannelEvent(block: (ChannelEvent) -> T): T? =
    channelEventOrNull() ?.let(block)

public inline fun ChatEvent.commonEventOrNull(): CommonEvent? = this as?
    dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.CommonEvent

public inline fun ChatEvent.commonEventOrThrow(): CommonEvent = this as
    dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.CommonEvent

public inline fun <T> ChatEvent.ifCommonEvent(block: (CommonEvent) -> T): T? = commonEventOrNull()
    ?.let(block)

public inline fun ChatEvent.forumEventOrNull(): ForumEvent? = this as?
    dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ForumEvent

public inline fun ChatEvent.forumEventOrThrow(): ForumEvent = this as
    dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ForumEvent

public inline fun <T> ChatEvent.ifForumEvent(block: (ForumEvent) -> T): T? = forumEventOrNull()
    ?.let(block)

public inline fun ChatEvent.groupEventOrNull(): GroupEvent? = this as?
    dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.GroupEvent

public inline fun ChatEvent.groupEventOrThrow(): GroupEvent = this as
    dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.GroupEvent

public inline fun <T> ChatEvent.ifGroupEvent(block: (GroupEvent) -> T): T? = groupEventOrNull()
    ?.let(block)

public inline fun ChatEvent.privateEventOrNull(): PrivateEvent? = this as?
    dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.PrivateEvent

public inline fun ChatEvent.privateEventOrThrow(): PrivateEvent = this as
    dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.PrivateEvent

public inline fun <T> ChatEvent.ifPrivateEvent(block: (PrivateEvent) -> T): T? =
    privateEventOrNull() ?.let(block)

public inline fun ChatEvent.publicChatEventOrNull(): PublicChatEvent? = this as?
    dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.PublicChatEvent

public inline fun ChatEvent.publicChatEventOrThrow(): PublicChatEvent = this as
    dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.PublicChatEvent

public inline fun <T> ChatEvent.ifPublicChatEvent(block: (PublicChatEvent) -> T): T? =
    publicChatEventOrNull() ?.let(block)

public inline fun ChatEvent.supergroupEventOrNull(): SupergroupEvent? = this as?
    dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.SupergroupEvent

public inline fun ChatEvent.supergroupEventOrThrow(): SupergroupEvent = this as
    dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.SupergroupEvent

public inline fun <T> ChatEvent.ifSupergroupEvent(block: (SupergroupEvent) -> T): T? =
    supergroupEventOrNull() ?.let(block)

public inline fun ChatEvent.videoChatEventOrNull(): VideoChatEvent? = this as?
    dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.VideoChatEvent

public inline fun ChatEvent.videoChatEventOrThrow(): VideoChatEvent = this as
    dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.VideoChatEvent

public inline fun <T> ChatEvent.ifVideoChatEvent(block: (VideoChatEvent) -> T): T? =
    videoChatEventOrNull() ?.let(block)

public inline fun ChatEvent.forumTopicClosedOrNull(): ForumTopicClosed? = this as?
    dev.inmo.tgbotapi.types.message.ChatEvents.forum.ForumTopicClosed

public inline fun ChatEvent.forumTopicClosedOrThrow(): ForumTopicClosed = this as
    dev.inmo.tgbotapi.types.message.ChatEvents.forum.ForumTopicClosed

public inline fun <T> ChatEvent.ifForumTopicClosed(block: (ForumTopicClosed) -> T): T? =
    forumTopicClosedOrNull() ?.let(block)

public inline fun ChatEvent.forumTopicCreatedOrNull(): ForumTopicCreated? = this as?
    dev.inmo.tgbotapi.types.message.ChatEvents.forum.ForumTopicCreated

public inline fun ChatEvent.forumTopicCreatedOrThrow(): ForumTopicCreated = this as
    dev.inmo.tgbotapi.types.message.ChatEvents.forum.ForumTopicCreated

public inline fun <T> ChatEvent.ifForumTopicCreated(block: (ForumTopicCreated) -> T): T? =
    forumTopicCreatedOrNull() ?.let(block)

public inline fun ChatEvent.forumTopicEditedOrNull(): ForumTopicEdited? = this as?
    dev.inmo.tgbotapi.types.message.ChatEvents.forum.ForumTopicEdited

public inline fun ChatEvent.forumTopicEditedOrThrow(): ForumTopicEdited = this as
    dev.inmo.tgbotapi.types.message.ChatEvents.forum.ForumTopicEdited

public inline fun <T> ChatEvent.ifForumTopicEdited(block: (ForumTopicEdited) -> T): T? =
    forumTopicEditedOrNull() ?.let(block)

public inline fun ChatEvent.forumTopicReopenedOrNull(): ForumTopicReopened? = this as?
    dev.inmo.tgbotapi.types.message.ChatEvents.forum.ForumTopicReopened

public inline fun ChatEvent.forumTopicReopenedOrThrow(): ForumTopicReopened = this as
    dev.inmo.tgbotapi.types.message.ChatEvents.forum.ForumTopicReopened

public inline fun <T> ChatEvent.ifForumTopicReopened(block: (ForumTopicReopened) -> T): T? =
    forumTopicReopenedOrNull() ?.let(block)

public inline fun ChatEvent.generalForumTopicHiddenOrNull(): GeneralForumTopicHidden? = this as?
    dev.inmo.tgbotapi.types.message.ChatEvents.forum.GeneralForumTopicHidden

public inline fun ChatEvent.generalForumTopicHiddenOrThrow(): GeneralForumTopicHidden = this as
    dev.inmo.tgbotapi.types.message.ChatEvents.forum.GeneralForumTopicHidden

public inline fun <T> ChatEvent.ifGeneralForumTopicHidden(block: (GeneralForumTopicHidden) -> T): T?
    = generalForumTopicHiddenOrNull() ?.let(block)

public inline fun ChatEvent.generalForumTopicUnhiddenOrNull(): GeneralForumTopicUnhidden? = this as?
    dev.inmo.tgbotapi.types.message.ChatEvents.forum.GeneralForumTopicUnhidden

public inline fun ChatEvent.generalForumTopicUnhiddenOrThrow(): GeneralForumTopicUnhidden = this as
    dev.inmo.tgbotapi.types.message.ChatEvents.forum.GeneralForumTopicUnhidden

public inline fun <T>
    ChatEvent.ifGeneralForumTopicUnhidden(block: (GeneralForumTopicUnhidden) -> T): T? =
    generalForumTopicUnhiddenOrNull() ?.let(block)

public inline fun ChatEvent.writeAccessAllowedOrNull(): WriteAccessAllowed? = this as?
    dev.inmo.tgbotapi.types.message.ChatEvents.forum.WriteAccessAllowed

public inline fun ChatEvent.writeAccessAllowedOrThrow(): WriteAccessAllowed = this as
    dev.inmo.tgbotapi.types.message.ChatEvents.forum.WriteAccessAllowed

public inline fun <T> ChatEvent.ifWriteAccessAllowed(block: (WriteAccessAllowed) -> T): T? =
    writeAccessAllowedOrNull() ?.let(block)

public inline fun ChatEvent.videoChatEndedOrNull(): VideoChatEnded? = this as?
    dev.inmo.tgbotapi.types.message.ChatEvents.voice.VideoChatEnded

public inline fun ChatEvent.videoChatEndedOrThrow(): VideoChatEnded = this as
    dev.inmo.tgbotapi.types.message.ChatEvents.voice.VideoChatEnded

public inline fun <T> ChatEvent.ifVideoChatEnded(block: (VideoChatEnded) -> T): T? =
    videoChatEndedOrNull() ?.let(block)

public inline fun ChatEvent.videoChatParticipantsInvitedOrNull(): VideoChatParticipantsInvited? =
    this as? dev.inmo.tgbotapi.types.message.ChatEvents.voice.VideoChatParticipantsInvited

public inline fun ChatEvent.videoChatParticipantsInvitedOrThrow(): VideoChatParticipantsInvited =
    this as dev.inmo.tgbotapi.types.message.ChatEvents.voice.VideoChatParticipantsInvited

public inline fun <T>
    ChatEvent.ifVideoChatParticipantsInvited(block: (VideoChatParticipantsInvited) -> T): T? =
    videoChatParticipantsInvitedOrNull() ?.let(block)

public inline fun ChatEvent.videoChatScheduledOrNull(): VideoChatScheduled? = this as?
    dev.inmo.tgbotapi.types.message.ChatEvents.voice.VideoChatScheduled

public inline fun ChatEvent.videoChatScheduledOrThrow(): VideoChatScheduled = this as
    dev.inmo.tgbotapi.types.message.ChatEvents.voice.VideoChatScheduled

public inline fun <T> ChatEvent.ifVideoChatScheduled(block: (VideoChatScheduled) -> T): T? =
    videoChatScheduledOrNull() ?.let(block)

public inline fun ChatEvent.videoChatStartedOrNull(): VideoChatStarted? = this as?
    dev.inmo.tgbotapi.types.message.ChatEvents.voice.VideoChatStarted

public inline fun ChatEvent.videoChatStartedOrThrow(): VideoChatStarted = this as
    dev.inmo.tgbotapi.types.message.ChatEvents.voice.VideoChatStarted

public inline fun <T> ChatEvent.ifVideoChatStarted(block: (VideoChatStarted) -> T): T? =
    videoChatStartedOrNull() ?.let(block)

public inline fun ChatEvent.successfulPaymentEventOrNull(): SuccessfulPaymentEvent? = this as?
    dev.inmo.tgbotapi.types.message.payments.SuccessfulPaymentEvent

public inline fun ChatEvent.successfulPaymentEventOrThrow(): SuccessfulPaymentEvent = this as
    dev.inmo.tgbotapi.types.message.payments.SuccessfulPaymentEvent

public inline fun <T> ChatEvent.ifSuccessfulPaymentEvent(block: (SuccessfulPaymentEvent) -> T): T? =
    successfulPaymentEventOrNull() ?.let(block)

public inline fun ChatEvent.chatSharedOrNull(): ChatShared? = this as?
    dev.inmo.tgbotapi.types.request.ChatShared

public inline fun ChatEvent.chatSharedOrThrow(): ChatShared = this as
    dev.inmo.tgbotapi.types.request.ChatShared

public inline fun <T> ChatEvent.ifChatShared(block: (ChatShared) -> T): T? = chatSharedOrNull()
    ?.let(block)

public inline fun ChatEvent.chatSharedRequestOrNull(): ChatSharedRequest? = this as?
    dev.inmo.tgbotapi.types.request.ChatSharedRequest

public inline fun ChatEvent.chatSharedRequestOrThrow(): ChatSharedRequest = this as
    dev.inmo.tgbotapi.types.request.ChatSharedRequest

public inline fun <T> ChatEvent.ifChatSharedRequest(block: (ChatSharedRequest) -> T): T? =
    chatSharedRequestOrNull() ?.let(block)

public inline fun ChatEvent.userSharedOrNull(): UserShared? = this as?
    dev.inmo.tgbotapi.types.request.UserShared

public inline fun ChatEvent.userSharedOrThrow(): UserShared = this as
    dev.inmo.tgbotapi.types.request.UserShared

public inline fun <T> ChatEvent.ifUserShared(block: (UserShared) -> T): T? = userSharedOrNull()
    ?.let(block)

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

public inline fun Message.channelContentMessageImplOrNull():
    ChannelContentMessageImpl<MessageContent>? = this as?
    dev.inmo.tgbotapi.types.message.ChannelContentMessageImpl<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun Message.channelContentMessageImplOrThrow():
    ChannelContentMessageImpl<MessageContent> = this as
    dev.inmo.tgbotapi.types.message.ChannelContentMessageImpl<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun <T>
    Message.ifChannelContentMessageImpl(block: (ChannelContentMessageImpl<MessageContent>) -> T): T?
    = channelContentMessageImplOrNull() ?.let(block)

public inline fun Message.channelEventMessageOrNull(): ChannelEventMessage<ChannelEvent>? = this as?
    dev.inmo.tgbotapi.types.message.ChannelEventMessage<dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ChannelEvent>

public inline fun Message.channelEventMessageOrThrow(): ChannelEventMessage<ChannelEvent> = this as
    dev.inmo.tgbotapi.types.message.ChannelEventMessage<dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ChannelEvent>

public inline fun <T>
    Message.ifChannelEventMessage(block: (ChannelEventMessage<ChannelEvent>) -> T): T? =
    channelEventMessageOrNull() ?.let(block)

public inline fun Message.commonGroupEventMessageOrNull(): CommonGroupEventMessage<GroupEvent>? =
    this as?
    dev.inmo.tgbotapi.types.message.CommonGroupEventMessage<dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.GroupEvent>

public inline fun Message.commonGroupEventMessageOrThrow(): CommonGroupEventMessage<GroupEvent> =
    this as
    dev.inmo.tgbotapi.types.message.CommonGroupEventMessage<dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.GroupEvent>

public inline fun <T>
    Message.ifCommonGroupEventMessage(block: (CommonGroupEventMessage<GroupEvent>) -> T): T? =
    commonGroupEventMessageOrNull() ?.let(block)

public inline fun Message.commonSupergroupEventMessageOrNull():
    CommonSupergroupEventMessage<SupergroupEvent>? = this as?
    dev.inmo.tgbotapi.types.message.CommonSupergroupEventMessage<dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.SupergroupEvent>

public inline fun Message.commonSupergroupEventMessageOrThrow():
    CommonSupergroupEventMessage<SupergroupEvent> = this as
    dev.inmo.tgbotapi.types.message.CommonSupergroupEventMessage<dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.SupergroupEvent>

public inline fun <T>
    Message.ifCommonSupergroupEventMessage(block: (CommonSupergroupEventMessage<SupergroupEvent>) -> T):
    T? = commonSupergroupEventMessageOrNull() ?.let(block)

public inline fun Message.connectedFromChannelGroupContentMessageImplOrNull():
    ConnectedFromChannelGroupContentMessageImpl<MessageContent>? = this as?
    dev.inmo.tgbotapi.types.message.ConnectedFromChannelGroupContentMessageImpl<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun Message.connectedFromChannelGroupContentMessageImplOrThrow():
    ConnectedFromChannelGroupContentMessageImpl<MessageContent> = this as
    dev.inmo.tgbotapi.types.message.ConnectedFromChannelGroupContentMessageImpl<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun <T>
    Message.ifConnectedFromChannelGroupContentMessageImpl(block: (ConnectedFromChannelGroupContentMessageImpl<MessageContent>) -> T):
    T? = connectedFromChannelGroupContentMessageImplOrNull() ?.let(block)

public inline fun Message.unconnectedFromChannelGroupContentMessageImplOrNull():
    UnconnectedFromChannelGroupContentMessageImpl<MessageContent>? = this as?
    dev.inmo.tgbotapi.types.message.UnconnectedFromChannelGroupContentMessageImpl<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun Message.unconnectedFromChannelGroupContentMessageImplOrThrow():
    UnconnectedFromChannelGroupContentMessageImpl<MessageContent> = this as
    dev.inmo.tgbotapi.types.message.UnconnectedFromChannelGroupContentMessageImpl<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun <T>
    Message.ifUnconnectedFromChannelGroupContentMessageImpl(block: (UnconnectedFromChannelGroupContentMessageImpl<MessageContent>) -> T):
    T? = unconnectedFromChannelGroupContentMessageImplOrNull() ?.let(block)

public inline fun Message.anonymousGroupContentMessageImplOrNull():
    AnonymousGroupContentMessageImpl<MessageContent>? = this as?
    dev.inmo.tgbotapi.types.message.AnonymousGroupContentMessageImpl<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun Message.anonymousGroupContentMessageImplOrThrow():
    AnonymousGroupContentMessageImpl<MessageContent> = this as
    dev.inmo.tgbotapi.types.message.AnonymousGroupContentMessageImpl<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun <T>
    Message.ifAnonymousGroupContentMessageImpl(block: (AnonymousGroupContentMessageImpl<MessageContent>) -> T):
    T? = anonymousGroupContentMessageImplOrNull() ?.let(block)

public inline fun Message.commonGroupContentMessageImplOrNull():
    CommonGroupContentMessageImpl<MessageContent>? = this as?
    dev.inmo.tgbotapi.types.message.CommonGroupContentMessageImpl<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun Message.commonGroupContentMessageImplOrThrow():
    CommonGroupContentMessageImpl<MessageContent> = this as
    dev.inmo.tgbotapi.types.message.CommonGroupContentMessageImpl<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun <T>
    Message.ifCommonGroupContentMessageImpl(block: (CommonGroupContentMessageImpl<MessageContent>) -> T):
    T? = commonGroupContentMessageImplOrNull() ?.let(block)

public inline fun Message.fromChannelForumContentMessageImplOrNull():
    FromChannelForumContentMessageImpl<MessageContent>? = this as?
    dev.inmo.tgbotapi.types.message.FromChannelForumContentMessageImpl<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun Message.fromChannelForumContentMessageImplOrThrow():
    FromChannelForumContentMessageImpl<MessageContent> = this as
    dev.inmo.tgbotapi.types.message.FromChannelForumContentMessageImpl<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun <T>
    Message.ifFromChannelForumContentMessageImpl(block: (FromChannelForumContentMessageImpl<MessageContent>) -> T):
    T? = fromChannelForumContentMessageImplOrNull() ?.let(block)

public inline fun Message.anonymousForumContentMessageImplOrNull():
    AnonymousForumContentMessageImpl<MessageContent>? = this as?
    dev.inmo.tgbotapi.types.message.AnonymousForumContentMessageImpl<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun Message.anonymousForumContentMessageImplOrThrow():
    AnonymousForumContentMessageImpl<MessageContent> = this as
    dev.inmo.tgbotapi.types.message.AnonymousForumContentMessageImpl<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun <T>
    Message.ifAnonymousForumContentMessageImpl(block: (AnonymousForumContentMessageImpl<MessageContent>) -> T):
    T? = anonymousForumContentMessageImplOrNull() ?.let(block)

public inline fun Message.commonForumContentMessageImplOrNull():
    CommonForumContentMessageImpl<MessageContent>? = this as?
    dev.inmo.tgbotapi.types.message.CommonForumContentMessageImpl<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun Message.commonForumContentMessageImplOrThrow():
    CommonForumContentMessageImpl<MessageContent> = this as
    dev.inmo.tgbotapi.types.message.CommonForumContentMessageImpl<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun <T>
    Message.ifCommonForumContentMessageImpl(block: (CommonForumContentMessageImpl<MessageContent>) -> T):
    T? = commonForumContentMessageImplOrNull() ?.let(block)

public inline fun Message.passportMessageOrNull(): PassportMessage? = this as?
    dev.inmo.tgbotapi.types.message.PassportMessage

public inline fun Message.passportMessageOrThrow(): PassportMessage = this as
    dev.inmo.tgbotapi.types.message.PassportMessage

public inline fun <T> Message.ifPassportMessage(block: (PassportMessage) -> T): T? =
    passportMessageOrNull() ?.let(block)

public inline fun Message.privateContentMessageImplOrNull():
    PrivateContentMessageImpl<MessageContent>? = this as?
    dev.inmo.tgbotapi.types.message.PrivateContentMessageImpl<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun Message.privateContentMessageImplOrThrow():
    PrivateContentMessageImpl<MessageContent> = this as
    dev.inmo.tgbotapi.types.message.PrivateContentMessageImpl<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun <T>
    Message.ifPrivateContentMessageImpl(block: (PrivateContentMessageImpl<MessageContent>) -> T): T?
    = privateContentMessageImplOrNull() ?.let(block)

public inline fun Message.privateEventMessageOrNull(): PrivateEventMessage<PrivateEvent>? = this as?
    dev.inmo.tgbotapi.types.message.PrivateEventMessage<dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.PrivateEvent>

public inline fun Message.privateEventMessageOrThrow(): PrivateEventMessage<PrivateEvent> = this as
    dev.inmo.tgbotapi.types.message.PrivateEventMessage<dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.PrivateEvent>

public inline fun <T>
    Message.ifPrivateEventMessage(block: (PrivateEventMessage<PrivateEvent>) -> T): T? =
    privateEventMessageOrNull() ?.let(block)

public inline fun Message.channelContentMessageOrNull(): ChannelContentMessage<MessageContent>? =
    this as?
    dev.inmo.tgbotapi.types.message.abstracts.ChannelContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun Message.channelContentMessageOrThrow(): ChannelContentMessage<MessageContent> =
    this as
    dev.inmo.tgbotapi.types.message.abstracts.ChannelContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun <T>
    Message.ifChannelContentMessage(block: (ChannelContentMessage<MessageContent>) -> T): T? =
    channelContentMessageOrNull() ?.let(block)

public inline fun Message.chatEventMessageOrNull(): ChatEventMessage<ChatEvent>? = this as?
    dev.inmo.tgbotapi.types.message.abstracts.ChatEventMessage<dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ChatEvent>

public inline fun Message.chatEventMessageOrThrow(): ChatEventMessage<ChatEvent> = this as
    dev.inmo.tgbotapi.types.message.abstracts.ChatEventMessage<dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ChatEvent>

public inline fun <T> Message.ifChatEventMessage(block: (ChatEventMessage<ChatEvent>) -> T): T? =
    chatEventMessageOrNull() ?.let(block)

public inline fun Message.commonMessageOrNull(): CommonMessage<MessageContent>? = this as?
    dev.inmo.tgbotapi.types.message.abstracts.CommonMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun Message.commonMessageOrThrow(): CommonMessage<MessageContent> = this as
    dev.inmo.tgbotapi.types.message.abstracts.CommonMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun <T> Message.ifCommonMessage(block: (CommonMessage<MessageContent>) -> T): T? =
    commonMessageOrNull() ?.let(block)

public inline fun Message.contentMessageOrNull(): ContentMessage<MessageContent>? = this as?
    dev.inmo.tgbotapi.types.message.abstracts.ContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun Message.contentMessageOrThrow(): ContentMessage<MessageContent> = this as
    dev.inmo.tgbotapi.types.message.abstracts.ContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun <T> Message.ifContentMessage(block: (ContentMessage<MessageContent>) -> T): T? =
    contentMessageOrNull() ?.let(block)

public inline fun Message.fromUserMessageOrNull(): FromUserMessage? = this as?
    dev.inmo.tgbotapi.types.message.abstracts.FromUserMessage

public inline fun Message.fromUserMessageOrThrow(): FromUserMessage = this as
    dev.inmo.tgbotapi.types.message.abstracts.FromUserMessage

public inline fun <T> Message.ifFromUserMessage(block: (FromUserMessage) -> T): T? =
    fromUserMessageOrNull() ?.let(block)

public inline fun Message.groupEventMessageOrNull(): GroupEventMessage<GroupEvent>? = this as?
    dev.inmo.tgbotapi.types.message.abstracts.GroupEventMessage<dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.GroupEvent>

public inline fun Message.groupEventMessageOrThrow(): GroupEventMessage<GroupEvent> = this as
    dev.inmo.tgbotapi.types.message.abstracts.GroupEventMessage<dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.GroupEvent>

public inline fun <T> Message.ifGroupEventMessage(block: (GroupEventMessage<GroupEvent>) -> T): T? =
    groupEventMessageOrNull() ?.let(block)

public inline fun Message.groupContentMessageOrNull(): GroupContentMessage<MessageContent>? = this
    as?
    dev.inmo.tgbotapi.types.message.abstracts.GroupContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun Message.groupContentMessageOrThrow(): GroupContentMessage<MessageContent> = this
    as
    dev.inmo.tgbotapi.types.message.abstracts.GroupContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun <T>
    Message.ifGroupContentMessage(block: (GroupContentMessage<MessageContent>) -> T): T? =
    groupContentMessageOrNull() ?.let(block)

public inline fun Message.forumContentMessageOrNull(): ForumContentMessage<MessageContent>? = this
    as?
    dev.inmo.tgbotapi.types.message.abstracts.ForumContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun Message.forumContentMessageOrThrow(): ForumContentMessage<MessageContent> = this
    as
    dev.inmo.tgbotapi.types.message.abstracts.ForumContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun <T>
    Message.ifForumContentMessage(block: (ForumContentMessage<MessageContent>) -> T): T? =
    forumContentMessageOrNull() ?.let(block)

public inline fun Message.fromChannelGroupContentMessageOrNull():
    FromChannelGroupContentMessage<MessageContent>? = this as?
    dev.inmo.tgbotapi.types.message.abstracts.FromChannelGroupContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun Message.fromChannelGroupContentMessageOrThrow():
    FromChannelGroupContentMessage<MessageContent> = this as
    dev.inmo.tgbotapi.types.message.abstracts.FromChannelGroupContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun <T>
    Message.ifFromChannelGroupContentMessage(block: (FromChannelGroupContentMessage<MessageContent>) -> T):
    T? = fromChannelGroupContentMessageOrNull() ?.let(block)

public inline fun Message.connectedFromChannelGroupContentMessageOrNull():
    ConnectedFromChannelGroupContentMessage<MessageContent>? = this as?
    dev.inmo.tgbotapi.types.message.abstracts.ConnectedFromChannelGroupContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun Message.connectedFromChannelGroupContentMessageOrThrow():
    ConnectedFromChannelGroupContentMessage<MessageContent> = this as
    dev.inmo.tgbotapi.types.message.abstracts.ConnectedFromChannelGroupContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun <T>
    Message.ifConnectedFromChannelGroupContentMessage(block: (ConnectedFromChannelGroupContentMessage<MessageContent>) -> T):
    T? = connectedFromChannelGroupContentMessageOrNull() ?.let(block)

public inline fun Message.unconnectedFromChannelGroupContentMessageOrNull():
    UnconnectedFromChannelGroupContentMessage<MessageContent>? = this as?
    dev.inmo.tgbotapi.types.message.abstracts.UnconnectedFromChannelGroupContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun Message.unconnectedFromChannelGroupContentMessageOrThrow():
    UnconnectedFromChannelGroupContentMessage<MessageContent> = this as
    dev.inmo.tgbotapi.types.message.abstracts.UnconnectedFromChannelGroupContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun <T>
    Message.ifUnconnectedFromChannelGroupContentMessage(block: (UnconnectedFromChannelGroupContentMessage<MessageContent>) -> T):
    T? = unconnectedFromChannelGroupContentMessageOrNull() ?.let(block)

public inline fun Message.anonymousGroupContentMessageOrNull():
    AnonymousGroupContentMessage<MessageContent>? = this as?
    dev.inmo.tgbotapi.types.message.abstracts.AnonymousGroupContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun Message.anonymousGroupContentMessageOrThrow():
    AnonymousGroupContentMessage<MessageContent> = this as
    dev.inmo.tgbotapi.types.message.abstracts.AnonymousGroupContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun <T>
    Message.ifAnonymousGroupContentMessage(block: (AnonymousGroupContentMessage<MessageContent>) -> T):
    T? = anonymousGroupContentMessageOrNull() ?.let(block)

public inline fun Message.commonGroupContentMessageOrNull():
    CommonGroupContentMessage<MessageContent>? = this as?
    dev.inmo.tgbotapi.types.message.abstracts.CommonGroupContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun Message.commonGroupContentMessageOrThrow():
    CommonGroupContentMessage<MessageContent> = this as
    dev.inmo.tgbotapi.types.message.abstracts.CommonGroupContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun <T>
    Message.ifCommonGroupContentMessage(block: (CommonGroupContentMessage<MessageContent>) -> T): T?
    = commonGroupContentMessageOrNull() ?.let(block)

public inline fun Message.fromChannelForumContentMessageOrNull():
    FromChannelForumContentMessage<MessageContent>? = this as?
    dev.inmo.tgbotapi.types.message.abstracts.FromChannelForumContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun Message.fromChannelForumContentMessageOrThrow():
    FromChannelForumContentMessage<MessageContent> = this as
    dev.inmo.tgbotapi.types.message.abstracts.FromChannelForumContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun <T>
    Message.ifFromChannelForumContentMessage(block: (FromChannelForumContentMessage<MessageContent>) -> T):
    T? = fromChannelForumContentMessageOrNull() ?.let(block)

public inline fun Message.anonymousForumContentMessageOrNull():
    AnonymousForumContentMessage<MessageContent>? = this as?
    dev.inmo.tgbotapi.types.message.abstracts.AnonymousForumContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun Message.anonymousForumContentMessageOrThrow():
    AnonymousForumContentMessage<MessageContent> = this as
    dev.inmo.tgbotapi.types.message.abstracts.AnonymousForumContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun <T>
    Message.ifAnonymousForumContentMessage(block: (AnonymousForumContentMessage<MessageContent>) -> T):
    T? = anonymousForumContentMessageOrNull() ?.let(block)

public inline fun Message.commonForumContentMessageOrNull():
    CommonForumContentMessage<MessageContent>? = this as?
    dev.inmo.tgbotapi.types.message.abstracts.CommonForumContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun Message.commonForumContentMessageOrThrow():
    CommonForumContentMessage<MessageContent> = this as
    dev.inmo.tgbotapi.types.message.abstracts.CommonForumContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun <T>
    Message.ifCommonForumContentMessage(block: (CommonForumContentMessage<MessageContent>) -> T): T?
    = commonForumContentMessageOrNull() ?.let(block)

public inline fun Message.unknownMessageTypeOrNull(): UnknownMessageType? = this as?
    dev.inmo.tgbotapi.types.message.abstracts.UnknownMessageType

public inline fun Message.unknownMessageTypeOrThrow(): UnknownMessageType = this as
    dev.inmo.tgbotapi.types.message.abstracts.UnknownMessageType

public inline fun <T> Message.ifUnknownMessageType(block: (UnknownMessageType) -> T): T? =
    unknownMessageTypeOrNull() ?.let(block)

public inline fun Message.possiblyEditedMessageOrNull(): PossiblyEditedMessage? = this as?
    dev.inmo.tgbotapi.types.message.abstracts.PossiblyEditedMessage

public inline fun Message.possiblyEditedMessageOrThrow(): PossiblyEditedMessage = this as
    dev.inmo.tgbotapi.types.message.abstracts.PossiblyEditedMessage

public inline fun <T> Message.ifPossiblyEditedMessage(block: (PossiblyEditedMessage) -> T): T? =
    possiblyEditedMessageOrNull() ?.let(block)

public inline fun Message.possiblyForwardedMessageOrNull(): PossiblyForwardedMessage? = this as?
    dev.inmo.tgbotapi.types.message.abstracts.PossiblyForwardedMessage

public inline fun Message.possiblyForwardedMessageOrThrow(): PossiblyForwardedMessage = this as
    dev.inmo.tgbotapi.types.message.abstracts.PossiblyForwardedMessage

public inline fun <T> Message.ifPossiblyForwardedMessage(block: (PossiblyForwardedMessage) -> T): T?
    = possiblyForwardedMessageOrNull() ?.let(block)

public inline fun Message.possiblyMediaGroupMessageOrNull():
    PossiblyMediaGroupMessage<MessageContent>? = this as?
    dev.inmo.tgbotapi.types.message.abstracts.PossiblyMediaGroupMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun Message.possiblyMediaGroupMessageOrThrow():
    PossiblyMediaGroupMessage<MessageContent> = this as
    dev.inmo.tgbotapi.types.message.abstracts.PossiblyMediaGroupMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun <T>
    Message.ifPossiblyMediaGroupMessage(block: (PossiblyMediaGroupMessage<MessageContent>) -> T): T?
    = possiblyMediaGroupMessageOrNull() ?.let(block)

public inline fun Message.possiblyPaymentMessageOrNull(): PossiblyPaymentMessage? = this as?
    dev.inmo.tgbotapi.types.message.abstracts.PossiblyPaymentMessage

public inline fun Message.possiblyPaymentMessageOrThrow(): PossiblyPaymentMessage = this as
    dev.inmo.tgbotapi.types.message.abstracts.PossiblyPaymentMessage

public inline fun <T> Message.ifPossiblyPaymentMessage(block: (PossiblyPaymentMessage) -> T): T? =
    possiblyPaymentMessageOrNull() ?.let(block)

public inline fun Message.possiblySentViaBotCommonMessageOrNull():
    PossiblySentViaBotCommonMessage<MessageContent>? = this as?
    dev.inmo.tgbotapi.types.message.abstracts.PossiblySentViaBotCommonMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun Message.possiblySentViaBotCommonMessageOrThrow():
    PossiblySentViaBotCommonMessage<MessageContent> = this as
    dev.inmo.tgbotapi.types.message.abstracts.PossiblySentViaBotCommonMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun <T>
    Message.ifPossiblySentViaBotCommonMessage(block: (PossiblySentViaBotCommonMessage<MessageContent>) -> T):
    T? = possiblySentViaBotCommonMessageOrNull() ?.let(block)

public inline fun Message.possiblyTopicMessageOrNull(): PossiblyTopicMessage? = this as?
    dev.inmo.tgbotapi.types.message.abstracts.PossiblyTopicMessage

public inline fun Message.possiblyTopicMessageOrThrow(): PossiblyTopicMessage = this as
    dev.inmo.tgbotapi.types.message.abstracts.PossiblyTopicMessage

public inline fun <T> Message.ifPossiblyTopicMessage(block: (PossiblyTopicMessage) -> T): T? =
    possiblyTopicMessageOrNull() ?.let(block)

public inline fun Message.privateContentMessageOrNull(): PrivateContentMessage<MessageContent>? =
    this as?
    dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun Message.privateContentMessageOrThrow(): PrivateContentMessage<MessageContent> =
    this as
    dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun <T>
    Message.ifPrivateContentMessage(block: (PrivateContentMessage<MessageContent>) -> T): T? =
    privateContentMessageOrNull() ?.let(block)

public inline fun Message.publicContentMessageOrNull(): PublicContentMessage<MessageContent>? = this
    as?
    dev.inmo.tgbotapi.types.message.abstracts.PublicContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun Message.publicContentMessageOrThrow(): PublicContentMessage<MessageContent> = this
    as
    dev.inmo.tgbotapi.types.message.abstracts.PublicContentMessage<dev.inmo.tgbotapi.types.message.content.MessageContent>

public inline fun <T>
    Message.ifPublicContentMessage(block: (PublicContentMessage<MessageContent>) -> T): T? =
    publicContentMessageOrNull() ?.let(block)

public inline fun Message.signedMessageOrNull(): SignedMessage? = this as?
    dev.inmo.tgbotapi.types.message.abstracts.SignedMessage

public inline fun Message.signedMessageOrThrow(): SignedMessage = this as
    dev.inmo.tgbotapi.types.message.abstracts.SignedMessage

public inline fun <T> Message.ifSignedMessage(block: (SignedMessage) -> T): T? =
    signedMessageOrNull() ?.let(block)

public inline fun Message.supergroupEventMessageOrNull(): SupergroupEventMessage<SupergroupEvent>? =
    this as?
    dev.inmo.tgbotapi.types.message.abstracts.SupergroupEventMessage<dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.SupergroupEvent>

public inline fun Message.supergroupEventMessageOrThrow(): SupergroupEventMessage<SupergroupEvent> =
    this as
    dev.inmo.tgbotapi.types.message.abstracts.SupergroupEventMessage<dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.SupergroupEvent>

public inline fun <T>
    Message.ifSupergroupEventMessage(block: (SupergroupEventMessage<SupergroupEvent>) -> T): T? =
    supergroupEventMessageOrNull() ?.let(block)

public inline fun ResendableContent.messageContentOrNull(): MessageContent? = this as?
    dev.inmo.tgbotapi.types.message.content.MessageContent

public inline fun ResendableContent.messageContentOrThrow(): MessageContent = this as
    dev.inmo.tgbotapi.types.message.content.MessageContent

public inline fun <T> ResendableContent.ifMessageContent(block: (MessageContent) -> T): T? =
    messageContentOrNull() ?.let(block)

public inline fun ResendableContent.mediaCollectionContentOrNull():
    MediaCollectionContent<TelegramMediaFile>? = this as?
    dev.inmo.tgbotapi.types.message.content.MediaCollectionContent<dev.inmo.tgbotapi.types.files.TelegramMediaFile>

public inline fun ResendableContent.mediaCollectionContentOrThrow():
    MediaCollectionContent<TelegramMediaFile> = this as
    dev.inmo.tgbotapi.types.message.content.MediaCollectionContent<dev.inmo.tgbotapi.types.files.TelegramMediaFile>

public inline fun <T>
    ResendableContent.ifMediaCollectionContent(block: (MediaCollectionContent<TelegramMediaFile>) -> T):
    T? = mediaCollectionContentOrNull() ?.let(block)

public inline fun ResendableContent.textedContentOrNull(): TextedContent? = this as?
    dev.inmo.tgbotapi.types.message.content.TextedContent

public inline fun ResendableContent.textedContentOrThrow(): TextedContent = this as
    dev.inmo.tgbotapi.types.message.content.TextedContent

public inline fun <T> ResendableContent.ifTextedContent(block: (TextedContent) -> T): T? =
    textedContentOrNull() ?.let(block)

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

public inline fun ResendableContent.audioMediaGroupPartContentOrNull(): AudioMediaGroupPartContent?
    = this as? dev.inmo.tgbotapi.types.message.content.AudioMediaGroupPartContent

public inline fun ResendableContent.audioMediaGroupPartContentOrThrow(): AudioMediaGroupPartContent
    = this as dev.inmo.tgbotapi.types.message.content.AudioMediaGroupPartContent

public inline fun <T>
    ResendableContent.ifAudioMediaGroupPartContent(block: (AudioMediaGroupPartContent) -> T): T? =
    audioMediaGroupPartContentOrNull() ?.let(block)

public inline fun ResendableContent.documentMediaGroupPartContentOrNull():
    DocumentMediaGroupPartContent? = this as?
    dev.inmo.tgbotapi.types.message.content.DocumentMediaGroupPartContent

public inline fun ResendableContent.documentMediaGroupPartContentOrThrow():
    DocumentMediaGroupPartContent = this as
    dev.inmo.tgbotapi.types.message.content.DocumentMediaGroupPartContent

public inline fun <T>
    ResendableContent.ifDocumentMediaGroupPartContent(block: (DocumentMediaGroupPartContent) -> T):
    T? = documentMediaGroupPartContentOrNull() ?.let(block)

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

public inline fun ResendableContent.mediaGroupPartContentOrNull(): MediaGroupPartContent? = this as?
    dev.inmo.tgbotapi.types.message.content.MediaGroupPartContent

public inline fun ResendableContent.mediaGroupPartContentOrThrow(): MediaGroupPartContent = this as
    dev.inmo.tgbotapi.types.message.content.MediaGroupPartContent

public inline fun <T>
    ResendableContent.ifMediaGroupPartContent(block: (MediaGroupPartContent) -> T): T? =
    mediaGroupPartContentOrNull() ?.let(block)

public inline fun ResendableContent.visualMediaGroupPartContentOrNull():
    VisualMediaGroupPartContent? = this as?
    dev.inmo.tgbotapi.types.message.content.VisualMediaGroupPartContent

public inline fun ResendableContent.visualMediaGroupPartContentOrThrow():
    VisualMediaGroupPartContent = this as
    dev.inmo.tgbotapi.types.message.content.VisualMediaGroupPartContent

public inline fun <T>
    ResendableContent.ifVisualMediaGroupPartContent(block: (VisualMediaGroupPartContent) -> T): T? =
    visualMediaGroupPartContentOrNull() ?.let(block)

public inline fun ResendableContent.animationContentOrNull(): AnimationContent? = this as?
    dev.inmo.tgbotapi.types.message.content.AnimationContent

public inline fun ResendableContent.animationContentOrThrow(): AnimationContent = this as
    dev.inmo.tgbotapi.types.message.content.AnimationContent

public inline fun <T> ResendableContent.ifAnimationContent(block: (AnimationContent) -> T): T? =
    animationContentOrNull() ?.let(block)

public inline fun ResendableContent.audioContentOrNull(): AudioContent? = this as?
    dev.inmo.tgbotapi.types.message.content.AudioContent

public inline fun ResendableContent.audioContentOrThrow(): AudioContent = this as
    dev.inmo.tgbotapi.types.message.content.AudioContent

public inline fun <T> ResendableContent.ifAudioContent(block: (AudioContent) -> T): T? =
    audioContentOrNull() ?.let(block)

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

public inline fun ResendableContent.documentContentOrNull(): DocumentContent? = this as?
    dev.inmo.tgbotapi.types.message.content.DocumentContent

public inline fun ResendableContent.documentContentOrThrow(): DocumentContent = this as
    dev.inmo.tgbotapi.types.message.content.DocumentContent

public inline fun <T> ResendableContent.ifDocumentContent(block: (DocumentContent) -> T): T? =
    documentContentOrNull() ?.let(block)

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

public inline fun ResendableContent.mediaGroupContentOrNull():
    MediaGroupContent<MediaGroupPartContent>? = this as?
    dev.inmo.tgbotapi.types.message.content.MediaGroupContent<dev.inmo.tgbotapi.types.message.content.MediaGroupPartContent>

public inline fun ResendableContent.mediaGroupContentOrThrow():
    MediaGroupContent<MediaGroupPartContent> = this as
    dev.inmo.tgbotapi.types.message.content.MediaGroupContent<dev.inmo.tgbotapi.types.message.content.MediaGroupPartContent>

public inline fun <T>
    ResendableContent.ifMediaGroupContent(block: (MediaGroupContent<MediaGroupPartContent>) -> T):
    T? = mediaGroupContentOrNull() ?.let(block)

public inline fun ResendableContent.photoContentOrNull(): PhotoContent? = this as?
    dev.inmo.tgbotapi.types.message.content.PhotoContent

public inline fun ResendableContent.photoContentOrThrow(): PhotoContent = this as
    dev.inmo.tgbotapi.types.message.content.PhotoContent

public inline fun <T> ResendableContent.ifPhotoContent(block: (PhotoContent) -> T): T? =
    photoContentOrNull() ?.let(block)

public inline fun ResendableContent.pollContentOrNull(): PollContent? = this as?
    dev.inmo.tgbotapi.types.message.content.PollContent

public inline fun ResendableContent.pollContentOrThrow(): PollContent = this as
    dev.inmo.tgbotapi.types.message.content.PollContent

public inline fun <T> ResendableContent.ifPollContent(block: (PollContent) -> T): T? =
    pollContentOrNull() ?.let(block)

public inline fun ResendableContent.stickerContentOrNull(): StickerContent? = this as?
    dev.inmo.tgbotapi.types.message.content.StickerContent

public inline fun ResendableContent.stickerContentOrThrow(): StickerContent = this as
    dev.inmo.tgbotapi.types.message.content.StickerContent

public inline fun <T> ResendableContent.ifStickerContent(block: (StickerContent) -> T): T? =
    stickerContentOrNull() ?.let(block)

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

public inline fun ResendableContent.videoContentOrNull(): VideoContent? = this as?
    dev.inmo.tgbotapi.types.message.content.VideoContent

public inline fun ResendableContent.videoContentOrThrow(): VideoContent = this as
    dev.inmo.tgbotapi.types.message.content.VideoContent

public inline fun <T> ResendableContent.ifVideoContent(block: (VideoContent) -> T): T? =
    videoContentOrNull() ?.let(block)

public inline fun ResendableContent.videoNoteContentOrNull(): VideoNoteContent? = this as?
    dev.inmo.tgbotapi.types.message.content.VideoNoteContent

public inline fun ResendableContent.videoNoteContentOrThrow(): VideoNoteContent = this as
    dev.inmo.tgbotapi.types.message.content.VideoNoteContent

public inline fun <T> ResendableContent.ifVideoNoteContent(block: (VideoNoteContent) -> T): T? =
    videoNoteContentOrNull() ?.let(block)

public inline fun ResendableContent.voiceContentOrNull(): VoiceContent? = this as?
    dev.inmo.tgbotapi.types.message.content.VoiceContent

public inline fun ResendableContent.voiceContentOrThrow(): VoiceContent = this as
    dev.inmo.tgbotapi.types.message.content.VoiceContent

public inline fun <T> ResendableContent.ifVoiceContent(block: (VoiceContent) -> T): T? =
    voiceContentOrNull() ?.let(block)

public inline fun TextSource.boldTextSourceOrNull(): BoldTextSource? = this as?
    dev.inmo.tgbotapi.types.message.textsources.BoldTextSource

public inline fun TextSource.boldTextSourceOrThrow(): BoldTextSource = this as
    dev.inmo.tgbotapi.types.message.textsources.BoldTextSource

public inline fun <T> TextSource.ifBoldTextSource(block: (BoldTextSource) -> T): T? =
    boldTextSourceOrNull() ?.let(block)

public inline fun TextSource.botCommandTextSourceOrNull(): BotCommandTextSource? = this as?
    dev.inmo.tgbotapi.types.message.textsources.BotCommandTextSource

public inline fun TextSource.botCommandTextSourceOrThrow(): BotCommandTextSource = this as
    dev.inmo.tgbotapi.types.message.textsources.BotCommandTextSource

public inline fun <T> TextSource.ifBotCommandTextSource(block: (BotCommandTextSource) -> T): T? =
    botCommandTextSourceOrNull() ?.let(block)

public inline fun TextSource.cashTagTextSourceOrNull(): CashTagTextSource? = this as?
    dev.inmo.tgbotapi.types.message.textsources.CashTagTextSource

public inline fun TextSource.cashTagTextSourceOrThrow(): CashTagTextSource = this as
    dev.inmo.tgbotapi.types.message.textsources.CashTagTextSource

public inline fun <T> TextSource.ifCashTagTextSource(block: (CashTagTextSource) -> T): T? =
    cashTagTextSourceOrNull() ?.let(block)

public inline fun TextSource.codeTextSourceOrNull(): CodeTextSource? = this as?
    dev.inmo.tgbotapi.types.message.textsources.CodeTextSource

public inline fun TextSource.codeTextSourceOrThrow(): CodeTextSource = this as
    dev.inmo.tgbotapi.types.message.textsources.CodeTextSource

public inline fun <T> TextSource.ifCodeTextSource(block: (CodeTextSource) -> T): T? =
    codeTextSourceOrNull() ?.let(block)

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

public inline fun TextSource.textLinkTextSourceOrNull(): TextLinkTextSource? = this as?
    dev.inmo.tgbotapi.types.message.textsources.TextLinkTextSource

public inline fun TextSource.textLinkTextSourceOrThrow(): TextLinkTextSource = this as
    dev.inmo.tgbotapi.types.message.textsources.TextLinkTextSource

public inline fun <T> TextSource.ifTextLinkTextSource(block: (TextLinkTextSource) -> T): T? =
    textLinkTextSourceOrNull() ?.let(block)

public inline fun TextSource.textMentionTextSourceOrNull(): TextMentionTextSource? = this as?
    dev.inmo.tgbotapi.types.message.textsources.TextMentionTextSource

public inline fun TextSource.textMentionTextSourceOrThrow(): TextMentionTextSource = this as
    dev.inmo.tgbotapi.types.message.textsources.TextMentionTextSource

public inline fun <T> TextSource.ifTextMentionTextSource(block: (TextMentionTextSource) -> T): T? =
    textMentionTextSourceOrNull() ?.let(block)

public inline fun TextSource.multilevelTextSourceOrNull(): MultilevelTextSource? = this as?
    dev.inmo.tgbotapi.types.message.textsources.MultilevelTextSource

public inline fun TextSource.multilevelTextSourceOrThrow(): MultilevelTextSource = this as
    dev.inmo.tgbotapi.types.message.textsources.MultilevelTextSource

public inline fun <T> TextSource.ifMultilevelTextSource(block: (MultilevelTextSource) -> T): T? =
    multilevelTextSourceOrNull() ?.let(block)

public inline fun TextSource.uRLTextSourceOrNull(): URLTextSource? = this as?
    dev.inmo.tgbotapi.types.message.textsources.URLTextSource

public inline fun TextSource.uRLTextSourceOrThrow(): URLTextSource = this as
    dev.inmo.tgbotapi.types.message.textsources.URLTextSource

public inline fun <T> TextSource.ifURLTextSource(block: (URLTextSource) -> T): T? =
    uRLTextSourceOrNull() ?.let(block)

public inline fun TextSource.underlineTextSourceOrNull(): UnderlineTextSource? = this as?
    dev.inmo.tgbotapi.types.message.textsources.UnderlineTextSource

public inline fun TextSource.underlineTextSourceOrThrow(): UnderlineTextSource = this as
    dev.inmo.tgbotapi.types.message.textsources.UnderlineTextSource

public inline fun <T> TextSource.ifUnderlineTextSource(block: (UnderlineTextSource) -> T): T? =
    underlineTextSourceOrNull() ?.let(block)

public inline fun PassportElementError.unknownPassportElementErrorOrNull():
    UnknownPassportElementError? = this as?
    dev.inmo.tgbotapi.types.passport.UnknownPassportElementError

public inline fun PassportElementError.unknownPassportElementErrorOrThrow():
    UnknownPassportElementError = this as
    dev.inmo.tgbotapi.types.passport.UnknownPassportElementError

public inline fun <T>
    PassportElementError.ifUnknownPassportElementError(block: (UnknownPassportElementError) -> T):
    T? = unknownPassportElementErrorOrNull() ?.let(block)

public inline fun PassportElementError.passportSingleElementErrorOrNull():
    PassportSingleElementError? = this as?
    dev.inmo.tgbotapi.types.passport.PassportSingleElementError

public inline fun PassportElementError.passportSingleElementErrorOrThrow():
    PassportSingleElementError = this as dev.inmo.tgbotapi.types.passport.PassportSingleElementError

public inline fun <T>
    PassportElementError.ifPassportSingleElementError(block: (PassportSingleElementError) -> T): T?
    = passportSingleElementErrorOrNull() ?.let(block)

public inline fun PassportElementError.passportMultipleElementsErrorOrNull():
    PassportMultipleElementsError? = this as?
    dev.inmo.tgbotapi.types.passport.PassportMultipleElementsError

public inline fun PassportElementError.passportMultipleElementsErrorOrThrow():
    PassportMultipleElementsError = this as
    dev.inmo.tgbotapi.types.passport.PassportMultipleElementsError

public inline fun <T>
    PassportElementError.ifPassportMultipleElementsError(block: (PassportMultipleElementsError) -> T):
    T? = passportMultipleElementsErrorOrNull() ?.let(block)

public inline fun PassportElementError.passportElementFileErrorOrNull(): PassportElementFileError? =
    this as? dev.inmo.tgbotapi.types.passport.PassportElementFileError

public inline fun PassportElementError.passportElementFileErrorOrThrow(): PassportElementFileError =
    this as dev.inmo.tgbotapi.types.passport.PassportElementFileError

public inline fun <T>
    PassportElementError.ifPassportElementFileError(block: (PassportElementFileError) -> T): T? =
    passportElementFileErrorOrNull() ?.let(block)

public inline fun PassportElementError.passportElementFilesErrorOrNull(): PassportElementFilesError?
    = this as? dev.inmo.tgbotapi.types.passport.PassportElementFilesError

public inline fun PassportElementError.passportElementFilesErrorOrThrow(): PassportElementFilesError
    = this as dev.inmo.tgbotapi.types.passport.PassportElementFilesError

public inline fun <T>
    PassportElementError.ifPassportElementFilesError(block: (PassportElementFilesError) -> T): T? =
    passportElementFilesErrorOrNull() ?.let(block)

public inline fun PassportElementError.passportElementErrorDataFieldOrNull():
    PassportElementErrorDataField? = this as?
    dev.inmo.tgbotapi.types.passport.PassportElementErrorDataField

public inline fun PassportElementError.passportElementErrorDataFieldOrThrow():
    PassportElementErrorDataField = this as
    dev.inmo.tgbotapi.types.passport.PassportElementErrorDataField

public inline fun <T>
    PassportElementError.ifPassportElementErrorDataField(block: (PassportElementErrorDataField) -> T):
    T? = passportElementErrorDataFieldOrNull() ?.let(block)

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

public inline fun PassportElementError.passportElementErrorFileOrNull(): PassportElementErrorFile? =
    this as? dev.inmo.tgbotapi.types.passport.PassportElementErrorFile

public inline fun PassportElementError.passportElementErrorFileOrThrow(): PassportElementErrorFile =
    this as dev.inmo.tgbotapi.types.passport.PassportElementErrorFile

public inline fun <T>
    PassportElementError.ifPassportElementErrorFile(block: (PassportElementErrorFile) -> T): T? =
    passportElementErrorFileOrNull() ?.let(block)

public inline fun PassportElementError.passportElementErrorFilesOrNull(): PassportElementErrorFiles?
    = this as? dev.inmo.tgbotapi.types.passport.PassportElementErrorFiles

public inline fun PassportElementError.passportElementErrorFilesOrThrow(): PassportElementErrorFiles
    = this as dev.inmo.tgbotapi.types.passport.PassportElementErrorFiles

public inline fun <T>
    PassportElementError.ifPassportElementErrorFiles(block: (PassportElementErrorFiles) -> T): T? =
    passportElementErrorFilesOrNull() ?.let(block)

public inline fun PassportElementError.passportElementErrorTranslationFileOrNull():
    PassportElementErrorTranslationFile? = this as?
    dev.inmo.tgbotapi.types.passport.PassportElementErrorTranslationFile

public inline fun PassportElementError.passportElementErrorTranslationFileOrThrow():
    PassportElementErrorTranslationFile = this as
    dev.inmo.tgbotapi.types.passport.PassportElementErrorTranslationFile

public inline fun <T>
    PassportElementError.ifPassportElementErrorTranslationFile(block: (PassportElementErrorTranslationFile) -> T):
    T? = passportElementErrorTranslationFileOrNull() ?.let(block)

public inline fun PassportElementError.passportElementErrorTranslationFilesOrNull():
    PassportElementErrorTranslationFiles? = this as?
    dev.inmo.tgbotapi.types.passport.PassportElementErrorTranslationFiles

public inline fun PassportElementError.passportElementErrorTranslationFilesOrThrow():
    PassportElementErrorTranslationFiles = this as
    dev.inmo.tgbotapi.types.passport.PassportElementErrorTranslationFiles

public inline fun <T>
    PassportElementError.ifPassportElementErrorTranslationFiles(block: (PassportElementErrorTranslationFiles) -> T):
    T? = passportElementErrorTranslationFilesOrNull() ?.let(block)

public inline fun PassportElementError.passportElementErrorUnspecifiedOrNull():
    PassportElementErrorUnspecified? = this as?
    dev.inmo.tgbotapi.types.passport.PassportElementErrorUnspecified

public inline fun PassportElementError.passportElementErrorUnspecifiedOrThrow():
    PassportElementErrorUnspecified = this as
    dev.inmo.tgbotapi.types.passport.PassportElementErrorUnspecified

public inline fun <T>
    PassportElementError.ifPassportElementErrorUnspecified(block: (PassportElementErrorUnspecified) -> T):
    T? = passportElementErrorUnspecifiedOrNull() ?.let(block)

public inline fun SecureValue.addressSecureValueOrNull(): AddressSecureValue? = this as?
    dev.inmo.tgbotapi.types.passport.decrypted.AddressSecureValue

public inline fun SecureValue.addressSecureValueOrThrow(): AddressSecureValue = this as
    dev.inmo.tgbotapi.types.passport.decrypted.AddressSecureValue

public inline fun <T> SecureValue.ifAddressSecureValue(block: (AddressSecureValue) -> T): T? =
    addressSecureValueOrNull() ?.let(block)

public inline fun SecureValue.identityWithReverseSideSecureValueOrNull():
    IdentityWithReverseSideSecureValue? = this as?
    dev.inmo.tgbotapi.types.passport.decrypted.IdentityWithReverseSideSecureValue

public inline fun SecureValue.identityWithReverseSideSecureValueOrThrow():
    IdentityWithReverseSideSecureValue = this as
    dev.inmo.tgbotapi.types.passport.decrypted.IdentityWithReverseSideSecureValue

public inline fun <T>
    SecureValue.ifIdentityWithReverseSideSecureValue(block: (IdentityWithReverseSideSecureValue) -> T):
    T? = identityWithReverseSideSecureValueOrNull() ?.let(block)

public inline fun SecureValue.driverLicenseSecureValueOrNull(): DriverLicenseSecureValue? = this as?
    dev.inmo.tgbotapi.types.passport.decrypted.DriverLicenseSecureValue

public inline fun SecureValue.driverLicenseSecureValueOrThrow(): DriverLicenseSecureValue = this as
    dev.inmo.tgbotapi.types.passport.decrypted.DriverLicenseSecureValue

public inline fun <T>
    SecureValue.ifDriverLicenseSecureValue(block: (DriverLicenseSecureValue) -> T): T? =
    driverLicenseSecureValueOrNull() ?.let(block)

public inline fun SecureValue.identityCardSecureValueOrNull(): IdentityCardSecureValue? = this as?
    dev.inmo.tgbotapi.types.passport.decrypted.IdentityCardSecureValue

public inline fun SecureValue.identityCardSecureValueOrThrow(): IdentityCardSecureValue = this as
    dev.inmo.tgbotapi.types.passport.decrypted.IdentityCardSecureValue

public inline fun <T> SecureValue.ifIdentityCardSecureValue(block: (IdentityCardSecureValue) -> T):
    T? = identityCardSecureValueOrNull() ?.let(block)

public inline fun SecureValue.otherDocumentsSecureValueOrNull(): OtherDocumentsSecureValue? = this
    as? dev.inmo.tgbotapi.types.passport.decrypted.OtherDocumentsSecureValue

public inline fun SecureValue.otherDocumentsSecureValueOrThrow(): OtherDocumentsSecureValue = this
    as dev.inmo.tgbotapi.types.passport.decrypted.OtherDocumentsSecureValue

public inline fun <T>
    SecureValue.ifOtherDocumentsSecureValue(block: (OtherDocumentsSecureValue) -> T): T? =
    otherDocumentsSecureValueOrNull() ?.let(block)

public inline fun SecureValue.utilityBillSecureValueOrNull(): UtilityBillSecureValue? = this as?
    dev.inmo.tgbotapi.types.passport.decrypted.UtilityBillSecureValue

public inline fun SecureValue.utilityBillSecureValueOrThrow(): UtilityBillSecureValue = this as
    dev.inmo.tgbotapi.types.passport.decrypted.UtilityBillSecureValue

public inline fun <T> SecureValue.ifUtilityBillSecureValue(block: (UtilityBillSecureValue) -> T): T?
    = utilityBillSecureValueOrNull() ?.let(block)

public inline fun SecureValue.bankStatementSecureValueOrNull(): BankStatementSecureValue? = this as?
    dev.inmo.tgbotapi.types.passport.decrypted.BankStatementSecureValue

public inline fun SecureValue.bankStatementSecureValueOrThrow(): BankStatementSecureValue = this as
    dev.inmo.tgbotapi.types.passport.decrypted.BankStatementSecureValue

public inline fun <T>
    SecureValue.ifBankStatementSecureValue(block: (BankStatementSecureValue) -> T): T? =
    bankStatementSecureValueOrNull() ?.let(block)

public inline fun SecureValue.rentalAgreementSecureValueOrNull(): RentalAgreementSecureValue? = this
    as? dev.inmo.tgbotapi.types.passport.decrypted.RentalAgreementSecureValue

public inline fun SecureValue.rentalAgreementSecureValueOrThrow(): RentalAgreementSecureValue = this
    as dev.inmo.tgbotapi.types.passport.decrypted.RentalAgreementSecureValue

public inline fun <T>
    SecureValue.ifRentalAgreementSecureValue(block: (RentalAgreementSecureValue) -> T): T? =
    rentalAgreementSecureValueOrNull() ?.let(block)

public inline fun SecureValue.passportRegistrationSecureValueOrNull():
    PassportRegistrationSecureValue? = this as?
    dev.inmo.tgbotapi.types.passport.decrypted.PassportRegistrationSecureValue

public inline fun SecureValue.passportRegistrationSecureValueOrThrow():
    PassportRegistrationSecureValue = this as
    dev.inmo.tgbotapi.types.passport.decrypted.PassportRegistrationSecureValue

public inline fun <T>
    SecureValue.ifPassportRegistrationSecureValue(block: (PassportRegistrationSecureValue) -> T): T?
    = passportRegistrationSecureValueOrNull() ?.let(block)

public inline fun SecureValue.temporalRegistrationSecureValueOrNull():
    TemporalRegistrationSecureValue? = this as?
    dev.inmo.tgbotapi.types.passport.decrypted.TemporalRegistrationSecureValue

public inline fun SecureValue.temporalRegistrationSecureValueOrThrow():
    TemporalRegistrationSecureValue = this as
    dev.inmo.tgbotapi.types.passport.decrypted.TemporalRegistrationSecureValue

public inline fun <T>
    SecureValue.ifTemporalRegistrationSecureValue(block: (TemporalRegistrationSecureValue) -> T): T?
    = temporalRegistrationSecureValueOrNull() ?.let(block)

public inline fun SecureValue.passportSecureValueOrNull(): PassportSecureValue? = this as?
    dev.inmo.tgbotapi.types.passport.decrypted.PassportSecureValue

public inline fun SecureValue.passportSecureValueOrThrow(): PassportSecureValue = this as
    dev.inmo.tgbotapi.types.passport.decrypted.PassportSecureValue

public inline fun <T> SecureValue.ifPassportSecureValue(block: (PassportSecureValue) -> T): T? =
    passportSecureValueOrNull() ?.let(block)

public inline fun SecureValue.commonPassportSecureValueOrNull(): CommonPassportSecureValue? = this
    as? dev.inmo.tgbotapi.types.passport.decrypted.CommonPassportSecureValue

public inline fun SecureValue.commonPassportSecureValueOrThrow(): CommonPassportSecureValue = this
    as dev.inmo.tgbotapi.types.passport.decrypted.CommonPassportSecureValue

public inline fun <T>
    SecureValue.ifCommonPassportSecureValue(block: (CommonPassportSecureValue) -> T): T? =
    commonPassportSecureValueOrNull() ?.let(block)

public inline fun SecureValue.internalPassportSecureValueOrNull(): InternalPassportSecureValue? =
    this as? dev.inmo.tgbotapi.types.passport.decrypted.InternalPassportSecureValue

public inline fun SecureValue.internalPassportSecureValueOrThrow(): InternalPassportSecureValue =
    this as dev.inmo.tgbotapi.types.passport.decrypted.InternalPassportSecureValue

public inline fun <T>
    SecureValue.ifInternalPassportSecureValue(block: (InternalPassportSecureValue) -> T): T? =
    internalPassportSecureValueOrNull() ?.let(block)

public inline fun SecureValue.personalDetailsSecureValueOrNull(): PersonalDetailsSecureValue? = this
    as? dev.inmo.tgbotapi.types.passport.decrypted.PersonalDetailsSecureValue

public inline fun SecureValue.personalDetailsSecureValueOrThrow(): PersonalDetailsSecureValue = this
    as dev.inmo.tgbotapi.types.passport.decrypted.PersonalDetailsSecureValue

public inline fun <T>
    SecureValue.ifPersonalDetailsSecureValue(block: (PersonalDetailsSecureValue) -> T): T? =
    personalDetailsSecureValueOrNull() ?.let(block)

public inline fun SecureValue.secureValueIdentityOrNull(): SecureValueIdentity? = this as?
    dev.inmo.tgbotapi.types.passport.decrypted.abstracts.SecureValueIdentity

public inline fun SecureValue.secureValueIdentityOrThrow(): SecureValueIdentity = this as
    dev.inmo.tgbotapi.types.passport.decrypted.abstracts.SecureValueIdentity

public inline fun <T> SecureValue.ifSecureValueIdentity(block: (SecureValueIdentity) -> T): T? =
    secureValueIdentityOrNull() ?.let(block)

public inline fun SecureValue.secureValueWithDataOrNull(): SecureValueWithData? = this as?
    dev.inmo.tgbotapi.types.passport.decrypted.abstracts.SecureValueWithData

public inline fun SecureValue.secureValueWithDataOrThrow(): SecureValueWithData = this as
    dev.inmo.tgbotapi.types.passport.decrypted.abstracts.SecureValueWithData

public inline fun <T> SecureValue.ifSecureValueWithData(block: (SecureValueWithData) -> T): T? =
    secureValueWithDataOrNull() ?.let(block)

public inline fun SecureValue.secureValueWithFilesOrNull(): SecureValueWithFiles? = this as?
    dev.inmo.tgbotapi.types.passport.decrypted.abstracts.SecureValueWithFiles

public inline fun SecureValue.secureValueWithFilesOrThrow(): SecureValueWithFiles = this as
    dev.inmo.tgbotapi.types.passport.decrypted.abstracts.SecureValueWithFiles

public inline fun <T> SecureValue.ifSecureValueWithFiles(block: (SecureValueWithFiles) -> T): T? =
    secureValueWithFilesOrNull() ?.let(block)

public inline fun SecureValue.secureValueWithReverseSideOrNull(): SecureValueWithReverseSide? = this
    as? dev.inmo.tgbotapi.types.passport.decrypted.abstracts.SecureValueWithReverseSide

public inline fun SecureValue.secureValueWithReverseSideOrThrow(): SecureValueWithReverseSide = this
    as dev.inmo.tgbotapi.types.passport.decrypted.abstracts.SecureValueWithReverseSide

public inline fun <T>
    SecureValue.ifSecureValueWithReverseSide(block: (SecureValueWithReverseSide) -> T): T? =
    secureValueWithReverseSideOrNull() ?.let(block)

public inline fun SecureValue.secureValueWithTranslationsOrNull(): SecureValueWithTranslations? =
    this as? dev.inmo.tgbotapi.types.passport.decrypted.abstracts.SecureValueWithTranslations

public inline fun SecureValue.secureValueWithTranslationsOrThrow(): SecureValueWithTranslations =
    this as dev.inmo.tgbotapi.types.passport.decrypted.abstracts.SecureValueWithTranslations

public inline fun <T>
    SecureValue.ifSecureValueWithTranslations(block: (SecureValueWithTranslations) -> T): T? =
    secureValueWithTranslationsOrNull() ?.let(block)

public inline fun EncryptedPassportElement.emailOrNull(): Email? = this as?
    dev.inmo.tgbotapi.types.passport.encrypted.Email

public inline fun EncryptedPassportElement.emailOrThrow(): Email = this as
    dev.inmo.tgbotapi.types.passport.encrypted.Email

public inline fun <T> EncryptedPassportElement.ifEmail(block: (Email) -> T): T? = emailOrNull()
    ?.let(block)

public inline fun EncryptedPassportElement.encryptedAddressOrNull(): EncryptedAddress? = this as?
    dev.inmo.tgbotapi.types.passport.encrypted.EncryptedAddress

public inline fun EncryptedPassportElement.encryptedAddressOrThrow(): EncryptedAddress = this as
    dev.inmo.tgbotapi.types.passport.encrypted.EncryptedAddress

public inline fun <T> EncryptedPassportElement.ifEncryptedAddress(block: (EncryptedAddress) -> T):
    T? = encryptedAddressOrNull() ?.let(block)

public inline
    fun EncryptedPassportElement.encryptedPassportElementWithTranslatableFilesCollectionOrNull():
    EncryptedPassportElementWithTranslatableFilesCollection? = this as?
    dev.inmo.tgbotapi.types.passport.encrypted.EncryptedPassportElementWithTranslatableFilesCollection

public inline
    fun EncryptedPassportElement.encryptedPassportElementWithTranslatableFilesCollectionOrThrow():
    EncryptedPassportElementWithTranslatableFilesCollection = this as
    dev.inmo.tgbotapi.types.passport.encrypted.EncryptedPassportElementWithTranslatableFilesCollection

public inline fun <T>
    EncryptedPassportElement.ifEncryptedPassportElementWithTranslatableFilesCollection(block: (EncryptedPassportElementWithTranslatableFilesCollection) -> T):
    T? = encryptedPassportElementWithTranslatableFilesCollectionOrNull() ?.let(block)

public inline fun EncryptedPassportElement.utilityBillOrNull(): UtilityBill? = this as?
    dev.inmo.tgbotapi.types.passport.encrypted.UtilityBill

public inline fun EncryptedPassportElement.utilityBillOrThrow(): UtilityBill = this as
    dev.inmo.tgbotapi.types.passport.encrypted.UtilityBill

public inline fun <T> EncryptedPassportElement.ifUtilityBill(block: (UtilityBill) -> T): T? =
    utilityBillOrNull() ?.let(block)

public inline fun EncryptedPassportElement.bankStatementOrNull(): BankStatement? = this as?
    dev.inmo.tgbotapi.types.passport.encrypted.BankStatement

public inline fun EncryptedPassportElement.bankStatementOrThrow(): BankStatement = this as
    dev.inmo.tgbotapi.types.passport.encrypted.BankStatement

public inline fun <T> EncryptedPassportElement.ifBankStatement(block: (BankStatement) -> T): T? =
    bankStatementOrNull() ?.let(block)

public inline fun EncryptedPassportElement.rentalAgreementOrNull(): RentalAgreement? = this as?
    dev.inmo.tgbotapi.types.passport.encrypted.RentalAgreement

public inline fun EncryptedPassportElement.rentalAgreementOrThrow(): RentalAgreement = this as
    dev.inmo.tgbotapi.types.passport.encrypted.RentalAgreement

public inline fun <T> EncryptedPassportElement.ifRentalAgreement(block: (RentalAgreement) -> T): T?
    = rentalAgreementOrNull() ?.let(block)

public inline fun EncryptedPassportElement.passportRegistrationOrNull(): PassportRegistration? =
    this as? dev.inmo.tgbotapi.types.passport.encrypted.PassportRegistration

public inline fun EncryptedPassportElement.passportRegistrationOrThrow(): PassportRegistration =
    this as dev.inmo.tgbotapi.types.passport.encrypted.PassportRegistration

public inline fun <T>
    EncryptedPassportElement.ifPassportRegistration(block: (PassportRegistration) -> T): T? =
    passportRegistrationOrNull() ?.let(block)

public inline fun EncryptedPassportElement.temporaryRegistrationOrNull(): TemporaryRegistration? =
    this as? dev.inmo.tgbotapi.types.passport.encrypted.TemporaryRegistration

public inline fun EncryptedPassportElement.temporaryRegistrationOrThrow(): TemporaryRegistration =
    this as dev.inmo.tgbotapi.types.passport.encrypted.TemporaryRegistration

public inline fun <T>
    EncryptedPassportElement.ifTemporaryRegistration(block: (TemporaryRegistration) -> T): T? =
    temporaryRegistrationOrNull() ?.let(block)

public inline
    fun EncryptedPassportElement.encryptedPassportElementWithTranslatableIDDocumentOrNull():
    EncryptedPassportElementWithTranslatableIDDocument? = this as?
    dev.inmo.tgbotapi.types.passport.encrypted.EncryptedPassportElementWithTranslatableIDDocument

public inline
    fun EncryptedPassportElement.encryptedPassportElementWithTranslatableIDDocumentOrThrow():
    EncryptedPassportElementWithTranslatableIDDocument = this as
    dev.inmo.tgbotapi.types.passport.encrypted.EncryptedPassportElementWithTranslatableIDDocument

public inline fun <T>
    EncryptedPassportElement.ifEncryptedPassportElementWithTranslatableIDDocument(block: (EncryptedPassportElementWithTranslatableIDDocument) -> T):
    T? = encryptedPassportElementWithTranslatableIDDocumentOrNull() ?.let(block)

public inline fun EncryptedPassportElement.driverLicenseOrNull(): DriverLicense? = this as?
    dev.inmo.tgbotapi.types.passport.encrypted.DriverLicense

public inline fun EncryptedPassportElement.driverLicenseOrThrow(): DriverLicense = this as
    dev.inmo.tgbotapi.types.passport.encrypted.DriverLicense

public inline fun <T> EncryptedPassportElement.ifDriverLicense(block: (DriverLicense) -> T): T? =
    driverLicenseOrNull() ?.let(block)

public inline fun EncryptedPassportElement.identityCardOrNull(): IdentityCard? = this as?
    dev.inmo.tgbotapi.types.passport.encrypted.IdentityCard

public inline fun EncryptedPassportElement.identityCardOrThrow(): IdentityCard = this as
    dev.inmo.tgbotapi.types.passport.encrypted.IdentityCard

public inline fun <T> EncryptedPassportElement.ifIdentityCard(block: (IdentityCard) -> T): T? =
    identityCardOrNull() ?.let(block)

public inline fun EncryptedPassportElement.encryptedPersonalDetailsOrNull():
    EncryptedPersonalDetails? = this as?
    dev.inmo.tgbotapi.types.passport.encrypted.EncryptedPersonalDetails

public inline fun EncryptedPassportElement.encryptedPersonalDetailsOrThrow():
    EncryptedPersonalDetails = this as
    dev.inmo.tgbotapi.types.passport.encrypted.EncryptedPersonalDetails

public inline fun <T>
    EncryptedPassportElement.ifEncryptedPersonalDetails(block: (EncryptedPersonalDetails) -> T): T?
    = encryptedPersonalDetailsOrNull() ?.let(block)

public inline fun EncryptedPassportElement.passportOrNull(): Passport? = this as?
    dev.inmo.tgbotapi.types.passport.encrypted.Passport

public inline fun EncryptedPassportElement.passportOrThrow(): Passport = this as
    dev.inmo.tgbotapi.types.passport.encrypted.Passport

public inline fun <T> EncryptedPassportElement.ifPassport(block: (Passport) -> T): T? =
    passportOrNull() ?.let(block)

public inline fun EncryptedPassportElement.commonPassportOrNull(): CommonPassport? = this as?
    dev.inmo.tgbotapi.types.passport.encrypted.CommonPassport

public inline fun EncryptedPassportElement.commonPassportOrThrow(): CommonPassport = this as
    dev.inmo.tgbotapi.types.passport.encrypted.CommonPassport

public inline fun <T> EncryptedPassportElement.ifCommonPassport(block: (CommonPassport) -> T): T? =
    commonPassportOrNull() ?.let(block)

public inline fun EncryptedPassportElement.internalPassportOrNull(): InternalPassport? = this as?
    dev.inmo.tgbotapi.types.passport.encrypted.InternalPassport

public inline fun EncryptedPassportElement.internalPassportOrThrow(): InternalPassport = this as
    dev.inmo.tgbotapi.types.passport.encrypted.InternalPassport

public inline fun <T> EncryptedPassportElement.ifInternalPassport(block: (InternalPassport) -> T):
    T? = internalPassportOrNull() ?.let(block)

public inline fun EncryptedPassportElement.phoneNumberOrNull(): PhoneNumber? = this as?
    dev.inmo.tgbotapi.types.passport.encrypted.PhoneNumber

public inline fun EncryptedPassportElement.phoneNumberOrThrow(): PhoneNumber = this as
    dev.inmo.tgbotapi.types.passport.encrypted.PhoneNumber

public inline fun <T> EncryptedPassportElement.ifPhoneNumber(block: (PhoneNumber) -> T): T? =
    phoneNumberOrNull() ?.let(block)

public inline fun EncryptedPassportElement.unknownEncryptedPassportElementOrNull():
    UnknownEncryptedPassportElement? = this as?
    dev.inmo.tgbotapi.types.passport.encrypted.abstracts.UnknownEncryptedPassportElement

public inline fun EncryptedPassportElement.unknownEncryptedPassportElementOrThrow():
    UnknownEncryptedPassportElement = this as
    dev.inmo.tgbotapi.types.passport.encrypted.abstracts.UnknownEncryptedPassportElement

public inline fun <T>
    EncryptedPassportElement.ifUnknownEncryptedPassportElement(block: (UnknownEncryptedPassportElement) -> T):
    T? = unknownEncryptedPassportElementOrNull() ?.let(block)

public inline fun EncryptedPassportElement.encryptedPassportElementTranslatableOrNull():
    EncryptedPassportElementTranslatable? = this as?
    dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementTranslatable

public inline fun EncryptedPassportElement.encryptedPassportElementTranslatableOrThrow():
    EncryptedPassportElementTranslatable = this as
    dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementTranslatable

public inline fun <T>
    EncryptedPassportElement.ifEncryptedPassportElementTranslatable(block: (EncryptedPassportElementTranslatable) -> T):
    T? = encryptedPassportElementTranslatableOrNull() ?.let(block)

public inline fun EncryptedPassportElement.encryptedPassportElementWithDataOrNull():
    EncryptedPassportElementWithData? = this as?
    dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementWithData

public inline fun EncryptedPassportElement.encryptedPassportElementWithDataOrThrow():
    EncryptedPassportElementWithData = this as
    dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementWithData

public inline fun <T>
    EncryptedPassportElement.ifEncryptedPassportElementWithData(block: (EncryptedPassportElementWithData) -> T):
    T? = encryptedPassportElementWithDataOrNull() ?.let(block)

public inline fun EncryptedPassportElement.encryptedPassportElementWithEmailOrNull():
    EncryptedPassportElementWithEmail? = this as?
    dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementWithEmail

public inline fun EncryptedPassportElement.encryptedPassportElementWithEmailOrThrow():
    EncryptedPassportElementWithEmail = this as
    dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementWithEmail

public inline fun <T>
    EncryptedPassportElement.ifEncryptedPassportElementWithEmail(block: (EncryptedPassportElementWithEmail) -> T):
    T? = encryptedPassportElementWithEmailOrNull() ?.let(block)

public inline fun EncryptedPassportElement.encryptedPassportElementWithFilesCollectionOrNull():
    EncryptedPassportElementWithFilesCollection? = this as?
    dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementWithFilesCollection

public inline fun EncryptedPassportElement.encryptedPassportElementWithFilesCollectionOrThrow():
    EncryptedPassportElementWithFilesCollection = this as
    dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementWithFilesCollection

public inline fun <T>
    EncryptedPassportElement.ifEncryptedPassportElementWithFilesCollection(block: (EncryptedPassportElementWithFilesCollection) -> T):
    T? = encryptedPassportElementWithFilesCollectionOrNull() ?.let(block)

public inline fun EncryptedPassportElement.encryptedPassportElementWithFrontSideOrNull():
    EncryptedPassportElementWithFrontSide? = this as?
    dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementWithFrontSide

public inline fun EncryptedPassportElement.encryptedPassportElementWithFrontSideOrThrow():
    EncryptedPassportElementWithFrontSide = this as
    dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementWithFrontSide

public inline fun <T>
    EncryptedPassportElement.ifEncryptedPassportElementWithFrontSide(block: (EncryptedPassportElementWithFrontSide) -> T):
    T? = encryptedPassportElementWithFrontSideOrNull() ?.let(block)

public inline fun EncryptedPassportElement.encryptedPassportElementWithPhoneNumberOrNull():
    EncryptedPassportElementWithPhoneNumber? = this as?
    dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementWithPhoneNumber

public inline fun EncryptedPassportElement.encryptedPassportElementWithPhoneNumberOrThrow():
    EncryptedPassportElementWithPhoneNumber = this as
    dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementWithPhoneNumber

public inline fun <T>
    EncryptedPassportElement.ifEncryptedPassportElementWithPhoneNumber(block: (EncryptedPassportElementWithPhoneNumber) -> T):
    T? = encryptedPassportElementWithPhoneNumberOrNull() ?.let(block)

public inline fun EncryptedPassportElement.encryptedPassportElementWithReverseSideOrNull():
    EncryptedPassportElementWithReverseSide? = this as?
    dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementWithReverseSide

public inline fun EncryptedPassportElement.encryptedPassportElementWithReverseSideOrThrow():
    EncryptedPassportElementWithReverseSide = this as
    dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementWithReverseSide

public inline fun <T>
    EncryptedPassportElement.ifEncryptedPassportElementWithReverseSide(block: (EncryptedPassportElementWithReverseSide) -> T):
    T? = encryptedPassportElementWithReverseSideOrNull() ?.let(block)

public inline fun EncryptedPassportElement.encryptedPassportElementWithSelfieOrNull():
    EncryptedPassportElementWithSelfie? = this as?
    dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementWithSelfie

public inline fun EncryptedPassportElement.encryptedPassportElementWithSelfieOrThrow():
    EncryptedPassportElementWithSelfie = this as
    dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementWithSelfie

public inline fun <T>
    EncryptedPassportElement.ifEncryptedPassportElementWithSelfie(block: (EncryptedPassportElementWithSelfie) -> T):
    T? = encryptedPassportElementWithSelfieOrNull() ?.let(block)

public inline fun ScheduledCloseInfo.exactScheduledCloseInfoOrNull(): ExactScheduledCloseInfo? =
    this as? dev.inmo.tgbotapi.types.polls.ExactScheduledCloseInfo

public inline fun ScheduledCloseInfo.exactScheduledCloseInfoOrThrow(): ExactScheduledCloseInfo =
    this as dev.inmo.tgbotapi.types.polls.ExactScheduledCloseInfo

public inline fun <T>
    ScheduledCloseInfo.ifExactScheduledCloseInfo(block: (ExactScheduledCloseInfo) -> T): T? =
    exactScheduledCloseInfoOrNull() ?.let(block)

public inline fun ScheduledCloseInfo.approximateScheduledCloseInfoOrNull():
    ApproximateScheduledCloseInfo? = this as?
    dev.inmo.tgbotapi.types.polls.ApproximateScheduledCloseInfo

public inline fun ScheduledCloseInfo.approximateScheduledCloseInfoOrThrow():
    ApproximateScheduledCloseInfo = this as
    dev.inmo.tgbotapi.types.polls.ApproximateScheduledCloseInfo

public inline fun <T>
    ScheduledCloseInfo.ifApproximateScheduledCloseInfo(block: (ApproximateScheduledCloseInfo) -> T):
    T? = approximateScheduledCloseInfoOrNull() ?.let(block)

public inline fun Poll.multipleAnswersPollOrNull(): MultipleAnswersPoll? = this as?
    dev.inmo.tgbotapi.types.polls.MultipleAnswersPoll

public inline fun Poll.multipleAnswersPollOrThrow(): MultipleAnswersPoll = this as
    dev.inmo.tgbotapi.types.polls.MultipleAnswersPoll

public inline fun <T> Poll.ifMultipleAnswersPoll(block: (MultipleAnswersPoll) -> T): T? =
    multipleAnswersPollOrNull() ?.let(block)

public inline fun Poll.unknownPollTypeOrNull(): UnknownPollType? = this as?
    dev.inmo.tgbotapi.types.polls.UnknownPollType

public inline fun Poll.unknownPollTypeOrThrow(): UnknownPollType = this as
    dev.inmo.tgbotapi.types.polls.UnknownPollType

public inline fun <T> Poll.ifUnknownPollType(block: (UnknownPollType) -> T): T? =
    unknownPollTypeOrNull() ?.let(block)

public inline fun Poll.regularPollOrNull(): RegularPoll? = this as?
    dev.inmo.tgbotapi.types.polls.RegularPoll

public inline fun Poll.regularPollOrThrow(): RegularPoll = this as
    dev.inmo.tgbotapi.types.polls.RegularPoll

public inline fun <T> Poll.ifRegularPoll(block: (RegularPoll) -> T): T? = regularPollOrNull()
    ?.let(block)

public inline fun Poll.quizPollOrNull(): QuizPoll? = this as? dev.inmo.tgbotapi.types.polls.QuizPoll

public inline fun Poll.quizPollOrThrow(): QuizPoll = this as dev.inmo.tgbotapi.types.polls.QuizPoll

public inline fun <T> Poll.ifQuizPoll(block: (QuizPoll) -> T): T? = quizPollOrNull() ?.let(block)

public inline fun RequestResponse.chatSharedOrNull(): ChatShared? = this as?
    dev.inmo.tgbotapi.types.request.ChatShared

public inline fun RequestResponse.chatSharedOrThrow(): ChatShared = this as
    dev.inmo.tgbotapi.types.request.ChatShared

public inline fun <T> RequestResponse.ifChatShared(block: (ChatShared) -> T): T? =
    chatSharedOrNull() ?.let(block)

public inline fun RequestResponse.chatSharedRequestOrNull(): ChatSharedRequest? = this as?
    dev.inmo.tgbotapi.types.request.ChatSharedRequest

public inline fun RequestResponse.chatSharedRequestOrThrow(): ChatSharedRequest = this as
    dev.inmo.tgbotapi.types.request.ChatSharedRequest

public inline fun <T> RequestResponse.ifChatSharedRequest(block: (ChatSharedRequest) -> T): T? =
    chatSharedRequestOrNull() ?.let(block)

public inline fun RequestResponse.userSharedOrNull(): UserShared? = this as?
    dev.inmo.tgbotapi.types.request.UserShared

public inline fun RequestResponse.userSharedOrThrow(): UserShared = this as
    dev.inmo.tgbotapi.types.request.UserShared

public inline fun <T> RequestResponse.ifUserShared(block: (UserShared) -> T): T? =
    userSharedOrNull() ?.let(block)

public inline fun Update.callbackQueryUpdateOrNull(): CallbackQueryUpdate? = this as?
    dev.inmo.tgbotapi.types.update.CallbackQueryUpdate

public inline fun Update.callbackQueryUpdateOrThrow(): CallbackQueryUpdate = this as
    dev.inmo.tgbotapi.types.update.CallbackQueryUpdate

public inline fun <T> Update.ifCallbackQueryUpdate(block: (CallbackQueryUpdate) -> T): T? =
    callbackQueryUpdateOrNull() ?.let(block)

public inline fun Update.channelPostUpdateOrNull(): ChannelPostUpdate? = this as?
    dev.inmo.tgbotapi.types.update.ChannelPostUpdate

public inline fun Update.channelPostUpdateOrThrow(): ChannelPostUpdate = this as
    dev.inmo.tgbotapi.types.update.ChannelPostUpdate

public inline fun <T> Update.ifChannelPostUpdate(block: (ChannelPostUpdate) -> T): T? =
    channelPostUpdateOrNull() ?.let(block)

public inline fun Update.chatJoinRequestUpdateOrNull(): ChatJoinRequestUpdate? = this as?
    dev.inmo.tgbotapi.types.update.ChatJoinRequestUpdate

public inline fun Update.chatJoinRequestUpdateOrThrow(): ChatJoinRequestUpdate = this as
    dev.inmo.tgbotapi.types.update.ChatJoinRequestUpdate

public inline fun <T> Update.ifChatJoinRequestUpdate(block: (ChatJoinRequestUpdate) -> T): T? =
    chatJoinRequestUpdateOrNull() ?.let(block)

public inline fun Update.chosenInlineResultUpdateOrNull(): ChosenInlineResultUpdate? = this as?
    dev.inmo.tgbotapi.types.update.ChosenInlineResultUpdate

public inline fun Update.chosenInlineResultUpdateOrThrow(): ChosenInlineResultUpdate = this as
    dev.inmo.tgbotapi.types.update.ChosenInlineResultUpdate

public inline fun <T> Update.ifChosenInlineResultUpdate(block: (ChosenInlineResultUpdate) -> T): T?
    = chosenInlineResultUpdateOrNull() ?.let(block)

public inline fun Update.commonChatMemberUpdatedUpdateOrNull(): CommonChatMemberUpdatedUpdate? =
    this as? dev.inmo.tgbotapi.types.update.CommonChatMemberUpdatedUpdate

public inline fun Update.commonChatMemberUpdatedUpdateOrThrow(): CommonChatMemberUpdatedUpdate =
    this as dev.inmo.tgbotapi.types.update.CommonChatMemberUpdatedUpdate

public inline fun <T>
    Update.ifCommonChatMemberUpdatedUpdate(block: (CommonChatMemberUpdatedUpdate) -> T): T? =
    commonChatMemberUpdatedUpdateOrNull() ?.let(block)

public inline fun Update.editChannelPostUpdateOrNull(): EditChannelPostUpdate? = this as?
    dev.inmo.tgbotapi.types.update.EditChannelPostUpdate

public inline fun Update.editChannelPostUpdateOrThrow(): EditChannelPostUpdate = this as
    dev.inmo.tgbotapi.types.update.EditChannelPostUpdate

public inline fun <T> Update.ifEditChannelPostUpdate(block: (EditChannelPostUpdate) -> T): T? =
    editChannelPostUpdateOrNull() ?.let(block)

public inline fun Update.editMessageUpdateOrNull(): EditMessageUpdate? = this as?
    dev.inmo.tgbotapi.types.update.EditMessageUpdate

public inline fun Update.editMessageUpdateOrThrow(): EditMessageUpdate = this as
    dev.inmo.tgbotapi.types.update.EditMessageUpdate

public inline fun <T> Update.ifEditMessageUpdate(block: (EditMessageUpdate) -> T): T? =
    editMessageUpdateOrNull() ?.let(block)

public inline fun Update.inlineQueryUpdateOrNull(): InlineQueryUpdate? = this as?
    dev.inmo.tgbotapi.types.update.InlineQueryUpdate

public inline fun Update.inlineQueryUpdateOrThrow(): InlineQueryUpdate = this as
    dev.inmo.tgbotapi.types.update.InlineQueryUpdate

public inline fun <T> Update.ifInlineQueryUpdate(block: (InlineQueryUpdate) -> T): T? =
    inlineQueryUpdateOrNull() ?.let(block)

public inline fun Update.messageUpdateOrNull(): MessageUpdate? = this as?
    dev.inmo.tgbotapi.types.update.MessageUpdate

public inline fun Update.messageUpdateOrThrow(): MessageUpdate = this as
    dev.inmo.tgbotapi.types.update.MessageUpdate

public inline fun <T> Update.ifMessageUpdate(block: (MessageUpdate) -> T): T? =
    messageUpdateOrNull() ?.let(block)

public inline fun Update.myChatMemberUpdatedUpdateOrNull(): MyChatMemberUpdatedUpdate? = this as?
    dev.inmo.tgbotapi.types.update.MyChatMemberUpdatedUpdate

public inline fun Update.myChatMemberUpdatedUpdateOrThrow(): MyChatMemberUpdatedUpdate = this as
    dev.inmo.tgbotapi.types.update.MyChatMemberUpdatedUpdate

public inline fun <T> Update.ifMyChatMemberUpdatedUpdate(block: (MyChatMemberUpdatedUpdate) -> T):
    T? = myChatMemberUpdatedUpdateOrNull() ?.let(block)

public inline fun Update.pollAnswerUpdateOrNull(): PollAnswerUpdate? = this as?
    dev.inmo.tgbotapi.types.update.PollAnswerUpdate

public inline fun Update.pollAnswerUpdateOrThrow(): PollAnswerUpdate = this as
    dev.inmo.tgbotapi.types.update.PollAnswerUpdate

public inline fun <T> Update.ifPollAnswerUpdate(block: (PollAnswerUpdate) -> T): T? =
    pollAnswerUpdateOrNull() ?.let(block)

public inline fun Update.pollUpdateOrNull(): PollUpdate? = this as?
    dev.inmo.tgbotapi.types.update.PollUpdate

public inline fun Update.pollUpdateOrThrow(): PollUpdate = this as
    dev.inmo.tgbotapi.types.update.PollUpdate

public inline fun <T> Update.ifPollUpdate(block: (PollUpdate) -> T): T? = pollUpdateOrNull()
    ?.let(block)

public inline fun Update.preCheckoutQueryUpdateOrNull(): PreCheckoutQueryUpdate? = this as?
    dev.inmo.tgbotapi.types.update.PreCheckoutQueryUpdate

public inline fun Update.preCheckoutQueryUpdateOrThrow(): PreCheckoutQueryUpdate = this as
    dev.inmo.tgbotapi.types.update.PreCheckoutQueryUpdate

public inline fun <T> Update.ifPreCheckoutQueryUpdate(block: (PreCheckoutQueryUpdate) -> T): T? =
    preCheckoutQueryUpdateOrNull() ?.let(block)

public inline fun Update.shippingQueryUpdateOrNull(): ShippingQueryUpdate? = this as?
    dev.inmo.tgbotapi.types.update.ShippingQueryUpdate

public inline fun Update.shippingQueryUpdateOrThrow(): ShippingQueryUpdate = this as
    dev.inmo.tgbotapi.types.update.ShippingQueryUpdate

public inline fun <T> Update.ifShippingQueryUpdate(block: (ShippingQueryUpdate) -> T): T? =
    shippingQueryUpdateOrNull() ?.let(block)

public inline fun Update.baseEditMessageUpdateOrNull(): BaseEditMessageUpdate? = this as?
    dev.inmo.tgbotapi.types.update.abstracts.BaseEditMessageUpdate

public inline fun Update.baseEditMessageUpdateOrThrow(): BaseEditMessageUpdate = this as
    dev.inmo.tgbotapi.types.update.abstracts.BaseEditMessageUpdate

public inline fun <T> Update.ifBaseEditMessageUpdate(block: (BaseEditMessageUpdate) -> T): T? =
    baseEditMessageUpdateOrNull() ?.let(block)

public inline fun Update.baseMessageUpdateOrNull(): BaseMessageUpdate? = this as?
    dev.inmo.tgbotapi.types.update.abstracts.BaseMessageUpdate

public inline fun Update.baseMessageUpdateOrThrow(): BaseMessageUpdate = this as
    dev.inmo.tgbotapi.types.update.abstracts.BaseMessageUpdate

public inline fun <T> Update.ifBaseMessageUpdate(block: (BaseMessageUpdate) -> T): T? =
    baseMessageUpdateOrNull() ?.let(block)

public inline fun Update.baseSentMessageUpdateOrNull(): BaseSentMessageUpdate? = this as?
    dev.inmo.tgbotapi.types.update.abstracts.BaseSentMessageUpdate

public inline fun Update.baseSentMessageUpdateOrThrow(): BaseSentMessageUpdate = this as
    dev.inmo.tgbotapi.types.update.abstracts.BaseSentMessageUpdate

public inline fun <T> Update.ifBaseSentMessageUpdate(block: (BaseSentMessageUpdate) -> T): T? =
    baseSentMessageUpdateOrNull() ?.let(block)

public inline fun Update.chatMemberUpdatedUpdateOrNull(): ChatMemberUpdatedUpdate? = this as?
    dev.inmo.tgbotapi.types.update.abstracts.ChatMemberUpdatedUpdate

public inline fun Update.chatMemberUpdatedUpdateOrThrow(): ChatMemberUpdatedUpdate = this as
    dev.inmo.tgbotapi.types.update.abstracts.ChatMemberUpdatedUpdate

public inline fun <T> Update.ifChatMemberUpdatedUpdate(block: (ChatMemberUpdatedUpdate) -> T): T? =
    chatMemberUpdatedUpdateOrNull() ?.let(block)

public inline fun Update.unknownUpdateOrNull(): UnknownUpdate? = this as?
    dev.inmo.tgbotapi.types.update.abstracts.UnknownUpdate

public inline fun Update.unknownUpdateOrThrow(): UnknownUpdate = this as
    dev.inmo.tgbotapi.types.update.abstracts.UnknownUpdate

public inline fun <T> Update.ifUnknownUpdate(block: (UnknownUpdate) -> T): T? =
    unknownUpdateOrNull() ?.let(block)
