package dev.inmo.tgbotapi.extensions.api.files

import dev.inmo.micro_utils.coroutines.doOutsideOfCoroutine
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.get.getFileAdditionalInfo
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.files.PathedFile
import dev.inmo.tgbotapi.types.files.TelegramMediaFile
import dev.inmo.tgbotapi.types.message.content.MediaContent
import io.ktor.util.cio.use
import io.ktor.util.cio.writeChannel
import io.ktor.utils.io.copyAndClose
import kotlinx.coroutines.job
import java.io.File
import kotlin.coroutines.coroutineContext

public suspend fun TelegramBot.downloadFile(
    filePath: String,
    destFile: File,
): File {
    val readChannel = downloadFileStream(filePath)

    destFile.deleteRecursively()
    destFile.parentFile.mkdirs()
    doOutsideOfCoroutine { destFile.createNewFile() }

    destFile.writeChannel(coroutineContext.job).use {
        readChannel.copyAndClose(this)
    }

    return destFile
}

public suspend fun TelegramBot.downloadFile(
    pathedFile: PathedFile,
    destFile: File,
): File =
    downloadFile(
        pathedFile.filePath,
        destFile,
    )

public suspend fun TelegramBot.downloadFile(
    fileId: FileId,
    destFile: File,
): File =
    downloadFile(
        getFileAdditionalInfo(fileId),
        destFile,
    )

public suspend fun TelegramBot.downloadFile(
    file: TelegramMediaFile,
    destFile: File,
): File =
    downloadFile(
        getFileAdditionalInfo(file),
        destFile,
    )

public suspend fun TelegramBot.downloadFile(
    file: MediaContent,
    destFile: File,
): File =
    downloadFile(
        getFileAdditionalInfo(file.media),
        destFile,
    )
