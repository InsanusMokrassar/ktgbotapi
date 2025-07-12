package dev.inmo.tgbotapi.requests.business_connection

import dev.inmo.tgbotapi.requests.abstracts.BusinessRequest
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.GiftId
import dev.inmo.tgbotapi.types.businessConnectionIdField
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.newOwnerChatIdField
import dev.inmo.tgbotapi.types.ownedGiftIdField
import dev.inmo.tgbotapi.types.starCountField
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.builtins.serializer

@Serializable
data class TransferGift(
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId,
    @SerialName(ownedGiftIdField)
    val ownedGiftId: GiftId,
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(newOwnerChatIdField)
    val newOwnerChatId: ChatId,
    @SerialName(starCountField)
    val transferPaymentStarCount: Int? = null,
) : BusinessRequest.Simple<Boolean> {
    override fun method(): String = "transferGift"

    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}