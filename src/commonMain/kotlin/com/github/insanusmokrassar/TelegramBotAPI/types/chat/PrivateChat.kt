package com.github.insanusmokrassar.TelegramBotAPI.types.chat

import com.github.insanusmokrassar.TelegramBotAPI.types.ChatId
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatPhoto

data class PrivateChat(
    override val id: ChatId,
    val username: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    override val chatPhoto: ChatPhoto? = null
) : Chat
