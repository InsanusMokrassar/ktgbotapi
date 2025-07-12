package dev.inmo.tgbotapi.extensions.utils.extensions

import dev.inmo.tgbotapi.types.files.PathedFile
import dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.readBytes
import io.ktor.client.statement.readRawBytes

suspend fun HttpClient.loadFile(
    telegramAPIUrlsKeeper: TelegramAPIUrlsKeeper,
    filePath: String
) = get("${telegramAPIUrlsKeeper.fileBaseUrl}/$filePath").readRawBytes()

suspend fun HttpClient.loadFile(
    telegramAPIUrlsKeeper: TelegramAPIUrlsKeeper,
    pathedFile: PathedFile
) = loadFile(telegramAPIUrlsKeeper, pathedFile.filePath)

suspend fun PathedFile.download(
    telegramAPIUrlsKeeper: TelegramAPIUrlsKeeper,
    client: HttpClient = HttpClient()
) = client.loadFile(telegramAPIUrlsKeeper, this)
