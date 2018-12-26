package com.github.insanusmokrassar.TelegramBotAPI.types.chat

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage

data class ChannelChat(
    override val id: ChatId,
    override val title: String? = null,
    val username: String? = null,
    val description: String? = null,
    override val inviteLink: String? = null,
    override val chatPhoto: ChatPhoto? = null,
    val pinnedMessage: RawMessage?
) : PublicChat
