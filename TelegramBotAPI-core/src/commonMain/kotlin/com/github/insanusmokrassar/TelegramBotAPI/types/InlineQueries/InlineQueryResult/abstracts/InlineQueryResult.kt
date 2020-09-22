package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.serializers.InlineQueryResultSerializer
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueryIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import kotlinx.serialization.Serializable

@Serializable(InlineQueryResultSerializer::class)
interface InlineQueryResult {
    val type: String
    val id: InlineQueryIdentifier
    val replyMarkup: InlineKeyboardMarkup?
}