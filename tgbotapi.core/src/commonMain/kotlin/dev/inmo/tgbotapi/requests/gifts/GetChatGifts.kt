package dev.inmo.tgbotapi.requests.gifts

import dev.inmo.micro_utils.common.Warning
import dev.inmo.tgbotapi.abstracts.types.ChatRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.OwnedGifts
import dev.inmo.tgbotapi.types.chatIdField
import dev.inmo.tgbotapi.types.excludeUnsavedField
import dev.inmo.tgbotapi.types.excludeSavedField
import dev.inmo.tgbotapi.types.excludeUnlimitedField
import dev.inmo.tgbotapi.types.excludeLimitedUpgradableField
import dev.inmo.tgbotapi.types.excludeLimitedNonUpgradableField
import dev.inmo.tgbotapi.types.excludeFromBlockchainField
import dev.inmo.tgbotapi.types.excludeUniqueField
import dev.inmo.tgbotapi.types.sortByPriceField
import dev.inmo.tgbotapi.types.gifts.GiftSentOrReceived
import dev.inmo.tgbotapi.types.limitField
import dev.inmo.tgbotapi.types.offsetField
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy

@Serializable
data class GetChatGifts(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(excludeUnsavedField)
    val excludeUnsaved: Boolean = false,
    @SerialName(excludeSavedField)
    val excludeSaved: Boolean = false,
    @SerialName(excludeUnlimitedField)
    val excludeUnlimited: Boolean = false,
    @SerialName(excludeLimitedUpgradableField)
    val excludeLimitedUpgradable: Boolean = false,
    @SerialName(excludeLimitedNonUpgradableField)
    val excludeLimitedNonUpgradable: Boolean = false,
    @SerialName(excludeFromBlockchainField)
    val excludeFromBlockchain: Boolean = false,
    @SerialName(excludeUniqueField)
    val excludeUnique: Boolean = false,
    @SerialName(sortByPriceField)
    val sortByPrice: Boolean = false,
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
