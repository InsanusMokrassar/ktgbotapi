package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.abstracts.TextedInput
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.media.SendDocument
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.media.TelegramMediaDocument
import dev.inmo.tgbotapi.types.media.toTelegramMediaDocument
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.abstracts.WithOptionalQuoteInfo
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.files.DocumentFile
import dev.inmo.tgbotapi.types.files.asDocumentFile
import dev.inmo.tgbotapi.types.message.SuggestedPostParameters
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import kotlinx.serialization.Serializable

@Serializable
data class DocumentContent(
    override val media: DocumentFile,
    override val text: String? = null,
    override val textSources: TextSourcesList = emptyList(),
    override val quote: TextQuote? = null
) : DocumentMediaGroupPartContent {
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
    ): Request<ContentMessage<DocumentContent>> = SendDocument(
        chatId = chatId,
        document = media.fileId,
        thumbnail = media.thumbnail ?.fileId,
        entities = textSources,
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

    override fun toMediaGroupMemberTelegramMedia(): TelegramMediaDocument = asTelegramMedia()

    override fun asTelegramMedia(): TelegramMediaDocument = media.toTelegramMediaDocument(textSources)
}

@Suppress("NOTHING_TO_INLINE")
inline fun MediaContent.asDocumentContent() = when (this) {
    is TextedInput -> DocumentContent(
        media.asDocumentFile(),
        text,
        textSources,
        (this as? WithOptionalQuoteInfo) ?.quote
    )
    else -> DocumentContent(
        media.asDocumentFile()
    )
}
