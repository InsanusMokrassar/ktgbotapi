package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.micro_utils.language_codes.IetfLang
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.SetMyCommands
import dev.inmo.tgbotapi.types.BotCommand
import dev.inmo.tgbotapi.types.commands.BotCommandScope
import dev.inmo.tgbotapi.types.commands.BotCommandScopeDefault

public suspend fun TelegramBot.setMyCommands(
    commands: List<BotCommand>,
    scope: BotCommandScope = BotCommandScopeDefault,
    languageCode: IetfLang?
): Boolean = execute(SetMyCommands(commands, scope, languageCode))

public suspend fun TelegramBot.setMyCommands(
    vararg commands: BotCommand,
    scope: BotCommandScope = BotCommandScopeDefault,
    languageCode: IetfLang?
): Boolean = setMyCommands(commands.toList(), scope, languageCode)

public suspend fun TelegramBot.setMyCommands(
    commands: List<BotCommand>,
    scope: BotCommandScope = BotCommandScopeDefault,
    languageCode: String? = null
): Boolean = setMyCommands(commands, scope, languageCode ?.let(::IetfLang))

public suspend fun TelegramBot.setMyCommands(
    vararg commands: BotCommand,
    scope: BotCommandScope = BotCommandScopeDefault,
    languageCode: String? = null
): Boolean = setMyCommands(commands.toList(), scope, languageCode)
