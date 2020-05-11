package com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.thumbField
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
