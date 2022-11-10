package dev.inmo.tgbotapi.requests.games

import dev.inmo.tgbotapi.abstracts.types.MessageAction
import dev.inmo.tgbotapi.requests.games.abstracts.SetGameScore
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*

@Serializable
data class SetGameScoreByChatId (
    @SerialName(userIdField)
    override val userId: UserId,
    @SerialName(scoreField)
    override val score: Long,
    @SerialName(chatIdField)
    override val chatId: IdChatIdentifier,
    @SerialName(messageIdField)
    override val messageId: MessageId,
    @SerialName(forceField)
    override val force: Boolean = false,
    @SerialName(disableEditMessageField)
    override val disableEditMessage: Boolean = false
) : SetGameScore, MessageAction {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
