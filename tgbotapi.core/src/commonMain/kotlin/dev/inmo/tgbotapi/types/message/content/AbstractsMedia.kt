package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.abstracts.TextedInput
import dev.inmo.tgbotapi.types.MediaGroupIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.files.AudioFile
import dev.inmo.tgbotapi.types.files.DocumentFile
import dev.inmo.tgbotapi.types.media.*
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
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

sealed interface TextedMediaContent : MediaContent, TextedInput

sealed interface MediaGroupCollectionContent : TextedMediaContent {
    @Serializable
    data class PartWrapper(
        val messageId: MessageId,
        val content: MediaGroupPartContent,
        val sourceMessage: PossiblySentViaBotCommonMessage<MediaGroupPartContent>
    )
    val group: List<PartWrapper>
    val mediaGroupId: MediaGroupIdentifier
}

sealed interface MediaGroupPartContent : TextedMediaContent {
    fun toMediaGroupMemberTelegramMedia(): MediaGroupMemberTelegramMedia
}

sealed interface VisualMediaGroupPartContent : MediaGroupPartContent {
    override fun toMediaGroupMemberTelegramMedia(): VisualMediaGroupMemberTelegramMedia
}
