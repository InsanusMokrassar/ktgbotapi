package com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.extensions

import com.github.insanusmokrassar.TelegramBotAPI.types.files.PathedFile
import com.github.insanusmokrassar.TelegramBotAPI.utils.TelegramAPIUrlsKeeper
import io.ktor.client.HttpClient
import io.ktor.client.request.get

suspend fun HttpClient.loadFile(
    telegramAPIUrlsKeeper: TelegramAPIUrlsKeeper,
    filePath: String
) = get<ByteArray>("${telegramAPIUrlsKeeper.fileBaseUrl}/$filePath")

suspend fun HttpClient.loadFile(
    telegramAPIUrlsKeeper: TelegramAPIUrlsKeeper,
    pathedFile: PathedFile
) = loadFile(telegramAPIUrlsKeeper, pathedFile.filePath)

suspend fun PathedFile.download(
    telegramAPIUrlsKeeper: TelegramAPIUrlsKeeper,
    client: HttpClient = HttpClient()
) = client.loadFile(telegramAPIUrlsKeeper, this)
