package dev.inmo.tgbotapi.requests.business_connection

import dev.inmo.micro_utils.common.Warning
import dev.inmo.tgbotapi.requests.abstracts.BusinessRequest
import dev.inmo.tgbotapi.types.OwnedGifts
import dev.inmo.tgbotapi.types.businessConnectionIdField
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.excludeUnsavedField
import dev.inmo.tgbotapi.types.excludeSavedField
import dev.inmo.tgbotapi.types.excludeUnlimitedField
import dev.inmo.tgbotapi.types.excludeLimitedUpgradableField
import dev.inmo.tgbotapi.types.excludeLimitedNonUpgradableField
import dev.inmo.tgbotapi.types.excludeUniqueField
import dev.inmo.tgbotapi.types.excludeFromBlockchainField
import dev.inmo.tgbotapi.types.sortByPriceField
import dev.inmo.tgbotapi.types.offsetField
import dev.inmo.tgbotapi.types.limitField
import dev.inmo.tgbotapi.types.gifts.GiftSentOrReceivedEvent
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy

@Serializable
data class GetBusinessAccountGifts(
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId,
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
    @SerialName(excludeUniqueField)
    val excludeUnique: Boolean = false,
    @SerialName(excludeFromBlockchainField)
    val excludeFromBlockchain: Boolean = false,
    @SerialName(sortByPriceField)
    val sortByPrice: Boolean = false,
    @SerialName(offsetField)
    val offset: String? = null,
    @SerialName(limitField)
    val limit: Int? = null,
) : BusinessRequest.Simple<OwnedGifts<GiftSentOrReceivedEvent.ReceivedInBusinessAccount>> {
    override fun method(): String = "getBusinessAccountGifts"

    override val resultDeserializer: DeserializationStrategy<OwnedGifts<GiftSentOrReceivedEvent.ReceivedInBusinessAccount>>
        get() = Companion.resultSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    companion object {
        @Warning("This API can be changed without any warranties of backward compatibility")
        val resultSerializer = OwnedGifts.serializer(GiftSentOrReceivedEvent.ReceivedInBusinessAccount.serializer())
    }
}