package dev.inmo.tgbotapi.utils

import com.benasher44.uuid.uuid4
import dev.inmo.micro_utils.common.MPPFile
import dev.inmo.micro_utils.common.filename
import io.ktor.utils.io.*
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
@Deprecated("Will be removed soon")
data class StorageFileInfo(
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
 * @param fileName Filename
 * @param inputSource Lambda which able to allocate [Input] for uploading/manipulating data
 *
 * @see StorageFileInfo
 * @see asStorageFile
 */
data class StorageFile(
    val fileName: String,
    private val inputSource: () -> Input
) {
    val input: Input
        get() = inputSource()
    @Deprecated("This field will be removed soon. Use fileName instead of StorageFileInfo")
    val storageFileInfo: StorageFileInfo
        get() = StorageFileInfo(fileName)

    /**
     * This methods is required for random generation of name for keeping warranties about unique file name
     */
    fun generateCustomName() = "${uuid4()}.${fileName.fileExtension}"

    @Deprecated("This constructor will be removed soon. Use constructor with fileName instead of StorageFileInfo")
    constructor(
        storageFileInfo: StorageFileInfo,
        inputSource: () -> Input
    ) : this(
        storageFileInfo.fileName,
        inputSource
    )
}

@Suppress("NOTHING_TO_INLINE")
inline fun StorageFile(
    fileName: String,
    bytes: ByteArray
) = StorageFile(
    fileName
) {
    ByteReadPacket(bytes)
}

@Suppress("NOTHING_TO_INLINE")
suspend inline fun StorageFile(
    fileName: String,
    byteReadChannel: ByteReadChannel
) = StorageFile(
    fileName,
    inputSource = byteReadChannel.asInput().let { { it } }
)

@Suppress("NOTHING_TO_INLINE", "unused")
inline fun ByteArray.asStorageFile(
    fileName: String
) = StorageFile(fileName, this)

@Suppress("NOTHING_TO_INLINE", "unused")
suspend inline fun ByteReadChannel.asStorageFile(
    fileName: String
) = StorageFile(fileName, this)

@Suppress("NOTHING_TO_INLINE", "unused")
suspend inline fun ByteReadChannelAllocator.asStorageFile(
    fileName: String
) = this.invoke().asStorageFile(fileName)
