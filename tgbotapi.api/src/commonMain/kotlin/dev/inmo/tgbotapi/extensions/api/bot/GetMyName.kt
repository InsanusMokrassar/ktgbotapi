package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.micro_utils.language_codes.IetfLang
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.GetMyName
import dev.inmo.tgbotapi.types.BotName

public suspend fun TelegramBot.getMyName(languageCode: IetfLang? = null): BotName = execute(GetMyName(languageCode))

public suspend fun TelegramBot.getMyName(languageCode: String?): BotName = getMyName(languageCode ?.let(::IetfLang))
