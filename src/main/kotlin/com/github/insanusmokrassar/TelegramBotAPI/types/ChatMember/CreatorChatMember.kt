package com.github.insanusmokrassar.TelegramBotAPI.types.ChatMember

import com.github.insanusmokrassar.TelegramBotAPI.types.User

data class CreatorChatMember(override val user: User) : ChatMember
