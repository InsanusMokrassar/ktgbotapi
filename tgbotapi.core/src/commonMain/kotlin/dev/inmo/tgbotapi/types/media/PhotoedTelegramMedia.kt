package dev.inmo.tgbotapi.types.media

import dev.inmo.tgbotapi.requests.abstracts.InputFile

sealed interface PhotoedTelegramMedia : TelegramMedia {
    val photo: InputFile
}
