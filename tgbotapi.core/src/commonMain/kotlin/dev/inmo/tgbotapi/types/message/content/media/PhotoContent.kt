package dev.inmo.tgbotapi.types.message.content.media

import dev.inmo.tgbotapi.CommonAbstracts.TextPart
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.media.SendPhoto
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.InputMedia.*
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.ParseMode.HTMLParseMode
import dev.inmo.tgbotapi.types.ParseMode.MarkdownV2
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.files.*
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.abstracts.*
import dev.inmo.tgbotapi.utils.toHtmlCaptions
import dev.inmo.tgbotapi.utils.toMarkdownV2Captions

data class PhotoContent(
    override val mediaCollection: Photo,
    override val caption: String? = null,
    override val captionEntities: List<TextPart> = emptyList()
) : MediaCollectionContent<PhotoSize>, VisualMediaGroupContent {
    override val media: PhotoSize = mediaCollection.biggest() ?: throw IllegalStateException("Can't locate any photo size for this content")

    override fun createResend(
        chatId: ChatIdentifier,
        disableNotification: Boolean,
        replyToMessageId: MessageIdentifier?,
        replyMarkup: KeyboardMarkup?
    ): Request<ContentMessage<PhotoContent>> = SendPhoto(
        chatId,
        media.fileId,
        toHtmlCaptions().firstOrNull(),
        HTMLParseMode,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )

    override fun toMediaGroupMemberInputMedia(): InputMediaPhoto = asInputMedia()

    override fun asInputMedia(): InputMediaPhoto = media.toInputMediaPhoto(
        toHtmlCaptions().firstOrNull(),
        HTMLParseMode
    )
}
