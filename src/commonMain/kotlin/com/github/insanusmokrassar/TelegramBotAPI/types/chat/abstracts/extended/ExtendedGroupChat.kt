package com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.extended

import com.github.insanusmokrassar.TelegramBotAPI.types.chat.ChatPermissions
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.GroupChat

interface ExtendedGroupChat : GroupChat, ExtendedPublicChat {
    val permissions: ChatPermissions
}