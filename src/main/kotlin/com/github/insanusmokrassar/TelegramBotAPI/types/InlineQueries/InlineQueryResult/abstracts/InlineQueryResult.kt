package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.idField
import com.github.insanusmokrassar.TelegramBotAPI.types.replyMarkupField
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName

interface InlineQueryResult {
    val type: String
    val id: String
    val replyMarkup: InlineKeyboardMarkup?
}