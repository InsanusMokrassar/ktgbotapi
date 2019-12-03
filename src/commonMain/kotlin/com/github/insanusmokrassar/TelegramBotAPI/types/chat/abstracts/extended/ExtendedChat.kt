package com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.extended

import com.github.insanusmokrassar.TelegramBotAPI.types.ChatPhoto
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.ExtendedChatSerializer
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import kotlinx.serialization.Serializable

@Serializable(ExtendedChatSerializer::class)
interface ExtendedChat : Chat {
    val chatPhoto: ChatPhoto?
}