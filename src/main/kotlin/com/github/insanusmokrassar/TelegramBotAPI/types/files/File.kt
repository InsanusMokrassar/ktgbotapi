package com.github.insanusmokrassar.TelegramBotAPI.types.files

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class File(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null
): TelegramMediaFile
