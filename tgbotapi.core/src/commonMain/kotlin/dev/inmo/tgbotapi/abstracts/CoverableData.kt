package dev.inmo.tgbotapi.abstracts

import dev.inmo.tgbotapi.types.files.Photo

interface CoverableData {
    val cover: Photo?
}
