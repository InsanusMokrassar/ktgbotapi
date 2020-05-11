package com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.types.FileUniqueId

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
