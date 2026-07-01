package dev.inmo.tgbotapi.requests.chat.invite_links

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.ChatJoinRequestQueryId
import dev.inmo.tgbotapi.types.chatJoinRequestQueryIdField
import dev.inmo.tgbotapi.types.resultField
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Result of an [AnswerChatJoinRequestQuery]. Serialized as a plain string.
 *
 * @see <a href="https://core.telegram.org/bots/api#answerchatjoinrequestquery">answerChatJoinRequestQuery</a>
 */
@Serializable(ChatJoinRequestQueryResult.Companion::class)
sealed interface ChatJoinRequestQueryResult {
    val name: String

    /**
     * Allow the user to join the chat.
     */
    @Serializable(ChatJoinRequestQueryResult.Companion::class)
    data object Approve : ChatJoinRequestQueryResult {
        override val name: String = "approve"
    }

    /**
     * Disallow the user to join the chat.
     */
    @Serializable(ChatJoinRequestQueryResult.Companion::class)
    data object Decline : ChatJoinRequestQueryResult {
        override val name: String = "decline"
    }

    /**
     * Leave the decision to other administrators.
     */
    @Serializable(ChatJoinRequestQueryResult.Companion::class)
    data object Queue : ChatJoinRequestQueryResult {
        override val name: String = "queue"
    }

    /**
     * Any other result which is currently unknown to this library.
     */
    @Serializable(ChatJoinRequestQueryResult.Companion::class)
    data class Unknown(override val name: String) : ChatJoinRequestQueryResult

    companion object : KSerializer<ChatJoinRequestQueryResult> {
        override val descriptor: SerialDescriptor =
            PrimitiveSerialDescriptor("ChatJoinRequestQueryResult", PrimitiveKind.STRING)

        override fun serialize(encoder: Encoder, value: ChatJoinRequestQueryResult) {
            encoder.encodeString(value.name)
        }

        override fun deserialize(decoder: Decoder): ChatJoinRequestQueryResult {
            return when (val name = decoder.decodeString()) {
                Approve.name -> Approve
                Decline.name -> Decline
                Queue.name -> Queue
                else -> Unknown(name)
            }
        }
    }
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
