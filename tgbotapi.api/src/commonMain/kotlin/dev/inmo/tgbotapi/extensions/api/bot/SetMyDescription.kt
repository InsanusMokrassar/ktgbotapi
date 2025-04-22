package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.micro_utils.language_codes.IetfLang
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.SetMyDescription

public suspend fun TelegramBot.setMyDescription(
    description: String? = null,
    languageCode: IetfLang? = null,
): Boolean = execute(SetMyDescription(description, languageCode))

public suspend fun TelegramBot.setMyDescription(
    description: String?,
    languageCode: String?,
): Boolean = setMyDescription(description, languageCode ?.let(::IetfLang))
