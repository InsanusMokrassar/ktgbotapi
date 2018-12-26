package com.github.insanusmokrassar.TelegramBotAPI.types.CallbackQuery

interface DataCallbackQuery : CallbackQuery {
    val data: String
}
