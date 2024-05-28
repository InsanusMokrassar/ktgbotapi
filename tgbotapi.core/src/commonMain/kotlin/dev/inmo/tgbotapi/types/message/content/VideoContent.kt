package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.media.SendVideo
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.media.TelegramMediaVideo
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.files.VideoFile
import dev.inmo.tgbotapi.types.files.toTelegramMediaVideo
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import kotlinx.serialization.Serializable

@Serializable
data class VideoContent(
    override val media: VideoFile,
    override val text: String? = null,
    override val textSources: TextSourcesList = emptyList(),
    override val spoilered: Boolean = false,
    override val quote: TextQuote? = null,
    override val showCaptionAboveMedia: Boolean = false
) : VisualMediaGroupPartContent {
    override fun createResend(
        chatId: ChatIdentifier,
        messageThreadId: MessageThreadId?,
        businessConnectionId: BusinessConnectionId?,
        disableNotification: Boolean,
        protectContent: Boolean,
        replyParameters: ReplyParameters?,
        replyMarkup: KeyboardMarkup?
    ): Request<ContentMessage<VideoContent>> = SendVideo(
        chatId,
        media.fileId,
        media.thumbnail ?.fileId,
        textSources,
        spoilered,
        media.duration,
        media.width,
        media.height,
        null,
        messageThreadId,
        businessConnectionId,
        disableNotification,
        protectContent,
        replyParameters,
        replyMarkup
    )

    override fun toMediaGroupMemberTelegramMedia(): TelegramMediaVideo = asTelegramMedia()

    override fun asTelegramMedia(): TelegramMediaVideo = media.toTelegramMediaVideo(
        textSources = textSources,
        spoilered = spoilered,
        showCaptionAboveMedia = showCaptionAboveMedia
    )
}
