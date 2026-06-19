package dev.inmo.tgbotapi.requests.chat.invite_links

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.ChatJoinRequestQueryId
import dev.inmo.tgbotapi.types.chatJoinRequestQueryIdField
import dev.inmo.tgbotapi.types.resultField
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy

/**
 * Result of an [AnswerChatJoinRequestQuery].
 *
 * @see <a href="https://core.telegram.org/bots/api#answerchatjoinrequestquery">answerChatJoinRequestQuery</a>
 */
@Serializable
enum class ChatJoinRequestQueryResult {
    /**
     * Allow the user to join the chat.
     */
    @SerialName("approve")
    Approve,

    /**
     * Disallow the user to join the chat.
     */
    @SerialName("decline")
    Decline,

    /**
     * Leave the decision to other administrators.
     */
    @SerialName("queue")
    Queue
}

/**
 * Use this method to process a received chat join request query.
 *
 * @see <a href="https://core.telegram.org/bots/api#answerchatjoinrequestquery">answerChatJoinRequestQuery</a>
 */
@Serializable
data class AnswerChatJoinRequestQuery(
    @SerialName(chatJoinRequestQueryIdField)
    val chatJoinRequestQueryId: ChatJoinRequestQueryId,
    @SerialName(resultField)
    val result: ChatJoinRequestQueryResult
) : SimpleRequest<Unit> {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer

    override fun method(): String = "answerChatJoinRequestQuery"
}
