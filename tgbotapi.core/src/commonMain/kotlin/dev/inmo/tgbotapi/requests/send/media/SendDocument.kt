package dev.inmo.tgbotapi.requests.send.media

import dev.inmo.tgbotapi.CommonAbstracts.TextSource
import dev.inmo.tgbotapi.CommonAbstracts.makeString
import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.send.abstracts.*
import dev.inmo.tgbotapi.requests.send.media.base.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.MessageEntity.*
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.ParseMode.parseModeField
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.media.DocumentContent
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
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): Request<ContentMessage<DocumentContent>> {
    val documentAsFileId = (document as? FileId) ?.fileId
    val documentAsFile = document as? MultipartFile
    val thumbAsFileId = (thumb as? FileId) ?.fileId
    val thumbAsFile = thumb as? MultipartFile

    val data = SendDocumentData(
        chatId,
        documentAsFileId,
        thumbAsFileId,
        text,
        parseMode,
        null,
        disableNotification,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup,
        disableContentTypeDetection
    )

    return if (documentAsFile == null && thumbAsFile == null) {
        data
    } else {
        MultipartRequestImpl(
            data,
            SendDocumentFiles(documentAsFile, thumbAsFile)
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
    thumb: InputFile? = null,
    entities: List<TextSource>,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): Request<ContentMessage<DocumentContent>> {
    val documentAsFileId = (document as? FileId) ?.fileId
    val documentAsFile = document as? MultipartFile
    val thumbAsFileId = (thumb as? FileId) ?.fileId
    val thumbAsFile = thumb as? MultipartFile

    val data = SendDocumentData(
        chatId,
        documentAsFileId,
        thumbAsFileId,
        entities.makeString(),
        null,
        entities.toRawMessageEntities(),
        disableNotification,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup,
        disableContentTypeDetection
    )

    return if (documentAsFile == null && thumbAsFile == null) {
        data
    } else {
        MultipartRequestImpl(
            data,
            SendDocumentFiles(documentAsFile, thumbAsFile)
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
    val document: String? = null,
    @SerialName(thumbField)
    override val thumb: String? = null,
    @SerialName(captionField)
    override val text: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(replyToMessageIdField)
    override val replyToMessageId: MessageIdentifier? = null,
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
    override val entities: List<TextSource>? by lazy {
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
    val thumb: MultipartFile? = null
) : Files by mapOfNotNull(
    documentField to document,
    thumbField to thumb
)
