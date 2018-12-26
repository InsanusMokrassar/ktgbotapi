package com.github.insanusmokrassar.TelegramBotAPI.types.files

import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import kotlinx.serialization.*
import kotlinx.serialization.internal.ArrayListSerializer

typealias Photo = List<PhotoSize>

fun Photo.biggest(): PhotoSize? {
    var biggest: PhotoSize = firstOrNull() ?: return null
    forEach {
        if (it.resolution > biggest.resolution) {
            biggest = it
        }
    }
    return biggest
}

object PhotoSerializer : KSerializer<Photo> by ArrayListSerializer(
    PhotoSize.serializer()
)

@Serializable
data class PhotoSize(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileSizeField)
    @Optional
    override val fileSize: Long? = null,
    override val width: Int,
    override val height: Int
) : SizedMediaFile {
    @Transient
    val resolution: Long by lazy {
        width.toLong() * height
    }
}
