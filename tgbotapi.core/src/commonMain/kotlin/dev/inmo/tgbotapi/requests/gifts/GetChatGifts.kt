package dev.inmo.tgbotapi.requests.gifts

import dev.inmo.micro_utils.common.Warning
import dev.inmo.tgbotapi.abstracts.types.ChatRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.OwnedGifts
import dev.inmo.tgbotapi.types.chatIdField
import dev.inmo.tgbotapi.types.gifts.GiftSentOrReceived
import dev.inmo.tgbotapi.types.limitField
import dev.inmo.tgbotapi.types.offsetField
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy

// TODO::Fix
@Serializable
data class GetChatGifts(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(offsetField)
    val offset: String? = null,
    @SerialName(limitField)
    val limit: Int? = null,
) : SimpleRequest<OwnedGifts<GiftSentOrReceived>>, ChatRequest {
    override fun method(): String = "getChatGifts"

    override val resultDeserializer: DeserializationStrategy<OwnedGifts<GiftSentOrReceived>>
        get() = Companion.resultSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    companion object {
        @Warning("This API can be changed without any warranties of backward compatibility")
        val resultSerializer = OwnedGifts.serializer(GiftSentOrReceived.serializer())
    }
}
