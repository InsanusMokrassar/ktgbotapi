package dev.inmo.tgbotapi.utils.passport

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.DownloadFile
import dev.inmo.tgbotapi.requests.get.GetFile
import dev.inmo.tgbotapi.types.passport.encrypted_data.PassportFile

expect class DecryptionContext(
    key: String
) {
    fun ByteArray.decrypt(): ByteArray
}

suspend fun DecryptionContext.decrypt(
    file: PassportFile,
    bot: TelegramBot
): ByteArray {
    return bot.execute(
        DownloadFile(
            bot.execute(
                GetFile(file.fileId)
            ).filePath
        )
    ).decrypt()
}
