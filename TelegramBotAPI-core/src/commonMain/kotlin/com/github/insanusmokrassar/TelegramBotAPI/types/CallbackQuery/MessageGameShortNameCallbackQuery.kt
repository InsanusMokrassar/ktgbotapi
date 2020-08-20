package com.github.insanusmokrassar.TelegramBotAPI.types.CallbackQuery

import com.github.insanusmokrassar.TelegramBotAPI.types.CallbackQueryIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.User
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.Message

data class MessageGameShortNameCallbackQuery(
    override val id: CallbackQueryIdentifier,
    override val user: User,
    override val chatInstance: String,
    override val message: Message,
    override val gameShortName: String
) : GameShortNameCallbackQuery, MessageCallbackQuery
