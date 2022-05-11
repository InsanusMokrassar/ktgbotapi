package dev.inmo.tgbotapi.types.media

sealed interface SizedTelegramMedia : TelegramMedia {
    val width: Int?
    val height: Int?
}
