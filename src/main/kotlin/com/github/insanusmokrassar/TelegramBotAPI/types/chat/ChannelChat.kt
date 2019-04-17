package com.github.insanusmokrassar.TelegramBotAPI.types.chat

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage

data class ChannelChat(
    override val id: ChatId,
    override val title: String? = null,
    override val username: Username? = null,
    override val description: String? = null,
    override val inviteLink: String? = null,
    override val chatPhoto: ChatPhoto? = null,
    override val pinnedMessage: RawMessage?
) : PublicChat, UsernameChat, DescriptionChat
