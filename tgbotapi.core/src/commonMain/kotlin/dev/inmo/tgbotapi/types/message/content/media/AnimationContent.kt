package dev.inmo.tgbotapi.types.message.content.media

import dev.inmo.tgbotapi.CommonAbstracts.CaptionedInput
import dev.inmo.tgbotapi.CommonAbstracts.TextPart
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.media.SendAnimation
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.InputMedia.InputMediaAnimation
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.ParseMode.HTMLParseMode
import dev.inmo.tgbotapi.types.ParseMode.MarkdownV2
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.files.AnimationFile
import dev.inmo.tgbotapi.types.files.DocumentFile
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.abstracts.MediaContent
import dev.inmo.tgbotapi.utils.internal.toHtmlCaptions
import dev.inmo.tgbotapi.utils.internal.toMarkdownV2Captions

data class AnimationContent(
    override val media: AnimationFile,
    val includedDocument: DocumentFile?,
    override val caption: String?,
    override val captionEntities: List<TextPart>
) : MediaContent, CaptionedInput {
    override fun createResend(
        chatId: ChatIdentifier,
        disableNotification: Boolean,
        replyToMessageId: MessageIdentifier?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: KeyboardMarkup?
    ): Request<ContentMessage<AnimationContent>> = SendAnimation(
        chatId,
        media.fileId,
        media.thumb ?.fileId,
        toHtmlCaptions().firstOrNull(),
        HTMLParseMode,
        media.duration,
        media.width,
        media.height,
        disableNotification,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup
    )

    override fun asInputMedia(): InputMediaAnimation = InputMediaAnimation(
        media.fileId,
        toMarkdownV2Captions().firstOrNull(),
        MarkdownV2,
        media.width,
        media.height,
        media.duration,
        media.thumb ?.fileId
    )
}
