package com.github.insanusmokrassar.TelegramBotAPI.requests.edit.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineMessageIdentifier
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer

interface EditInlineMessage : SimpleRequest<Boolean> {
    val inlineMessageId: InlineMessageIdentifier
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = BooleanSerializer
}