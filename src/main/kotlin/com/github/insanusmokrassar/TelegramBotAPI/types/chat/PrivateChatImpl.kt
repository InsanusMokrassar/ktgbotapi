package com.github.insanusmokrassar.TelegramBotAPI.types.chat

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.*

data class PrivateChatImpl(
    override val id: ChatId,
    override val username: Username? = null,
    override val firstName: String = "",
    override val lastName: String = ""
) : PrivateChat
