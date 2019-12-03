package com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.InputFile
import kotlinx.serialization.Serializable

const val inputMediaFileAttachmentNameTemplate = "attach://%s"

@Serializable(InputMediaSerializer::class)
interface InputMedia {
    val type: String
    val file: InputFile
}