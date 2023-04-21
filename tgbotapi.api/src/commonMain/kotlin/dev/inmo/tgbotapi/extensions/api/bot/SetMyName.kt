package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.micro_utils.language_codes.IetfLanguageCode
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.GetMyCommands
import dev.inmo.tgbotapi.requests.bot.GetMyName
import dev.inmo.tgbotapi.requests.bot.SetMyName
import dev.inmo.tgbotapi.types.commands.BotCommandScope
import dev.inmo.tgbotapi.types.commands.BotCommandScopeDefault

suspend fun TelegramBot.setMyName(
    name: String? = null,
    languageCode: IetfLanguageCode? = null
) = execute(SetMyName(name, languageCode))

suspend fun TelegramBot.setMyName(
    name: String?,
    languageCode: String?
) = setMyName(name, languageCode ?.let(::IetfLanguageCode))
