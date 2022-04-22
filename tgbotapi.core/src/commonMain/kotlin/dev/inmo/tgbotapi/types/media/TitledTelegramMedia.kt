package dev.inmo.tgbotapi.types.media

sealed interface TitledTelegramMedia : TelegramMedia {
    val title: String?
}
