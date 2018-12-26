package com.github.insanusmokrassar.TelegramBotAPI.types.message.ChatEvents

import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.message.ChatEvents.abstracts.SupergroupEvent

class SupergroupChatCreated(
    val migratedFrom: ChatIdentifier?
): SupergroupEvent
