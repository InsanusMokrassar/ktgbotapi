package dev.inmo.tgbotapi.requests.send

import dev.inmo.tgbotapi.abstracts.types.OptionallyBusinessConnectionRequest
import dev.inmo.tgbotapi.requests.send.abstracts.OptionallyMessageThreadRequest
import dev.inmo.tgbotapi.requests.send.abstracts.SendChatMessageRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.actions.BotAction
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
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
    @OptIn(ExperimentalSerializationApi::class)
    @SerialName(messageThreadIdField)
    @EncodeDefault
    override val threadId: MessageThreadId? = chatId.threadId,
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId
): SendChatMessageRequest<Unit>, OptionallyMessageThreadRequest, OptionallyBusinessConnectionRequest {
    override fun method(): String = "sendChatAction"
    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
