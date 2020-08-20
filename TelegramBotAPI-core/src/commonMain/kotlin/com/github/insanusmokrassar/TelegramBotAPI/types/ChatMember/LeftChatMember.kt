package com.github.insanusmokrassar.TelegramBotAPI.types.ChatMember

import com.github.insanusmokrassar.TelegramBotAPI.types.ChatMember.abstracts.ChatMember
import com.github.insanusmokrassar.TelegramBotAPI.types.User

data class LeftChatMember(override val user: User) :
    ChatMember
