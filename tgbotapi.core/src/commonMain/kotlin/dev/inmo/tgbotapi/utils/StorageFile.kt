package dev.inmo.tgbotapi.utils

import com.benasher44.uuid.uuid4
import io.ktor.utils.io.core.ByteReadPacket
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

@Suppress("NOTHING_TO_INLINE")
inline fun StorageFile(
    fileName: String,
    bytes: ByteArray,
    mimeType: MimeType
) = StorageFile(
    StorageFileInfo(mimeType.raw, fileName)
) {
    ByteReadPacket(bytes)
}

@Suppress("NOTHING_TO_INLINE")
inline fun ByteArray.asStorageFile(fileName: String, mimeType: MimeType) = StorageFile(fileName, this, mimeType)
