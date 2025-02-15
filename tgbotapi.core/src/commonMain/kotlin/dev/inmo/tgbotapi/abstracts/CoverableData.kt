package dev.inmo.tgbotapi.abstracts

import dev.inmo.tgbotapi.types.files.Photo
import dev.inmo.tgbotapi.types.files.TelegramMediaFile

interface CoverableData {
    val cover: Photo?
}
