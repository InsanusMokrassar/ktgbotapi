package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.micro_utils.language_codes.IetfLang
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.SetMyCommands
import dev.inmo.tgbotapi.types.BotCommand
import dev.inmo.tgbotapi.types.commands.BotCommandScope
import dev.inmo.tgbotapi.types.commands.BotCommandScopeDefault

suspend fun TelegramBot.setMyCommands(
    commands: List<BotCommand>,
    scope: BotCommandScope = BotCommandScopeDefault,
    languageCode: IetfLang?
) = execute(SetMyCommands(commands, scope, languageCode))

suspend fun TelegramBot.setMyCommands(
    vararg commands: BotCommand,
    scope: BotCommandScope = BotCommandScopeDefault,
    languageCode: IetfLang?
) = setMyCommands(commands.toList(), scope, languageCode)

suspend fun TelegramBot.setMyCommands(
    commands: List<BotCommand>,
    scope: BotCommandScope = BotCommandScopeDefault,
    languageCode: String? = null
) = setMyCommands(commands, scope, languageCode ?.let(::IetfLang))

suspend fun TelegramBot.setMyCommands(
    vararg commands: BotCommand,
    scope: BotCommandScope = BotCommandScopeDefault,
    languageCode: String? = null
) = setMyCommands(commands.toList(), scope, languageCode)
