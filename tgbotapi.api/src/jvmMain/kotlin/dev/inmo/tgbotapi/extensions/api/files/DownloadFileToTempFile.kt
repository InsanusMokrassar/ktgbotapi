package dev.inmo.tgbotapi.extensions.api.files

import com.benasher44.uuid.uuid4
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.get.getFileAdditionalInfo
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.files.PathedFile
import dev.inmo.tgbotapi.types.files.TelegramMediaFile
import dev.inmo.tgbotapi.types.message.content.MediaContent
import dev.inmo.tgbotapi.utils.fileExtension
import java.io.File

public suspend fun TelegramBot.downloadFileToTemp(filePath: String): File {
    return downloadFile(
        filePath,
        File.createTempFile(uuid4().toString(), "_temp").apply {
            deleteOnExit()
        },
    )
}

public suspend fun TelegramBot.downloadFileToTemp(pathedFile: PathedFile): File =
    downloadFileToTemp(
        pathedFile.filePath,
    ).run {
        val newFile = File(parentFile, "$nameWithoutExtension.${pathedFile.fileName.fileExtension}")
        val success =
            runCatching {
                renameTo(newFile)
            }.getOrElse { false }
        if (success) {
            newFile
        } else {
            this@run
        }
    }

public suspend fun TelegramBot.downloadFileToTemp(fileId: FileId): File =
    downloadFileToTemp(
        getFileAdditionalInfo(fileId),
    )

public suspend fun TelegramBot.downloadFileToTemp(file: TelegramMediaFile): File =
    downloadFileToTemp(
        getFileAdditionalInfo(file),
    )

public suspend fun TelegramBot.downloadFileToTemp(file: MediaContent): File =
    downloadFileToTemp(
        getFileAdditionalInfo(file.media),
    )
