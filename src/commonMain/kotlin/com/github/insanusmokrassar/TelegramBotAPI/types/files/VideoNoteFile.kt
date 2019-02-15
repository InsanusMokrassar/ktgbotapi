package com.github.insanusmokrassar.TelegramBotAPI.types.files

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.*
import kotlinx.serialization.*

@Serializable
data class VideoNoteFile(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName("length")
    override val width: Int,
    @Optional
    override val duration: Long? = null,
    @Optional
    override val thumb: PhotoSize? = null,
    @SerialName(fileSizeField)
    @Optional
    override val fileSize: Long? = null
) : TelegramMediaFile, ThumbedMediaFile, PlayableMediaFile, SizedMediaFile {
    override val height: Int
        get() = width
}
