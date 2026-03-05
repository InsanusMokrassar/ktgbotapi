package dev.inmo.tgbotapi.requests.edit.abstracts

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.InlineMessageId
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.builtins.serializer

interface EditInlineMessage : SimpleRequest<Unit> {
    val inlineMessageId: InlineMessageId
    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer
}