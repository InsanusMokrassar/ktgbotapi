package com.github.insanusmokrassar.TelegramBotAPI.types.message.content.media

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextPart
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.SendVideo
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia.InputMediaVideo
import com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia.MediaGroupMemberInputMedia
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.HTMLParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.MarkdownV2
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.files.VideoFile
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MediaGroupContent
import com.github.insanusmokrassar.TelegramBotAPI.utils.toHtmlCaptions
import com.github.insanusmokrassar.TelegramBotAPI.utils.toMarkdownV2Captions

data class VideoContent(
    override val media: VideoFile,
    override val caption: String? = null,
    override val captionEntities: List<TextPart> = emptyList()
) : MediaGroupContent {
    override fun createResend(
        chatId: ChatIdentifier,
        disableNotification: Boolean,
        replyToMessageId: MessageIdentifier?,
        replyMarkup: KeyboardMarkup?
    ): Request<ContentMessage<VideoContent>> = SendVideo(
        chatId,
        media.fileId,
        media.thumb ?.fileId,
        toHtmlCaptions().firstOrNull(),
        HTMLParseMode,
        media.duration,
        media.width,
        media.height,
        null,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )

    override fun toMediaGroupMemberInputMedia(): MediaGroupMemberInputMedia = InputMediaVideo(
        media.fileId,
        toHtmlCaptions().firstOrNull(),
        HTMLParseMode,
        media.width,
        media.height,
        media.duration,
        media.thumb ?.fileId
    )

    override fun asInputMedia(): InputMediaVideo = InputMediaVideo(
        media.fileId,
        toMarkdownV2Captions().firstOrNull(),
        MarkdownV2,
        media.width,
        media.height,
        media.duration,
        media.thumb ?.fileId
    )
}
