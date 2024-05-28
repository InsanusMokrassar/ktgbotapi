package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.media.SendPhoto
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.media.TelegramMediaPhoto
import dev.inmo.tgbotapi.types.media.toTelegramMediaPhoto
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.abstracts.WithOptionalQuoteInfo
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.files.*
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import kotlinx.serialization.Serializable

@Serializable
data class PhotoContent(
    override val mediaCollection: Photo,
    override val text: String? = null,
    override val textSources: TextSourcesList = emptyList(),
    override val spoilered: Boolean = false,
    override val quote: TextQuote? = null,
    override val showCaptionAboveMedia: Boolean = false
) : MediaCollectionContent<PhotoSize>, VisualMediaGroupPartContent, WithOptionalQuoteInfo {
    override val media: PhotoSize = mediaCollection.biggest() ?: throw IllegalStateException("Can't locate any photo size for this content")

    override fun createResend(
        chatId: ChatIdentifier,
        messageThreadId: MessageThreadId?,
        businessConnectionId: BusinessConnectionId?,
        disableNotification: Boolean,
        protectContent: Boolean,
        replyParameters: ReplyParameters?,
        replyMarkup: KeyboardMarkup?
    ): Request<ContentMessage<PhotoContent>> = SendPhoto(
        chatId,
        media.fileId,
        textSources,
        spoilered,
        messageThreadId,
        businessConnectionId,
        disableNotification,
        protectContent,
        replyParameters,
        replyMarkup
    )

    override fun toMediaGroupMemberTelegramMedia(): TelegramMediaPhoto = asTelegramMedia()

    override fun asTelegramMedia(): TelegramMediaPhoto = media.toTelegramMediaPhoto(textSources, spoilered, showCaptionAboveMedia)
}
