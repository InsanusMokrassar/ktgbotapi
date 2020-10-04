package dev.inmo.tgbotapi.extensions.api.bot

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.bot.SetMyCommands
import com.github.insanusmokrassar.TelegramBotAPI.types.BotCommand

suspend fun TelegramBot.setMyCommands(
    commands: List<BotCommand>
) = execute(SetMyCommands(commands))

suspend fun TelegramBot.setMyCommands(
    vararg commands: BotCommand
) = setMyCommands(commands.toList())
