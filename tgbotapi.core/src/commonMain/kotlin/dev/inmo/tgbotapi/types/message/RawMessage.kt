package dev.inmo.tgbotapi.types.message

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.*
import dev.inmo.tgbotapi.types.chat.CommonBot
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.dice.Dice
import dev.inmo.tgbotapi.types.files.*
import dev.inmo.tgbotapi.types.files.Sticker
import dev.inmo.tgbotapi.types.games.RawGame
import dev.inmo.tgbotapi.types.gifts.GiftSentOrReceived
import dev.inmo.tgbotapi.types.giveaway.*
import dev.inmo.tgbotapi.types.location.Location
import dev.inmo.tgbotapi.types.message.ChatEvents.*
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.*
import dev.inmo.tgbotapi.types.message.ChatEvents.forum.ForumTopicClosed
import dev.inmo.tgbotapi.types.message.ChatEvents.forum.ForumTopicCreated
import dev.inmo.tgbotapi.types.message.ChatEvents.forum.ForumTopicEdited
import dev.inmo.tgbotapi.types.message.ChatEvents.forum.ForumTopicReopened
import dev.inmo.tgbotapi.types.message.ChatEvents.forum.GeneralForumTopicHidden
import dev.inmo.tgbotapi.types.message.ChatEvents.forum.GeneralForumTopicUnhidden
import dev.inmo.tgbotapi.types.message.ChatEvents.forum.WriteAccessAllowed
import dev.inmo.tgbotapi.types.message.ChatEvents.voice.*
import dev.inmo.tgbotapi.types.message.abstracts.*
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.types.message.content.GiveawayContent
import dev.inmo.tgbotapi.types.message.content.InvoiceContent
import dev.inmo.tgbotapi.types.message.content.MessageContent
import dev.inmo.tgbotapi.types.message.payments.*
import dev.inmo.tgbotapi.types.passport.PassportData
import dev.inmo.tgbotapi.types.payments.Invoice
import dev.inmo.tgbotapi.types.payments.RefundedPayment
import dev.inmo.tgbotapi.types.payments.SuccessfulPayment
import dev.inmo.tgbotapi.types.polls.Poll
import dev.inmo.tgbotapi.types.request.ChatShared
import dev.inmo.tgbotapi.types.request.UsersShared
import dev.inmo.tgbotapi.types.stories.Story
import dev.inmo.tgbotapi.types.venue.Venue
import dev.inmo.tgbotapi.utils.isFakeTelegramUser
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass

// TODO:: add PassportData type
@Serializable
internal data class RawMessage(
    @SerialName(messageIdField)
    val messageId: MessageId,
    val date: TelegramDate,
    private val chat: PreviewChat,
    @SerialName(messageThreadIdField)
    private val messageThreadId: MessageThreadId? = null,
    private val from: PreviewUser? = null,
    private val sender_chat: PreviewPublicChat? = null,
    private val forward_origin: MessageOrigin? = null,
    private val is_topic_message: Boolean? = null,
    private val is_automatic_forward: Boolean? = null,
    private val reply_to_message: RawMessage? = null,
    private val reply_to_story: Story? = null,
    private val external_reply: ReplyInfo.External? = null,
    private val quote: TextQuote? = null,
    private val via_bot: CommonBot? = null,
    private val edit_date: TelegramDate? = null,
    private val has_protected_content: Boolean? = null,
    private val media_group_id: MediaGroupId? = null,
    private val author_signature: AuthorSignature? = null,
    private val text: String? = null,
    private val entities: RawMessageEntities? = null,
    private val caption: String? = null,
    private val caption_entities: RawMessageEntities? = null,
    private val has_media_spoiler: Boolean? = null,
    private val story: Story? = null,
    private val audio: AudioFile? = null,
    private val document: DocumentFile? = null,
    private val paid_media: PaidMediaInfo? = null,
    private val animation: AnimationFile? = null,
    private val game: RawGame? = null,
    @Serializable(PhotoSerializer::class)
    private val photo: PhotoFile? = null,
    private val sticker: Sticker? = null,
    private val video: VideoFile? = null,
    private val voice: VoiceFile? = null,
    private val video_note: VideoNoteFile? = null,
    private val contact: Contact? = null,
    private val location: Location? = null,
    private val venue: Venue? = null,
    private val poll: Poll? = null,
    private val new_chat_members: List<User>? = null,
    private val left_chat_member: User? = null,
    private val new_chat_title: String? = null,
    @Serializable(PhotoSerializer::class)
    private val new_chat_photo: PhotoFile? = null,
    private val delete_chat_photo: Boolean = false,
    private val group_chat_created: Boolean = false,
    private val supergroup_chat_created: Boolean = false,
    private val channel_chat_created: Boolean = false,
    private val migrate_to_chat_id: IdChatIdentifier? = null,
    private val migrate_from_chat_id: IdChatIdentifier? = null,
    private val pinned_message: RawMessage? = null,
    private val invoice: Invoice? = null,
    private val dice: Dice? = null,
    private val successful_payment: SuccessfulPayment? = null,
    private val refunded_payment: RefundedPayment? = null,
    private val sender_boost_count: Int? = null,
    private val users_shared: UsersShared? = null,
    private val chat_shared: ChatShared? = null,
    private val is_from_offline: Boolean = false,
    private val paid_star_count: Int? = null,
    private val paid_message_price_changed: PaidMessagePriceChanged? = null,
    // Voice Chat Service Messages
    private val video_chat_scheduled: VideoChatScheduled? = null,
    private val video_chat_started: VideoChatStarted? = null,
    private val video_chat_ended: VideoChatEnded? = null,
    private val video_chat_participants_invited: VideoChatParticipantsInvited? = null,
    // Forum
    private val forum_topic_created: ForumTopicCreated? = null,
    private val forum_topic_edited: ForumTopicEdited? = null,
    private val forum_topic_closed: ForumTopicClosed? = null,
    private val forum_topic_reopened: ForumTopicReopened? = null,
    private val general_forum_topic_hidden: GeneralForumTopicHidden? = null,
    private val general_forum_topic_unhidden: GeneralForumTopicUnhidden? = null,
    private val write_access_allowed: WriteAccessAllowed? = null,
    // Boost added to groups
    private val boost_added: ChatBoostAdded? = null,
    // Chat background has been changed
    private val chat_background_set: ChatBackground? = null,
    // AutoDelete Message time changed
    private val message_auto_delete_timer_changed: MessageAutoDeleteTimerChanged? = null,
    // login property
    private val connected_website: String? = null,
    // login property
    private val web_app_data: WebAppData? = null,
    // passport property
    private val passport_data: PassportData? = null,
    private val proximity_alert_triggered: ProximityAlertTriggered? = null,
    private val link_preview_options: LinkPreviewOptions? = null,
    private val effect_id: EffectId? = null,
    private val show_caption_above_media: Boolean = false,
    private val reply_markup: InlineKeyboardMarkup? = null,
    // Business
    private val business_connection_id: BusinessConnectionId? = null,
    private val sender_business_bot: PreviewBot? = null,
    // Giveaways
    private val giveaway_created: GiveawayCreated? = null,
    private val giveaway: Giveaway? = null,
    private val giveaway_winners: GiveawayPublicResults? = null,
    private val giveaway_completed: GiveawayPrivateResults? = null,
    // Gifts
    private val gift: GiftSentOrReceived.Regular? = null,
    private val unique_gift: GiftSentOrReceived.Unique? = null,
) {
    private val checkedFrom = from ?.takeIf { !it.isFakeTelegramUser() }
    private val content: MessageContent? by lazy {
        val adaptedCaptionEntities = caption ?.let {
            (caption_entities ?: emptyList()).asTextSources(caption)
        } ?: emptyList()

        when {
            story != null ->
                StoryContent(
                    chat,
                    messageId,
                    story,
                )
            text != null -> TextContent(text, (entities ?: emptyList()).asTextSources(text), link_preview_options, quote)
            audio != null ->
                AudioContent(
                    audio,
                    caption,
                    adaptedCaptionEntities,
                    quote,
                )
            video != null ->
                VideoContent(
                    video,
                    caption,
                    adaptedCaptionEntities,
                    has_media_spoiler ?: false,
                    quote,
                    show_caption_above_media,
                )
            animation != null ->
                AnimationContent(
                    animation,
                    document,
                    caption,
                    adaptedCaptionEntities,
                    has_media_spoiler ?: false,
                    quote,
                    show_caption_above_media,
                )
            document != null ->
                DocumentContent(
                    document,
                    caption,
                    adaptedCaptionEntities,
                    quote,
                )
            paid_media != null ->
                PaidMediaInfoContent(
                    paid_media,
                    caption,
                    adaptedCaptionEntities,
                    quote,
                )
            voice != null ->
                VoiceContent(
                    voice,
                    caption,
                    adaptedCaptionEntities,
                    quote,
                )
            photo != null ->
                PhotoContent(
                    photo,
                    caption,
                    adaptedCaptionEntities,
                    has_media_spoiler ?: false,
                    quote,
                    show_caption_above_media,
                )
            sticker != null -> StickerContent(sticker)
            dice != null -> DiceContent(dice)
            game != null -> GameContent(game.asGame)
            video_note != null -> VideoNoteContent(video_note)
            contact != null -> ContactContent(contact)
            location != null -> LocationContent(location)
            venue != null -> VenueContent(venue)
            poll != null -> PollContent(poll)
            invoice != null -> InvoiceContent(invoice)
            giveaway != null -> GiveawayContent(chat, messageId, giveaway)
            giveaway_winners is GiveawayPublicResults -> GiveawayPublicResultsContent(giveaway_winners)
            else -> null
        }
    }

    private val chatEvent: ChatEvent? by lazy {
        when {
            new_chat_members != null -> NewChatMembers(new_chat_members.toList())
            left_chat_member != null -> LeftChatMemberEvent(left_chat_member)
            new_chat_title != null -> NewChatTitle(new_chat_title)
            new_chat_photo != null -> NewChatPhoto(new_chat_photo)
            video_chat_started != null -> video_chat_started
            video_chat_scheduled != null -> video_chat_scheduled
            message_auto_delete_timer_changed != null -> message_auto_delete_timer_changed
            forum_topic_created != null -> forum_topic_created
            forum_topic_edited != null -> forum_topic_edited
            general_forum_topic_hidden != null -> general_forum_topic_hidden
            general_forum_topic_unhidden != null -> general_forum_topic_unhidden
            write_access_allowed != null -> write_access_allowed
            forum_topic_closed != null -> forum_topic_closed
            forum_topic_reopened != null -> forum_topic_reopened
            video_chat_ended != null -> video_chat_ended
            video_chat_participants_invited != null -> video_chat_participants_invited
            delete_chat_photo -> DeleteChatPhoto()
            group_chat_created ->
                GroupChatCreated(
                    migrate_to_chat_id,
                )

            supergroup_chat_created ->
                SupergroupChatCreated(
                    migrate_from_chat_id,
                )

            migrate_from_chat_id != null ->
                MigratedToSupergroup(
                    migrate_from_chat_id,
                )

            channel_chat_created -> ChannelChatCreated()
            pinned_message != null -> PinnedMessage(pinned_message.asMessage)
            proximity_alert_triggered != null -> proximity_alert_triggered
            successful_payment != null -> SuccessfulPaymentEvent(successful_payment)
            refunded_payment != null -> RefundedPaymentEvent(refunded_payment)
            connected_website != null -> UserLoggedIn(connected_website)
            web_app_data != null -> web_app_data
            users_shared != null -> users_shared
            chat_shared != null -> chat_shared
            giveaway_created != null -> giveaway_created
            giveaway_completed != null -> giveaway_completed
            boost_added != null -> boost_added
            chat_background_set != null -> chat_background_set
            paid_message_price_changed != null -> paid_message_price_changed
            gift != null -> gift
            unique_gift != null -> unique_gift
            else -> null
        }
    }

    val asMessage: Message by lazy {
        if (date.date == 0L) {
            return@lazy InaccessibleMessage(
                chat,
                messageId,
            )
        }

        try {
            chatEvent ?.let { chatEvent ->
                when (chat) {
                    is PreviewSupergroupChat ->
                        CommonSupergroupEventMessage(
                            messageId,
                            checkedFrom ?: from ?: error("Supergroup events are expected to contain 'from' field"),
                            chat,
                            chatEvent as? SupergroupEvent ?: throwWrongChatEvent(SupergroupEvent::class, chatEvent),
                            date.asDate,
                        )
                    is PreviewGroupChat ->
                        CommonGroupEventMessage(
                            messageId,
                            checkedFrom ?: from ?: error("Supergroup events are expected to contain 'from' field"),
                            chat,
                            chatEvent as? GroupEvent ?: throwWrongChatEvent(GroupChat::class, chatEvent),
                            date.asDate,
                        )
                    is PreviewChannelChat ->
                        ChannelEventMessage(
                            messageId,
                            chat,
                            chatEvent as? ChannelEvent ?: throwWrongChatEvent(ChannelEvent::class, chatEvent),
                            date.asDate,
                        )
                    is PreviewPrivateChat ->
                        PrivateEventMessage(
                            messageId,
                            chat,
                            chatEvent as? PrivateEvent ?: throwWrongChatEvent(PrivateEvent::class, chatEvent),
                            date.asDate,
                        )
                    else -> error("Expected one of the public chats, but was $chat (in extracting of chat event message)")
                }
            } ?: content?.let { content ->
                val replyInfo: ReplyInfo? = when {
                    reply_to_message != null ->
                        ReplyInfo.Internal(
                            reply_to_message.asMessage,
                        )
                    reply_to_story != null -> ReplyInfo.ToStory(reply_to_story)
                    external_reply != null -> external_reply
                    else -> null
                }
                when (chat) {
                    is PreviewPublicChat ->
                        when (chat) {
                            is PreviewChannelChat ->
                                ChannelContentMessageImpl(
                                    messageId = messageId,
                                    chat = chat,
                                    senderChat = checkedFrom ?: sender_chat ?: chat,
                                    content = content,
                                    date = date.asDate,
                                    editDate = edit_date?.asDate,
                                    hasProtectedContent = has_protected_content == true,
                                    forwardOrigin = forward_origin,
                                    replyInfo = replyInfo,
                                    replyMarkup = reply_markup,
                                    senderBot = via_bot,
                                    authorSignature = author_signature,
                                    mediaGroupId = media_group_id,
                                    fromOffline = is_from_offline,
                                    cost = paid_star_count,
                                )
                            is PreviewForumChat ->
                                if (messageThreadId != null) {
                                    val chatId = ChatIdWithThreadId(
                                        chat.id.chatId,
                                        messageThreadId,
                                    )
                                    val actualForumChat = when (chat) {
                                        is ForumChatImpl -> chat.copy(id = chatId)
                                    }
                                    when (sender_chat) {
                                        is PreviewChannelChat ->
                                            FromChannelForumContentMessageImpl(
                                                chat = actualForumChat,
                                                channel = sender_chat,
                                                messageId = messageId,
                                                threadId = messageThreadId,
                                                date = date.asDate,
                                                forwardOrigin = forward_origin,
                                                editDate = edit_date ?.asDate,
                                                hasProtectedContent = has_protected_content == true,
                                                replyInfo = replyInfo,
                                                replyMarkup = reply_markup,
                                                content = content,
                                                senderBot = via_bot,
                                                authorSignature = author_signature,
                                                mediaGroupId = media_group_id,
                                                fromOffline = is_from_offline,
                                                cost = paid_star_count,
                                            )
                                        is PreviewGroupChat ->
                                            AnonymousForumContentMessageImpl(
                                                chat = actualForumChat,
                                                messageId = messageId,
                                                threadId = messageThreadId,
                                                date = date.asDate,
                                                forwardOrigin = forward_origin,
                                                editDate = edit_date ?.asDate,
                                                hasProtectedContent = has_protected_content == true,
                                                replyInfo = replyInfo,
                                                replyMarkup = reply_markup,
                                                content = content,
                                                senderBot = via_bot,
                                                authorSignature = author_signature,
                                                mediaGroupId = media_group_id,
                                                fromOffline = is_from_offline,
                                                cost = paid_star_count,
                                            )
                                        null ->
                                            CommonForumContentMessageImpl(
                                                chat = actualForumChat,
                                                messageId = messageId,
                                                threadId = messageThreadId,
                                                from = checkedFrom ?: from ?: error("It is expected that in messages from non anonymous users and channels user must be specified"),
                                                date = date.asDate,
                                                forwardOrigin = forward_origin,
                                                editDate = edit_date ?.asDate,
                                                hasProtectedContent = has_protected_content == true,
                                                replyInfo = replyInfo,
                                                replyMarkup = reply_markup,
                                                content = content,
                                                senderBot = via_bot,
                                                mediaGroupId = media_group_id,
                                                senderBoostsCount = sender_boost_count,
                                                fromOffline = is_from_offline,
                                                cost = paid_star_count,
                                            )
                                    }
                                } else {
                                    when (sender_chat) {
                                        is PreviewChannelChat ->
                                            if (is_automatic_forward == true) {
                                                ConnectedFromChannelGroupContentMessageImpl(
                                                    chat = chat,
                                                    channel = sender_chat,
                                                    messageId = messageId,
                                                    date = date.asDate,
                                                    forwardOrigin = forward_origin,
                                                    editDate = edit_date ?.asDate,
                                                    hasProtectedContent = has_protected_content == true,
                                                    replyInfo = replyInfo,
                                                    replyMarkup = reply_markup,
                                                    content = content,
                                                    senderBot = via_bot,
                                                    authorSignature = author_signature,
                                                    mediaGroupId = media_group_id,
                                                    fromOffline = is_from_offline,
                                                    cost = paid_star_count,
                                                )
                                            } else {
                                                UnconnectedFromChannelGroupContentMessageImpl(
                                                    chat = chat,
                                                    channel = sender_chat,
                                                    messageId = messageId,
                                                    date = date.asDate,
                                                    forwardOrigin = forward_origin,
                                                    editDate = edit_date ?.asDate,
                                                    hasProtectedContent = has_protected_content == true,
                                                    replyInfo = replyInfo,
                                                    replyMarkup = reply_markup,
                                                    content = content,
                                                    senderBot = via_bot,
                                                    authorSignature = author_signature,
                                                    mediaGroupId = media_group_id,
                                                    fromOffline = is_from_offline,
                                                    cost = paid_star_count,
                                                )
                                            }
                                        is GroupChat ->
                                            AnonymousGroupContentMessageImpl(
                                                chat = chat,
                                                messageId = messageId,
                                                date = date.asDate,
                                                forwardOrigin = forward_origin,
                                                editDate = edit_date ?.asDate,
                                                hasProtectedContent = has_protected_content == true,
                                                replyInfo = replyInfo,
                                                replyMarkup = reply_markup,
                                                content = content,
                                                senderBot = via_bot,
                                                authorSignature = author_signature,
                                                mediaGroupId = media_group_id,
                                                fromOffline = is_from_offline,
                                                cost = paid_star_count,
                                            )
                                        null ->
                                            CommonGroupContentMessageImpl(
                                                chat = chat,
                                                messageId = messageId,
                                                from = checkedFrom ?: from ?: error("It is expected that in messages from non anonymous users and channels user must be specified"),
                                                date = date.asDate,
                                                forwardOrigin = forward_origin,
                                                editDate = edit_date ?.asDate,
                                                hasProtectedContent = has_protected_content == true,
                                                replyInfo = replyInfo,
                                                replyMarkup = reply_markup,
                                                content = content,
                                                senderBot = via_bot,
                                                mediaGroupId = media_group_id,
                                                senderBoostsCount = sender_boost_count,
                                                fromOffline = is_from_offline,
                                                cost = paid_star_count,
                                            )
                                    }
                                }
                            is PreviewGroupChat ->
                                when (sender_chat) {
                                    is PreviewChannelChat ->
                                        if (is_automatic_forward == true) {
                                            ConnectedFromChannelGroupContentMessageImpl(
                                                chat = chat,
                                                channel = sender_chat,
                                                messageId = messageId,
                                                date = date.asDate,
                                                forwardOrigin = forward_origin,
                                                editDate = edit_date ?.asDate,
                                                hasProtectedContent = has_protected_content == true,
                                                replyInfo = replyInfo,
                                                replyMarkup = reply_markup,
                                                content = content,
                                                senderBot = via_bot,
                                                authorSignature = author_signature,
                                                mediaGroupId = media_group_id,
                                                fromOffline = is_from_offline,
                                                cost = paid_star_count,
                                            )
                                        } else {
                                            UnconnectedFromChannelGroupContentMessageImpl(
                                                chat = chat,
                                                channel = sender_chat,
                                                messageId = messageId,
                                                date = date.asDate,
                                                forwardOrigin = forward_origin,
                                                editDate = edit_date ?.asDate,
                                                hasProtectedContent = has_protected_content == true,
                                                replyInfo = replyInfo,
                                                replyMarkup = reply_markup,
                                                content = content,
                                                senderBot = via_bot,
                                                authorSignature = author_signature,
                                                mediaGroupId = media_group_id,
                                                fromOffline = is_from_offline,
                                                cost = paid_star_count,
                                            )
                                        }
                                    is PreviewGroupChat ->
                                        AnonymousGroupContentMessageImpl(
                                            chat = chat,
                                            messageId = messageId,
                                            date = date.asDate,
                                            forwardOrigin = forward_origin,
                                            editDate = edit_date ?.asDate,
                                            hasProtectedContent = has_protected_content == true,
                                            replyInfo = replyInfo,
                                            replyMarkup = reply_markup,
                                            content = content,
                                            senderBot = via_bot,
                                            authorSignature = author_signature,
                                            mediaGroupId = media_group_id,
                                            fromOffline = is_from_offline,
                                            cost = paid_star_count,
                                        )
                                    null ->
                                        CommonGroupContentMessageImpl(
                                            chat = chat,
                                            messageId = messageId,
                                            from = checkedFrom ?: from ?: error("It is expected that in messages from non anonymous users and channels user must be specified"),
                                            date = date.asDate,
                                            forwardOrigin = forward_origin,
                                            editDate = edit_date ?.asDate,
                                            hasProtectedContent = has_protected_content == true,
                                            replyInfo = replyInfo,
                                            replyMarkup = reply_markup,
                                            content = content,
                                            senderBot = via_bot,
                                            mediaGroupId = media_group_id,
                                            senderBoostsCount = sender_boost_count,
                                            fromOffline = is_from_offline,
                                            cost = paid_star_count,
                                        )
                                }
                        }
                    is PreviewPrivateChat ->
                        if (business_connection_id == null) {
                            PrivateContentMessageImpl(
                                messageId = messageId,
                                from = checkedFrom ?: from ?: error("Was detected common message, but owner (sender) of the message was not found"),
                                chat = chat,
                                content = content,
                                date = date.asDate,
                                editDate = edit_date?.asDate,
                                hasProtectedContent = has_protected_content == true,
                                forwardOrigin = forward_origin,
                                replyInfo = replyInfo,
                                replyMarkup = reply_markup,
                                senderBot = via_bot,
                                mediaGroupId = media_group_id,
                                fromOffline = is_from_offline,
                                effectId = effect_id,
                                cost = paid_star_count,
                            )
                        } else {
                            BusinessContentMessageImpl(
                                messageId = messageId,
                                from = checkedFrom ?: from ?: error("Was detected common message, but owner (sender) of the message was not found"),
                                chat = BusinessChatImpl(
                                    chat.id.toBusinessChatId(business_connection_id),
                                    chat,
                                ),
                                businessConnectionId = business_connection_id,
                                content = content,
                                date = date.asDate,
                                editDate = edit_date?.asDate,
                                hasProtectedContent = has_protected_content == true,
                                forwardOrigin = forward_origin,
                                replyInfo = replyInfo,
                                replyMarkup = reply_markup,
                                senderBot = via_bot,
                                mediaGroupId = media_group_id,
                                senderBusinessBot = sender_business_bot,
                                fromOffline = is_from_offline,
                                cost = paid_star_count,
                            )
                        }
                    else -> error("Unknown type of chat: $chat")
                }
            } ?: passport_data ?.let {
                PassportMessage(
                    messageId = messageId,
                    chat = chat,
                    from = checkedFrom ?: from ?: error("For passport must be provided user, but got null"),
                    date = date.asDate,
                    passportData = passport_data,
                )
            } ?: error("Was not found supported type of data")
        } catch (e: Exception) {
            UnknownMessageType(
                messageId,
                chat,
                date.asDate,
                e,
            )
        }
    }

    private fun throwWrongChatEvent(
        expected: KClass<*>,
        but: ChatEvent,
    ): Nothing {
        error("Wrong type of chat event: expected $expected, but was $but")
    }
}
