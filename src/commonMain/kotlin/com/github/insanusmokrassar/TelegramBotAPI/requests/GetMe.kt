package com.github.insanusmokrassar.TelegramBotAPI.requests

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.User
import kotlinx.serialization.*

@Serializable
class GetMe : SimpleRequest<User> {
    override fun method(): String = "getMe"
    override val resultDeserializer: DeserializationStrategy<User>
        get() = User.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}