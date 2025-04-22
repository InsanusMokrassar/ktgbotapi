package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.media.SendSticker
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.files.Sticker
import dev.inmo.tgbotapi.types.media.TelegramMediaDocument
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import kotlinx.serialization.Serializable

@Serializable
data class StickerContent(
    override val media: Sticker,
) : MediaContent {
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
    ): Request<ContentMessage<StickerContent>> =
        SendSticker(
            chatId = chatId,
            sticker = media.fileId,
            threadId = messageThreadId,
            businessConnectionId = businessConnectionId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            replyParameters = replyParameters,
            replyMarkup = replyMarkup,
        )

    override fun asTelegramMedia(): TelegramMediaDocument =
        TelegramMediaDocument(
            media.fileId,
            null,
            thumb = media.thumbnail ?.fileId,
        )
}
