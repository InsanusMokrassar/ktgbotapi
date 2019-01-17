package com.github.insanusmokrassar.TelegramBotAPI.types.message.content.media

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.SendPhoto
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.MessageEntity
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.MarkdownParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.files.PhotoSize
import com.github.insanusmokrassar.TelegramBotAPI.types.files.biggest
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.utils.toMarkdownCaption

data class PhotoContent(
    override val media: List<PhotoSize>,
    override val caption: String? = null,
    override val captionEntities: List<MessageEntity> = emptyList()
) : MediaCollectionContent<PhotoSize>, CaptionedMediaContent, MediaGroupContent {
    override fun createResend(
        chatId: ChatIdentifier,
        disableNotification: Boolean,
        replyToMessageId: MessageIdentifier?,
        replyMarkup: KeyboardMarkup?
    ): Request<RawMessage> = SendPhoto(
        chatId,
        media.biggest() ?.fileId ?: throw IllegalStateException("Empty list of media"),
        toMarkdownCaption(),
        MarkdownParseMode,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )
}
