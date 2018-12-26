package com.github.insanusmokrassar.TelegramBotAPI.types.files

import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import kotlinx.serialization.*

@Serializable
data class VideoFile(
    @SerialName(fileIdField)
    override val fileId: FileId,
    override val width: Int,
    override val height: Int,
    @Optional
    override val duration: Long? = null,
    @Optional
    override val thumb: PhotoSize? = null,
    @SerialName(mimeTypeField)
    @Optional
    override val mimeType: String? = null,
    @SerialName(fileSizeField)
    @Optional
    override val fileSize: Long? = null
) : TelegramMediaFile, MimedMediaFile, ThumbedMediaFile, PlayableMediaFile, SizedMediaFile
