package com.github.insanusmokrassar.TelegramBotAPI.types.message.content.media

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.CaptionedInput
import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextPart
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.SendVoice
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia.InputMediaAudio
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.HTMLParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.MarkdownV2
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.files.VoiceFile
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MediaContent
import com.github.insanusmokrassar.TelegramBotAPI.utils.toHtmlCaptions
import com.github.insanusmokrassar.TelegramBotAPI.utils.toMarkdownV2Captions

data class VoiceContent(
    override val media: VoiceFile,
    override val caption: String? = null,
    override val captionEntities: List<TextPart> = emptyList()
) : MediaContent, CaptionedInput {
    override fun createResend(
        chatId: ChatIdentifier,
        disableNotification: Boolean,
        replyToMessageId: MessageIdentifier?,
        replyMarkup: KeyboardMarkup?
    ): Request<ContentMessage<VoiceContent>> = SendVoice(
        chatId,
        media.fileId,
        null,
        toHtmlCaptions().firstOrNull(),
        HTMLParseMode,
        media.duration,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )

    override fun asInputMedia(): InputMediaAudio = InputMediaAudio(
        media.fileId,
        toMarkdownV2Captions().firstOrNull(),
        MarkdownV2,
        media.duration
    )
}
