package com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.InputFile

const val inputMediaFileAttachmentNameTemplate = "attach://%s"

interface InputMedia {
    val type: String
    val file: InputFile
}