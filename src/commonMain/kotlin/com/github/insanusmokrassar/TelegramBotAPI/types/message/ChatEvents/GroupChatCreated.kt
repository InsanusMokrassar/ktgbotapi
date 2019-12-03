package com.github.insanusmokrassar.TelegramBotAPI.types.message.ChatEvents

import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.message.ChatEvents.abstracts.GroupEvent

class GroupChatCreated(
    val migratedTo: ChatIdentifier?
): GroupEvent
