package com.github.insanusmokrassar.TelegramBotAPI.requests.edit.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineMessageIdentifier
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.builtins.serializer

interface EditInlineMessage : SimpleRequest<Boolean> {
    val inlineMessageId: InlineMessageIdentifier
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
}