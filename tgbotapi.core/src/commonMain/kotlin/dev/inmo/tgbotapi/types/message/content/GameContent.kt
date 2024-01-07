package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.games.SendGame
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.ReplyParameters
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.games.Game
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import kotlinx.serialization.Serializable

@Serializable
data class GameContent(
    val game: Game
) : MessageContent {
    override fun createResend(
        chatId: ChatIdentifier,
        messageThreadId: MessageThreadId?,
        disableNotification: Boolean,
        protectContent: Boolean,
        replyParameters: ReplyParameters?,
        replyMarkup: KeyboardMarkup?
    ): Request<ContentMessage<GameContent>> = SendGame(
        chatId,
        game.title,
        messageThreadId,
        disableNotification,
        protectContent,
        replyParameters,
        replyMarkup
    )
}
