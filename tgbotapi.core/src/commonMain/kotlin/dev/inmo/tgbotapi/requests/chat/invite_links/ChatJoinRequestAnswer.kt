package dev.inmo.tgbotapi.requests.chat.invite_links

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

/**
 * Represent a join request answer. See inheritors for more info
 */
sealed interface ChatJoinRequestAnswer : SimpleRequest<Boolean> {
    val chatId: ChatIdentifier
    val userId: UserId

    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
}

/**
 * Represent [approve](https://core.telegram.org/bots/api#approvechatjoinrequest) [ChatJoinRequestAnswer]. You may approve
 * the requests retrieved in with [ChatJoinRequest] (in [dev.inmo.tgbotapi.types.update.ChatJoinRequestUpdate])
 */
@Serializable
data class ApproveChatJoinRequest(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(userIdField)
    override val userId: UserId,
) : ChatJoinRequestAnswer {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String = "approveChatJoinRequest"
}

/**
 * Represent [decline](https://core.telegram.org/bots/api#declinechatjoinrequest) [ChatJoinRequestAnswer]. You may approve
 * the requests retrieved in with [ChatJoinRequest] (in [dev.inmo.tgbotapi.types.update.ChatJoinRequestUpdate])
 */
@Serializable
data class DeclineChatJoinRequest(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(userIdField)
    override val userId: UserId,
) : ChatJoinRequestAnswer {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String = "declineChatJoinRequest"
}
