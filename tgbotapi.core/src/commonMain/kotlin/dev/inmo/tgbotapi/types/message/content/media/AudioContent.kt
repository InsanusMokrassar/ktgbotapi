package dev.inmo.tgbotapi.types.message.content.media

import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.media.SendAudio
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.InputMedia.InputMediaAudio
import dev.inmo.tgbotapi.types.InputMedia.toInputMediaAudio
import dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.files.AudioFile
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.abstracts.AudioMediaGroupContent
import kotlinx.serialization.Serializable

@Serializable
data class AudioContent(
    override val media: AudioFile,
    override val text: String? = null,
    override val textSources: TextSourcesList = emptyList()
) : AudioMediaGroupContent {
    override fun createResend(
        chatId: ChatIdentifier,
        disableNotification: Boolean,
        protectContent: Boolean,
        replyToMessageId: MessageIdentifier?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: KeyboardMarkup?
    ): Request<ContentMessage<AudioContent>> = SendAudio(
        chatId,
        media.fileId,
        media.thumb ?.fileId,
        textSources,
        media.duration,
        media.performer,
        media.title,
        disableNotification,
        protectContent,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup
    )

    override fun toMediaGroupMemberInputMedia(): InputMediaAudio = asInputMedia()

    override fun asInputMedia(): InputMediaAudio = media.toInputMediaAudio(textSources)
}
