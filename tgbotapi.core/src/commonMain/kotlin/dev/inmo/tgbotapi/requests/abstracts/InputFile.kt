package dev.inmo.tgbotapi.requests.abstracts

import dev.inmo.micro_utils.common.MPPFile
import dev.inmo.tgbotapi.utils.*
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.core.Input
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Common type for all files in Telegram Bot API which can be sent via requests like [dev.inmo.tgbotapi.requests.send.media.SendDocument].
 * You may use methods like [MPPFile.asMultipartFile] when you want to send files from your file system, but you should
 * remember about [restrictions][https://core.telegram.org/bots/api#sending-files] in Telegram for bots. In case you
 * wish to send file by its url, use [FileId] and pass your url as [FileId.fileId]
 *
 * @see MPPFile.asMultipartFile
 * @see ByteArray.asMultipartFile
 * @see ByteReadChannel.asMultipartFile
 * @see ByteReadChannelAllocator.asMultipartFile
 */
@Serializable(InputFileSerializer::class)
sealed class InputFile {
    abstract val fileId: String
}

internal inline val InputFile.attachFileId
    get() = "attach://$fileId"
internal inline val InputFile.fileIdToSend
    get() = when (this) {
        is FileId -> fileId
        is MultipartFile -> attachFileId
    }

// TODO:: add checks for file url/file id regex
/**
 * Contains file id or file url
 */
@Serializable(InputFileSerializer::class)
data class FileId(
    override val fileId: String
) : InputFile()

fun String.toInputFile() = FileId(this)

@RiskFeature
object InputFileSerializer : KSerializer<InputFile> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(FileId::class.toString(), PrimitiveKind.STRING)
    override fun serialize(encoder: Encoder, value: InputFile) = encoder.encodeString(value.fileId)
    override fun deserialize(decoder: Decoder): FileId = FileId(decoder.decodeString())
}

// TODO:: add checks for files size
/**
 * Contains info about file for sending
 *
 * @see asMultipartFile
 */
@Serializable(InputFileSerializer::class)
data class MultipartFile (
    val file: StorageFile,
    val filename: String = file.storageFileInfo.fileName
) : InputFile() {
    override val fileId: String = file.storageFileInfo.generateCustomName()

    @Deprecated("This constructor is redundant. Use constructor without mime type")
    constructor(file: StorageFile, mimeType: String, filename: String): this(file, filename)
}

@Suppress("NOTHING_TO_INLINE", "unused")
inline fun StorageFile.asMultipartFile() = MultipartFile(this)

@Deprecated("This method is redundant. Use asMultipartFile without mime type")
@Suppress("NOTHING_TO_INLINE", "unused")
inline fun ByteArray.asMultipartFile(
    fileName: String,
    mimeType: MimeType
) = MultipartFile(asStorageFile(fileName))

@Suppress("NOTHING_TO_INLINE", "unused")
suspend inline fun ByteReadChannel.asMultipartFile(
    fileName: String
) = MultipartFile(asStorageFile(fileName))

@Suppress("NOTHING_TO_INLINE", "unused")
suspend inline fun ByteReadChannelAllocator.asMultipartFile(
    fileName: String
) = this.invoke().asMultipartFile(fileName)

expect suspend fun MPPFile.asMultipartFile(): MultipartFile
