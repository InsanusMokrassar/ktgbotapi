package com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.extended

import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage

interface ExtendedPublicChat : ExtendedChat, PublicChat {
    val description: String
    val inviteLink: String?
    val pinnedMessage: RawMessage?
}