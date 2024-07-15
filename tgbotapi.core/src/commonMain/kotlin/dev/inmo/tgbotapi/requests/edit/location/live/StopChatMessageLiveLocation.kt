package dev.inmo.tgbotapi.requests.edit.location.live

import dev.inmo.tgbotapi.requests.edit.abstracts.EditChatMessage
import dev.inmo.tgbotapi.requests.edit.abstracts.EditReplyMessage
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.LocationContent
import dev.inmo.tgbotapi.types.message.content.StaticLocationContent
import kotlinx.serialization.*

private val commonResultDeserializer = TelegramBotAPIMessageDeserializationStrategyClass<ContentMessage<StaticLocationContent>>()
const val stopMessageLiveLocationMethod = "stopMessageLiveLocation"

@Serializable
data class StopChatMessageLiveLocation(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(messageIdField)
    override val messageId: MessageId,
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null
) : EditChatMessage<StaticLocationContent>, EditReplyMessage {
    override fun method(): String = stopMessageLiveLocationMethod
    override val resultDeserializer: DeserializationStrategy<ContentMessage<StaticLocationContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
