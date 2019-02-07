package com.github.insanusmokrassar.TelegramBotAPI.requests.edit.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineMessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage
import kotlinx.serialization.KSerializer
import kotlinx.serialization.internal.BooleanSerializer

interface EditInlineMessage : SimpleRequest<Boolean> {
    val inlineMessageId: InlineMessageIdentifier
    override fun resultSerializer(): KSerializer<Boolean> = BooleanSerializer
}