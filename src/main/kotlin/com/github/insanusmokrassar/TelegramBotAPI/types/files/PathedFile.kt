package com.github.insanusmokrassar.TelegramBotAPI.types.files

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PathedFile(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(filePathField)
    val filePath: String,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null
): TelegramMediaFile

fun PathedFile.makeFileUrl(
    botToken: String,
    apiHost: String = "https://api.telegram.org"
) = "${downloadingFilesBaseUrl(botToken, apiHost)}/$filePath"

fun downloadingFilesBaseUrl(
    botToken: String,
    apiHost: String
) = "$apiHost/file/bot$botToken"
