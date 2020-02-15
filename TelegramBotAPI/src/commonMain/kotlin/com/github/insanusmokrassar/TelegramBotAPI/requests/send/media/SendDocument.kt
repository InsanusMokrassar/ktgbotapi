package com.github.insanusmokrassar.TelegramBotAPI.requests.send.media

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.base.*
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.parseModeField
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.files.DocumentFile
import com.github.insanusmokrassar.TelegramBotAPI.types.files.PhotoSize
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.media.DocumentContent
import com.github.insanusmokrassar.TelegramBotAPI.utils.mapOfNotNull
import kotlinx.serialization.*

fun SendDocument(
    chatId: ChatIdentifier,
    document: InputFile,
    thumb: InputFile? = null,
    caption: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<ContentMessage<DocumentContent>> {
    val documentAsFileId = (document as? FileId) ?.fileId
    val documentAsFile = document as? MultipartFile
    val thumbAsFileId = (thumb as? FileId) ?.fileId
    val thumbAsFile = thumb as? MultipartFile

    val data = SendDocumentData(
        chatId,
        documentAsFileId,
        thumbAsFileId,
        caption,
        parseMode,
        disableNotification,
        replyToMessageId,
        replyMarkup
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
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(replyToMessageIdField)
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : DataRequest<ContentMessage<DocumentContent>>,
    SendMessageRequest<ContentMessage<DocumentContent>>,
    ReplyingMarkupSendMessageRequest<ContentMessage<DocumentContent>>,
    TextableSendMessageRequest<ContentMessage<DocumentContent>>,
    ThumbedSendMessageRequest<ContentMessage<DocumentContent>>
{
    init {
        text ?.let {
            if (it.length !in captionLength) {
                throw IllegalArgumentException("Caption must be in $captionLength range")
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

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendDocument(
    chatId: ChatIdentifier,
    document: FileId,
    thumb: FileId? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendDocumentData(
        chatId,
        document.fileId,
        thumb ?.fileId,
        text,
        parseMode,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )
)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendDocument(
    chatId: ChatIdentifier,
    document: DocumentFile,
    thumb: PhotoSize? = document.thumb,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendDocument(
    chatId, document.fileId, thumb ?.fileId, text, parseMode, disableNotification, replyToMessageId, replyMarkup
)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendDocument(
    chatId: ChatIdentifier,
    document: MultipartFile,
    thumb: FileId? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    MultipartRequestImpl(
        SendDocumentData(
            chatId, null, thumb ?.fileId, text, parseMode, disableNotification, replyToMessageId, replyMarkup
        ),
        SendDocumentFiles(document)
    )
)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendDocument(
    chatId: ChatIdentifier,
    document: MultipartFile,
    thumb: MultipartFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    MultipartRequestImpl(
        SendDocumentData(
            chatId, null, null, text, parseMode, disableNotification, replyToMessageId, replyMarkup
        ),
        SendDocumentFiles(document, thumb)
    )
)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendDocument(
    chatId: ChatIdentifier,
    document: FileId,
    thumb: MultipartFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    MultipartRequestImpl(
        SendDocumentData(
            chatId, document.fileId, null, text, parseMode, disableNotification, replyToMessageId, replyMarkup
        ),
        SendDocumentFiles(null, thumb)
    )
)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendDocument(
    chatId: ChatIdentifier,
    document: MultipartFile,
    thumb: PhotoSize? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendDocument(
    chatId, document, thumb ?.fileId , text, parseMode, disableNotification, replyToMessageId, replyMarkup
)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendDocument(
    chatId: ChatIdentifier,
    document: DocumentFile,
    thumb: MultipartFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendDocument(
    chatId, document.fileId, thumb, text, parseMode, disableNotification, replyToMessageId, replyMarkup
)
