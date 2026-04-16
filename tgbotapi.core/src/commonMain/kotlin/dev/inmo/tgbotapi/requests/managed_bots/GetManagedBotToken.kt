package dev.inmo.tgbotapi.requests.managed_bots

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.BotToken
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.userIdField
import kotlinx.serialization.*

@Serializable
data class GetManagedBotToken(
    @SerialName(userIdField)
    val userId: ChatId
) : SimpleRequest<BotToken> {
    override fun method(): String = "getManagedBotToken"
    override val resultDeserializer: DeserializationStrategy<BotToken>
        get() = BotToken.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
