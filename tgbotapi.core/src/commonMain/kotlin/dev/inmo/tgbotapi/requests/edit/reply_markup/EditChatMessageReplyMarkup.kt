package dev.inmo.tgbotapi.requests.edit.reply_markup

import dev.inmo.tgbotapi.requests.edit.abstracts.EditChatMessage
import dev.inmo.tgbotapi.requests.edit.abstracts.EditReplyMessage
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.MessageContent
import kotlinx.serialization.*

const val editMessageReplyMarkupMethod = "editMessageReplyMarkup"

private val commonResultDeserializer = TelegramBotAPIMessageDeserializationStrategyClass<ContentMessage<MessageContent>>()

@Serializable
data class EditChatMessageReplyMarkup(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(messageIdField)
    override val messageId: MessageId,
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null,
) : EditChatMessage<MessageContent>, EditReplyMessage {
    override fun method(): String = editMessageReplyMarkupMethod

    override val resultDeserializer: DeserializationStrategy<ContentMessage<MessageContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
