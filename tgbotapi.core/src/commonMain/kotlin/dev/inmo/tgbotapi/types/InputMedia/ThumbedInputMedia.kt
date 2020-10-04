package dev.inmo.tgbotapi.types.InputMedia

import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.types.thumbField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

interface ThumbedInputMedia : InputMedia {
    val thumb: InputFile?
    @Serializable
    @SerialName(thumbField)
    val thumbMedia: String?
        get() = thumb ?.let {
            when (it) {
                is FileId -> it.fileId
                is MultipartFile -> it.fileId.toInputMediaFileAttachmentName()
            }
        }
}
