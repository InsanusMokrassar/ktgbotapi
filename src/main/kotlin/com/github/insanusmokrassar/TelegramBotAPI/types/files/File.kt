package com.github.insanusmokrassar.TelegramBotAPI.types.files

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.*
import kotlinx.serialization.*

@Serializable
data class File(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileSizeField)
    @Optional
    override val fileSize: Long? = null
): TelegramMediaFile
