package com.github.insanusmokrassar.TelegramBotAPI.requests.send.games

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.ReplyMarkup
import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts.SendMessageRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.games.Game
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.GameContent
import kotlinx.serialization.*

private val commonResultDeserializer: DeserializationStrategy<ContentMessage<GameContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

@Serializable
data class SendGame (
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(gameShortNameField)
    val gameShortName: String,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(replyToMessageIdField)
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : SendMessageRequest<ContentMessage<GameContent>>,
    ReplyMarkup {
    override fun method(): String = "sendGame"
    override val resultDeserializer: DeserializationStrategy<ContentMessage<GameContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendGame(
    chatId: ChatIdentifier,
    gameShortName: String,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendGame(
        chatId, gameShortName, disableNotification, replyToMessageId, replyMarkup
    )
)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendGame(
    chat: Chat,
    gameShortName: String,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendGame(
    chat.id, gameShortName, disableNotification, replyToMessageId, replyMarkup
)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendGame(
    chatId: ChatIdentifier,
    game: Game,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendGame(
    chatId, game.title, disableNotification, replyToMessageId, replyMarkup
)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendGame(
    chat: Chat,
    game: Game,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendGame(
    chat.id, game.title, disableNotification, replyToMessageId, replyMarkup
)
