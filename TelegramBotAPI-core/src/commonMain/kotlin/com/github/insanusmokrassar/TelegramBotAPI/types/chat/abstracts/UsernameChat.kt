package com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.Username

interface UsernameChat : Chat {
    val username: Username?
}
