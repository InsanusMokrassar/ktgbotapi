package com.github.insanusmokrassar.TelegramBotAPI.types.message.ChatEvents

import com.github.insanusmokrassar.TelegramBotAPI.types.User
import com.github.insanusmokrassar.TelegramBotAPI.types.message.ChatEvents.abstracts.GroupEvent

data class LeftChatMember(
    val user: User
): GroupEvent
