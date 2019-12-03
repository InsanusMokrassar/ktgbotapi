package com.github.insanusmokrassar.TelegramBotAPI.utils

import kotlinx.io.core.Input
import kotlinx.io.streams.asInput
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.io.File
import java.nio.file.Files
import java.util.*

@Serializable
actual class StorageFile(
    @Transient
    private val file: File = throw IllegalStateException("Can't create object without file")
) {
    actual val contentType: String = Files.probeContentType(file.toPath())
    actual val fileName: String = file.name
    actual fun generateCustomName(): String = "${UUID.randomUUID()}.${file.extension}"
    actual fun asInput(): Input = Files.newInputStream(file.toPath()).asInput()
}
