package com.github.insanusmokrassar.TelegramBotAPI.bot.settings.limiters

object EmptyLimiter : RequestLimiter {
    override suspend fun <T> limit(block: suspend () -> T): T = block()
}
