package dev.inmo.tgbotapi.requests.bot

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.BotCommand
import kotlinx.serialization.*
import kotlinx.serialization.builtins.ListSerializer

private val getMyCommandsSerializer = ListSerializer(BotCommand.serializer())

@Serializable
object GetMyCommands : SimpleRequest<List<BotCommand>> {
    override fun method(): String = "getMyCommands"
    override val resultDeserializer: DeserializationStrategy<List<BotCommand>>
        get() = getMyCommandsSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
