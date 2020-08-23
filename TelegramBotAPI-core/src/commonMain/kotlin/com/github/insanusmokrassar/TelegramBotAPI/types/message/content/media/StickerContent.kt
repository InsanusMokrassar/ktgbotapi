package com.github.insanusmokrassar.TelegramBotAPI.types.message.content.media

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.SendSticker
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia.InputMediaDocument
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.files.Sticker
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MediaContent

data class StickerContent(
    override val media: Sticker
) : MediaContent {
    override fun createResend(
        chatId: ChatIdentifier,
        disableNotification: Boolean,
        replyToMessageId: MessageIdentifier?,
        replyMarkup: KeyboardMarkup?
    ): Request<ContentMessage<StickerContent>> = SendSticker(
        chatId,
        media.fileId,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )

    override fun asInputMedia(): InputMediaDocument = InputMediaDocument(
        media.fileId,
        null,
        thumb = media.thumb ?.fileId
    )
}
