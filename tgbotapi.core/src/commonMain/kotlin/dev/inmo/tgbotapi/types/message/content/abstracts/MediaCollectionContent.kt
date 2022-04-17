package dev.inmo.tgbotapi.types.message.content.abstracts

import dev.inmo.tgbotapi.types.files.TelegramMediaFile

interface MediaCollectionContent<T: TelegramMediaFile>: MessageContent, MediaContent {
    val mediaCollection: List<T>
}
