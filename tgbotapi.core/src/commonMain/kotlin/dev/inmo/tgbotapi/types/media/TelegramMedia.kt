package dev.inmo.tgbotapi.types.media

import dev.inmo.tgbotapi.requests.abstracts.InputFile
import kotlinx.serialization.Serializable

@Serializable(TelegramMediaSerializer::class)
sealed interface TelegramMedia {
    val type: String
    val file: InputFile
    val media: String
}
