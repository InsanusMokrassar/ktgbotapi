package dev.inmo.tgbotapi.requests.games

import dev.inmo.tgbotapi.abstracts.types.MessageAction
import dev.inmo.tgbotapi.requests.games.abstracts.GetGameHighScores
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*

@Serializable
data class GetGameHighScoresByChat (
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(userIdField)
    override val userId: UserId,
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(chatIdField)
    override val chatId: IdChatIdentifier,
    @SerialName(messageIdField)
    override val messageId: MessageId
) : GetGameHighScores, MessageAction {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
