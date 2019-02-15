package com.github.insanusmokrassar.TelegramBotAPI.utils

import java.io.File
import java.nio.file.Files
import java.util.*
import kotlinx.io.core.Input
import kotlinx.io.streams.asInput
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
actual class StorageFile(
    @Transient
    private val realFile: File = throw IllegalStateException("Object can't be automatically deserialized")
) {
    actual val contentType: String = Files.probeContentType(realFile.toPath())
    actual val fileName: String = realFile.name

    actual fun generateCustomName(): String = "${UUID.randomUUID()}.${realFile.extension}"

    actual fun asInput(): Input = realFile.inputStream().asInput()
}