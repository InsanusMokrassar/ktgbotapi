package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.abstracts.TextedInput
import dev.inmo.tgbotapi.types.files.AudioFile
import dev.inmo.tgbotapi.types.files.DocumentFile
import dev.inmo.tgbotapi.types.media.*

sealed interface AudioMediaGroupContent : MediaGroupContent {
    override val media: AudioFile

    override fun toMediaGroupMemberTelegramMedia(): AudioMediaGroupMemberTelegramMedia
}

sealed interface DocumentMediaGroupContent : MediaGroupContent {
    override val media: DocumentFile

    override fun toMediaGroupMemberTelegramMedia(): DocumentMediaGroupMemberTelegramMedia
}

sealed interface MediaGroupContent : TextedMediaContent {
    fun toMediaGroupMemberTelegramMedia(): MediaGroupMemberTelegramMedia
}

sealed interface TextedMediaContent : MediaContent, TextedInput

sealed interface VisualMediaGroupContent : MediaGroupContent {
    override fun toMediaGroupMemberTelegramMedia(): VisualMediaGroupMemberTelegramMedia
}
