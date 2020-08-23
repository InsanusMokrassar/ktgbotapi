package com.github.insanusmokrassar.TelegramBotAPI.bot.settings.limiters

interface RequestLimiter {
    /**
     * Use limit for working of block (like delay between or after, for example)
     */
    suspend fun <T> limit(block: suspend () -> T): T
}
