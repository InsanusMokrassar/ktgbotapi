package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.micro_utils.language_codes.IetfLanguageCode
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.GetMyCommands
import dev.inmo.tgbotapi.requests.bot.GetMyShortDescription
import dev.inmo.tgbotapi.types.commands.BotCommandScope
import dev.inmo.tgbotapi.types.commands.BotCommandScopeDefault

suspend fun TelegramBot.getMyShortDescription(
    languageCode: IetfLanguageCode? = null
) = execute(GetMyShortDescription(languageCode))

suspend fun TelegramBot.getMyShortDescription(
    languageCode: String?
) = getMyShortDescription(languageCode ?.let(::IetfLanguageCode))
