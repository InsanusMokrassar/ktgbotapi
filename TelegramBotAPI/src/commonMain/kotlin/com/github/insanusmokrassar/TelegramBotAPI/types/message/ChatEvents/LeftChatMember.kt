package com.github.insanusmokrassar.TelegramBotAPI.types.message.ChatEvents

import com.github.insanusmokrassar.TelegramBotAPI.types.User
import com.github.insanusmokrassar.TelegramBotAPI.types.message.ChatEvents.abstracts.CommonEvent

data class LeftChatMember(
    val user: User
): CommonEvent
