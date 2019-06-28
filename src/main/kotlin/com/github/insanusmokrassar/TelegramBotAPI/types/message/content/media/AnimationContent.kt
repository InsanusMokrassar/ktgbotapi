package com.github.insanusmokrassar.TelegramBotAPI.types.message.content.media

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.CaptionedInput
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.SendAnimation
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.MessageEntity
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.HTMLParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.files.AnimationFile
import com.github.insanusmokrassar.TelegramBotAPI.types.files.DocumentFile
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MediaContent
import com.github.insanusmokrassar.TelegramBotAPI.utils.toHtmlCaptions

data class AnimationContent(
    override val media: AnimationFile,
    val includedDocument: DocumentFile?,
    override val caption: String?,
    override val captionEntities: List<MessageEntity>
) : MediaContent, CaptionedInput {
    override fun createResend(
        chatId: ChatIdentifier,
        disableNotification: Boolean,
        replyToMessageId: MessageIdentifier?,
        replyMarkup: KeyboardMarkup?
    ): Request<RawMessage> = createResend(chatId, toHtmlCaptions().firstOrNull(), HTMLParseMode, disableNotification, replyToMessageId, replyMarkup)

    @Deprecated("Will be fully replaced by default method")
    fun createResend(
        chatId: ChatIdentifier,
        caption: String?,
        parseMode: ParseMode? = null,
        disableNotification: Boolean = false,
        replyToMessageId: MessageIdentifier? = null,
        replyMarkup: KeyboardMarkup? = null
    ): Request<RawMessage> = SendAnimation(
        chatId,
        media.fileId,
        media.thumb ?.fileId,
        caption,
        parseMode,
        media.duration,
        media.width,
        media.height,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )
}