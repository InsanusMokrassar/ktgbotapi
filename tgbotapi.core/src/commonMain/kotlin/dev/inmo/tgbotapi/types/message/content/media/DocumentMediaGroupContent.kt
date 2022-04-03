package dev.inmo.tgbotapi.types.message.content.media

import dev.inmo.tgbotapi.types.InputMedia.*
import dev.inmo.tgbotapi.types.files.DocumentFile

interface DocumentMediaGroupContent : TextedMediaGroupMediaContent {
    override val media: DocumentFile

    override fun toMediaGroupMemberInputMedia(): DocumentMediaGroupMemberInputMedia
}
