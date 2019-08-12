package com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.chat.ChatPermissions

interface GroupChat : PublicChat, DescriptionChat {
    val permissions: ChatPermissions?
}
