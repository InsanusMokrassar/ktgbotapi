package com.github.insanusmokrassar.TelegramBotAPI.types.passport

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.types.fileDateField
import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.fileIdField
import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.fileSizeField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.joda.time.DateTime

@Serializable
data class PassportFile(
    @SerialName(fileIdField)
    val fileId: FileId,
    @SerialName(fileSizeField)
    val fileSize: Long,
    @SerialName(fileDateField)
    val fileDate: DateTime
)