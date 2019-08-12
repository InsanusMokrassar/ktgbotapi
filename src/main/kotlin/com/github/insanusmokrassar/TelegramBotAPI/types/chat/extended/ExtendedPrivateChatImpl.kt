package com.github.insanusmokrassar.TelegramBotAPI.types.chat.extended

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.extended.ExtendedPrivateChat

data class ExtendedPrivateChatImpl(
    override val id: ChatId,
    override val username: Username? = null,
    override val firstName: String = "",
    override val lastName: String = "",
    override val chatPhoto: ChatPhoto
) : ExtendedPrivateChat
