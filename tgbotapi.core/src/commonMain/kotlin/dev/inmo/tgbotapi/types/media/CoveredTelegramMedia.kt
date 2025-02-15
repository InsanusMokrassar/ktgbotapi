package dev.inmo.tgbotapi.types.media

import dev.inmo.tgbotapi.requests.abstracts.InputFile

sealed interface CoveredTelegramMedia : TelegramMedia {
    val cover: InputFile?
}
