package com.github.insanusmokrassar.TelegramBotAPI.types.files

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.types.FileUniqueId
import com.github.insanusmokrassar.TelegramBotAPI.types.fileUniqueIdField
import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.utils.TelegramAPIUrlsKeeper
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PathedFile(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: FileUniqueId,
    @SerialName(filePathField)
    val filePath: String,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null
): TelegramMediaFile

fun TelegramAPIUrlsKeeper.resolveFileURL(file: PathedFile): String = "$fileBaseUrl/${file.filePath}"
inline fun PathedFile.fullUrl(keeper: TelegramAPIUrlsKeeper): String = keeper.resolveFileURL(this)
