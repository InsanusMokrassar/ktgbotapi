package com.github.insanusmokrassar.TelegramBotAPI.requests

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.Bot
import com.github.insanusmokrassar.TelegramBotAPI.types.User
import kotlinx.serialization.*

@Serializable
class GetMe : SimpleRequest<Bot> {
    override fun method(): String = "getMe"
    override val resultDeserializer: DeserializationStrategy<Bot>
        get() = Bot.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}