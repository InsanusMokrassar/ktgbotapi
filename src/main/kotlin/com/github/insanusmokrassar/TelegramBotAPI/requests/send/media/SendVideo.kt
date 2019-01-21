package com.github.insanusmokrassar.TelegramBotAPI.requests.send.media

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.base.*
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.parseModeField
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage
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
): Request<RawMessage> {
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

@Serializable
data class SendVideoData internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(videoField)
    @Optional
    val video: String? = null,
    @SerialName(thumbField)
    @Optional
    override val thumb: String? = null,
    @SerialName(captionField)
    @Optional
    override val text: String? = null,
    @SerialName(parseModeField)
    @Optional
    override val parseMode: ParseMode? = null,
    @SerialName(durationField)
    @Optional
    override val duration: Long? = null,
    @SerialName(widthField)
    @Optional
    override val width: Int? = null,
    @SerialName(heightField)
    @Optional
    override val height: Int? = null,
    @SerialName(supportStreamingField)
    @Optional
    val supportStreaming: Boolean? = null,
    @SerialName(disableNotificationField)
    @Optional
    override val disableNotification: Boolean = false,
    @SerialName(replyToMessageIdField)
    @Optional
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(replyMarkupField)
    @Optional
    override val replyMarkup: KeyboardMarkup? = null
) : Data<RawMessage>,
    SendMessageRequest<RawMessage>,
    ReplyingMarkupSendMessageRequest<RawMessage>,
    TextableSendMessageRequest<RawMessage>,
    ThumbedSendMessageRequest<RawMessage>,
    DuratedSendMessageRequest<RawMessage>,
    SizedSendMessageRequest<RawMessage>
{

    override fun method(): String = "sendVideo"
    override fun resultSerializer(): KSerializer<RawMessage> = RawMessage.serializer()
}

data class SendVideoFiles internal constructor(
    val video: MultipartFile? = null,
    val thumb: MultipartFile? = null
) : Files by mapOfNotNull(
    videoField to video,
    thumbField to thumb
)
