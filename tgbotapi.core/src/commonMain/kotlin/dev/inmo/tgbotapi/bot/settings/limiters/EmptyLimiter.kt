package dev.inmo.tgbotapi.bot.settings.limiters

object EmptyLimiter : RequestLimiter {
    override suspend fun <T> limit(block: suspend () -> T): T = block()
}
