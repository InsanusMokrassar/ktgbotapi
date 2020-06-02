package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.bot

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.bot.SetMyCommands
import com.github.insanusmokrassar.TelegramBotAPI.types.BotCommand

suspend fun RequestsExecutor.setMyCommands(
    commands: List<BotCommand>
) = execute(SetMyCommands(commands))

suspend fun RequestsExecutor.setMyCommands(
    vararg commands: BotCommand
) = setMyCommands(commands.toList())
