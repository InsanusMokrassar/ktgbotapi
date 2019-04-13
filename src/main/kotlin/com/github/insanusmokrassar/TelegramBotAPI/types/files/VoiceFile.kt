package com.github.insanusmokrassar.TelegramBotAPI.types.files

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.*
import kotlinx.serialization.*

@Serializable
data class VoiceFile(
    @SerialName(fileIdField)
    override val fileId: FileId,
    override val duration: Long? = null,
    @SerialName(mimeTypeField)
    override val mimeType: String? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null
) : TelegramMediaFile, MimedMediaFile, PlayableMediaFile
