package com.github.insanusmokrassar.TelegramBotAPI.requests.games

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.MessageAction
import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.games.abstracts.SetGameScore
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.GameContent
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
    override val force: Boolean = false,
    @SerialName(disableEditMessageField)
    override val disableEditMessage: Boolean = false
) : SetGameScore, MessageAction {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}


suspend fun RequestsExecutor.setGameScore(
    userId: UserId,
    score: Long,
    chatId: ChatId,
    messageId: MessageIdentifier,
    force: Boolean = false,
    disableEditMessage: Boolean = false
) = execute(
    SetGameScoreByChatId(userId, score, chatId, messageId, force, disableEditMessage)
)

suspend fun RequestsExecutor.setGameScore(
    user: CommonUser,
    score: Long,
    chatId: ChatId,
    messageId: MessageIdentifier,
    force: Boolean = false,
    disableEditMessage: Boolean = false
) = setGameScore(
    user.id, score, chatId, messageId, force, disableEditMessage
)

suspend fun RequestsExecutor.setGameScore(
    userId: UserId,
    score: Long,
    chat: Chat,
    messageId: MessageIdentifier,
    force: Boolean = false,
    disableEditMessage: Boolean = false
) = setGameScore(
    userId, score, chat.id, messageId, force, disableEditMessage
)

suspend fun RequestsExecutor.setGameScore(
    user: CommonUser,
    score: Long,
    chat: Chat,
    messageId: MessageIdentifier,
    force: Boolean = false,
    disableEditMessage: Boolean = false
) = setGameScore(
    user.id, score, chat.id, messageId, force, disableEditMessage
)

suspend fun RequestsExecutor.setGameScore(
    userId: UserId,
    score: Long,
    message: ContentMessage<GameContent>,
    force: Boolean = false,
    disableEditMessage: Boolean = false
) = setGameScore(
    userId, score, message.chat.id, message.messageId, force, disableEditMessage
)

suspend fun RequestsExecutor.setGameScore(
    user: CommonUser,
    score: Long,
    message: ContentMessage<GameContent>,
    force: Boolean = false,
    disableEditMessage: Boolean = false
) = setGameScore(
    user.id, score, message.chat.id, message.messageId, force, disableEditMessage
)
