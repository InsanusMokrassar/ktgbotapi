package com.github.insanusmokrassar.TelegramBotAPI.requests.send

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.edit.LiveLocation.EditChatMessageLiveLocation
import com.github.insanusmokrassar.TelegramBotAPI.requests.edit.LiveLocation.StopChatMessageLiveLocation
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.LocationContent
import com.github.insanusmokrassar.TelegramBotAPI.utils.extensions.executeAsync
import com.soywiz.klock.DateTime
import com.soywiz.klock.TimeSpan
import io.ktor.utils.io.core.Closeable
import kotlinx.coroutines.*
import kotlinx.serialization.*


private val commonResultDeserializer: DeserializationStrategy<ContentMessage<LocationContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

// TODO:: Add location tracker for tracking location
@Serializable
data class SendLocation(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(latitudeField)
    override val latitude: Double,
    @SerialName(longitudeField)
    override val longitude: Double,
    @SerialName(livePeriodField)
    val livePeriod: Long? = null,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(replyToMessageIdField)
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : SendMessageRequest<ContentMessage<LocationContent>>,
    ReplyingMarkupSendMessageRequest<ContentMessage<LocationContent>>,
    PositionedSendMessageRequest<ContentMessage<LocationContent>>
{
    override fun method(): String = "sendLocation"
    override val resultDeserializer: DeserializationStrategy<ContentMessage<LocationContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    init {
        if (livePeriod != null && livePeriod !in livePeriodLimit) {
            error("Live period for sending location must be in $livePeriodLimit, but was $livePeriod")
        }
    }
}

suspend fun RequestsExecutor.sendLocation(
    chatId: ChatIdentifier,
    latitude: Double,
    longitude: Double,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendLocation(
        chatId,
        latitude,
        longitude,
        disableNotification = disableNotification,
        replyToMessageId = replyToMessageId,
        replyMarkup = replyMarkup
    )
)

suspend fun RequestsExecutor.sendLocation(
    chatId: ChatIdentifier,
    location: Location,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendLocation(
    chatId,
    location.latitude,
    location.longitude,
    disableNotification,
    replyToMessageId,
    replyMarkup
)

suspend fun RequestsExecutor.sendLocation(
    chat: Chat,
    latitude: Double,
    longitude: Double,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendLocation(
    chat.id,
    latitude,
    longitude,
    disableNotification,
    replyToMessageId,
    replyMarkup
)

suspend fun RequestsExecutor.sendLocation(
    chat: Chat,
    location: Location,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendLocation(
    chat.id,
    location.latitude,
    location.longitude,
    disableNotification,
    replyToMessageId,
    replyMarkup
)
