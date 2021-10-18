package dev.inmo.tgbotapi.types.files

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.FileUniqueId
import dev.inmo.tgbotapi.types.fileUniqueIdField
import dev.inmo.tgbotapi.types.files.abstracts.*
import dev.inmo.tgbotapi.utils.*
import kotlinx.serialization.*

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
): TelegramMediaFile {
    @Transient
    val fileName: FileName by lazy(LazyThreadSafetyMode.PUBLICATION) {
        filePath.filenameFromUrl
    }
}

fun TelegramAPIUrlsKeeper.resolveFileURL(file: PathedFile): String = "$fileBaseUrl/${file.filePath}"
fun PathedFile.fullUrl(keeper: TelegramAPIUrlsKeeper): String = keeper.resolveFileURL(this)
