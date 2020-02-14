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
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.media.VideoContent
import com.github.insanusmokrassar.TelegramBotAPI.utils.mapOfNotNull
import kotlinx.serialization.*

fun SendVideo(
    chatId: ChatIdentifier,
    video: InputFile,
    thumb: InputFile? = null,
    caption: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    supportStreaming: Boolean? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<ContentMessage<VideoContent>> {
    val videoAsFileId = (video as? FileId) ?.fileId
    val videoAsFile = video as? MultipartFile
    val thumbAsFileId = (thumb as? FileId) ?.fileId
    val thumbAsFile = thumb as? MultipartFile

    val data = SendVideoData(
        chatId,
        videoAsFileId,
        thumbAsFileId,
        caption,
        parseMode,
        duration,
        width,
        height,
        supportStreaming,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )

    return if (videoAsFile == null && thumbAsFile == null) {
        data
    } else {
        MultipartRequestImpl(
            data,
            SendVideoFiles(videoAsFile, thumbAsFile)
        )
    }
}

private val commonResultDeserializer: DeserializationStrategy<ContentMessage<VideoContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

@Serializable
data class SendVideoData internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(videoField)
    val video: String? = null,
    @SerialName(thumbField)
    override val thumb: String? = null,
    @SerialName(captionField)
    override val text: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(durationField)
    override val duration: Long? = null,
    @SerialName(widthField)
    override val width: Int? = null,
    @SerialName(heightField)
    override val height: Int? = null,
    @SerialName(supportStreamingField)
    val supportStreaming: Boolean? = null,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(replyToMessageIdField)
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : DataRequest<ContentMessage<VideoContent>>,
    SendMessageRequest<ContentMessage<VideoContent>>,
    ReplyingMarkupSendMessageRequest<ContentMessage<VideoContent>>,
    TextableSendMessageRequest<ContentMessage<VideoContent>>,
    ThumbedSendMessageRequest<ContentMessage<VideoContent>>,
    DuratedSendMessageRequest<ContentMessage<VideoContent>>,
    SizedSendMessageRequest<ContentMessage<VideoContent>>
{
    init {
        text ?.let {
            if (it.length !in captionLength) {
                throw IllegalArgumentException("Caption must be in $captionLength range")
            }
        }
    }

    override fun method(): String = "sendVideo"
    override val resultDeserializer: DeserializationStrategy<ContentMessage<VideoContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

data class SendVideoFiles internal constructor(
    val video: MultipartFile? = null,
    val thumb: MultipartFile? = null
) : Files by mapOfNotNull(
    videoField to video,
    thumbField to thumb
)

suspend fun RequestsExecutor.sendVideo(
    chatId: ChatIdentifier,
    video: FileId,
    thumb: FileId? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendVideoData(
        chatId,
        video.fileId,
        thumb ?.fileId,
        text,
        parseMode,
        duration,
        width,
        height,
        null,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )
)

suspend fun RequestsExecutor.sendVideo(
    chatId: ChatIdentifier,
    video: VideoFile,
    thumb: PhotoSize? = video.thumb,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(
    chatId, video.fileId, thumb ?.fileId, text, parseMode, video.duration, video.width, video.height, disableNotification, replyToMessageId, replyMarkup
)

suspend fun RequestsExecutor.sendVideo(
    chatId: ChatIdentifier,
    video: MultipartFile,
    thumb: FileId? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    supportStreaming: Boolean? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    MultipartRequestImpl(
        SendVideoData(
            chatId, null, thumb ?.fileId, text, parseMode, duration, width, height, supportStreaming, disableNotification, replyToMessageId, replyMarkup
        ),
        SendVideoFiles(video)
    )
)

suspend fun RequestsExecutor.sendVideo(
    chatId: ChatIdentifier,
    video: MultipartFile,
    thumb: MultipartFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    supportStreaming: Boolean? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    MultipartRequestImpl(
        SendVideoData(
            chatId, null, null, text, parseMode, duration, width, height, supportStreaming, disableNotification, replyToMessageId, replyMarkup
        ),
        SendVideoFiles(video, thumb)
    )
)

suspend fun RequestsExecutor.sendVideo(
    chatId: ChatIdentifier,
    video: FileId,
    thumb: MultipartFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    MultipartRequestImpl(
        SendVideoData(
            chatId, video.fileId, null, text, parseMode, duration, width, height, null, disableNotification, replyToMessageId, replyMarkup
        ),
        SendVideoFiles(null, thumb)
    )
)

suspend fun RequestsExecutor.sendVideo(
    chatId: ChatIdentifier,
    video: MultipartFile,
    thumb: PhotoSize? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    supportStreaming: Boolean? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(
    chatId, video, thumb ?.fileId , text, parseMode, duration, width, height, supportStreaming, disableNotification, replyToMessageId, replyMarkup
)

suspend fun RequestsExecutor.sendVideo(
    chatId: ChatIdentifier,
    video: VideoFile,
    thumb: MultipartFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(
    chatId, video.fileId, thumb, text, parseMode, video.duration, video.width, video.height, disableNotification, replyToMessageId, replyMarkup
)

