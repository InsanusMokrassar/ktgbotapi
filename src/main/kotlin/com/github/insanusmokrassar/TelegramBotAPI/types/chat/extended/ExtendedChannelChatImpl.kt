package com.github.insanusmokrassar.TelegramBotAPI.types.chat.extended

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.extended.ExtendedChannelChat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage

data class ExtendedChannelChatImpl(
    override val id: ChatId,
    override val title: String,
    override val username: Username?,
    override val chatPhoto: ChatPhoto,
    override val description: String,
    override val inviteLink: String?,
    override val pinnedMessage: RawMessage?
) : ExtendedChannelChat
