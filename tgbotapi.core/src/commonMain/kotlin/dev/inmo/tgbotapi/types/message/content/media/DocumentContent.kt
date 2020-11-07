package dev.inmo.tgbotapi.types.message.content.media

import dev.inmo.tgbotapi.CommonAbstracts.CaptionedInput
import dev.inmo.tgbotapi.CommonAbstracts.TextPart
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.media.SendDocument
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.InputMedia.InputMediaDocument
import dev.inmo.tgbotapi.types.InputMedia.toInputMediaDocument
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.ParseMode.HTMLParseMode
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.files.DocumentFile
import dev.inmo.tgbotapi.types.files.asDocumentFile
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.abstracts.DocumentMediaGroupContent
import dev.inmo.tgbotapi.types.message.content.abstracts.MediaContent
import dev.inmo.tgbotapi.utils.internal.toHtmlCaptions

data class DocumentContent(
    override val media: DocumentFile,
    override val caption: String? = null,
    override val captionEntities: List<TextPart> = emptyList()
) : DocumentMediaGroupContent {
    override fun createResend(
        chatId: ChatIdentifier,
        disableNotification: Boolean,
        replyToMessageId: MessageIdentifier?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: KeyboardMarkup?
    ): Request<ContentMessage<DocumentContent>> = SendDocument(
        chatId,
        media.fileId,
        media.thumb ?.fileId,
        toHtmlCaptions().firstOrNull(),
        HTMLParseMode,
        disableNotification,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup
    )

    override fun toMediaGroupMemberInputMedia(): InputMediaDocument = asInputMedia()

    override fun asInputMedia(): InputMediaDocument = media.toInputMediaDocument(
        toHtmlCaptions().firstOrNull(),
        HTMLParseMode
    )
}

@Suppress("NOTHING_TO_INLINE")
inline fun MediaContent.asDocumentContent() = when (this) {
    is CaptionedInput -> DocumentContent(
        media.asDocumentFile(),
        caption,
        captionEntities
    )
    else -> DocumentContent(
        media.asDocumentFile()
    )
}
