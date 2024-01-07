package dev.inmo.tgbotapi.types.files

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.files.*
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.serialization.*
import kotlinx.serialization.builtins.ListSerializer
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class Photo(
    val photos: List<PhotoSize>
) : List<PhotoSize> by photos, ReplyInfo.External.ContentVariant

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
    override val fileUniqueId: FileUniqueId,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null,
    override val width: Int,
    override val height: Int
) : SizedMediaFile {
    val resolution: Long by lazy {
        width.toLong() * height
    }
}
