package com.github.insanusmokrassar.TelegramBotAPI.types.message.ChatEvents

import com.github.insanusmokrassar.TelegramBotAPI.types.message.ChatEvents.abstracts.CommonEvent

data class NewChatTitle(
    val title: String
): CommonEvent
