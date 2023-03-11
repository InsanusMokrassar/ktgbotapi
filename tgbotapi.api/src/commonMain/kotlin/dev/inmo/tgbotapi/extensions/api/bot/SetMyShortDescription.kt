package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.micro_utils.language_codes.IetfLanguageCode
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.SetMyShortDescription

suspend fun TelegramBot.setMyShortDescription(
    shortDescription: String? = null,
    languageCode: IetfLanguageCode? = null
) = execute(SetMyShortDescription(shortDescription, languageCode))

suspend fun TelegramBot.setMyShortDescription(
    shortDescription: String?,
    languageCode: String?
) = setMyShortDescription(shortDescription, languageCode ?.let(::IetfLanguageCode))
