package dev.inmo.tgbotapi.requests.games

import dev.inmo.tgbotapi.abstracts.types.InlineMessageAction
import dev.inmo.tgbotapi.requests.games.abstracts.GetGameHighScores
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*

@Serializable
data class GetGameHighScoresByInlineMessageId (
    @SerialName(userIdField)
    override val userId: UserId,
    @SerialName(inlineMessageIdField)
    override val inlineMessageId: InlineMessageIdentifier
) : GetGameHighScores, InlineMessageAction {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
