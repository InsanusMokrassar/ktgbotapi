package dev.inmo.tgbotapi.requests.edit.abstracts

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.InlineMessageIdentifier
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.builtins.serializer

interface EditInlineMessage : SimpleRequest<Boolean> {
    val inlineMessageId: InlineMessageIdentifier
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
}