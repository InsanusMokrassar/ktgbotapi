package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.micro_utils.language_codes.IetfLanguageCode
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.GetMyCommands
import dev.inmo.tgbotapi.requests.bot.GetMyDescription
import dev.inmo.tgbotapi.types.commands.BotCommandScope
import dev.inmo.tgbotapi.types.commands.BotCommandScopeDefault

suspend fun TelegramBot.getMyDescription(
    languageCode: IetfLanguageCode? = null
) = execute(GetMyDescription(languageCode))

suspend fun TelegramBot.getMyDescription(
    languageCode: String?
) = getMyDescription(languageCode ?.let(::IetfLanguageCode))
