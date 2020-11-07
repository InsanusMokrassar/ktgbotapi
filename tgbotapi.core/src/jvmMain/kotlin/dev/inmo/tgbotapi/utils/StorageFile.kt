package dev.inmo.tgbotapi.utils

import io.ktor.utils.io.streams.asInput
import java.io.File
import java.io.InputStream
import java.nio.file.Files

fun StorageFile(
    file: File
) = StorageFile(
    StorageFileInfo(
        Files.probeContentType(file.toPath()),
        file.name
    )
) {
    file.inputStream().asInput()
}
