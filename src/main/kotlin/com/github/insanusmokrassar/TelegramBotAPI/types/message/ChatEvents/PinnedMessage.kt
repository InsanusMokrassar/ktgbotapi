package com.github.insanusmokrassar.TelegramBotAPI.types.message.ChatEvents

import com.github.insanusmokrassar.TelegramBotAPI.types.message.ChatEvents.abstracts.CommonEvent
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.Message

data class PinnedMessage(
    val message: Message
): CommonEvent
