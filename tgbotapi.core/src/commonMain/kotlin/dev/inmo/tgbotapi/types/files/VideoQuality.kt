package dev.inmo.tgbotapi.types.files

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoQuality(
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: TgFileUniqueId,
    @SerialName(widthField)
    override val width: Int,
    @SerialName(heightField)
    override val height: Int,
    @SerialName(codecField)
    val codec: VideoCodec,
    @SerialName(fileSizeField)
    override val fileSize: FileSize? = null
) : TelegramMediaFile, SizedMediaFile {
}
