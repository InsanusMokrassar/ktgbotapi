package com.github.insanusmokrassar.TelegramBotAPI.requests.games

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.ByMessageId
import com.github.insanusmokrassar.TelegramBotAPI.requests.games.abstracts.SetGameScore
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.*

@Serializable
data class SetGameScoreByChatId (
    @SerialName(userIdField)
    override val userId: UserId,
    @SerialName(scoreField)
    override val score: Long,
    @SerialName(chatIdField)
    override val chatId: ChatId,
    @SerialName(messageIdField)
    override val messageId: MessageIdentifier,
    @SerialName(forceField)
    @Optional
    override val force: Boolean = false,
    @SerialName(disableEditMessageField)
    @Optional
    override val disableEditMessage: Boolean = false
) : SetGameScore, ByMessageId
