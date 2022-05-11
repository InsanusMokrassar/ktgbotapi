package dev.inmo.tgbotapi.utils.passport

import dev.inmo.micro_utils.crypto.SourceBytes
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.DownloadFile
import dev.inmo.tgbotapi.requests.get.GetFile
import dev.inmo.tgbotapi.types.passport.credentials.*
import dev.inmo.tgbotapi.types.files.PassportFile

fun EndDataCredentials.decryptData(
    bytes: EncryptedData
): SourceBytes {
    return createDecryptor().decrypt(bytes)
}

fun FileCredentials.decryptFile(
    fileBytes: ByteArray
): SourceBytes {
    return createDecryptor().decrypt(fileBytes)
}
suspend fun FileCredentials.decryptFile(
    bot: TelegramBot,
    passportFile: PassportFile
): SourceBytes {
    val pathedFile = bot.execute(GetFile(passportFile.fileId))
    val bytes = bot.execute(DownloadFile(pathedFile.filePath))
    return decryptFile(bytes)
}
