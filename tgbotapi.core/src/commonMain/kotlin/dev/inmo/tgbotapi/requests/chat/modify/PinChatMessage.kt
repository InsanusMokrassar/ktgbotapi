package dev.inmo.tgbotapi.requests.chat.modify

import dev.inmo.tgbotapi.abstracts.types.*
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

/**
 * Use this method to add a message to the list of pinned messages in a chat. If the chat is not a private chat, the bot
 * must be an administrator in the chat for this to work and must have the 'can_pin_messages' admin right in a
 * supergroup or 'can_edit_messages' admin right in a channel.
 */
@Serializable
data class PinChatMessage (
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(messageIdField)
    override val messageId: MessageIdentifier,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false
): ChatRequest, SimpleRequest<Boolean>, MessageAction, DisableNotification {
    override fun method(): String = "pinChatMessage"
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
