package com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.utils.StorageFile
import kotlinx.serialization.*
import java.io.File
import java.nio.file.Files
import java.util.*

sealed class InputFile {
    abstract val fileId: String
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
    override val fileId: String = file.generateCustomName()
}

fun File.toInputFile(): InputFile = MultipartFile(
    StorageFile(this)
)
