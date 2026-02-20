package dev.inmo.tgbotapi.requests.gifts

import dev.inmo.micro_utils.common.Warning
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.OwnedGifts
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.excludeUnlimitedField
import dev.inmo.tgbotapi.types.excludeLimitedUpgradableField
import dev.inmo.tgbotapi.types.excludeLimitedNonUpgradableField
import dev.inmo.tgbotapi.types.excludeFromBlockchainField
import dev.inmo.tgbotapi.types.excludeUniqueField
import dev.inmo.tgbotapi.types.sortByPriceField
import dev.inmo.tgbotapi.types.gifts.GiftSentOrReceived
import dev.inmo.tgbotapi.types.limitField
import dev.inmo.tgbotapi.types.offsetField
import dev.inmo.tgbotapi.types.userIdField
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy

@Serializable
data class GetUserGifts(
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(userIdField)
    val userId: UserId,
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
) : SimpleRequest<OwnedGifts<GiftSentOrReceived>> {
    override fun method(): String = "getUserGifts"

    override val resultDeserializer: DeserializationStrategy<OwnedGifts<GiftSentOrReceived>>
        get() = Companion.resultSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    companion object {
        @Warning("This API can be changed without any warranties of backward compatibility")
        val resultSerializer = OwnedGifts.serializer(GiftSentOrReceived.serializer())
    }
}
