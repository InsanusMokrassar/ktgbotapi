package com.github.insanusmokrassar.TelegramBotAPI.types.files

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.types.FileUniqueId
import com.github.insanusmokrassar.TelegramBotAPI.types.fileUniqueIdField
import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.*
import kotlinx.serialization.*
import kotlinx.serialization.builtins.ListSerializer

typealias Photo = List<PhotoSize>

fun Photo.biggest(): PhotoSize? = maxByOrNull {
    it.resolution
}

internal object PhotoSerializer : KSerializer<Photo> by ListSerializer(
    PhotoSize.serializer()
)

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
