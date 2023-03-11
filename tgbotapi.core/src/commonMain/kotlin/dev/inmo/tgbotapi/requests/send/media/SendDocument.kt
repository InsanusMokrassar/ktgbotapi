package dev.inmo.tgbotapi.requests.send.media

import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.common.CommonMultipartFileRequest
import dev.inmo.tgbotapi.requests.send.abstracts.*
import dev.inmo.tgbotapi.requests.send.media.base.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.parseModeField
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.DocumentContent
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.utils.mapOfNotNull
import dev.inmo.tgbotapi.utils.throwRangeError
import kotlinx.serialization.*

/**
 * Use this method to send general files. On success, the sent [ContentMessage] with [DocumentContent] is returned.
 * Bots can currently send files of any type of up to 50 MB in size, this limit may be changed in the future.
 *
 * @param disableContentTypeDetection Disables automatic server-side content type detection for [document] [MultipartFile]
 *
 * @see ContentMessage
 * @see DocumentContent
 */
fun SendDocument(
    chatId: ChatIdentifier,
    document: InputFile,
    thumbnail: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): Request<ContentMessage<DocumentContent>> {
    val documentAsFile = document as? MultipartFile
    val thumbAsFile = thumbnail as? MultipartFile

    val data = SendDocumentData(
        chatId,
        document,
        thumbnail ?.fileId,
        text,
        parseMode,
        null,
        threadId,
        disableNotification,
        protectContent,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup,
        disableContentTypeDetection
    )

    return if (documentAsFile == null && thumbAsFile == null) {
        data
    } else {
        CommonMultipartFileRequest(
            data,
            listOfNotNull(documentAsFile, thumbAsFile).associateBy { it.fileId }
        )
    }
}

/**
 * Use this method to send general files. On success, the sent [ContentMessage] with [DocumentContent] is returned.
 * Bots can currently send files of any type of up to 50 MB in size, this limit may be changed in the future.
 *
 * @param disableContentTypeDetection Disables automatic server-side content type detection for [document] [MultipartFile]
 *
 * @see ContentMessage
 * @see DocumentContent
 */
fun SendDocument(
    chatId: ChatIdentifier,
    document: InputFile,
    thumbnail: InputFile? = null,
    entities: TextSourcesList,
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): Request<ContentMessage<DocumentContent>> {
    val documentAsFile = document as? MultipartFile
    val thumbAsFile = thumbnail as? MultipartFile

    val data = SendDocumentData(
        chatId,
        document,
        thumbnail ?.fileId,
        entities.makeString(),
        null,
        entities.toRawMessageEntities(),
        threadId,
        disableNotification,
        protectContent,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup,
        disableContentTypeDetection
    )

    return if (documentAsFile == null && thumbAsFile == null) {
        data
    } else {
        CommonMultipartFileRequest(
            data,
            listOfNotNull(documentAsFile, thumbAsFile).associateBy { it.fileId }
        )
    }
}

private val commonResultDeserializer: DeserializationStrategy<ContentMessage<DocumentContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

/**
 * Use this method to send general files. On success, the sent [ContentMessage] with [DocumentContent] is returned.
 * Bots can currently send files of any type of up to 50 MB in size, this limit may be changed in the future.
 *
 * @param disableContentTypeDetection Disables automatic server-side content type detection for [document] [MultipartFile]
 *
 * @see ContentMessage
 * @see DocumentContent
 */
@Serializable
data class SendDocumentData internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(documentField)
    val document: InputFile,
    @SerialName(thumbnailField)
    override val thumbnail: String? = null,
    @SerialName(captionField)
    override val text: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
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
    override val replyMarkup: KeyboardMarkup? = null,
    @SerialName(disableContentTypeDetectionField)
    val disableContentTypeDetection: Boolean? = null
) : DataRequest<ContentMessage<DocumentContent>>,
    SendMessageRequest<ContentMessage<DocumentContent>>,
    ReplyingMarkupSendMessageRequest<ContentMessage<DocumentContent>>,
    TextableSendMessageRequest<ContentMessage<DocumentContent>>,
    ThumbedSendMessageRequest<ContentMessage<DocumentContent>>
{
    override val textSources: TextSourcesList? by lazy {
        rawEntities ?.asTextSources(text ?: return@lazy null)
    }

    init {
        text ?.let {
            if (it.length !in captionLength) {
                throwRangeError("Caption length", captionLength, it.length)
            }
        }
    }

    override fun method(): String = "sendDocument"
    override val resultDeserializer: DeserializationStrategy<ContentMessage<DocumentContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

data class SendDocumentFiles internal constructor(
    val document: MultipartFile? = null,
    val thumbnail: MultipartFile? = null
) : Files by mapOfNotNull(
    documentField to document,
    thumbnailField to thumbnail
)
