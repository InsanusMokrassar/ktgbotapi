package com.github.insanusmokrassar.TelegramBotAPI.types.message.content.media

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.SendVideo
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia.InputMediaVideo
import com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia.MediaGroupMemberInputMedia
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.MessageEntity
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.HTMLParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.files.VideoFile
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.utils.toHtmlCaptions

data class VideoContent(
    override val media: VideoFile,
    override val caption: String? = null,
    override val captionEntities: List<MessageEntity> = emptyList()
) : CaptionedMediaContent, MediaGroupContent<VideoFile> {
    override fun createResend(
        chatId: ChatIdentifier,
        disableNotification: Boolean,
        replyToMessageId: MessageIdentifier?,
        replyMarkup: KeyboardMarkup?
    ): Request<RawMessage> = SendVideo(
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
}
