package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup

interface InlineQueryResult {
    val type: String
    val id: String
    val replyMarkup: InlineKeyboardMarkup?
}