package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.media.SendAudio
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.files.AudioFile
import dev.inmo.tgbotapi.types.media.TelegramMediaAudio
import dev.inmo.tgbotapi.types.media.toTelegramMediaAudio
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import kotlinx.serialization.Serializable

@Serializable
data class AudioContent(
    override val media: AudioFile,
    override val text: String? = null,
    override val textSources: TextSourcesList = emptyList(),
    override val quote: TextQuote? = null,
) : AudioMediaGroupPartContent {
    override fun createResend(
        chatId: ChatIdentifier,
        messageThreadId: MessageThreadId?,
        businessConnectionId: BusinessConnectionId?,
        disableNotification: Boolean,
        protectContent: Boolean,
        allowPaidBroadcast: Boolean,
        effectId: EffectId?,
        replyParameters: ReplyParameters?,
        replyMarkup: KeyboardMarkup?,
    ): Request<ContentMessage<AudioContent>> =
        SendAudio(
            chatId = chatId,
            audio = media.fileId,
            thumbnail = media.thumbnail ?.fileId,
            entities = textSources,
            duration = media.duration,
            performer = media.performer,
            title = media.title,
            threadId = messageThreadId,
            businessConnectionId = businessConnectionId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            replyParameters = replyParameters,
            replyMarkup = replyMarkup,
        )

    override fun toMediaGroupMemberTelegramMedia(): TelegramMediaAudio = asTelegramMedia()

    override fun asTelegramMedia(): TelegramMediaAudio = media.toTelegramMediaAudio(textSources)
}
