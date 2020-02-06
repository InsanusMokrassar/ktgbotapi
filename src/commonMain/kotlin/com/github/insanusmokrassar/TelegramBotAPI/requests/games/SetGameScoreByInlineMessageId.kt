package com.github.insanusmokrassar.TelegramBotAPI.requests.games

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.InlineMessageAction
import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.games.abstracts.SetGameScore
import com.github.insanusmokrassar.TelegramBotAPI.types.*
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

suspend fun RequestsExecutor.setGameScore(
    userId: UserId,
    score: Long,
    inlineMessageId: InlineMessageIdentifier,
    force: Boolean = false,
    disableEditMessage: Boolean = false
) = execute(
    SetGameScoreByInlineMessageId(
        userId, score, inlineMessageId, force, disableEditMessage
    )
)

suspend fun RequestsExecutor.setGameScore(
    user: CommonUser,
    score: Long,
    inlineMessageId: InlineMessageIdentifier,
    force: Boolean = false,
    disableEditMessage: Boolean = false
) = setGameScore(user.id, score, inlineMessageId, force, disableEditMessage)
