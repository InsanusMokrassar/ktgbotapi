package dev.inmo.tgbotapi.types.message.content.media

import dev.inmo.tgbotapi.CommonAbstracts.TextedInput
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.media.SendDocument
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.media.TelegramMediaDocument
import dev.inmo.tgbotapi.types.media.toTelegramMediaDocument
import dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.files.DocumentFile
import dev.inmo.tgbotapi.types.files.asDocumentFile
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.abstracts.MediaContent
import kotlinx.serialization.Serializable

@Serializable
data class DocumentContent(
    override val media: DocumentFile,
    override val text: String? = null,
    override val textSources: TextSourcesList = emptyList()
) : DocumentMediaGroupContent {
    override fun createResend(
        chatId: ChatIdentifier,
        disableNotification: Boolean,
        protectContent: Boolean,
        replyToMessageId: MessageIdentifier?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: KeyboardMarkup?
    ): Request<ContentMessage<DocumentContent>> = SendDocument(
        chatId,
        media.fileId,
        media.thumb ?.fileId,
        textSources,
        disableNotification,
        protectContent,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup
    )

    override fun toMediaGroupMemberTelegramMedia(): TelegramMediaDocument = asTelegramMedia()

    override fun asTelegramMedia(): TelegramMediaDocument = media.toTelegramMediaDocument(textSources)
}

@Suppress("NOTHING_TO_INLINE")
inline fun MediaContent.asDocumentContent() = when (this) {
    is TextedInput -> DocumentContent(
        media.asDocumentFile(),
        text,
        textSources
    )
    else -> DocumentContent(
        media.asDocumentFile()
    )
}
