package dev.inmo.tgbotapi.requests.chat.members

import dev.inmo.tgbotapi.requests.chat.abstracts.ChatSenderRequest
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

/**
 * Representation of [banChatSenderChat](https://core.telegram.org/bots/api#banchatsenderchat) request
 */
@Serializable
data class BanChatSenderChat(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(senderChatIdField)
    override val senderChatId: ChatId
) : ChatSenderRequest {
    override fun method(): String = "banChatSenderChat"
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
