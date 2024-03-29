package dev.inmo.tgbotapi.requests.chat.forum

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.builtins.serializer

sealed interface ModifyForumRequest : ForumRequest<Boolean> {
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
}
