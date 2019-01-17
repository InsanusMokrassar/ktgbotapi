package com.github.insanusmokrassar.TelegramBotAPI.types.message.content.media

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.SendAnimation
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.MarkdownParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.files.AnimationFile
import com.github.insanusmokrassar.TelegramBotAPI.types.files.DocumentFile
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MediaContent

data class AnimationContent(
    override val media: AnimationFile,
    val includedDocument: DocumentFile?
) : MediaContent<AnimationFile> {
    override fun createResend(
        chatId: ChatIdentifier,
        disableNotification: Boolean,
        replyToMessageId: MessageIdentifier?,
        replyMarkup: KeyboardMarkup?
    ): Request<RawMessage> = createResend(chatId, null, null, disableNotification, replyToMessageId, replyMarkup)

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