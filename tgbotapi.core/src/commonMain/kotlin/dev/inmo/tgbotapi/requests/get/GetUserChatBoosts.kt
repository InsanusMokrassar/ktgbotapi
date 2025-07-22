package dev.inmo.tgbotapi.requests.get

import dev.inmo.tgbotapi.abstracts.types.ChatRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.boosts.UserChatBoosts
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy

@Serializable
data class GetUserChatBoosts(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(userIdField)
    val userId: UserId
) : SimpleRequest<UserChatBoosts>, ChatRequest {
    override fun method(): String = "getUserChatBoosts"
    override val resultDeserializer: DeserializationStrategy<UserChatBoosts>
    get() = UserChatBoosts.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}