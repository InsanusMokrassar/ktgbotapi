package dev.inmo.tgbotapi.types.files

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.FileUniqueId
import dev.inmo.tgbotapi.types.fileUniqueIdField
import dev.inmo.tgbotapi.types.files.abstracts.*
import dev.inmo.tgbotapi.utils.MimeType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DocumentFile(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: FileUniqueId,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null,
    override val thumb: PhotoSize? = null,
    @SerialName(mimeTypeField)
    override val mimeType: MimeType? = null,
    @SerialName(fileNameField)
    override val fileName: String? = null
) : TelegramMediaFile, MimedMediaFile, ThumbedMediaFile, CustomNamedMediaFile

@Suppress("NOTHING_TO_INLINE")
inline fun TelegramMediaFile.asDocumentFile() = DocumentFile(
    fileId,
    fileUniqueId,
    fileSize,
    (this as? ThumbedMediaFile) ?.thumb,
    (this as? MimedMediaFile) ?.mimeType,
    (this as? CustomNamedMediaFile) ?.fileName
)
