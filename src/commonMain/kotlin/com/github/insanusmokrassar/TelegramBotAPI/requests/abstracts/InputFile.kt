package com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.utils.StorageFile
import kotlinx.serialization.*

@Serializable(InputFileSerializer::class)
sealed class InputFile {
    abstract val fileId: String
}

@Serializer(InputFile::class)
object InputFileSerializer : KSerializer<InputFile> {
    override fun serialize(encoder: Encoder, obj: InputFile) {
        when (obj) {
            is FileId -> FileIdSerializer.serialize(encoder, obj)
            else -> throw IllegalArgumentException("Serialization of InputFile is unavailable(except of except of FileId)")
        }
    }

    override fun deserialize(decoder: Decoder): InputFile = FileIdSerializer.deserialize(decoder)
}

// TODO:: add checks for file url/file id regex
/**
 * Contains file id or file url
 */
@Serializable(FileIdSerializer::class)
data class FileId(
    override val fileId: String
) : InputFile()

fun String.toInputFile(): InputFile = FileId(this)

@Serializer(FileId::class)
object FileIdSerializer : KSerializer<FileId> {
    override fun serialize(output: Encoder, obj: FileId) = output.encodeString(obj.fileId)
    override fun deserialize(input: Decoder): FileId = FileId(input.decodeString())
}

// TODO:: add checks for files size
/**
 * Contains info about file for sending
 */
@Serializable
data class MultipartFile (
    val file: StorageFile,
    val mimeType: String = file.contentType,
    val filename: String = file.fileName
) : InputFile() {
    override val fileId: String by lazy {
        file.generateCustomName()
    }
}
