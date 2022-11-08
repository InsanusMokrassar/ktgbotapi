package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.media.SendMediaGroup
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MediaGroupIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.files.TelegramMediaFile
import dev.inmo.tgbotapi.types.media.TelegramMedia
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import kotlinx.serialization.Serializable

@Serializable
data class MediaGroupContent<T : MediaGroupPartContent>(
    override val group: List<MediaGroupCollectionContent.PartWrapper<T>>,
    override val mediaGroupId: MediaGroupIdentifier
) : MediaGroupCollectionContent<T> {
    val mainContent: MediaGroupPartContent
        get() = group.first().content
    override val media: TelegramMediaFile
        get() = mainContent.media

    override val textSources: List<TextSource>
        get() = mainContent.textSources
    override val text: String?
        get() = mainContent.text

    override fun asTelegramMedia(): TelegramMedia = mainContent.asTelegramMedia()

    override fun createResend(
        chatId: ChatIdentifier,
        threadId: MessageThreadId?,
        disableNotification: Boolean,
        protectContent: Boolean,
        replyToMessageId: MessageId?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: KeyboardMarkup?
    ): Request<out Message> = SendMediaGroup<MediaGroupPartContent>(
        chatId,
        group.map { it.content.toMediaGroupMemberTelegramMedia() },
        threadId,
        disableNotification,
        protectContent,
        replyToMessageId,
        allowSendingWithoutReply
    )
}
