package dev.inmo.tgbotapi.types.files

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.files.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoNoteFile(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: FileUniqueId,
    @SerialName("length")
    override val width: Int,
    override val duration: Long? = null,
    override val thumbnail: PhotoSize? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null
) : TelegramMediaFile, ThumbedMediaFile, PlayableMediaFile, SizedMediaFile, ExternalReplyInfo.ContentVariant {
    override val height: Int
        get() = width
}
