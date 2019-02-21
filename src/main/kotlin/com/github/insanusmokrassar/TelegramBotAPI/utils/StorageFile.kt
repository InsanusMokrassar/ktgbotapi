package com.github.insanusmokrassar.TelegramBotAPI.utils

import kotlinx.io.core.Input
import kotlinx.io.streams.asInput
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.io.File
import java.nio.file.Files
import java.util.*

@Serializable
data class StorageFile(
    @Transient
    private val file: File = throw IllegalStateException("Can't create object without file")
) {
    val contentType: String = Files.probeContentType(file.toPath())
    val fileName: String = file.name
    fun generateCustomName(): String = "${UUID.randomUUID()}.${file.extension}"
    fun asInput(): Input = Files.newInputStream(file.toPath()).asInput()
}
