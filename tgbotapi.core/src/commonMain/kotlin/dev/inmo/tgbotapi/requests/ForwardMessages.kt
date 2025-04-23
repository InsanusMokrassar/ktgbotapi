package dev.inmo.tgbotapi.requests

import dev.inmo.tgbotapi.abstracts.types.DisableNotification
import dev.inmo.tgbotapi.abstracts.types.MessagesAction
import dev.inmo.tgbotapi.abstracts.types.ProtectContent
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.requests.send.abstracts.OptionallyMessageThreadRequest
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*
import kotlinx.serialization.builtins.ListSerializer

fun ForwardMessages(
    toChatId: ChatIdentifier,
    fromChatId: ChatIdentifier,
    messageIds: Array<MessageId>,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    removeCaption: Boolean = false,
) = ForwardMessages(
    toChatId = toChatId,
    fromChatId = fromChatId,
    messageIds = messageIds.toList(),
    threadId = threadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    removeCaption = removeCaption,
)

@Serializable
data class ForwardMessages(
    @SerialName(chatIdField)
    val toChatId: ChatIdentifier,
    @SerialName(fromChatIdField)
    val fromChatId: ChatIdentifier,
    @SerialName(messageIdsField)
    override val messageIds: List<MessageId>,
    @SerialName(messageThreadIdField)
    override val threadId: MessageThreadId? = toChatId.threadId,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(protectContentField)
    override val protectContent: Boolean = false,
    @SerialName(removeCaptionField)
    private val removeCaption: Boolean = false,
) : SimpleRequest<List<MessageId>>,
    MessagesAction,
    ProtectContent,
    OptionallyMessageThreadRequest,
    DisableNotification {
    override val chatId: ChatIdentifier
        get() = fromChatId

    init {
        require(messageIds.size in forwardMessagesLimit) {
            "Messages count for forwardMessages must be in $forwardMessagesLimit range"
        }
    }

    override fun method(): String = "forwardMessages"

    override val resultDeserializer: DeserializationStrategy<List<MessageId>>
        get() = ListSerializer(MessageIdSerializer)
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
