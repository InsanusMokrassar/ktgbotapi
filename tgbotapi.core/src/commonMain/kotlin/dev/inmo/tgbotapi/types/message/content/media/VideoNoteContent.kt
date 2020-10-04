package dev.inmo.tgbotapi.types.message.content.media

import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.media.SendVideoNote
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.InputMedia.InputMediaVideo
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.files.VideoNoteFile
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.abstracts.MediaContent

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
