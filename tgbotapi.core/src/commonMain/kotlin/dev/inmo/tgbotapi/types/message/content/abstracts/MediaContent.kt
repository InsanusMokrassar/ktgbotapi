package dev.inmo.tgbotapi.types.message.content.abstracts

import dev.inmo.tgbotapi.types.InputMedia.InputMedia
import dev.inmo.tgbotapi.types.files.abstracts.TelegramMediaFile

interface MediaContent: MessageContent {
    val media: TelegramMediaFile
    fun asInputMedia(): InputMedia
}
