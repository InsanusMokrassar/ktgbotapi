package com.github.insanusmokrassar.TelegramBotAPI.types.message.content

import com.github.insanusmokrassar.TelegramBotAPI.types.games.Game
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MessageContent

data class GameContent(
    val game: Game
) : MessageContent