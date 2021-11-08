package dev.inmo.tgbotapi.requests.chat.invite_links

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.builtins.serializer

sealed interface ChatJoinRequestAnswer : SimpleRequest<Boolean> {
    val chatId: ChatIdentifier
    val userId: UserId

    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
}

@Serializable
data class ApproveChatJoinRequest(
    override val chatId: ChatIdentifier,
    override val userId: UserId
) : ChatJoinRequestAnswer {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String  = "approveChatJoinRequest"
}

@Serializable
data class DeclineChatJoinRequest(
    override val chatId: ChatIdentifier,
    override val userId: UserId
) : ChatJoinRequestAnswer {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String  = "declineChatJoinRequest"
}
