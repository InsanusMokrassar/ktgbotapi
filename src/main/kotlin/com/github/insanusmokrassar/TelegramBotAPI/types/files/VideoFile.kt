package com.github.insanusmokrassar.TelegramBotAPI.types.files

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.*
import kotlinx.serialization.*

@Serializable
data class VideoFile(
    @SerialName(fileIdField)
    override val fileId: FileId,
    override val width: Int,
    override val height: Int,
    override val duration: Long? = null,
    override val thumb: PhotoSize? = null,
    @SerialName(mimeTypeField)
    override val mimeType: String? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null
) : TelegramMediaFile, MimedMediaFile, ThumbedMediaFile, PlayableMediaFile, SizedMediaFile
