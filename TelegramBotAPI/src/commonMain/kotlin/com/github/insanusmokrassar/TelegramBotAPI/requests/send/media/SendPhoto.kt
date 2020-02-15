package com.github.insanusmokrassar.TelegramBotAPI.requests.send.media

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.base.*
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.parseModeField
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.files.*
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.media.PhotoContent
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
                throw IllegalArgumentException("Caption must be in $captionLength range")
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

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendPhoto(
    chatId: ChatIdentifier,
    fileId: FileId,
    caption: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendPhotoData(
        chatId,
        fileId.fileId,
        caption,
        parseMode,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )
)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendPhoto(
    chatId: ChatIdentifier,
    file: PhotoSize,
    caption: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(
    chatId, file.fileId, caption, parseMode, disableNotification, replyToMessageId, replyMarkup
)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendPhoto(
    chatId: ChatIdentifier,
    photo: Photo,
    caption: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(
    chatId, photo.biggest() ?: throw IllegalArgumentException("Photo $photo is empty"), caption, parseMode, disableNotification, replyToMessageId, replyMarkup
)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendPhoto(
    chat: Chat,
    fileId: FileId,
    caption: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(
    chat.id,
    fileId,
    caption,
    parseMode,
    disableNotification,
    replyToMessageId,
    replyMarkup
)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendPhoto(
    chat: Chat,
    file: PhotoSize,
    caption: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(
    chat.id, file.fileId, caption, parseMode, disableNotification, replyToMessageId, replyMarkup
)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendPhoto(
    chat: Chat,
    photo: Photo,
    caption: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(
    chat.id, photo.biggest() ?: throw IllegalArgumentException("Photo $photo is empty"), caption, parseMode, disableNotification, replyToMessageId, replyMarkup
)
