package dev.inmo.tgbotapi.utils

import com.benasher44.uuid.uuid4
import io.ktor.utils.io.core.ByteReadPacket
import io.ktor.utils.io.core.Input
import kotlinx.serialization.Serializable

/**
 * Information about file for [StorageFile]
 *
 * @param contentType Raw type like "application/json"
 * @param fileName This filename will be used in telegram system as name of file
 */
@Serializable
data class StorageFileInfo(
    val contentType: String,
    val fileName: String
) {
    /**
     * This methods is required for random generation of name for keeping warranties about unique file name
     */
    fun generateCustomName() = "${uuid4()}.${fileName.fileExtension}"
}

/**
 * Contains info about file, which potentially can be sent to telegram system.
 *
 * @param storageFileInfo Information about this file
 * @param inputSource Lambda which able to allocate [Input] for uploading/manipulating data
 *
 * @see StorageFileInfo
 * @see asStorageFile
 */
data class StorageFile(
    val storageFileInfo: StorageFileInfo,
    private val inputSource: () -> Input
) {
    val input: Input
        get() = inputSource()
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

/**
 *
 */
@Suppress("NOTHING_TO_INLINE", "unused")
inline fun ByteArray.asStorageFile(fileName: String, mimeType: MimeType) = StorageFile(fileName, this, mimeType)
