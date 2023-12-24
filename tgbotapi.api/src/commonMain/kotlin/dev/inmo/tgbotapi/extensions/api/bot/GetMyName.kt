package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.micro_utils.language_codes.IetfLang
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.GetMyName

suspend fun TelegramBot.getMyName(
    languageCode: IetfLang? = null
) = execute(GetMyName(languageCode))

suspend fun TelegramBot.getMyName(
    languageCode: String?
) = getMyName(languageCode ?.let(::IetfLang))
