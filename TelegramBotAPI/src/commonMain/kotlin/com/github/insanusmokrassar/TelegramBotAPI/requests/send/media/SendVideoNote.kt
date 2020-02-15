package com.github.insanusmokrassar.TelegramBotAPI.requests.send.media

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.base.*
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.parseModeField
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.files.PhotoSize
import com.github.insanusmokrassar.TelegramBotAPI.types.files.VideoFile
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.media.VideoNoteContent
import com.github.insanusmokrassar.TelegramBotAPI.utils.mapOfNotNull
import kotlinx.serialization.*

fun SendVideoNote(
    chatId: ChatIdentifier,
    videoNote: InputFile,
    thumb: InputFile? = null,
    caption: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    size: Int? = null, // in documentation - length (size of video side)
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<ContentMessage<VideoNoteContent>> {
    val videoNoteAsFileId = (videoNote as? FileId) ?.fileId
    val videoNoteAsFile = videoNote as? MultipartFile
    val thumbAsFileId = (thumb as? FileId) ?.fileId
    val thumbAsFile = thumb as? MultipartFile

    val data = SendVideoNoteData(
        chatId,
        videoNoteAsFileId,
        thumbAsFileId,
        caption,
        parseMode,
        duration,
        size,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )

    return if (videoNoteAsFile == null && thumbAsFile == null) {
        data
    } else {
        MultipartRequestImpl(
            data,
            SendVideoNoteFiles(videoNoteAsFile, thumbAsFile)
        )
    }
}

private val commonResultDeserializer: DeserializationStrategy<ContentMessage<VideoNoteContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

@Serializable
data class SendVideoNoteData internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(videoNoteField)
    val videoNote: String? = null,
    @SerialName(thumbField)
    override val thumb: String? = null,
    @SerialName(captionField)
    override val text: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(durationField)
    override val duration: Long? = null,
    @SerialName(lengthField)
    override val width: Int? = null,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(replyToMessageIdField)
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : DataRequest<ContentMessage<VideoNoteContent>>,
    SendMessageRequest<ContentMessage<VideoNoteContent>>,
    ReplyingMarkupSendMessageRequest<ContentMessage<VideoNoteContent>>,
    TextableSendMessageRequest<ContentMessage<VideoNoteContent>>,
    ThumbedSendMessageRequest<ContentMessage<VideoNoteContent>>,
    DuratedSendMessageRequest<ContentMessage<VideoNoteContent>>,
    SizedSendMessageRequest<ContentMessage<VideoNoteContent>>
{
    override val height: Int?
        get() = width

    init {
        text ?.let {
            if (it.length !in captionLength) {
                throw IllegalArgumentException("Caption must be in $captionLength range")
            }
        }
    }

    override fun method(): String = "sendVideoNote"
    override val resultDeserializer: DeserializationStrategy<ContentMessage<VideoNoteContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

data class SendVideoNoteFiles internal constructor(
    val videoNote: MultipartFile? = null,
    val thumb: MultipartFile? = null
) : Files by mapOfNotNull(
    videoNoteField to videoNote,
    thumbField to thumb
)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendVideoNote(
    chatId: ChatIdentifier,
    videoNote: FileId,
    thumb: FileId? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    size: Int? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendVideoNoteData(
        chatId,
        videoNote.fileId,
        thumb ?.fileId,
        text,
        parseMode,
        duration,
        size,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )
)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendVideoNote(
    chatId: ChatIdentifier,
    videoNote: VideoFile,
    thumb: PhotoSize? = videoNote.thumb,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideoNote(
    chatId, videoNote.fileId, thumb ?.fileId, text, parseMode, videoNote.duration, videoNote.width, disableNotification, replyToMessageId, replyMarkup
)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendVideoNote(
    chatId: ChatIdentifier,
    videoNote: MultipartFile,
    thumb: FileId? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    size: Int? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    MultipartRequestImpl(
        SendVideoNoteData(
            chatId, null, thumb ?.fileId, text, parseMode, duration, size, disableNotification, replyToMessageId, replyMarkup
        ),
        SendVideoNoteFiles(videoNote)
    )
)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendVideoNote(
    chatId: ChatIdentifier,
    videoNote: MultipartFile,
    thumb: MultipartFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    size: Int? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    MultipartRequestImpl(
        SendVideoNoteData(
            chatId, null, null, text, parseMode, duration, size, disableNotification, replyToMessageId, replyMarkup
        ),
        SendVideoNoteFiles(videoNote, thumb)
    )
)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendVideoNote(
    chatId: ChatIdentifier,
    videoNote: FileId,
    thumb: MultipartFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    size: Int? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    MultipartRequestImpl(
        SendVideoNoteData(
            chatId, videoNote.fileId, null, text, parseMode, duration, size, disableNotification, replyToMessageId, replyMarkup
        ),
        SendVideoNoteFiles(null, thumb)
    )
)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendVideoNote(
    chatId: ChatIdentifier,
    videoNote: MultipartFile,
    thumb: PhotoSize? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    size: Int? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideoNote(
    chatId, videoNote, thumb ?.fileId , text, parseMode, duration, size, disableNotification, replyToMessageId, replyMarkup
)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendVideoNote(
    chatId: ChatIdentifier,
    videoNote: VideoFile,
    thumb: MultipartFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideoNote(
    chatId, videoNote.fileId, thumb, text, parseMode, videoNote.duration, videoNote.width, disableNotification, replyToMessageId, replyMarkup
)
