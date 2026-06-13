package dev.inmo.tgbotapi.requests.answers

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.GuestQueryId
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.InlineQueryResult
import dev.inmo.tgbotapi.types.guest.SentGuestMessage
import dev.inmo.tgbotapi.types.guestQueryIdField
import dev.inmo.tgbotapi.types.resultField
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.Serializable

@Serializable
data class AnswerGuestQuery(
    @SerialName(guestQueryIdField)
    val guestQueryId: GuestQueryId,
    @SerialName(resultField)
    val result: InlineQueryResult
) : SimpleRequest<SentGuestMessage> {
    override fun method(): String = "answerGuestQuery"
    override val resultDeserializer: DeserializationStrategy<SentGuestMessage>
        get() = SentGuestMessage.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
