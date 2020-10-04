package dev.inmo.tgbotapi.utils

import com.benasher44.uuid.uuid4
import io.ktor.utils.io.core.Input
import kotlinx.serialization.Serializable

@Serializable
data class StorageFileInfo(
    val contentType: String,
    val fileName: String
) {
    fun generateCustomName() = "${uuid4()}.${fileName.fileExtension}"
}

data class StorageFile(
    val storageFileInfo: StorageFileInfo,
    private val inputSource: () -> Input
) {
    fun asInput() = inputSource()
}
