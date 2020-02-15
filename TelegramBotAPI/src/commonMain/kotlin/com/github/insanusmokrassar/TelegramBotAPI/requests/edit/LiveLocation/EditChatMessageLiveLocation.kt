package com.github.insanusmokrassar.TelegramBotAPI.requests.edit.LiveLocation

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.edit.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.LocationContent
import kotlinx.serialization.*

private val commonResultDeserializer = TelegramBotAPIMessageDeserializationStrategyClass<ContentMessage<LocationContent>>()

@Serializable
data class EditChatMessageLiveLocation(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(messageIdField)
    override val messageId: MessageIdentifier,
    @SerialName(latitudeField)
    override val latitude: Double,
    @SerialName(longitudeField)
    override val longitude: Double,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null
) : EditChatMessage<LocationContent>, EditReplyMessage, EditLocationMessage {
    override fun method(): String = "editMessageLiveLocation"
    override val resultDeserializer: DeserializationStrategy<ContentMessage<LocationContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.editLiveLocation(
    chatId: ChatIdentifier,
    messageId: MessageIdentifier,
    latitude: Double,
    longitude: Double,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(
    EditChatMessageLiveLocation(
        chatId, messageId, latitude, longitude, replyMarkup
    )
)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.editLiveLocation(
    chat: Chat,
    messageId: MessageIdentifier,
    latitude: Double,
    longitude: Double,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(chat.id, messageId, latitude, longitude, replyMarkup)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.editLiveLocation(
    message: ContentMessage<LocationContent>,
    latitude: Double,
    longitude: Double,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(message.chat, message.messageId, latitude, longitude, replyMarkup)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.editLiveLocation(
    chatId: ChatIdentifier,
    messageId: MessageIdentifier,
    location: Location,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(
    EditChatMessageLiveLocation(
        chatId, messageId, location.latitude, location.longitude, replyMarkup
    )
)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.editLiveLocation(
    chat: Chat,
    messageId: MessageIdentifier,
    location: Location,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(chat.id, messageId, location.latitude, location.longitude, replyMarkup)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.editLiveLocation(
    message: ContentMessage<LocationContent>,
    location: Location,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(message.chat, message.messageId, location.latitude, location.longitude, replyMarkup)
