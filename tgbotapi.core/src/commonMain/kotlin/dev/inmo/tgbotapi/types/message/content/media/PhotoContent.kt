package dev.inmo.tgbotapi.types.message.content.media

import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.media.SendPhoto
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.InputMedia.InputMediaPhoto
import dev.inmo.tgbotapi.types.InputMedia.toInputMediaPhoto
import dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.files.*
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.abstracts.MediaCollectionContent
import kotlinx.serialization.Serializable

@Serializable
data class PhotoContent(
    override val mediaCollection: Photo,
    override val text: String? = null,
    override val textSources: TextSourcesList = emptyList()
) : MediaCollectionContent<PhotoSize>, VisualMediaGroupContent {
    override val media: PhotoSize = mediaCollection.biggest() ?: throw IllegalStateException("Can't locate any photo size for this content")

    override fun createResend(
        chatId: ChatIdentifier,
        disableNotification: Boolean,
        protectContent: Boolean,
        replyToMessageId: MessageIdentifier?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: KeyboardMarkup?
    ): Request<ContentMessage<PhotoContent>> = SendPhoto(
        chatId,
        media.fileId,
        textSources,
        disableNotification,
        protectContent,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup
    )

    override fun toMediaGroupMemberInputMedia(): InputMediaPhoto = asInputMedia()

    override fun asInputMedia(): InputMediaPhoto = media.toInputMediaPhoto(textSources)
}
