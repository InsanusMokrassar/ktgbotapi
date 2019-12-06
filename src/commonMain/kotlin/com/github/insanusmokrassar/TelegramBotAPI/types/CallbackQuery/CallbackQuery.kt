package com.github.insanusmokrassar.TelegramBotAPI.types.CallbackQuery

import com.github.insanusmokrassar.TelegramBotAPI.types.CallbackQueryIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.User

interface CallbackQuery {
    val id: CallbackQueryIdentifier
    val user: User
    val chatInstance: String
}