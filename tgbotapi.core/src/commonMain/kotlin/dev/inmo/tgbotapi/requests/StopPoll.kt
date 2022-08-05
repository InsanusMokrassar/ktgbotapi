package dev.inmo.tgbotapi.requests

import dev.inmo.tgbotapi.abstracts.types.MessageAction
import dev.inmo.tgbotapi.abstracts.types.WithReplyMarkup
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.polls.Poll
import dev.inmo.tgbotapi.types.polls.PollSerializer
import kotlinx.serialization.*

@Serializable
data class StopPoll(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(messageIdField)
    override val messageId: MessageIdentifier,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null
) : MessageAction, SimpleRequest<Poll>, WithReplyMarkup {
    override fun method(): String = "stopPoll"
    override val resultDeserializer: DeserializationStrategy<Poll>
        get() = PollSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
