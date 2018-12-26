package com.github.insanusmokrassar.TelegramBotAPI.types.files

import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import kotlinx.serialization.*

@Serializable
data class VoiceFile(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @Optional
    override val duration: Long? = null,
    @SerialName(mimeTypeField)
    @Optional
    override val mimeType: String? = null,
    @SerialName(fileSizeField)
    @Optional
    override val fileSize: Long? = null
) : TelegramMediaFile, MimedMediaFile, PlayableMediaFile
