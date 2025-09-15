package dev.inmo.tgbotapi.requests

import dev.inmo.tgbotapi.abstracts.WithCustomStartMediaData
import dev.inmo.tgbotapi.abstracts.types.MessageAction
import dev.inmo.tgbotapi.abstracts.types.ProtectContent
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.requests.send.abstracts.OptionallyDirectMessageThreadRequest
import dev.inmo.tgbotapi.requests.send.abstracts.OptionallyMessageThreadRequest
import dev.inmo.tgbotapi.requests.send.abstracts.OptionallySuggestedPostRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.SuggestedPostParameters
import dev.inmo.tgbotapi.types.message.abstracts.PossiblyForwardedMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import kotlinx.serialization.*

private val AbleToBeForwardedMessageDeserializer = TelegramBotAPIMessageDeserializationStrategyClass<PossiblyForwardedMessage>()

@Serializable
data class ForwardMessage(
    @SerialName(fromChatIdField)
    val fromChatId: ChatIdentifier,
    @SerialName(chatIdField)
    val toChatId: ChatIdentifier,
    @SerialName(messageIdField)
    override val messageId: MessageId,
    @OptIn(ExperimentalSerializationApi::class)
    @SerialName(messageThreadIdField)
    @EncodeDefault
    override val threadId: MessageThreadId? = toChatId.threadId,
    @OptIn(ExperimentalSerializationApi::class)
    @EncodeDefault
    @SerialName(directMessagesTopicIdField)
    override val directMessageThreadId: DirectMessageThreadId? = toChatId.directMessageThreadId,
    @SerialName(videoStartTimestampField)
    override val startTimestamp: Seconds? = null,
    @SerialName(disableNotificationField)
    val disableNotification: Boolean = false,
    @SerialName(protectContentField)
    override val protectContent: Boolean = false,
    @SerialName(suggestedPostParametersField)
    override val suggestedPostParameters: SuggestedPostParameters? = null
): SimpleRequest<PossiblyForwardedMessage>,
    MessageAction,
    ProtectContent,
    OptionallyMessageThreadRequest,
    OptionallyDirectMessageThreadRequest,
    OptionallySuggestedPostRequest,
    WithCustomStartMediaData {
    override val chatId: ChatIdentifier
        get() = fromChatId

    override fun method(): String = "forwardMessage"

    override val resultDeserializer: DeserializationStrategy<PossiblyForwardedMessage>
        get() = AbleToBeForwardedMessageDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
