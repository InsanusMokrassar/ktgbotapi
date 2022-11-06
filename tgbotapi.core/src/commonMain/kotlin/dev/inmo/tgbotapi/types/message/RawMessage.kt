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
import dev.inmo.tgbotapi.types.location.Location
import dev.inmo.tgbotapi.types.message.ChatEvents.*
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.*
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
    private val chat: Chat,
    @SerialName(messageThreadIdField)
    private val messageThreadId: MessageThreadId? = null,
    private val from: User? = null,
    private val sender_chat: PublicChat? = null,
    private val forward_from: User? = null,
    private val forward_from_chat: Chat? = null,
    private val forward_from_message_id: MessageId? = null,
    private val forward_signature: ForwardSignature? = null,
    private val forward_sender_name: ForwardSenderName? = null,
    private val forward_date: TelegramDate? = null,
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
    private val migrate_to_chat_id: ChatId? = null,
    private val migrate_from_chat_id: ChatId? = null,
    private val pinned_message: RawMessage? = null,
    private val invoice: Invoice? = null,
    private val dice: Dice? = null,
    private val successful_payment: SuccessfulPayment? = null,

    // Voice Chat Service Messages
    private val video_chat_scheduled: VideoChatScheduled? = null,
    private val video_chat_started: VideoChatStarted? = null,
    private val video_chat_ended: VideoChatEnded? = null,
    private val video_chat_participants_invited: VideoChatParticipantsInvited? = null,

    // AutoDelete Message time changed
    private val message_auto_delete_timer_changed: MessageAutoDeleteTimerChanged? = null,

    // login property
    private val connected_website: String? = null,

    // login property
    private val web_app_data: WebAppData? = null,

    // passport property
    private val passport_data: PassportData? = null,
    private val proximity_alert_triggered: ProximityAlertTriggered? = null,

    private val reply_markup: InlineKeyboardMarkup? = null
) {
    private val content: MessageContent? by lazy {
        val adaptedCaptionEntities = caption ?.let {
            (caption_entities ?: emptyList()).asTextSources(caption)
        } ?: emptyList()

        when {
            text != null -> TextContent(text, (entities ?: emptyList()).asTextSources(text))
            audio != null -> AudioContent(
                audio,
                caption,
                adaptedCaptionEntities
            )
            video != null -> VideoContent(
                video,
                caption,
                adaptedCaptionEntities
            )
            animation != null -> AnimationContent(
                animation,
                document,
                caption,
                adaptedCaptionEntities
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
                adaptedCaptionEntities
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

    private val forwarded: ForwardInfo? by lazy {
        forward_date
            ?: return@lazy null // According to the documentation, now any forwarded message contains this field
        when {
            forward_sender_name != null -> ForwardInfo.ByAnonymous(
                forward_date,
                forward_sender_name
            )

            forward_from_chat is ChannelChat -> if (forward_from_message_id == null) {
                ForwardInfo.PublicChat.SentByChannel(
                    forward_date,
                    forward_from_chat,
                    forward_signature
                )
            } else {
                ForwardInfo.PublicChat.FromChannel(
                    forward_date,
                    forward_from_message_id,
                    forward_from_chat,
                    forward_signature
                )
            }

            forward_from_chat is SupergroupChat -> ForwardInfo.PublicChat.FromSupergroup(
                forward_date,
                forward_from_chat
            )

            forward_from != null -> ForwardInfo.ByUser(
                forward_date,
                forward_from
            )

            else -> null
        }
    }

    private val chatEvent: ChatEvent? by lazy {
        when {
            new_chat_members != null -> NewChatMembers(new_chat_members.toList())
            left_chat_member != null -> LeftChatMember(left_chat_member)
            new_chat_title != null -> NewChatTitle(new_chat_title)
            new_chat_photo != null -> NewChatPhoto(new_chat_photo.toList())
            video_chat_started != null -> video_chat_started
            video_chat_scheduled != null -> video_chat_scheduled
            message_auto_delete_timer_changed != null -> message_auto_delete_timer_changed
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
            else -> null
        }
    }

    val asMessage: Message by lazy {
        try {
            chatEvent?.let { chatEvent ->
                when (chat) {
                    is SupergroupChat -> CommonSupergroupEventMessage(
                        messageId,
                        from ?: error("Supergroup events are expected to contain 'from' field"),
                        chat,
                        chatEvent as? SupergroupEvent ?: throwWrongChatEvent(SupergroupEvent::class, chatEvent),
                        date.asDate
                    )
                    is GroupChat -> CommonGroupEventMessage(
                        messageId,
                        from ?: error("Supergroup events are expected to contain 'from' field"),
                        chat,
                        chatEvent as? GroupEvent ?: throwWrongChatEvent(GroupChat::class, chatEvent),
                        date.asDate
                    )
                    is ChannelChat -> ChannelEventMessage(
                        messageId,
                        chat,
                        chatEvent as? ChannelEvent ?: throwWrongChatEvent(ChannelEvent::class, chatEvent),
                        date.asDate
                    )
                    is PrivateChat -> PrivateEventMessage(
                        messageId,
                        chat,
                        chatEvent as? PrivateEvent ?: throwWrongChatEvent(PrivateEvent::class, chatEvent),
                        date.asDate
                    )
                    else -> error("Expected one of the public chats, but was $chat (in extracting of chat event message)")
                }
            } ?: content?.let { content ->
                media_group_id?.let {
                    val checkedContent = when (content) {
                        is PhotoContent -> content
                        is VideoContent -> content
                        is AudioContent -> content
                        is DocumentContent -> content
                        else -> error("Unsupported content for media group")
                    }
                    when (from) {
                        null -> ChannelMediaGroupMessage(
                            messageId,
                            chat,
                            date.asDate,
                            it,
                            checkedContent,
                            edit_date?.asDate,
                            has_protected_content == true,
                            forwarded,
                            reply_to_message?.asMessage,
                            reply_markup
                        )
                        else -> CommonMediaGroupMessage(
                            messageId,
                            from,
                            chat,
                            date.asDate,
                            it,
                            checkedContent,
                            edit_date?.asDate,
                            has_protected_content == true,
                            forwarded,
                            reply_to_message?.asMessage,
                            reply_markup
                        )
                    }
                } ?: when (chat) {
                    is PublicChat -> when (chat) {
                        is ChannelChat -> ChannelContentMessageImpl(
                            messageId,
                            chat,
                            content,
                            date.asDate,
                            edit_date?.asDate,
                            has_protected_content == true,
                            forwarded,
                            reply_to_message?.asMessage,
                            reply_markup,
                            via_bot,
                            author_signature
                        )
                        is ForumChat -> if (messageThreadId != null) {
                            when (sender_chat) {
                                is ChannelChat -> FromChannelForumContentMessageImpl(
                                    chat,
                                    sender_chat,
                                    messageId,
                                    messageThreadId,
                                    date.asDate,
                                    forwarded,
                                    edit_date ?.asDate,
                                    has_protected_content == true,
                                    reply_to_message ?.asMessage,
                                    reply_markup,
                                    content,
                                    via_bot,
                                    author_signature
                                )
                                is GroupChat -> AnonymousForumContentMessageImpl(
                                    chat,
                                    messageId,
                                    messageThreadId,
                                    date.asDate,
                                    forwarded,
                                    edit_date ?.asDate,
                                    has_protected_content == true,
                                    reply_to_message ?.asMessage,
                                    reply_markup,
                                    content,
                                    via_bot,
                                    author_signature
                                )
                                null -> CommonForumContentMessageImpl(
                                    chat,
                                    messageId,
                                    messageThreadId,
                                    from ?: error("It is expected that in messages from non anonymous users and channels user must be specified"),
                                    date.asDate,
                                    forwarded,
                                    edit_date ?.asDate,
                                    has_protected_content == true,
                                    reply_to_message ?.asMessage,
                                    reply_markup,
                                    content,
                                    via_bot
                                )
                            }
                        } else {
                            when (sender_chat) {
                                is ChannelChat -> if (is_automatic_forward == true) {
                                    ConnectedFromChannelGroupContentMessageImpl(
                                        chat,
                                        sender_chat,
                                        messageId,
                                        date.asDate,
                                        forwarded,
                                        edit_date ?.asDate,
                                        has_protected_content == true,
                                        reply_to_message ?.asMessage,
                                        reply_markup,
                                        content,
                                        via_bot,
                                        author_signature
                                    )
                                } else {
                                    UnconnectedFromChannelGroupContentMessageImpl(
                                        chat,
                                        sender_chat,
                                        messageId,
                                        date.asDate,
                                        forwarded,
                                        edit_date ?.asDate,
                                        has_protected_content == true,
                                        reply_to_message ?.asMessage,
                                        reply_markup,
                                        content,
                                        via_bot,
                                        author_signature
                                    )
                                }
                                is GroupChat -> AnonymousGroupContentMessageImpl(
                                    chat,
                                    messageId,
                                    date.asDate,
                                    forwarded,
                                    edit_date ?.asDate,
                                    has_protected_content == true,
                                    reply_to_message ?.asMessage,
                                    reply_markup,
                                    content,
                                    via_bot,
                                    author_signature
                                )
                                null -> CommonGroupContentMessageImpl(
                                    chat,
                                    messageId,
                                    from ?: error("It is expected that in messages from non anonymous users and channels user must be specified"),
                                    date.asDate,
                                    forwarded,
                                    edit_date ?.asDate,
                                    has_protected_content == true,
                                    reply_to_message ?.asMessage,
                                    reply_markup,
                                    content,
                                    via_bot
                                )
                            }
                        }
                        is GroupChat -> when (sender_chat) {
                            is ChannelChat -> if (is_automatic_forward == true) {
                                ConnectedFromChannelGroupContentMessageImpl(
                                    chat,
                                    sender_chat,
                                    messageId,
                                    date.asDate,
                                    forwarded,
                                    edit_date ?.asDate,
                                    has_protected_content == true,
                                    reply_to_message ?.asMessage,
                                    reply_markup,
                                    content,
                                    via_bot,
                                    author_signature
                                )
                            } else {
                                UnconnectedFromChannelGroupContentMessageImpl(
                                    chat,
                                    sender_chat,
                                    messageId,
                                    date.asDate,
                                    forwarded,
                                    edit_date ?.asDate,
                                    has_protected_content == true,
                                    reply_to_message ?.asMessage,
                                    reply_markup,
                                    content,
                                    via_bot,
                                    author_signature
                                )
                            }
                            is GroupChat -> AnonymousGroupContentMessageImpl(
                                chat,
                                messageId,
                                date.asDate,
                                forwarded,
                                edit_date ?.asDate,
                                has_protected_content == true,
                                reply_to_message ?.asMessage,
                                reply_markup,
                                content,
                                via_bot,
                                author_signature
                            )
                            null -> CommonGroupContentMessageImpl(
                                chat,
                                messageId,
                                from ?: error("It is expected that in messages from non anonymous users and channels user must be specified"),
                                date.asDate,
                                forwarded,
                                edit_date ?.asDate,
                                has_protected_content == true,
                                reply_to_message ?.asMessage,
                                reply_markup,
                                content,
                                via_bot
                            )
                        }
                    }
                    is PrivateChat -> PrivateContentMessageImpl(
                        messageId,
                        from ?: error("Was detected common message, but owner (sender) of the message was not found"),
                        chat,
                        content,
                        date.asDate,
                        edit_date?.asDate,
                        has_protected_content == true,
                        forwarded,
                        reply_to_message?.asMessage,
                        reply_markup,
                        via_bot
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
