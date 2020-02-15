package com.github.insanusmokrassar.TelegramBotAPI.requests

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.ExtendedBot
import kotlinx.serialization.*

@Serializable
class GetMe : SimpleRequest<ExtendedBot> {
    override fun method(): String = "getMe"
    override val resultDeserializer: DeserializationStrategy<ExtendedBot>
        get() = ExtendedBot.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.getMe() = execute(GetMe())