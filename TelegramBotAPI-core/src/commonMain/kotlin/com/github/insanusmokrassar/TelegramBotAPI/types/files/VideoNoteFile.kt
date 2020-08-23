package com.github.insanusmokrassar.TelegramBotAPI.types.files

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.types.FileUniqueId
import com.github.insanusmokrassar.TelegramBotAPI.types.fileUniqueIdField
import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoNoteFile(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: FileUniqueId,
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
