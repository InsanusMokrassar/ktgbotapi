package dev.inmo.tgbotapi.requests.send

import dev.inmo.tgbotapi.abstracts.types.*
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.requests.send.abstracts.OptionallyMessageThreadRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.extensions.makeString
import kotlinx.serialization.*
import kotlinx.serialization.builtins.ListSerializer

fun CopyMessages(
    toChatId: ChatIdentifier,
    fromChatId: ChatIdentifier,
    messageIds: Array<MessageId>,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    removeCaption: Boolean = false
) = CopyMessages(
    toChatId = toChatId,
    fromChatId = fromChatId,
    messageIds = messageIds.toList(),
    threadId = threadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    removeCaption = removeCaption
)

@Serializable
data class CopyMessages (
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
): SimpleRequest<List<MessageId>>,
    MessagesAction,
    ProtectContent,
    OptionallyMessageThreadRequest,
    DisableNotification {
    override val chatId: ChatIdentifier
        get() = fromChatId

    init {
        require(messageIds.size in copyMessagesLimit) {
            "Messages count for copyMessages must be in $copyMessagesLimit range"
        }
    }

    override fun method(): String = "copyMessages"

    override val resultDeserializer: DeserializationStrategy<List<MessageId>>
        get() = ListSerializer(MessageIdSerializer)
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
