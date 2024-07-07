package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.files.*
import dev.inmo.tgbotapi.types.files.toTelegramPaidMediaVideo
import dev.inmo.tgbotapi.types.media.*
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.payments.PaidMedia
import kotlinx.serialization.Serializable

@Serializable
data class PaidMediaInfoContent(
    val paidMediaInfo: PaidMediaInfo,
    override val text: String? = null,
    override val textSources: TextSourcesList = emptyList(),
    override val quote: TextQuote? = null,
    override val showCaptionAboveMedia: Boolean = false
) : TextedMediaContent, WithCustomizedCaptionMediaContent {
    override val media: TelegramMediaFile
        get() = paidMediaInfo.media.fir
    override fun asTelegramMedia(): TelegramMediaFile = when (val media = media) {
        is PaidMedia.Photo -> media.photo.biggest.toTelegramPaidMediaPhoto()
        is PaidMedia.Preview,
        is PaidMedia.Unknown -> error("Unable to create telegram media out of $media")
        is PaidMedia.Video -> media.video.toTelegramPaidMediaVideo()
    }

    override fun createResend(
        chatId: ChatIdentifier,
        messageThreadId: MessageThreadId?,
        businessConnectionId: BusinessConnectionId?,
        disableNotification: Boolean,
        protectContent: Boolean,
        effectId: EffectId?,
        replyParameters: ReplyParameters?,
        replyMarkup: KeyboardMarkup?
    ): Request<ContentMessage<PaidMediaInfoContent>> = TODO()
}
