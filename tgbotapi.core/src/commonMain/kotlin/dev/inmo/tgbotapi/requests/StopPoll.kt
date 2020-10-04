package dev.inmo.tgbotapi.requests

import dev.inmo.tgbotapi.CommonAbstracts.types.MessageAction
import dev.inmo.tgbotapi.CommonAbstracts.types.ReplyMarkup
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.polls.Poll
import kotlinx.serialization.*

@Serializable
data class StopPoll(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(messageIdField)
    override val messageId: MessageIdentifier,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null
) : MessageAction, SimpleRequest<Poll>, ReplyMarkup {
    override fun method(): String = "stopPoll"
    override val resultDeserializer: DeserializationStrategy<Poll>
        get() = Poll.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
