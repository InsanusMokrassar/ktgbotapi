package dev.inmo.tgbotapi.types.message.content.media

import dev.inmo.tgbotapi.types.InputMedia.*
import dev.inmo.tgbotapi.types.files.DocumentFile
import dev.inmo.tgbotapi.types.files.abstracts.TelegramMediaFile
import dev.inmo.tgbotapi.types.message.content.media.MediaGroupContent

interface DocumentMediaGroupContent : TextedMediaGroupMediaInput {
    override val media: DocumentFile

    override fun toMediaGroupMemberInputMedia(): DocumentMediaGroupMemberInputMedia
}
