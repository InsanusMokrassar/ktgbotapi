package dev.inmo.tgbotapi.requests.chat.modify

import dev.inmo.tgbotapi.CommonAbstracts.types.ChatRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chatIdField
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

/**
 * Use this method to clear the list of pinned messages in a chat. If the chat is not a private chat, the bot must be an
 * administrator in the chat for this to work and must have the 'can_pin_messages' admin right in a supergroup or
 * 'can_edit_messages' admin right in a channel.
 *
 * @see PinChatMessage
 * @see UnpinChatMessage
 */
@Serializable
data class UnpinAllChatMessages(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier
): ChatRequest, SimpleRequest<Boolean> {
    override fun method(): String = "unpinAllChatMessages"
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
