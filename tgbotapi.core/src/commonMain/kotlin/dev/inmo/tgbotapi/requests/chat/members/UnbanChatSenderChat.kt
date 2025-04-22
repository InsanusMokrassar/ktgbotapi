package dev.inmo.tgbotapi.requests.chat.members

import dev.inmo.tgbotapi.requests.chat.abstracts.ChatSenderRequest
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

/**
 * Representation of [unbanChatSenderChat](https://core.telegram.org/bots/api#unbanchatsenderchat) request
 */
@Serializable
data class UnbanChatSenderChat(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(senderChatIdField)
    override val senderChatId: IdChatIdentifier,
) : ChatSenderRequest {
    override fun method(): String = "unbanChatSenderChat"

    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
