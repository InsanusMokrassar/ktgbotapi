package dev.inmo.tgbotapi.types.files

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.MimeType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DocumentFile(
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: TgFileUniqueId,
    @SerialName(fileSizeField)
    override val fileSize: FileSize? = null,
    override val thumbnail: PhotoSize? = null,
    @SerialName(mimeTypeField)
    override val mimeType: MimeType? = null,
    @SerialName(fileNameField)
    override val fileName: String? = null
) : TelegramMediaFile, MimedMediaFile, ThumbedMediaFile, CustomNamedMediaFile, MediaContentVariant

@Suppress("NOTHING_TO_INLINE")
inline fun TelegramMediaFile.asDocumentFile() = if (this is DocumentFile) {
    this
} else {
    DocumentFile(
        fileId = fileId,
        fileUniqueId = fileUniqueId,
        fileSize = fileSize,
        thumbnail = (this as? ThumbedMediaFile) ?.thumbnail,
        mimeType = (this as? MimedMediaFile) ?.mimeType,
        fileName = (this as? CustomNamedMediaFile) ?.fileName
    )
}
