package com.github.insanusmokrassar.TelegramBotAPI.types.files

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TelegramFile(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null,
    @SerialName("file_path")
    val filePath: String? = null
) : TelegramMediaFile
