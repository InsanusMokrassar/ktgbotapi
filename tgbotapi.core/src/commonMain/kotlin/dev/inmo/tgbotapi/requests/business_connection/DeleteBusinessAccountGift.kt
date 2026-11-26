package dev.inmo.tgbotapi.requests.business_connection

import dev.inmo.tgbotapi.requests.abstracts.BusinessRequest
import dev.inmo.tgbotapi.types.GiftId
import dev.inmo.tgbotapi.types.businessConnectionIdField
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.ownedGiftIdField
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy

/** Deletes an unlimited, unrefunded regular gift with the can_convert_gifts_to_stars business right. */
@Serializable
data class DeleteBusinessAccountGift(
    @SerialName(businessConnectionIdField) override val businessConnectionId: BusinessConnectionId,
    @SerialName(ownedGiftIdField) val ownedGiftId: GiftId,
) : BusinessRequest.Simple<Unit> {
    override fun method(): String = "deleteBusinessAccountGift"
    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
