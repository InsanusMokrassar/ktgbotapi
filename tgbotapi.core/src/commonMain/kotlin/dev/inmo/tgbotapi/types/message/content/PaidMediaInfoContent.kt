package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.abstracts.WithCustomizableCaption
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.media.SendPaidMedia
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.abstracts.WithOptionalQuoteInfo
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.files.*
import dev.inmo.tgbotapi.types.files.toTelegramPaidMediaVideo
import dev.inmo.tgbotapi.types.media.*
import dev.inmo.tgbotapi.types.message.SuggestedPostParameters
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
) : MessageContent, TextedContent, WithCustomizableCaption, WithOptionalQuoteInfo {
    override fun createResend(
        chatId: ChatIdentifier,
        messageThreadId: MessageThreadId?,
        directMessageThreadId: DirectMessageThreadId?,
        businessConnectionId: BusinessConnectionId?,
        disableNotification: Boolean,
        protectContent: Boolean,
        allowPaidBroadcast: Boolean,
        effectId: EffectId?,
        suggestedPostParameters: SuggestedPostParameters?,
        replyParameters: ReplyParameters?,
        replyMarkup: KeyboardMarkup?
    ): Request<ContentMessage<PaidMediaInfoContent>> = SendPaidMedia(
        chatId = chatId,
        starCount = paidMediaInfo.stars,
        media = paidMediaInfo.media.mapNotNull {
            when (it) {
                is PaidMedia.Photo -> it.photo.biggest.toTelegramPaidMediaPhoto()
                is PaidMedia.Preview -> null
                is PaidMedia.Unknown -> null
                is PaidMedia.Video -> it.video.toTelegramPaidMediaVideo()
            }
        }.ifEmpty {
            error("Unable to create resend for paid media content without any revealed content")
        },
        entities = textSources,
        showCaptionAboveMedia = showCaptionAboveMedia,
        threadId = messageThreadId,
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        suggestedPostParameters = suggestedPostParameters,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )
}
