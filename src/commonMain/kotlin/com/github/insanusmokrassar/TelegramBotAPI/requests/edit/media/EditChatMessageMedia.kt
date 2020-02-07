package com.github.insanusmokrassar.TelegramBotAPI.requests.edit.media

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.MultipartFile
import com.github.insanusmokrassar.TelegramBotAPI.requests.edit.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.edit.caption.EditChatMessageCaption
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia.InputMedia
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.CommonMessageImpl
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.TextContent
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MediaContent
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.media.PhotoContent
import kotlinx.serialization.*

const val editMessageMediaMethod = "editMessageMedia"

internal val MediaContentMessageResultDeserializer = TelegramBotAPIMessageDeserializationStrategyClass<ContentMessage<MediaContent>>()

@Serializable
data class EditChatMessageMedia(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(messageIdField)
    override val messageId: MessageIdentifier,
    @SerialName(mediaField)
    override val media: InputMedia,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null
) : EditChatMessage<MediaContent>, EditReplyMessage, EditMediaMessage {

    init {
        if (media.file is MultipartFile) {
            throw IllegalArgumentException("For editing of media messages you MUST use file id (according to documentation)")
        }
    }

    override fun method(): String = editMessageMediaMethod
    override val resultDeserializer: DeserializationStrategy<ContentMessage<MediaContent>>
        get() = MediaContentMessageResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

suspend fun RequestsExecutor.editMessageMedia(
    chatId: ChatIdentifier,
    messageId: MessageIdentifier,
    media: InputMedia,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(
    EditChatMessageMedia(chatId, messageId, media, replyMarkup)
)

suspend fun RequestsExecutor.editMessageMedia(
    chat: Chat,
    messageId: MessageIdentifier,
    media: InputMedia,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageMedia(chat.id, messageId, media, replyMarkup)

suspend fun RequestsExecutor.editMessageMedia(
    message: ContentMessage<out MediaContent>,
    media: InputMedia,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageMedia(message.chat.id, message.messageId, media, replyMarkup)
