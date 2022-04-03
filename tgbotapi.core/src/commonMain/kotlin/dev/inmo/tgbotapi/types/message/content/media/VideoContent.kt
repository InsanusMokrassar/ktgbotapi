package dev.inmo.tgbotapi.types.message.content.media

import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.media.SendVideo
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.InputMedia.InputMediaVideo
import dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.files.VideoFile
import dev.inmo.tgbotapi.types.files.toInputMediaVideo
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import kotlinx.serialization.Serializable

@Serializable
data class VideoContent(
    override val media: VideoFile,
    override val text: String? = null,
    override val textSources: TextSourcesList = emptyList()
) : VisualMediaGroupContent, TextedMediaContent {
    override fun createResend(
        chatId: ChatIdentifier,
        disableNotification: Boolean,
        protectContent: Boolean,
        replyToMessageId: MessageIdentifier?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: KeyboardMarkup?
    ): Request<ContentMessage<VideoContent>> = SendVideo(
        chatId,
        media.fileId,
        media.thumb ?.fileId,
        textSources,
        media.duration,
        media.width,
        media.height,
        null,
        disableNotification,
        protectContent,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup
    )

    override fun toMediaGroupMemberInputMedia(): InputMediaVideo = asInputMedia()

    override fun asInputMedia(): InputMediaVideo = media.toInputMediaVideo(textSources)
}
