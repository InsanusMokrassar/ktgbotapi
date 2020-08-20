package com.github.insanusmokrassar.TelegramBotAPI.types.files

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.types.FileUniqueId
import com.github.insanusmokrassar.TelegramBotAPI.types.fileUniqueIdField
import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.utils.MimeType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoFile(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: FileUniqueId,
    override val width: Int,
    override val height: Int,
    override val duration: Long? = null,
    override val thumb: PhotoSize? = null,
    @SerialName(mimeTypeField)
    override val mimeType: MimeType? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null
) : TelegramMediaFile, MimedMediaFile, ThumbedMediaFile, PlayableMediaFile, SizedMediaFile
