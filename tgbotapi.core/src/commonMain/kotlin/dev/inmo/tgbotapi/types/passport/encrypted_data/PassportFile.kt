package dev.inmo.tgbotapi.types.passport.encrypted_data

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.files.abstracts.*
import dev.inmo.tgbotapi.types.files.abstracts.fileIdField
import dev.inmo.tgbotapi.types.files.abstracts.fileSizeField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * This object represents a file uploaded to Telegram Passport. Currently all Telegram Passport files are in JPEG format
 * when decrypted and don't exceed 10MB.
 */
@Serializable
data class PassportFile(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: FileUniqueId,
    @SerialName(fileSizeField)
    override val fileSize: Long,
    @SerialName(fileDateField)
    val uploadingDate: TelegramDate
) : TelegramMediaFile
