package dev.inmo.tgbotapi.extensions.utils.extensions.raw

import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.*
import dev.inmo.tgbotapi.types.dice.Dice
import dev.inmo.tgbotapi.types.files.*
import dev.inmo.tgbotapi.types.games.Game
import dev.inmo.tgbotapi.types.location.Location
import dev.inmo.tgbotapi.types.message.ChatEvents.*
import dev.inmo.tgbotapi.types.message.ChatEvents.voice.*
import dev.inmo.tgbotapi.types.message.abstracts.ConnectedFromChannelGroupContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.message.content.TextContent
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.passport.PassportData
import dev.inmo.tgbotapi.types.payments.Invoice
import dev.inmo.tgbotapi.types.payments.SuccessfulPayment
import dev.inmo.tgbotapi.types.polls.Poll
import dev.inmo.tgbotapi.types.venue.Venue
import dev.inmo.tgbotapi.utils.RiskFeature

@RiskFeature(RawFieldsUsageWarning)
inline val Message.from: User?
    get() = asFromUser() ?.from
@RiskFeature(RawFieldsUsageWarning)
inline val Message.sender_chat: PublicChat?
    get() = asFromChannelGroupContentMessage() ?.senderChat
@RiskFeature(RawFieldsUsageWarning)
inline val Message.forward_from: User?
    get() = asPossiblyForwardedMessage() ?.forwardInfo ?.asUserForwardInfo() ?.from
@RiskFeature(RawFieldsUsageWarning)
inline val Message.forward_from_chat: Chat?
    get() = asPossiblyForwardedMessage() ?.forwardInfo ?.asForwardFromPublicChatInfo() ?.chat
@RiskFeature(RawFieldsUsageWarning)
inline val Message.forward_from_message_id: MessageId?
    get() = asPossiblyForwardedMessage() ?.forwardInfo ?.asForwardFromChannelInfo() ?.messageId
@RiskFeature(RawFieldsUsageWarning)
inline val Message.forward_signature: ForwardSignature?
    get() = asPossiblyForwardedMessage() ?.forwardInfo ?.asForwardFromChannelInfo() ?.signature
@RiskFeature(RawFieldsUsageWarning)
inline val Message.forward_sender_name: ForwardSenderName?
    get() = asPossiblyForwardedMessage() ?.forwardInfo ?.asAnonymousForwardInfo() ?.senderName
@RiskFeature(RawFieldsUsageWarning)
inline val Message.forward_date: TelegramDate?
    get() = asPossiblyForwardedMessage() ?.forwardInfo ?.dateOfOriginal
@RiskFeature(RawFieldsUsageWarning)
inline val Message.is_automatic_forward: Boolean?
    get() = this is ConnectedFromChannelGroupContentMessage<*>
@RiskFeature(RawFieldsUsageWarning)
inline val Message.reply_to_message: Message?
    get() = asPossiblyReplyMessage() ?.replyTo
@RiskFeature(RawFieldsUsageWarning)
inline val Message.via_bot: CommonBot?
    get() = asPossiblySentViaBotCommonMessage() ?.senderBot
@RiskFeature(RawFieldsUsageWarning)
inline val Message.edit_date: TelegramDate?
    get() = asPossiblyEditedMessage() ?.editDate ?.toTelegramDate()
@RiskFeature(RawFieldsUsageWarning)
inline val Message.has_protected_content: Boolean?
    get() = asContentMessage() ?.hasProtectedContent
@RiskFeature(RawFieldsUsageWarning)
inline val Message.media_group_id: MediaGroupIdentifier?
    get() = asMediaGroupMessage() ?.mediaGroupId
@RiskFeature(RawFieldsUsageWarning)
inline val Message.author_signature: AuthorSignature?
    get() = asSignedMessage() ?.authorSignature
@RiskFeature(RawFieldsUsageWarning)
inline val Message.text: String?
    get() = asContentMessage() ?.content ?.asTextContent() ?.text
@RiskFeature(RawFieldsUsageWarning)
inline val Message.entities: TextSourcesList?
    get() = asContentMessage() ?.content ?.asTextContent() ?.textSources
@RiskFeature(RawFieldsUsageWarning)
inline val Message.caption: String?
    get() = whenContentMessage {
        if (it.content !is TextContent) {
            it.content.asTextedInput() ?.text
        } else {
            null
        }
    }
@RiskFeature(RawFieldsUsageWarning)
inline val Message.caption_entities: TextSourcesList?
    get() = whenContentMessage {
        if (it.content !is TextContent) {
            it.content.asTextedInput() ?.textSources
        } else {
            null
        }
    }
@RiskFeature(RawFieldsUsageWarning)
inline val Message.audio: AudioFile?
    get() = asContentMessage() ?.content ?.asAudioContent() ?.media
@RiskFeature(RawFieldsUsageWarning)
inline val Message.document: DocumentFile?
    get() = asContentMessage() ?.content ?.asDocumentContent() ?.media
@RiskFeature(RawFieldsUsageWarning)
inline val Message.animation: AnimationFile?
    get() = asContentMessage() ?.content ?.asAnimationContent() ?.media
@RiskFeature(RawFieldsUsageWarning)
inline val Message.game: Game?
    get() = asContentMessage() ?.content ?.asGameContent() ?.game
@RiskFeature(RawFieldsUsageWarning)
inline val Message.photo: Photo?
    get() = asContentMessage() ?.content ?.asPhotoContent() ?.mediaCollection
@RiskFeature(RawFieldsUsageWarning)
inline val Message.sticker: Sticker?
    get() = asContentMessage() ?.content ?.asStickerContent() ?.media
@RiskFeature(RawFieldsUsageWarning)
inline val Message.video: VideoFile?
    get() = asContentMessage() ?.content ?.asVideoContent() ?.media
@RiskFeature(RawFieldsUsageWarning)
inline val Message.voice: VoiceFile?
    get() = asContentMessage() ?.content ?.asVoiceContent() ?.media
@RiskFeature(RawFieldsUsageWarning)
inline val Message.video_note: VideoNoteFile?
    get() = asContentMessage() ?.content ?.asVideoNoteContent() ?.media
@RiskFeature(RawFieldsUsageWarning)
inline val Message.contact: Contact?
    get() = asContentMessage() ?.content ?.asContactContent() ?.contact
@RiskFeature(RawFieldsUsageWarning)
inline val Message.location: Location?
    get() = asContentMessage() ?.content ?.asLocationContent() ?.location
@RiskFeature(RawFieldsUsageWarning)
inline val Message.venue: Venue?
    get() = asContentMessage() ?.content ?.asVenueContent() ?.venue
@RiskFeature(RawFieldsUsageWarning)
inline val Message.poll: Poll?
    get() = asContentMessage() ?.content ?.asPollContent() ?.poll
@RiskFeature(RawFieldsUsageWarning)
inline val Message.invoice: Invoice?
    get() = asContentMessage() ?.content ?.asInvoiceContent() ?.invoice
@RiskFeature(RawFieldsUsageWarning)
inline val Message.dice: Dice?
    get() = asContentMessage() ?.content ?.asDiceContent() ?.dice
@RiskFeature(RawFieldsUsageWarning)
inline val Message.new_chat_members: List<User>?
    get() = asChatEventMessage() ?.chatEvent ?.asNewChatMembers() ?.members
@RiskFeature(RawFieldsUsageWarning)
inline val Message.left_chat_member: User?
    get() = asChatEventMessage() ?.chatEvent ?.asLeftChatMember() ?.user
@RiskFeature(RawFieldsUsageWarning)
inline val Message.new_chat_title: String?
    get() = asChatEventMessage() ?.chatEvent ?.asNewChatTitle() ?.title
@RiskFeature(RawFieldsUsageWarning)
inline val Message.new_chat_photo: Photo?
    get() = asChatEventMessage() ?.chatEvent ?.asNewChatPhoto() ?.photo
@RiskFeature(RawFieldsUsageWarning)
inline val Message.delete_chat_photo: Boolean
    get() = asChatEventMessage() ?.chatEvent is DeleteChatPhoto
@RiskFeature(RawFieldsUsageWarning)
inline val Message.group_chat_created: Boolean
    get() = asChatEventMessage() ?.chatEvent is GroupChatCreated
@RiskFeature(RawFieldsUsageWarning)
inline val Message.supergroup_chat_created: Boolean
    get() = asChatEventMessage() ?.chatEvent is SupergroupChatCreated
@RiskFeature(RawFieldsUsageWarning)
inline val Message.channel_chat_created: Boolean
    get() = asChatEventMessage() ?.chatEvent is ChannelChatCreated
@RiskFeature(RawFieldsUsageWarning)
inline val Message.migrate_to_chat_id: ChatId?
    get() = asChatEventMessage() ?.chatEvent ?.asGroupChatCreated() ?.migratedTo
@RiskFeature(RawFieldsUsageWarning)
inline val Message.migrate_from_chat_id: ChatId?
    get() = asChatEventMessage() ?.chatEvent ?.let {
         it ?.asSupergroupChatCreated() ?.migratedFrom ?: it ?.asMigratedToSupergroup() ?.migratedFrom
    }
@RiskFeature(RawFieldsUsageWarning)
inline val Message.pinned_message: Message?
    get() = asChatEventMessage() ?.chatEvent ?.asPinnedMessage() ?.message
@RiskFeature(RawFieldsUsageWarning)
inline val Message.successful_payment: SuccessfulPayment?
    get() = asChatEventMessage() ?.chatEvent ?.asSuccessfulPaymentEvent() ?.payment

@RiskFeature(RawFieldsUsageWarning)
inline val Message.video_chat_scheduled: VideoChatScheduled?
    get() = asChatEventMessage() ?.chatEvent ?.asVideoChatScheduled()
@RiskFeature(RawFieldsUsageWarning)
inline val Message.video_chat_started: VideoChatStarted?
    get() = asChatEventMessage() ?.chatEvent ?.asVideoChatStarted()
@RiskFeature(RawFieldsUsageWarning)
inline val Message.video_chat_ended: VideoChatEnded?
    get() = asChatEventMessage() ?.chatEvent ?.asVideoChatEnded()
@RiskFeature(RawFieldsUsageWarning)
inline val Message.video_chat_participants_invited: VideoChatParticipantsInvited?
    get() = asChatEventMessage() ?.chatEvent ?.asVideoChatParticipantsInvited()

@RiskFeature(RawFieldsUsageWarning)
inline val Message.message_auto_delete_timer_changed: MessageAutoDeleteTimerChanged?
    get() = asChatEventMessage() ?.chatEvent ?.asMessageAutoDeleteTimerChanged()

@RiskFeature(RawFieldsUsageWarning)
inline val Message.connected_website: String?
    get() = asChatEventMessage() ?.chatEvent ?.asUserLoggedIn() ?.domain
@RiskFeature(RawFieldsUsageWarning)
inline val Message.proximity_alert_triggered: ProximityAlertTriggered?
    get() = asChatEventMessage() ?.chatEvent ?.asProximityAlertTriggered()

@RiskFeature(RawFieldsUsageWarning)
inline val Message.passport_data: PassportData?
    get() = asPassportMessage() ?.passportData

@RiskFeature(RawFieldsUsageWarning)
inline val Message.reply_markup: InlineKeyboardMarkup?
    get() = asCommonMessage() ?.replyMarkup
