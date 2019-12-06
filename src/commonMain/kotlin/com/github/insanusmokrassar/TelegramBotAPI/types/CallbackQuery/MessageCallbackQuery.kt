package com.github.insanusmokrassar.TelegramBotAPI.types.CallbackQuery

import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.Message

interface MessageCallbackQuery : CallbackQuery {
    val message: Message
}