package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.micro_utils.language_codes.IetfLanguageCode
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.GetMyCommands
import dev.inmo.tgbotapi.requests.bot.GetMyName
import dev.inmo.tgbotapi.types.commands.BotCommandScope
import dev.inmo.tgbotapi.types.commands.BotCommandScopeDefault

suspend fun TelegramBot.getMyName(
    languageCode: IetfLanguageCode? = null
) = execute(GetMyName(languageCode))

suspend fun TelegramBot.getMyName(
    languageCode: String?
) = getMyName(languageCode ?.let(::IetfLanguageCode))
