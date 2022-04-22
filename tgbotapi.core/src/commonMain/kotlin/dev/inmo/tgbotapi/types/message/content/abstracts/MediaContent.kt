package dev.inmo.tgbotapi.types.message.content.abstracts

import dev.inmo.tgbotapi.types.media.TelegramMedia
import dev.inmo.tgbotapi.types.files.TelegramMediaFile

interface MediaContent: MessageContent {
    val media: TelegramMediaFile
    fun asTelegramMedia(): TelegramMedia
    @Deprecated("Renamed", ReplaceWith("asTelegramMedia()"))
    fun asInputMedia(): TelegramMedia = asTelegramMedia()
}
