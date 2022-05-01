package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.media.SendVoice
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.media.TelegramMediaAudio
import dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.files.VoiceFile
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import kotlinx.serialization.Serializable

@Serializable
data class VoiceContent(
    override val media: VoiceFile,
    override val text: String? = null,
    override val textSources: TextSourcesList = emptyList()
) : TextedMediaContent {
    override fun createResend(
        chatId: ChatIdentifier,
        disableNotification: Boolean,
        protectContent: Boolean,
        replyToMessageId: MessageIdentifier?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: KeyboardMarkup?
    ): Request<ContentMessage<VoiceContent>> = SendVoice(
        chatId,
        media.fileId,
        textSources,
        media.duration,
        disableNotification,
        protectContent,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup
    )

    override fun asTelegramMedia(): TelegramMediaAudio = TelegramMediaAudio(
        media.fileId,
        textSources,
        media.duration
    )
}
