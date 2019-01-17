package com.github.insanusmokrassar.TelegramBotAPI

import com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor.KtorRequestsExecutor
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
