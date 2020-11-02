package dev.inmo.tgbotapi.types.InputMedia

import dev.inmo.tgbotapi.requests.abstracts.InputFile
import kotlinx.serialization.Serializable

@Deprecated("Will be removed due to redundancy for end-side users")
fun String.toInputMediaFileAttachmentName() = "attach://$this"

@Serializable(InputMediaSerializer::class)
interface InputMedia {
    val type: String
    val file: InputFile
    val media: String
}