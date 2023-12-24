package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.micro_utils.language_codes.IetfLang
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.GetMyDescription

suspend fun TelegramBot.getMyDescription(
    languageCode: IetfLang? = null
) = execute(GetMyDescription(languageCode))

suspend fun TelegramBot.getMyDescription(
    languageCode: String?
) = getMyDescription(languageCode ?.let(::IetfLang))
