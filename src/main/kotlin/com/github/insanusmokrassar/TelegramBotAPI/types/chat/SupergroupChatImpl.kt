package com.github.insanusmokrassar.TelegramBotAPI.types.chat

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.GroupChat
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.SupergroupChat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage

data class SupergroupChatImpl(
    override val id: ChatId,
    override val title: String,
    override val username: Username? = null
) : SupergroupChat
