package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.edit.LiveLocation

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.edit.LiveLocation.StopInlineMessageLiveLocation
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineMessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup

suspend fun RequestsExecutor.stopLiveLocation(
    inlineMessageId: InlineMessageIdentifier,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(
    StopInlineMessageLiveLocation(
        inlineMessageId, replyMarkup
    )
)
