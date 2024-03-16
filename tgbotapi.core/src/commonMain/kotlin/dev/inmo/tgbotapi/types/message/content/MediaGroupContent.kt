package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.media.SendMediaGroup
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.abstracts.WithOptionalQuoteInfo
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.files.TelegramMediaFile
import dev.inmo.tgbotapi.types.media.TelegramMedia
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import kotlinx.serialization.Serializable

@Serializable
data class MediaGroupContent<T : MediaGroupPartContent>(
    override val group: List<MediaGroupCollectionContent.PartWrapper<T>>,
    override val mediaGroupId: MediaGroupId,
) : MediaGroupCollectionContent<T>, WithOptionalQuoteInfo {
    val mainContent: MediaGroupPartContent
        get() = group.first().content
    override val quote: TextQuote?
        get() = mainContent.quote
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
        replyParameters: ReplyParameters?,
        replyMarkup: KeyboardMarkup?
    ): Request<ContentMessage<MediaGroupContent<MediaGroupPartContent>>> = SendMediaGroup<MediaGroupPartContent>(
        chatId,
        group.map { it.content.toMediaGroupMemberTelegramMedia() },
        threadId,
        disableNotification,
        protectContent,
        replyParameters,
    )
}
