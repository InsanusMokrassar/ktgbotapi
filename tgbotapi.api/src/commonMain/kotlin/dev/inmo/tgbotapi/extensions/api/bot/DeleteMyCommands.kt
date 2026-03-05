package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.micro_utils.language_codes.IetfLang
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.DeleteMyCommands
import dev.inmo.tgbotapi.types.commands.BotCommandScope
import dev.inmo.tgbotapi.types.commands.BotCommandScopeDefault

public suspend fun TelegramBot.deleteMyCommands(
    scope: BotCommandScope = BotCommandScopeDefault,
    languageCode: IetfLang?
): Unit = execute(DeleteMyCommands(scope, languageCode))

public suspend fun TelegramBot.deleteMyCommands(
    scope: BotCommandScope = BotCommandScopeDefault,
    languageCode: String? = null
): Unit = deleteMyCommands(scope, languageCode ?.let(::IetfLang))
