package dev.inmo.tgbotapi.types.files

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.serialization.*
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class PhotoFile(
    val photos: List<PhotoSize>
) : List<PhotoSize> by photos, MediaContentVariant, UsefulAsPaidMediaFile {
    val biggest: PhotoSize
        get() = biggest()!!
    override val fileId: FileId
        get() = biggest.fileId
    override val fileUniqueId: TgFileUniqueId
        get() = biggest.fileUniqueId
    override val fileSize: FileSize?
        get() = biggest.fileSize

    init {
        require(photos.isNotEmpty()) {
            "Photos collection must not be empty"
        }
    }
}

typealias Photo = PhotoFile

fun PhotoFile.biggest(): PhotoSize? = maxByOrNull {
    it.resolution
}

@RiskFeature
object PhotoSerializer : KSerializer<PhotoFile> by PhotoFile.serializer()

@Serializable
data class PhotoSize(
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: TgFileUniqueId,
    @SerialName(fileSizeField)
    override val fileSize: FileSize? = null,
    override val width: Int,
    override val height: Int
) : SizedMediaFile {
    val resolution: Long by lazy {
        width.toLong() * height
    }
}
