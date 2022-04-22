package dev.inmo.tgbotapi.types.message.content.media

import dev.inmo.tgbotapi.types.media.*
import dev.inmo.tgbotapi.types.files.DocumentFile

interface DocumentMediaGroupContent : MediaGroupContent {
    override val media: DocumentFile

    override fun toMediaGroupMemberTelegramMedia(): DocumentMediaGroupMemberTelegramMedia
}
