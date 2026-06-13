package dev.inmo.tgbotapi.types.files

import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.TgFileUniqueId
import dev.inmo.tgbotapi.types.media.BaseTelegramMediaFile

/**
 * Declare common part of media files in Telegram. Note: it is not representation of JVM `File` type
 */
sealed interface TelegramMediaFile : BaseTelegramMediaFile {
    val fileId: FileId
    val fileUniqueId: TgFileUniqueId
    val fileSize: FileSize?
}
