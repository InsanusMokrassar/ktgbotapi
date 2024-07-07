package dev.inmo.tgbotapi.types.files

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.serialization.*
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class Photo(
    val photos: List<PhotoSize>
) : List<PhotoSize> by photos, MediaContentVariant, UsefulAsPaidMediaFile {
    val biggest: PhotoSize
        get() = biggest()!!
    override val fileId: FileId
        get() = biggest.fileId
    override val fileUniqueId: TgFileUniqueId
        get() = biggest.fileUniqueId
    override val fileSize: Long?
        get() = biggest.fileSize

    init {
        require(photos.isNotEmpty()) {
            "Photos collection must not be empty"
        }
    }
}

fun Photo.biggest(): PhotoSize? = maxByOrNull {
    it.resolution
}

@RiskFeature
object PhotoSerializer : KSerializer<Photo> by Photo.serializer()

@Serializable
data class PhotoSize(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: TgFileUniqueId,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null,
    override val width: Int,
    override val height: Int
) : SizedMediaFile {
    val resolution: Long by lazy {
        width.toLong() * height
    }
}
