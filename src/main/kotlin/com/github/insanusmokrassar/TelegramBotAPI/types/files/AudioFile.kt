package com.github.insanusmokrassar.TelegramBotAPI.types.files

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.Performerable
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.*
import kotlinx.serialization.*

@Serializable
data class AudioFile(
    @SerialName(fileIdField)
    override val fileId: FileId,
    override val duration: Long? = null,
    override val performer: String? = null,
    override val title: String? = null,
    @SerialName(mimeTypeField)
    override val mimeType: String? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null,
    override val thumb: PhotoSize? = null
) : TelegramMediaFile, MimedMediaFile, ThumbedMediaFile, PlayableMediaFile, TitledMediaFile, Performerable
