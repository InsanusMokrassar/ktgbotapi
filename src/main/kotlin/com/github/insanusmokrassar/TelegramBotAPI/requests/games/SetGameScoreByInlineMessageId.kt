package com.github.insanusmokrassar.TelegramBotAPI.requests.games

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.types.ByInlineMessageId
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
    @Optional
    override val force: Boolean = false,
    @SerialName(disableEditMessageField)
    @Optional
    override val disableEditMessage: Boolean = false
) : SetGameScore, ByInlineMessageId
