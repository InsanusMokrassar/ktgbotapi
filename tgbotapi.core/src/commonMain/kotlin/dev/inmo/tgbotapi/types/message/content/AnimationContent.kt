package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.media.SendAnimation
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.media.TelegramMediaAnimation
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.files.AnimationFile
import dev.inmo.tgbotapi.types.files.DocumentFile
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import kotlinx.serialization.Serializable

@Serializable
data class AnimationContent(
    override val media: AnimationFile,
    val includedDocument: DocumentFile?,
    override val text: String?,
    override val textSources: TextSourcesList = emptyList(),
    override val spoilered: Boolean = false,
    override val quote: TextQuote? = null,
    override val showCaptionAboveMedia: Boolean = false
) : TextedMediaContent, SpoilerableMediaContent, WithCustomizedCaptionMediaContent {
    override fun createResend(
        chatId: ChatIdentifier,
        messageThreadId: MessageThreadId?,
        businessConnectionId: BusinessConnectionId?,
        disableNotification: Boolean,
        protectContent: Boolean,
        replyParameters: ReplyParameters?,
        replyMarkup: KeyboardMarkup?
    ): Request<ContentMessage<AnimationContent>> = SendAnimation(
        chatId,
        media.fileId,
        media.thumbnail ?.fileId,
        textSources,
        spoilered,
        media.duration,
        media.width,
        media.height,
        messageThreadId,
        businessConnectionId,
        disableNotification,
        protectContent,
        replyParameters,
        replyMarkup
    )

    override fun asTelegramMedia(): TelegramMediaAnimation = TelegramMediaAnimation(
        file = media.fileId,
        entities = textSources,
        spoilered = spoilered,
        showCaptionAboveMedia = showCaptionAboveMedia,
        width = media.width,
        height = media.height,
        duration = media.duration,
        thumb = media.thumbnail ?.fileId
    )
}
