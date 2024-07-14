package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.micro_utils.language_codes.IetfLang
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.GetMyCommands
import dev.inmo.tgbotapi.requests.bot.GetMyShortDescription
import dev.inmo.tgbotapi.types.BotShortDescription
import dev.inmo.tgbotapi.types.commands.BotCommandScope
import dev.inmo.tgbotapi.types.commands.BotCommandScopeDefault

public suspend fun TelegramBot.getMyShortDescription(
    languageCode: IetfLang? = null
): BotShortDescription = execute(GetMyShortDescription(languageCode))

public suspend fun TelegramBot.getMyShortDescription(
    languageCode: String?
): BotShortDescription = getMyShortDescription(languageCode ?.let(::IetfLang))
