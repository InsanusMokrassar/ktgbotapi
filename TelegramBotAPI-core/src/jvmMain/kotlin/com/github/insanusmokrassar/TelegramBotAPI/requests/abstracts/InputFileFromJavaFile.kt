package com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.utils.StorageFile
import java.io.File

fun File.toInputFile() = if (exists()) {
    MultipartFile(
        StorageFile(this)
    )
} else {
    error("Specified file $absolutePath does not exists")
}
