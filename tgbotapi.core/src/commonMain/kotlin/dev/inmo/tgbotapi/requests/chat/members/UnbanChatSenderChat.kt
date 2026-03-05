package dev.inmo.tgbotapi.requests.chat.members

import dev.inmo.tgbotapi.requests.chat.abstracts.ChatSenderRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

/**
 * Representation of [unbanChatSenderChat](https://core.telegram.org/bots/api#unbanchatsenderchat) request
 */
@Serializable
data class UnbanChatSenderChat(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(senderChatIdField)
    override val senderChatId: IdChatIdentifier
) : ChatSenderRequest {
    override fun method(): String = "unbanChatSenderChat"
    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
