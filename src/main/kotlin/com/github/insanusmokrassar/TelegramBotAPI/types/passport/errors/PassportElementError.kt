package com.github.insanusmokrassar.TelegramBotAPI.types.passport.errors

interface PassportElementError {
    val source: String
    val type: String
    val message: String
}