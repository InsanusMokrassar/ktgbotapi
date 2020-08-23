package com.github.insanusmokrassar.TelegramBotAPI.types.message.ChatEvents

import com.github.insanusmokrassar.TelegramBotAPI.types.User
import com.github.insanusmokrassar.TelegramBotAPI.types.message.ChatEvents.abstracts.GroupEvent
import com.github.insanusmokrassar.TelegramBotAPI.types.message.ChatEvents.abstracts.SupergroupEvent

data class NewChatMembers(
    val members: List<User>
): GroupEvent, SupergroupEvent
