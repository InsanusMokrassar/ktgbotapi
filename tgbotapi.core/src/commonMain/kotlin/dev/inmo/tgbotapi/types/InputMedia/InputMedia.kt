package dev.inmo.tgbotapi.types.InputMedia

import dev.inmo.tgbotapi.requests.abstracts.InputFile
import kotlinx.serialization.Serializable

@Serializable(InputMediaSerializer::class)
sealed interface InputMedia {
    val type: String
    val file: InputFile
    val media: String
}
