package dev.inmo.tgbotapi.types.files.abstracts

interface SizedMediaFile : TelegramMediaFile {
    val width: Int
    val height: Int
}