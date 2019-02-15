package com.github.insanusmokrassar.TelegramBotAPI.types.files

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.*
import kotlinx.serialization.*

@Serializable
data class DocumentFile(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileSizeField)
    @Optional
    override val fileSize: Long? = null,
    @Optional
    override val thumb: PhotoSize? = null,
    @SerialName(mimeTypeField)
    @Optional
    override val mimeType: String? = null,
    @SerialName(fileNameField)
    @Optional
    override val fileName: String? = null
) : TelegramMediaFile, MimedMediaFile, ThumbedMediaFile, CustomNamedMediaFile
