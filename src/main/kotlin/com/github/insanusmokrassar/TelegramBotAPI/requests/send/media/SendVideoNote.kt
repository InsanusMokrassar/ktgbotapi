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

fun SendVideoNote(
    chatId: ChatIdentifier,
    videoNote: InputFile,
    thumb: InputFile?,
    caption: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    size: Int? = null, // in documentation - length (size of video side)
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<RawMessage> {
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

@Serializable
data class SendVideoNoteData internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(videoNoteField)
    @Optional
    val videoNote: String? = null,
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
    @SerialName(lengthField)
    @Optional
    override val width: Int? = null,
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
    @Transient
    override val height: Int?
        get() = width


    override fun method(): String = "sendVideoNote"
    override fun resultSerializer(): KSerializer<RawMessage> = RawMessage.serializer()
}

data class SendVideoNoteFiles internal constructor(
    val videoNote: MultipartFile? = null,
    val thumb: MultipartFile? = null
) : Files by mapOfNotNull(
    videoNoteField to videoNote,
    thumbField to thumb
)
