package dev.inmo.tgbotapi.requests.games

import dev.inmo.tgbotapi.abstracts.types.InlineMessageAction
import dev.inmo.tgbotapi.requests.games.abstracts.SetGameScore
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*

@Serializable
data class SetGameScoreByInlineMessageId (
    @SerialName(userIdField)
    override val userId: UserId,
    @SerialName(scoreField)
    override val score: Long,
    @SerialName(inlineMessageIdField)
    override val inlineMessageId: InlineMessageIdentifier,
    @SerialName(forceField)
    override val force: Boolean = false,
    @SerialName(disableEditMessageField)
    override val disableEditMessage: Boolean = false
) : SetGameScore, InlineMessageAction {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
