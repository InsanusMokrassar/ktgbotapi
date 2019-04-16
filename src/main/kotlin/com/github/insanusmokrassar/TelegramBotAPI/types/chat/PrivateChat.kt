package com.github.insanusmokrassar.TelegramBotAPI.types.chat

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.UsernameChat

data class PrivateChat(
    override val id: ChatId,
    override val username: Username? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    override val chatPhoto: ChatPhoto? = null
) : Chat, UsernameChat
