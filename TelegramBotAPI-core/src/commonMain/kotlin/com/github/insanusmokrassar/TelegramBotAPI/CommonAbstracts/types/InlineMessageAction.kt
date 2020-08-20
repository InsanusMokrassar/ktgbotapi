package com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types

import com.github.insanusmokrassar.TelegramBotAPI.types.InlineMessageIdentifier

interface InlineMessageAction {
    val inlineMessageId: InlineMessageIdentifier
}