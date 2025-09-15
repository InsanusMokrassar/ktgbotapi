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
        directMessageThreadId: DirectMessageThreadId?,
        businessConnectionId: BusinessConnectionId?,
        disableNotification: Boolean,
        protectContent: Boolean,
        allowPaidBroadcast: Boolean,
        effectId: EffectId?,
        replyParameters: ReplyParameters?,
        replyMarkup: KeyboardMarkup?
    ): Request<ContentMessage<AnimationContent>> = SendAnimation(
        chatId = chatId,
        animation = media.fileId,
        thumbnail = media.thumbnail ?.fileId,
        entities = textSources,
        showCaptionAboveMedia = showCaptionAboveMedia,
        spoilered = spoilered,
        duration = media.duration,
        width = media.width,
        height = media.height,
        threadId = messageThreadId,
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
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
