package dev.inmo.tgbotapi.requests.abstracts

import dev.inmo.tgbotapi.utils.MimeType
import dev.inmo.tgbotapi.utils.StorageFile
import java.io.File
import java.io.InputStream

fun File.toInputFile() = if (exists()) {
    MultipartFile(
        StorageFile(this)
    )
} else {
    error("Specified file $absolutePath does not exists")
}
