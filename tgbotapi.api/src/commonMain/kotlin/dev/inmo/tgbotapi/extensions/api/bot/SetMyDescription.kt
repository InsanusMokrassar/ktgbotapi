package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.micro_utils.language_codes.IetfLanguageCode
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.GetMyCommands
import dev.inmo.tgbotapi.requests.bot.GetMyDescription
import dev.inmo.tgbotapi.requests.bot.SetMyDescription
import dev.inmo.tgbotapi.types.commands.BotCommandScope
import dev.inmo.tgbotapi.types.commands.BotCommandScopeDefault

suspend fun TelegramBot.setMyDescription(
    description: String? = null,
    languageCode: IetfLanguageCode? = null
) = execute(SetMyDescription(description, languageCode))

suspend fun TelegramBot.setMyDescription(
    description: String?,
    languageCode: String?
) = setMyDescription(description, languageCode ?.let(::IetfLanguageCode))
