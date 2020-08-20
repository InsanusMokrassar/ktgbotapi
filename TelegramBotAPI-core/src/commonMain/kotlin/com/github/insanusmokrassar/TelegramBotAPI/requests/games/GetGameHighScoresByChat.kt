package com.github.insanusmokrassar.TelegramBotAPI.requests.games

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.MessageAction
import com.github.insanusmokrassar.TelegramBotAPI.requests.games.abstracts.GetGameHighScores
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.*

@Serializable
data class GetGameHighScoresByChat (
    @SerialName(userIdField)
    override val userId: UserId,
    @SerialName(chatIdField)
    override val chatId: ChatId,
    @SerialName(messageIdField)
    override val messageId: MessageIdentifier
) : GetGameHighScores, MessageAction {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
