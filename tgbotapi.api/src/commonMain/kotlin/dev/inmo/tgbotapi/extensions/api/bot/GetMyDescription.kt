package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.micro_utils.language_codes.IetfLang
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.GetMyDescription
import dev.inmo.tgbotapi.types.BotDescription

public suspend fun TelegramBot.getMyDescription(
    languageCode: IetfLang? = null
): BotDescription = execute(GetMyDescription(languageCode))

public suspend fun TelegramBot.getMyDescription(
    languageCode: String?
): BotDescription = getMyDescription(languageCode ?.let(::IetfLang))
