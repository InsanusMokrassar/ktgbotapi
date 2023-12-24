package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.micro_utils.language_codes.IetfLang
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.GetMyCommands
import dev.inmo.tgbotapi.types.commands.BotCommandScope
import dev.inmo.tgbotapi.types.commands.BotCommandScopeDefault

suspend fun TelegramBot.getMyCommands(
    scope: BotCommandScope = BotCommandScopeDefault,
    languageCode: IetfLang? = null
) = execute(GetMyCommands(scope, languageCode))

suspend fun TelegramBot.getMyCommands(
    scope: BotCommandScope = BotCommandScopeDefault,
    languageCode: String?
) = getMyCommands(scope, languageCode ?.let(::IetfLang))
