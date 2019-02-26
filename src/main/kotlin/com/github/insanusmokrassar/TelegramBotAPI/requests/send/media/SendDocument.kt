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

fun SendDocument(
    chatId: ChatIdentifier,
    document: InputFile,
    thumb: InputFile? = null,
    caption: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<RawMessage> {
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

@Serializable
data class SendDocumentData internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(documentField)
    @Optional
    val document: String? = null,
    @SerialName(thumbField)
    @Optional
    override val thumb: String? = null,
    @SerialName(captionField)
    @Optional
    override val text: String? = null,
    @SerialName(parseModeField)
    @Optional
    override val parseMode: ParseMode? = null,
    @SerialName(disableNotificationField)
    @Optional
    override val disableNotification: Boolean = false,
    @SerialName(replyToMessageIdField)
    @Optional
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(replyMarkupField)
    @Optional
    override val replyMarkup: KeyboardMarkup? = null
) : DataRequest<RawMessage>,
    SendMessageRequest<RawMessage>,
    ReplyingMarkupSendMessageRequest<RawMessage>,
    TextableSendMessageRequest<RawMessage>,
    ThumbedSendMessageRequest<RawMessage>
{
    init {
        text ?.let {
            if (it.length !in captionLength) {
                throw IllegalArgumentException("Caption must be in $captionLength range")
            }
        }
    }

    override fun method(): String = "sendDocument"
    override fun resultSerializer(): KSerializer<RawMessage> = RawMessage.serializer()
}

data class SendDocumentFiles internal constructor(
    val document: MultipartFile? = null,
    val thumb: MultipartFile? = null
) : Files by mapOfNotNull(
    documentField to document,
    thumbField to thumb
)
