package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.files.*
import dev.inmo.tgbotapi.types.media.PaidMedia
import dev.inmo.tgbotapi.types.media.TelegramPaidMedia
import dev.inmo.tgbotapi.types.media.toTelegramMediaPhoto
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import kotlinx.serialization.Serializable

@Serializable
data class PaidMediaInfoContent(
    override val mediaCollection: List<PaidMedia>,
    override val text: String? = null,
    override val textSources: TextSourcesList = emptyList(),
    override val quote: TextQuote? = null,
    override val showCaptionAboveMedia: Boolean = false
) : MediaCollectionContent<UsefulAsPaidMediaFile>, TextedMediaContent, WithCustomizedCaptionMediaContent {
    override val media: UsefulAsPaidMediaFile
        get() = mediaCollection.first()
    override fun asTelegramMedia(): TelegramPaidMedia = when (val media = media) {
        is VideoFile -> media.toTelegramPaidMediaVideo()
        is Photo -> media.biggest.toTelegramMediaPhoto()
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
