package dev.inmo.tgbotapi.requests.send.media

import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.common.CommonMultipartFileRequest
import dev.inmo.tgbotapi.requests.send.abstracts.*
import dev.inmo.tgbotapi.requests.send.media.base.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.VideoNoteContent
import dev.inmo.tgbotapi.utils.mapOfNotNull
import kotlinx.serialization.*

fun SendVideoNote(
    chatId: ChatIdentifier,
    videoNote: InputFile,
    thumbnail: InputFile? = null,
    duration: Long? = null,
    size: Int? = null, // in documentation - length (size of video side)
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<ContentMessage<VideoNoteContent>> {
    val videoNoteAsFile = videoNote as? MultipartFile
    val thumbAsFile = thumbnail as? MultipartFile

    val data = SendVideoNoteData(
        chatId,
        videoNote,
        thumbnail ?.fileId,
        duration,
        size,
        threadId,
        disableNotification,
        protectContent,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup
    )

    return if (videoNoteAsFile == null && thumbAsFile == null) {
        data
    } else {
        CommonMultipartFileRequest(
            data,
            listOfNotNull(videoNoteAsFile, thumbAsFile).associateBy { it.fileId }
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
    val videoNote: InputFile,
    @SerialName(thumbnailField)
    override val thumbnail: String? = null,
    @SerialName(durationField)
    override val duration: Long? = null,
    @SerialName(lengthField)
    override val width: Int? = null,
    @SerialName(messageThreadIdField)
    override val threadId: MessageThreadId? = chatId.threadId,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(protectContentField)
    override val protectContent: Boolean = false,
    @SerialName(replyToMessageIdField)
    override val replyToMessageId: MessageId? = null,
    @SerialName(allowSendingWithoutReplyField)
    override val allowSendingWithoutReply: Boolean? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : DataRequest<ContentMessage<VideoNoteContent>>,
    SendMessageRequest<ContentMessage<VideoNoteContent>>,
    ReplyingMarkupSendMessageRequest<ContentMessage<VideoNoteContent>>,
    ThumbedSendMessageRequest<ContentMessage<VideoNoteContent>>,
    DuratedSendMessageRequest<ContentMessage<VideoNoteContent>>,
    SizedSendMessageRequest<ContentMessage<VideoNoteContent>>
{
    override val height: Int?
        get() = width

    override fun method(): String = "sendVideoNote"
    override val resultDeserializer: DeserializationStrategy<ContentMessage<VideoNoteContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

data class SendVideoNoteFiles internal constructor(
    val videoNote: MultipartFile? = null,
    val thumbnail: MultipartFile? = null
) : Files by mapOfNotNull(
    videoNoteField to videoNote,
    thumbnailField to thumbnail
)
