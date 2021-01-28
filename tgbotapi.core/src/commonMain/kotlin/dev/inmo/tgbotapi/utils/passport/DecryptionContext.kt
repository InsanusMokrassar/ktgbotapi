package dev.inmo.tgbotapi.utils.passport

import dev.inmo.micro_utils.crypto.SourceBytes
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.DownloadFile
import dev.inmo.tgbotapi.requests.get.GetFile
import dev.inmo.tgbotapi.types.passport.credentials.EncryptedCredentials
import dev.inmo.tgbotapi.types.passport.credentials.EncryptedData
import dev.inmo.tgbotapi.types.passport.encrypted.PassportFile
import dev.inmo.tgbotapi.utils.nonstrictJsonFormat
import kotlinx.serialization.json.JsonObject

interface Decryptor {
    fun decrypt(data: EncryptedData): SourceBytes
}
