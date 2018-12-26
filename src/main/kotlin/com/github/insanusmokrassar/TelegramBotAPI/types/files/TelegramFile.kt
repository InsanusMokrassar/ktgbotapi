package com.github.insanusmokrassar.TelegramBotAPI.types.files

import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import kotlinx.serialization.*

@Serializable
data class TelegramFile(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileSizeField)
    @Optional
    override val fileSize: Long? = null,
    @SerialName("file_path")
    @Optional
    val filePath: String? = null
) : TelegramMediaFile
