package dev.inmo.tgbotapi.requests.edit.location.live

import dev.inmo.tgbotapi.requests.edit.abstracts.EditChatMessage
import dev.inmo.tgbotapi.requests.edit.abstracts.EditReplyMessage
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.LocationContent
import kotlinx.serialization.*

private val commonResultDeserializer = TelegramBotAPIMessageDeserializationStrategyClass<ContentMessage<LocationContent>>()
const val stopMessageLiveLocationMethod = "stopMessageLiveLocation"

@Serializable
data class StopChatMessageLiveLocation(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(messageIdField)
    override val messageId: MessageId,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null
) : EditChatMessage<LocationContent>, EditReplyMessage {
    override fun method(): String = stopMessageLiveLocationMethod
    override val resultDeserializer: DeserializationStrategy<ContentMessage<LocationContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
