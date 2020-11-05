package dev.inmo.tgbotapi.types.message.content.media

import dev.inmo.tgbotapi.CommonAbstracts.CaptionedInput
import dev.inmo.tgbotapi.CommonAbstracts.TextPart
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.media.SendVoice
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.InputMedia.InputMediaAudio
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.ParseMode.HTMLParseMode
import dev.inmo.tgbotapi.types.ParseMode.MarkdownV2
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.files.VoiceFile
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.abstracts.MediaContent
import dev.inmo.tgbotapi.utils.toHtmlCaptions
import dev.inmo.tgbotapi.utils.toMarkdownV2Captions

data class VoiceContent(
    override val media: VoiceFile,
    override val caption: String? = null,
    override val captionEntities: List<TextPart> = emptyList()
) : MediaContent, CaptionedInput {
    override fun createResend(
        chatId: ChatIdentifier,
        disableNotification: Boolean,
        replyToMessageId: MessageIdentifier?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: KeyboardMarkup?
    ): Request<ContentMessage<VoiceContent>> = SendVoice(
        chatId,
        media.fileId,
        toHtmlCaptions().firstOrNull(),
        HTMLParseMode,
        media.duration,
        disableNotification,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup
    )

    override fun asInputMedia(): InputMediaAudio = InputMediaAudio(
        media.fileId,
        toMarkdownV2Captions().firstOrNull(),
        MarkdownV2,
        media.duration
    )
}
