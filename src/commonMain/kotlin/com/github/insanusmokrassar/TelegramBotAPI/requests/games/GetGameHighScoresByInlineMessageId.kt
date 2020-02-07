package com.github.insanusmokrassar.TelegramBotAPI.requests.games

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.InlineMessageAction
import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.games.abstracts.GetGameHighScores
import com.github.insanusmokrassar.TelegramBotAPI.types.*
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

suspend fun RequestsExecutor.getGameScore(
    userId: UserId,
    inlineMessageId: InlineMessageIdentifier
) = execute(
    GetGameHighScoresByInlineMessageId(
        userId, inlineMessageId
    )
)

suspend fun RequestsExecutor.getGameScore(
    user: CommonUser,
    inlineMessageId: InlineMessageIdentifier
) = getGameScore(user.id, inlineMessageId)
