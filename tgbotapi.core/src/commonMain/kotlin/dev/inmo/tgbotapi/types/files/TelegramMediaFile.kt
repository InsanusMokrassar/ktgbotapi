package dev.inmo.tgbotapi.types.files

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.FileUniqueId

/**
 * Declare common part of media files in Telegram. Note: it is not representation of JVM `File` type
 */
sealed interface TelegramMediaFile {
    val fileId: FileId
    val fileUniqueId: FileUniqueId
    val fileSize: Long?
}
