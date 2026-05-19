package dev.inmo.tgbotapi.requests.managed_bots

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.managed_bots.BotAccessSettings
import dev.inmo.tgbotapi.types.userIdField
import kotlinx.serialization.*

@Serializable
data class GetManagedBotAccessSettings(
    @SerialName(userIdField)
    val userId: ChatId
) : SimpleRequest<BotAccessSettings> {
    override fun method(): String = "getManagedBotAccessSettings"
    override val resultDeserializer: DeserializationStrategy<BotAccessSettings>
        get() = BotAccessSettings.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
