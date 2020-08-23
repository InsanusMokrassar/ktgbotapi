package com.github.insanusmokrassar.TelegramBotAPI.types.CallbackQuery

import com.github.insanusmokrassar.TelegramBotAPI.types.CallbackQueryIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.User

interface CallbackQuery {
    val id: CallbackQueryIdentifier
    val user: User
    val chatInstance: String
}

data class UnknownCallbackQueryType(
    override val id: CallbackQueryIdentifier,
    override val user: User,
    override val chatInstance: String,
    val raw: String
) : CallbackQuery
