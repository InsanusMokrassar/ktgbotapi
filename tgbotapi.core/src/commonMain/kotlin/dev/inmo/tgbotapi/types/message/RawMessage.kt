package dev.inmo.tgbotapi.types.message

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.*
import dev.inmo.tgbotapi.types.chat.CommonBot
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.dice.Dice
import dev.inmo.tgbotapi.types.files.*
import dev.inmo.tgbotapi.types.files.Sticker
import dev.inmo.tgbotapi.types.games.RawGame
import dev.inmo.tgbotapi.types.giveaway.Giveaway
import dev.inmo.tgbotapi.types.giveaway.GiveawayCreated
import dev.inmo.tgbotapi.types.giveaway.GiveawayPrivateResults
import dev.inmo.tgbotapi.types.giveaway.GiveawayResults
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
import dev.inmo.tgbotapi.types.message.content.InvoiceContent
import dev.inmo.tgbotapi.types.message.content.MessageContent
import dev.inmo.tgbotapi.types.message.payments.*
import dev.inmo.tgbotapi.types.passport.PassportData
import dev.inmo.tgbotapi.types.payments.Invoice
import dev.inmo.tgbotapi.types.payments.SuccessfulPayment
import dev.inmo.tgbotapi.types.polls.Poll
import dev.inmo.tgbotapi.types.request.ChatShared
import dev.inmo.tgbotapi.types.request.UsersShared
import dev.inmo.tgbotapi.types.stories.Story
import dev.inmo.tgbotapi.types.venue.Venue
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
    private val from: User? = null,
    private val sender_chat: PreviewPublicChat? = null,
    private val forward_origin: MessageOrigin? = null,
    private val is_topic_message: Boolean? = null,
    private val is_automatic_forward: Boolean? = null,
    private val reply_to_message: RawMessage? = null,
    private val via_bot: CommonBot? = null,
    private val edit_date: TelegramDate? = null,
    private val has_protected_content: Boolean? = null,
    private val media_group_id: MediaGroupIdentifier? = null,
    private val author_signature: AuthorSignature? = null,
    private val text: String? = null,
    private val entities: RawMessageEntities? = null,
    private val caption: String? = null,
    private val caption_entities: RawMessageEntities? = null,
    private val has_media_spoiler: Boolean? = null,
    private val story: Story? = null,
    private val audio: AudioFile? = null,
    private val document: DocumentFile? = null,
    private val animation: AnimationFile? = null,
    private val game: RawGame? = null,
    @Serializable(PhotoSerializer::class)
    private val photo: Photo? = null,
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
    private val new_chat_photo: Photo? = null,
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

    private val users_shared: UsersShared? = null,
    private val chat_shared: ChatShared? = null,

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

    private val reply_markup: InlineKeyboardMarkup? = null,

    // Giveaways
    private val giveaway_created: GiveawayCreated? = null,
    private val giveaway: Giveaway? = null,
    private val giveaway_winners: GiveawayResults? = null,
    private val giveaway_completed: GiveawayPrivateResults? = null,
) {
    private val content: MessageContent? by lazy {
        val adaptedCaptionEntities = caption ?.let {
            (caption_entities ?: emptyList()).asTextSources(caption)
        } ?: emptyList()

        when {
            story != null -> StoryContent(
                chat,
                messageId,
                story
            )
            text != null -> TextContent(text, (entities ?: emptyList()).asTextSources(text), link_preview_options)
            audio != null -> AudioContent(
                audio,
                caption,
                adaptedCaptionEntities
            )
            video != null -> VideoContent(
                video,
                caption,
                adaptedCaptionEntities,
                has_media_spoiler ?: false
            )
            animation != null -> AnimationContent(
                animation,
                document,
                caption,
                adaptedCaptionEntities,
                has_media_spoiler ?: false
            )
            document != null -> DocumentContent(
                document,
                caption,
                adaptedCaptionEntities
            )
            voice != null -> VoiceContent(
                voice,
                caption,
                adaptedCaptionEntities
            )
            photo != null -> PhotoContent(
                photo.toList(),
                caption,
                adaptedCaptionEntities,
                has_media_spoiler ?: false
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
            else -> null
        }
    }

    private val chatEvent: ChatEvent? by lazy {
        when {
            new_chat_members != null -> NewChatMembers(new_chat_members.toList())
            left_chat_member != null -> LeftChatMemberEvent(left_chat_member)
            new_chat_title != null -> NewChatTitle(new_chat_title)
            new_chat_photo != null -> NewChatPhoto(new_chat_photo.toList())
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
            group_chat_created -> GroupChatCreated(
                migrate_to_chat_id
            )

            supergroup_chat_created -> SupergroupChatCreated(
                migrate_from_chat_id
            )

            migrate_from_chat_id != null -> MigratedToSupergroup(
                migrate_from_chat_id
            )

            channel_chat_created -> ChannelChatCreated()
            pinned_message != null -> PinnedMessage(pinned_message.asMessage)
            proximity_alert_triggered != null -> proximity_alert_triggered
            successful_payment != null -> SuccessfulPaymentEvent(successful_payment)
            connected_website != null -> UserLoggedIn(connected_website)
            web_app_data != null -> web_app_data
            users_shared != null -> users_shared
            chat_shared != null -> chat_shared
            giveaway_created != null -> giveaway_created
            giveaway != null -> giveaway
            giveaway_winners != null -> giveaway_winners
            giveaway_completed != null -> giveaway_completed
            else -> null
        }
    }

    val asMessage: AccessibleMessage by lazy {
        if (date.date == 0L) {
            return@lazy InaccessibleMessage(
                chat,
                messageId
            )
        }

        try {
            chatEvent?.let { chatEvent ->
                when (chat) {
                    is PreviewSupergroupChat -> CommonSupergroupEventMessage(
                        messageId,
                        from ?: error("Supergroup events are expected to contain 'from' field"),
                        chat,
                        chatEvent as? SupergroupEvent ?: throwWrongChatEvent(SupergroupEvent::class, chatEvent),
                        date.asDate
                    )
                    is PreviewGroupChat -> CommonGroupEventMessage(
                        messageId,
                        from ?: error("Supergroup events are expected to contain 'from' field"),
                        chat,
                        chatEvent as? GroupEvent ?: throwWrongChatEvent(GroupChat::class, chatEvent),
                        date.asDate
                    )
                    is PreviewChannelChat -> ChannelEventMessage(
                        messageId,
                        chat,
                        chatEvent as? ChannelEvent ?: throwWrongChatEvent(ChannelEvent::class, chatEvent),
                        date.asDate
                    )
                    is PreviewPrivateChat -> PrivateEventMessage(
                        messageId,
                        chat,
                        chatEvent as? PrivateEvent ?: throwWrongChatEvent(PrivateEvent::class, chatEvent),
                        date.asDate
                    )
                    else -> error("Expected one of the public chats, but was $chat (in extracting of chat event message)")
                }
            } ?: content?.let { content -> when (chat) {
                    is PreviewPublicChat -> when (chat) {
                        is PreviewChannelChat -> ChannelContentMessageImpl(
                            messageId,
                            chat,
                            content,
                            date.asDate,
                            edit_date?.asDate,
                            has_protected_content == true,
                            forward_origin,
                            reply_to_message?.asMessage,
                            reply_markup,
                            via_bot,
                            author_signature,
                            media_group_id
                        )
                        is PreviewForumChat -> if (messageThreadId != null) {
                            val chatId = ChatIdWithThreadId(
                                chat.id.chatId,
                                messageThreadId
                            )
                            val actualForumChat = when (chat) {
                                is ForumChatImpl -> chat.copy(id = chatId)
                            }
                            when (sender_chat) {
                                is PreviewChannelChat -> FromChannelForumContentMessageImpl(
                                    actualForumChat,
                                    sender_chat,
                                    messageId,
                                    messageThreadId,
                                    date.asDate,
                                    forward_origin,
                                    edit_date ?.asDate,
                                    has_protected_content == true,
                                    reply_to_message ?.asMessage,
                                    reply_markup,
                                    content,
                                    via_bot,
                                    author_signature,
                                    media_group_id
                                )
                                is PreviewGroupChat -> AnonymousForumContentMessageImpl(
                                    actualForumChat,
                                    messageId,
                                    messageThreadId,
                                    date.asDate,
                                    forward_origin,
                                    edit_date ?.asDate,
                                    has_protected_content == true,
                                    reply_to_message ?.asMessage,
                                    reply_markup,
                                    content,
                                    via_bot,
                                    author_signature,
                                    media_group_id
                                )
                                null -> CommonForumContentMessageImpl(
                                    actualForumChat,
                                    messageId,
                                    messageThreadId,
                                    from ?: error("It is expected that in messages from non anonymous users and channels user must be specified"),
                                    date.asDate,
                                    forward_origin,
                                    edit_date ?.asDate,
                                    has_protected_content == true,
                                    reply_to_message ?.asMessage,
                                    reply_markup,
                                    content,
                                    via_bot,
                                    media_group_id
                                )
                            }
                        } else {
                            when (sender_chat) {
                                is PreviewChannelChat -> if (is_automatic_forward == true) {
                                    ConnectedFromChannelGroupContentMessageImpl(
                                        chat,
                                        sender_chat,
                                        messageId,
                                        date.asDate,
                                        forward_origin,
                                        edit_date ?.asDate,
                                        has_protected_content == true,
                                        reply_to_message ?.asMessage,
                                        reply_markup,
                                        content,
                                        via_bot,
                                        author_signature,
                                        media_group_id
                                    )
                                } else {
                                    UnconnectedFromChannelGroupContentMessageImpl(
                                        chat,
                                        sender_chat,
                                        messageId,
                                        date.asDate,
                                        forward_origin,
                                        edit_date ?.asDate,
                                        has_protected_content == true,
                                        reply_to_message ?.asMessage,
                                        reply_markup,
                                        content,
                                        via_bot,
                                        author_signature,
                                        media_group_id
                                    )
                                }
                                is GroupChat -> AnonymousGroupContentMessageImpl(
                                    chat,
                                    messageId,
                                    date.asDate,
                                    forward_origin,
                                    edit_date ?.asDate,
                                    has_protected_content == true,
                                    reply_to_message ?.asMessage,
                                    reply_markup,
                                    content,
                                    via_bot,
                                    author_signature,
                                    media_group_id
                                )
                                null -> CommonGroupContentMessageImpl(
                                    chat,
                                    messageId,
                                    from ?: error("It is expected that in messages from non anonymous users and channels user must be specified"),
                                    date.asDate,
                                    forward_origin,
                                    edit_date ?.asDate,
                                    has_protected_content == true,
                                    reply_to_message ?.asMessage,
                                    reply_markup,
                                    content,
                                    via_bot,
                                    media_group_id
                                )
                            }
                        }
                        is PreviewGroupChat -> when (sender_chat) {
                            is PreviewChannelChat -> if (is_automatic_forward == true) {
                                ConnectedFromChannelGroupContentMessageImpl(
                                    chat,
                                    sender_chat,
                                    messageId,
                                    date.asDate,
                                    forward_origin,
                                    edit_date ?.asDate,
                                    has_protected_content == true,
                                    reply_to_message ?.asMessage,
                                    reply_markup,
                                    content,
                                    via_bot,
                                    author_signature,
                                    media_group_id
                                )
                            } else {
                                UnconnectedFromChannelGroupContentMessageImpl(
                                    chat,
                                    sender_chat,
                                    messageId,
                                    date.asDate,
                                    forward_origin,
                                    edit_date ?.asDate,
                                    has_protected_content == true,
                                    reply_to_message ?.asMessage,
                                    reply_markup,
                                    content,
                                    via_bot,
                                    author_signature,
                                    media_group_id
                                )
                            }
                            is PreviewGroupChat -> AnonymousGroupContentMessageImpl(
                                chat,
                                messageId,
                                date.asDate,
                                forward_origin,
                                edit_date ?.asDate,
                                has_protected_content == true,
                                reply_to_message ?.asMessage,
                                reply_markup,
                                content,
                                via_bot,
                                author_signature,
                                media_group_id
                            )
                            null -> CommonGroupContentMessageImpl(
                                chat,
                                messageId,
                                from ?: error("It is expected that in messages from non anonymous users and channels user must be specified"),
                                date.asDate,
                                forward_origin,
                                edit_date ?.asDate,
                                has_protected_content == true,
                                reply_to_message ?.asMessage,
                                reply_markup,
                                content,
                                via_bot,
                                media_group_id
                            )
                        }
                    }
                    is PreviewPrivateChat -> PrivateContentMessageImpl(
                        messageId,
                        from ?: error("Was detected common message, but owner (sender) of the message was not found"),
                        chat,
                        content,
                        date.asDate,
                        edit_date?.asDate,
                        has_protected_content == true,
                        forward_origin,
                        reply_to_message?.asMessage,
                        reply_markup,
                        via_bot,
                        media_group_id
                    )
                    else -> error("Unknown type of chat: $chat")
                }
            } ?: passport_data ?.let{
                PassportMessage(
                    messageId,
                    chat,
                    from ?: error("For passport must be provided user, but got null"),
                    date.asDate,
                    passport_data
                )
            } ?: error("Was not found supported type of data")
        } catch (e: Exception) {
            UnknownMessageType(
                messageId,
                chat,
                date.asDate,
                e
            )
        }
    }

    private fun throwWrongChatEvent(expected: KClass<*>, but: ChatEvent): Nothing {
        error("Wrong type of chat event: expected $expected, but was $but")
    }
}
