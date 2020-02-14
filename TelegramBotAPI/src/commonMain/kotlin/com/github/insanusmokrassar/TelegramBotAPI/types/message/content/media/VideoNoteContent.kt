package com.github.insanusmokrassar.TelegramBotAPI.types.message.content.media

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.SendVideoNote
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia.InputMediaVideo
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.files.VideoNoteFile
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MediaContent

data class VideoNoteContent(
    override val media: VideoNoteFile
) : MediaContent {
    override fun createResend(
        chatId: ChatIdentifier,
        disableNotification: Boolean,
        replyToMessageId: MessageIdentifier?,
        replyMarkup: KeyboardMarkup?
    ): Request<ContentMessage<VideoNoteContent>> = createResend(chatId, null, null, disableNotification, replyToMessageId, replyMarkup)

    fun createResend(
        chatId: ChatIdentifier,
        caption: String?,
        parseMode: ParseMode? = null,
        disableNotification: Boolean = false,
        replyToMessageId: MessageIdentifier? = null,
        replyMarkup: KeyboardMarkup? = null
    ): Request<ContentMessage<VideoNoteContent>> = SendVideoNote(
        chatId,
        media.fileId,
        media.thumb ?.fileId,
        caption,
        parseMode,
        media.duration,
        media.width,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )

    override fun asInputMedia(): InputMediaVideo = InputMediaVideo(
        media.fileId,
        width = media.width,
        height = media.height,
        duration = media.duration,
        thumb = media.thumb ?.fileId
    )
}
