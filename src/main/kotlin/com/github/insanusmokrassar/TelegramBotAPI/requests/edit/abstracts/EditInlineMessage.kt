package com.github.insanusmokrassar.TelegramBotAPI.requests.edit.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineMessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage

interface EditInlineMessage : SimpleRequest<RawMessage> {
    val inlineMessageId: InlineMessageIdentifier
}