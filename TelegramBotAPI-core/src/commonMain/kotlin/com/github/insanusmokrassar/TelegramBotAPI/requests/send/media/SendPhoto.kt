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
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.media.PhotoContent
import com.github.insanusmokrassar.TelegramBotAPI.utils.throwRangeError
import kotlinx.serialization.*

fun SendPhoto(
    chatId: ChatIdentifier,
    photo: InputFile,
    caption: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<ContentMessage<PhotoContent>> {
    val data = SendPhotoData(
        chatId,
        (photo as? FileId) ?.fileId,
        caption,
        parseMode,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )
    return data.photo ?.let {
        data
    } ?:  MultipartRequestImpl(
        data,
        SendPhotoFiles(photo as MultipartFile)
    )
}

private val commonResultDeserializer: DeserializationStrategy<ContentMessage<PhotoContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

@Serializable
data class SendPhotoData internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(photoField)
    val photo: String? = null,
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
) : DataRequest<ContentMessage<PhotoContent>>,
    SendMessageRequest<ContentMessage<PhotoContent>>,
    ReplyingMarkupSendMessageRequest<ContentMessage<PhotoContent>>,
    TextableSendMessageRequest<ContentMessage<PhotoContent>>
{
    init {
        text ?.let {
            if (it.length !in captionLength) {
                throwRangeError("Caption length", captionLength, it.length)
            }
        }
    }

    override fun method(): String = "sendPhoto"
    override val resultDeserializer: DeserializationStrategy<ContentMessage<PhotoContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

data class SendPhotoFiles internal constructor(
    val photo: MultipartFile
) : Files by mapOf(
    photoField to photo
)
