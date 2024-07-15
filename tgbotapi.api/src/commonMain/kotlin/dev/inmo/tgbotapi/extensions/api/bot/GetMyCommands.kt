package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.micro_utils.language_codes.IetfLang
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.GetMyCommands
import dev.inmo.tgbotapi.types.BotCommand
import dev.inmo.tgbotapi.types.commands.BotCommandScope
import dev.inmo.tgbotapi.types.commands.BotCommandScopeDefault

public suspend fun TelegramBot.getMyCommands(
    scope: BotCommandScope = BotCommandScopeDefault,
    languageCode: IetfLang? = null
): List<BotCommand> = execute(GetMyCommands(scope, languageCode))

public suspend fun TelegramBot.getMyCommands(
    scope: BotCommandScope = BotCommandScopeDefault,
    languageCode: String?
): List<BotCommand> = getMyCommands(scope, languageCode ?.let(::IetfLang))
