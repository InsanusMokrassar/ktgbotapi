package com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage

interface PublicChat : Chat {
    val title: String?
    val inviteLink: String?
    val pinnedMessage: RawMessage?
}
