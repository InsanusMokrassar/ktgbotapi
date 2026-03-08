package dev.inmo.tgbotapi.requests.chat.forum

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.builtins.serializer

sealed interface ModifyForumRequest : ForumRequest<Unit> {
    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer
}
