package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.micro_utils.language_codes.IetfLang
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.GetMyCommands
import dev.inmo.tgbotapi.requests.bot.GetMyDescription
import dev.inmo.tgbotapi.requests.bot.SetMyDescription
import dev.inmo.tgbotapi.types.commands.BotCommandScope
import dev.inmo.tgbotapi.types.commands.BotCommandScopeDefault

public suspend fun TelegramBot.setMyDescription(
    description: String? = null,
    languageCode: IetfLang? = null
): Unit = execute(SetMyDescription(description, languageCode))

public suspend fun TelegramBot.setMyDescription(
    description: String?,
    languageCode: String?
): Unit = setMyDescription(description, languageCode ?.let(::IetfLang))
