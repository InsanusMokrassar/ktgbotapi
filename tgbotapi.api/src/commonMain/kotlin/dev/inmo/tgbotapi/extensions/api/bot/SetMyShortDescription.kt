package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.micro_utils.language_codes.IetfLang
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.SetMyShortDescription

public suspend fun TelegramBot.setMyShortDescription(
    shortDescription: String? = null,
    languageCode: IetfLang? = null
): Boolean = execute(SetMyShortDescription(shortDescription, languageCode))

public suspend fun TelegramBot.setMyShortDescription(
    shortDescription: String?,
    languageCode: String?
): Boolean = setMyShortDescription(shortDescription, languageCode ?.let(::IetfLang))
