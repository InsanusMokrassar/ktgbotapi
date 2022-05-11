package dev.inmo.tgbotapi.types.media

import dev.inmo.tgbotapi.requests.abstracts.InputFile

sealed interface ThumbedTelegramMedia : TelegramMedia {
    val thumb: InputFile?
}
