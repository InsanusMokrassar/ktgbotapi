package com.github.insanusmokrassar.TelegramBotAPI.requests.games

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.MessageAction
import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.games.abstracts.GetGameHighScores
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.GameContent
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


suspend fun RequestsExecutor.getGameScore(
    userId: UserId,
    chatId: ChatId,
    messageId: MessageIdentifier
) = execute(
    GetGameHighScoresByChat(userId, chatId, messageId)
)

suspend fun RequestsExecutor.getGameScore(
    user: CommonUser,
    chatId: ChatId,
    messageId: MessageIdentifier
) = getGameScore(
    user.id, chatId, messageId
)

suspend fun RequestsExecutor.getGameScore(
    userId: UserId,
    chat: Chat,
    messageId: MessageIdentifier
) = getGameScore(
    userId, chat.id, messageId
)

suspend fun RequestsExecutor.getGameScore(
    user: CommonUser,
    chat: Chat,
    messageId: MessageIdentifier
) = getGameScore(
    user.id, chat.id, messageId
)

suspend fun RequestsExecutor.getGameScore(
    userId: UserId,
    message: ContentMessage<GameContent>
) = getGameScore(
    userId, message.chat.id, message.messageId
)

suspend fun RequestsExecutor.getGameScore(
    user: CommonUser,
    message: ContentMessage<GameContent>
) = getGameScore(
    user.id, message.chat.id, message.messageId
)
