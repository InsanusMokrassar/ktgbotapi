package com.github.insanusmokrassar.TelegramBotAPI.utils

import com.benasher44.uuid.uuid4
import kotlinx.io.core.Input
import kotlinx.io.streams.asInput
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.io.File
import java.nio.file.Files

@Serializable
actual class StorageFile(
    @Transient
    private val file: File = throw IllegalStateException("Can't create object without file")
) {
    actual val contentType: String = Files.probeContentType(file.toPath())
    actual val fileName: String = file.name
    actual fun generateCustomName(): String = "${uuid4()}.${file.extension}"
    actual fun asInput(): Input = Files.newInputStream(file.toPath()).asInput()
}
