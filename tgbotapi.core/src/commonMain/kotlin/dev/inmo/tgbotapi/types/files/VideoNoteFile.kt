package dev.inmo.tgbotapi.types.files

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoNoteFile(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: TgFileUniqueId,
    @SerialName("length")
    override val width: Int,
    override val duration: Long? = null,
    override val thumbnail: PhotoSize? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null
) : TelegramMediaFile, ThumbedMediaFile, PlayableMediaFile, SizedMediaFile, MediaContentVariant {
    override val height: Int
        get() = width
}
