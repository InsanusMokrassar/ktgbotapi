package com.github.insanusmokrassar.TelegramBotAPI.types.CallbackQuery

import com.github.insanusmokrassar.TelegramBotAPI.types.InlineMessageIdentifier

interface InlineMessageIdCallbackQuery : CallbackQuery {
    val inlineMessageId: InlineMessageIdentifier
}