package dev.inmo.tgbotapi.utils.passport

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.DownloadFile
import dev.inmo.tgbotapi.requests.get.GetFile
import dev.inmo.tgbotapi.types.passport.credentials.EncryptedCredentials
import dev.inmo.tgbotapi.types.passport.encrypted.PassportFile
import dev.inmo.tgbotapi.utils.nonstrictJsonFormat
import kotlinx.serialization.json.JsonObject

interface Decryptor {
    fun ByteArray.decrypt(): ByteArray
}

suspend fun Decryptor.decrypt(
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
fun Decryptor.decryptData(
    data: EncryptedCredentials
) = nonstrictJsonFormat.decodeFromString(
    JsonObject.serializer(),
    data.data.decrypt().decodeToString()
)
