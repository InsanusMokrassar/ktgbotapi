package dev.inmo.tgbotapi.utils

import io.ktor.utils.io.streams.asInput
import java.io.File
import java.nio.file.Files

fun StorageFile(
    file: File
) = StorageFile(
    file.name
) {
    file.inputStream().asInput()
}
