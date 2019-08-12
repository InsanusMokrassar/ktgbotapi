package com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.chat.ChatPermissions

interface GroupChat : PublicChat {
    val permissions: ChatPermissions?
}
