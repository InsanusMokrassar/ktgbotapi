package com.github.insanusmokrassar.TelegramBotAPI.utils

import io.ktor.utils.io.core.Input
import kotlinx.serialization.Serializable

@Serializable
expect class StorageFile {
    val contentType: String
    val fileName: String
    fun generateCustomName(): String
    fun asInput(): Input
}
