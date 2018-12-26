package com.github.insanusmokrassar.TelegramBotAPI.types.message

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.*
import com.github.insanusmokrassar.TelegramBotAPI.types.files.*
import com.github.insanusmokrassar.TelegramBotAPI.types.games.Game
import com.github.insanusmokrassar.TelegramBotAPI.types.message.ChatEvents.*
import com.github.insanusmokrassar.TelegramBotAPI.types.message.ChatEvents.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.Message
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.*
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MessageContent
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.media.*
import com.github.insanusmokrassar.TelegramBotAPI.types.message.payments.InvoiceOfPayment
import com.github.insanusmokrassar.TelegramBotAPI.types.message.payments.SuccessfulPaymentInfo
import com.github.insanusmokrassar.TelegramBotAPI.types.message.payments.abstracts.PaymentInfo
import com.github.insanusmokrassar.TelegramBotAPI.types.payments.Invoice
import com.github.insanusmokrassar.TelegramBotAPI.types.payments.SuccessfulPayment
import kotlinx.serialization.*
import kotlin.reflect.KClass

// TODO:: add PassportData type
@Serializable
data class RawMessage(
    @SerialName(messageIdField)
    val messageId: MessageIdentifier,
    @SerialName(dateField)
    val date: TelegramDate,
    @SerialName(chatField)
    private val chat: RawChat,
    @SerialName(fromField)
    @Optional
    private val from: User? = null,
    @Optional private val forward_from: User? = null,
    @Optional private val forward_from_chat: RawChat? = null,
    @Optional private val forward_from_message_id: MessageIdentifier? = null,
    @Optional private val forward_signature: ForwardSignature? = null,
    @Optional private val forward_date: TelegramDate? = null,
    @Optional private val reply_to_message: RawMessage? = null,
    @Optional private val edit_date: TelegramDate? = null,
    @Optional private val media_group_id: MediaGroupIdentifier? = null,
    @Optional private val author_signature: AuthorSignature? = null,
    @Optional private val text: String? = null,
    @Serializable(RawMessageEntitiesSerializer::class)
    @Optional private val entities: RawMessageEntities? = null,
    @Optional private val caption: String? = null,
    @Serializable(RawMessageEntitiesSerializer::class)
    @Optional private val caption_entities: RawMessageEntities? = null,
    @Optional private val audio: AudioFile? = null,
    @Optional private val document: DocumentFile? = null,
    @Optional private val animation: AnimationFile? = null,
    @Optional private val game: Game? = null,
    @Serializable(PhotoSerializer::class)
    @Optional private val photo: Photo? = null,
    @Optional private val sticker: Sticker? = null,
    @Optional private val video: VideoFile? = null,
    @Optional private val voice: VoiceFile? = null,
    @Optional private val video_note: VideoNoteFile? = null,
    @Optional private val contact: Contact? = null,
    @Optional private val location: Location? = null,
    @Optional private val venue: Venue? = null,
    @Optional private val new_chat_members: Array<User>? = null,
    @Optional private val left_chat_member: User? = null,
    @Optional private val new_chat_title: String? = null,
    @Serializable(PhotoSerializer::class)
    @Optional private val new_chat_photo: Photo? = null,
    @Optional private val delete_chat_photo: Boolean = false,
    @Optional private val group_chat_created: Boolean = false,
    @Optional private val supergroup_chat_created: Boolean = false,
    @Optional private val channel_chat_created: Boolean = false,
    @Optional private val migrate_to_chat_id: ChatIdentifier? = null,
    @Optional private val migrate_from_chat_id: ChatIdentifier? = null,
    @Optional private val pinned_message: RawMessage? = null,
    @Optional private val invoice: Invoice? = null,
    @Optional private val successful_payment: SuccessfulPayment? = null,

    // login property
    @Optional private val connected_website: String? = null,

    // passport property
    @Optional private val passport_data: Unit? = null
) {
    @Transient
    private val content: MessageContent? by lazy {
        val adaptedCaptionEntities = caption ?.let { _ ->
            caption_entities ?.map {
                it.asMessageEntity(caption)
            }
        } ?: emptyList()

        when {
            text != null -> TextContent(text, entities ?.map { it.asMessageEntity(text) } ?: emptyList())
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
            animation != null -> AnimationContent(animation, document)
            sticker != null -> StickerContent(sticker)
            game != null -> GameContent(game)
            video_note != null -> VideoNoteContent(video_note)
            contact != null -> ContactContent(contact)
            location != null -> LocationContent(location)
            venue != null -> VenueContent(venue)
            else -> null
        }
    }

    @Transient
    private val forwarded: ForwardedMessage? by lazy {
        forward_from_message_id ?.let {
            forward_date ?: throw IllegalStateException("For forwarded messages date of original message declared as set up required")
            forward_from_chat ?.let {
                ForwardedFromChannelMessage(
                    forward_from_message_id,
                    forward_date,
                    forward_from,
                    forward_from_chat.extractChat(),
                    forward_signature
                )
            } ?: CommonForwardedMessage(
                forward_from_message_id,
                forward_date,
                forward_from
                    ?: throw IllegalStateException("For common forwarded messages author of original message declared as set up required")
            )
        }
    }

    @Transient
    private val chatEvent: ChatEvent? by lazy {
        when {
            new_chat_members != null -> NewChatMembers(new_chat_members.toList())
            left_chat_member != null -> LeftChatMember(left_chat_member)
            new_chat_title != null -> NewChatTitle(new_chat_title)
            new_chat_photo != null -> NewChatPhoto(new_chat_photo.toList())
            delete_chat_photo -> DeleteChatPhoto()
            group_chat_created -> GroupChatCreated(
                migrate_to_chat_id
            )
            supergroup_chat_created -> SupergroupChatCreated(
                migrate_from_chat_id
            )
            channel_chat_created -> ChannelChatCreated()
            pinned_message != null -> PinnedMessage(pinned_message.asMessage)
            else -> null
        }
    }

    @Transient
    private val paymentInfo: PaymentInfo? by lazy {
        when {
            invoice != null -> InvoiceOfPayment(invoice)
            successful_payment != null -> SuccessfulPaymentInfo(successful_payment)
            else -> null
        }
    }



    @Transient
    val asMessage: Message by lazy {
        val chat = chat.extractChat()

        chatEvent ?.let {
            chatEvent ->
            when (chat) {
                is GroupChat -> GroupEventMessage(
                    messageId,
                    chat,
                    chatEvent as? GroupEvent ?: throwWrongChatEvent(GroupChat::class, chatEvent),
                    date.asDate
                )
                is SupergroupChat -> SupergroupEventMessage(
                    messageId,
                    chat,
                    chatEvent as? SupergroupEvent ?: throwWrongChatEvent(SupergroupEvent::class, chatEvent),
                    date.asDate
                )
                is ChannelChat -> ChannelEventMessage(
                    messageId,
                    chat,
                    chatEvent as? ChannelEvent ?: throwWrongChatEvent(ChannelEvent::class, chatEvent),
                    date.asDate
                )
                else -> throw IllegalStateException("Expected one of the public chats, but was $chat (in extracting of chat event message)")
            }
        } ?: content ?.let {
            content ->
            when (chat) {
                is ChannelChat -> ChannelMessage(
                    messageId,
                    chat,
                    content,
                    date.asDate,
                    edit_date ?.asDate,
                    forwarded,
                    reply_to_message ?.asMessage,
                    author_signature
                )
                else -> CommonMessageImpl(
                    messageId,
                    from ?: throw IllegalStateException("Was detected common message, but owner (sender) of the message was not found"),
                    chat,
                    content,
                    date.asDate,
                    edit_date ?.asDate,
                    forwarded,
                    reply_to_message ?.asMessage,
                    paymentInfo
                )
            }
        } ?: throw IllegalStateException("Was not found supported type of data")
    }

    private fun throwWrongChatEvent(expected: KClass<*>, but: ChatEvent): CommonEvent {
        throw IllegalStateException("Wrong type of chat event: expected $expected, but was $but")
    }
}
