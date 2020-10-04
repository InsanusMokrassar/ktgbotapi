package dev.inmo.tgbotapi.types.files.abstracts

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.FileUniqueId

internal const val fileIdField = "file_id"
internal const val fileSizeField = "file_size"
internal const val filePathField = "file_path"

/**
 * Declare common part of media files in Telegram. Note: it is not representation of JVM `File` type
 */
interface TelegramMediaFile {
    val fileId: FileId
    val fileUniqueId: FileUniqueId
    val fileSize: Long?
}
