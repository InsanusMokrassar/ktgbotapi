package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.micro_utils.language_codes.IetfLang
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.SetMyShortDescription

suspend fun TelegramBot.setMyShortDescription(
    shortDescription: String? = null,
    languageCode: IetfLang? = null
) = execute(SetMyShortDescription(shortDescription, languageCode))

suspend fun TelegramBot.setMyShortDescription(
    shortDescription: String?,
    languageCode: String?
) = setMyShortDescription(shortDescription, languageCode ?.let(::IetfLang))
