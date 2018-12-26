package com.github.insanusmokrassar.TelegramBotAPI.types.chat

interface PublicChat : Chat {
    val title: String?
    val inviteLink: String?
}
