package com.github.insanusmokrassar.TelegramBotAPI.requests.edit.ReplyMarkup

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.edit.abstracts.EditChatMessage
import com.github.insanusmokrassar.TelegramBotAPI.requests.edit.abstracts.EditReplyMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MessageContent
import kotlinx.serialization.*

const val editMessageReplyMarkupMethod = "editMessageReplyMarkup"

private val commonResultDeserializer = TelegramBotAPIMessageDeserializationStrategyClass<ContentMessage<MessageContent>>()

@Serializable
data class EditChatMessageReplyMarkup(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(messageIdField)
    override val messageId: MessageIdentifier,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null
) : EditChatMessage<MessageContent>, EditReplyMessage {

    override fun method(): String = editMessageReplyMarkupMethod
    override val resultDeserializer: DeserializationStrategy<ContentMessage<MessageContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
