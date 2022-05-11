package dev.inmo.tgbotapi.bot.Ktor

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.utils.*

@RiskFeature
@Deprecated("Replaced", ReplaceWith("createTelegramBotDefaultKtorCallRequestsFactories", "dev.inmo.tgbotapi.bot.ktor.createTelegramBotDefaultKtorCallRequestsFactories"))
fun createTelegramBotDefaultKtorCallRequestsFactories() = dev.inmo.tgbotapi.bot.ktor.createTelegramBotDefaultKtorCallRequestsFactories()

@Deprecated("Replaced", ReplaceWith("KtorRequestsExecutor", "dev.inmo.tgbotapi.bot.ktor.KtorRequestsExecutor"))
typealias KtorRequestsExecutor = dev.inmo.tgbotapi.bot.ktor.KtorRequestsExecutor

@Deprecated("Replaced", ReplaceWith("KtorRequestsExecutorBuilder", "dev.inmo.tgbotapi.bot.ktor.KtorRequestsExecutorBuilder"))
typealias KtorRequestsExecutorBuilder = dev.inmo.tgbotapi.bot.ktor.KtorRequestsExecutorBuilder

@Deprecated("telegramBot", ReplaceWith("createTelegramBotDefaultKtorCallRequestsFactories", "dev.inmo.tgbotapi.bot.ktor.telegramBot"))
inline fun telegramBot(
    telegramAPIUrlsKeeper: TelegramAPIUrlsKeeper,
    builder: KtorRequestsExecutorBuilder.() -> Unit = {}
): TelegramBot = dev.inmo.tgbotapi.bot.ktor.telegramBot(telegramAPIUrlsKeeper, builder)

/**
 * Shortcut for [telegramBot]
 */
@Suppress("NOTHING_TO_INLINE")
@Deprecated("telegramBot", ReplaceWith("createTelegramBotDefaultKtorCallRequestsFactories", "dev.inmo.tgbotapi.bot.ktor.telegramBot"))
inline fun telegramBot(
    token: String,
    apiUrl: String = telegramBotAPIDefaultUrl,
    testServer: Boolean = false,
    builder: KtorRequestsExecutorBuilder.() -> Unit = {}
): TelegramBot = telegramBot(TelegramAPIUrlsKeeper(token, testServer, apiUrl), builder)
