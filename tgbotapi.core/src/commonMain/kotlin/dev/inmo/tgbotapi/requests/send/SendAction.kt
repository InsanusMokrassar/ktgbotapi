package dev.inmo.tgbotapi.requests.send

import dev.inmo.tgbotapi.requests.send.abstracts.OptionallyMessageThreadRequest
import dev.inmo.tgbotapi.requests.send.abstracts.SendChatMessageRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.actions.BotAction
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

/**
 * Send notification to user which will be shown for 5 seconds or while user have no messages from bot
 */
@Serializable
data class SendAction(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(actionField)
    val action: BotAction,
    @SerialName(messageThreadIdField)
    override val threadId: MessageThreadId? = chatId.threadId
): SendChatMessageRequest<Boolean>, OptionallyMessageThreadRequest {
    override fun method(): String = "sendChatAction"
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
