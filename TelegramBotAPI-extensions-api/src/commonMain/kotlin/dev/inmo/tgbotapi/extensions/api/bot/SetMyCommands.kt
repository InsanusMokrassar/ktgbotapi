package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.SetMyCommands
import dev.inmo.tgbotapi.types.BotCommand

suspend fun TelegramBot.setMyCommands(
    commands: List<BotCommand>
) = execute(SetMyCommands(commands))

suspend fun TelegramBot.setMyCommands(
    vararg commands: BotCommand
) = setMyCommands(commands.toList())
