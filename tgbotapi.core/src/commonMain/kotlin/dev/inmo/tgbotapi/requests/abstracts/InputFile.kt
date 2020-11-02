package dev.inmo.tgbotapi.requests.abstracts

import dev.inmo.tgbotapi.utils.StorageFile
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

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

@Serializer(InputFile::class)
internal object InputFileSerializer : KSerializer<InputFile> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(FileId::class.toString(), PrimitiveKind.STRING)
    override fun serialize(encoder: Encoder, value: InputFile) = encoder.encodeString(value.fileId)
    override fun deserialize(decoder: Decoder): FileId = FileId(decoder.decodeString())
}

// TODO:: add checks for files size
/**
 * Contains info about file for sending
 */
@Serializable(InputFileSerializer::class)
data class MultipartFile (
    val file: StorageFile,
    val mimeType: String = file.storageFileInfo.contentType,
    val filename: String = file.storageFileInfo.fileName
) : InputFile() {
    override val fileId: String = file.storageFileInfo.generateCustomName()
}
