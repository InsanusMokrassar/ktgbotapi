package dev.inmo.tgbotapi.extensions.api.files

import com.benasher44.uuid.uuid4
import dev.inmo.micro_utils.common.filename
import dev.inmo.micro_utils.coroutines.doOutsideOfCoroutine
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.get.getFileAdditionalInfo
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.files.PathedFile
import dev.inmo.tgbotapi.types.files.TelegramMediaFile
import dev.inmo.tgbotapi.types.message.content.MediaContent
import dev.inmo.tgbotapi.utils.fileExtension
import io.ktor.util.cio.use
import io.ktor.util.cio.writeChannel
import io.ktor.utils.io.copyTo
import kotlinx.coroutines.job
import java.io.File
import kotlin.coroutines.coroutineContext

suspend fun TelegramBot.downloadFileToTemp(
    filePath: String
): File {
    return downloadFile(
        filePath,
        File.createTempFile(uuid4().toString(), "_temp").apply {
            deleteOnExit()
        }
    )
}

suspend fun TelegramBot.downloadFileToTemp(
    pathedFile: PathedFile
): File = downloadFileToTemp(
    pathedFile.filePath
).run {
    val newFile = File(parentFile, "$nameWithoutExtension.${pathedFile.fileName.fileExtension}")
    val success = runCatching {
        renameTo(newFile)
    }.getOrElse { false }
    if (success) {
        newFile
    } else {
        this@run
    }
}

suspend fun TelegramBot.downloadFileToTemp(
    fileId: FileId
) = downloadFileToTemp(
    getFileAdditionalInfo(fileId)
)

suspend fun TelegramBot.downloadFileToTemp(
    file: TelegramMediaFile
): File = downloadFileToTemp(
    getFileAdditionalInfo(file)
)

suspend fun TelegramBot.downloadFileToTemp(
    file: MediaContent
) = downloadFileToTemp(
    getFileAdditionalInfo(file.media)
)
