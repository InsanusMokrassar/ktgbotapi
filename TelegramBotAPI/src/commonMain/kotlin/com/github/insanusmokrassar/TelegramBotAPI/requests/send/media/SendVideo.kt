package com.github.insanusmokrassar.TelegramBotAPI.requests.send.media

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.base.*
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.parseModeField
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.media.VideoContent
import com.github.insanusmokrassar.TelegramBotAPI.utils.mapOfNotNull
import com.github.insanusmokrassar.TelegramBotAPI.utils.throwRangeError
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
                throwRangeError("Caption length", captionLength, it.length)
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
