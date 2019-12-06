package com.github.insanusmokrassar.TelegramBotAPI.utils

import com.benasher44.uuid.uuid4
import kotlinx.io.core.Input
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
actual data class StorageFile(
    actual val contentType: String,
    actual val fileName: String,
    @Transient
    val inputGetter: () -> Input = throw IllegalStateException("Can't create object without input"),
    @Transient
    val extension: String = throw IllegalStateException("Can't create object without extension")
) {
    actual fun asInput(): Input = inputGetter()
    actual fun generateCustomName(): String = "${uuid4()}.$extension"
}