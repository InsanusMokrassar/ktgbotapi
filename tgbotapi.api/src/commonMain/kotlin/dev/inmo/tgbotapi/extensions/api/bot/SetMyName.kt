package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.micro_utils.language_codes.IetfLang
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.SetMyName

public suspend fun TelegramBot.setMyName(
    name: String? = null,
    languageCode: IetfLang? = null,
): Boolean = execute(SetMyName(name, languageCode))

public suspend fun TelegramBot.setMyName(
    name: String?,
    languageCode: String?,
): Boolean = setMyName(name, languageCode ?.let(::IetfLang))
