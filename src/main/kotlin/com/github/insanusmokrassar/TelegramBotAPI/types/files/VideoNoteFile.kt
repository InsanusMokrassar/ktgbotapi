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
    override val duration: Long? = null,
    override val thumb: PhotoSize? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null
) : TelegramMediaFile, ThumbedMediaFile, PlayableMediaFile, SizedMediaFile {
    override val height: Int
        get() = width
}
