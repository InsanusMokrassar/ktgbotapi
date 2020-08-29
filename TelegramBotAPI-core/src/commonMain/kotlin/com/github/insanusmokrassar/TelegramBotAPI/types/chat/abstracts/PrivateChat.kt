package com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts

interface PrivateChat : Chat, UsernameChat {
    val firstName: String
    val lastName: String
}
