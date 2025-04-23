package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.types.MediaGroupId
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.abstracts.WithOptionalQuoteInfo
import dev.inmo.tgbotapi.types.files.AudioFile
import dev.inmo.tgbotapi.types.files.DocumentFile
import dev.inmo.tgbotapi.types.media.*
import dev.inmo.tgbotapi.types.message.abstracts.PossiblySentViaBotCommonMessage
import kotlinx.serialization.Serializable

sealed interface AudioMediaGroupPartContent : MediaGroupPartContent {
    override val media: AudioFile

    override fun toMediaGroupMemberTelegramMedia(): AudioMediaGroupMemberTelegramMedia
}

sealed interface DocumentMediaGroupPartContent : MediaGroupPartContent {
    override val media: DocumentFile

    override fun toMediaGroupMemberTelegramMedia(): DocumentMediaGroupMemberTelegramMedia
}

sealed interface TextedMediaContent : TextedContent, MediaContent, WithOptionalQuoteInfo

sealed interface MediaGroupCollectionContent<T : MediaGroupPartContent> : TextedMediaContent {
    @Serializable
    data class PartWrapper<T : MediaGroupPartContent>(
        val messageId: MessageId,
        val content: T,
        val sourceMessage: PossiblySentViaBotCommonMessage<T>,
    )

    val group: List<PartWrapper<T>>
    val mediaGroupId: MediaGroupId
}

sealed interface MediaGroupPartContent : TextedMediaContent {
    fun toMediaGroupMemberTelegramMedia(): MediaGroupMemberTelegramMedia
}

sealed interface VisualMediaGroupPartContent : MediaGroupPartContent, SpoilerableMediaContent, WithCustomizedCaptionMediaContent {
    override fun toMediaGroupMemberTelegramMedia(): VisualMediaGroupMemberTelegramMedia
}
