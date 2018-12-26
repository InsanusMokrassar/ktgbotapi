package com.github.insanusmokrassar.TelegramBotAPI

import com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor.KtorRequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.bot.ProxySettings
import com.github.insanusmokrassar.TelegramBotAPI.bot.useWith
import com.github.insanusmokrassar.TelegramBotAPI.types.message.CommonMessageImpl
import com.github.insanusmokrassar.TelegramBotAPI.requests.get.GetUserProfilePhotos
import com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia.toInputMediaPhoto
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.SendMediaGroup
import com.github.insanusmokrassar.TelegramBotAPI.types.files.biggest
import com.github.insanusmokrassar.TelegramBotAPI.utils.extensions.*
import io.ktor.client.engine.okhttp.OkHttp
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    runBlocking {
        KtorRequestsExecutor(
            args[0],
            OkHttp.create()
        ).apply {
            // It is just template of creating requests executor
        }
    }
}
