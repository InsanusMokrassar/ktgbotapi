package dev.inmo.tgbotapi.types.media

import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import kotlinx.serialization.Serializable

@Serializable(TelegramMediaSerializer::class)
@ClassCastsIncluded
sealed interface TelegramMedia {
    val type: String
    val file: InputFile
    val media: String
}
