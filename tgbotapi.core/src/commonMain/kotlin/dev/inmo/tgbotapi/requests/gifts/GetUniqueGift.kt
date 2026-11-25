package dev.inmo.tgbotapi.requests.gifts

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.gifts.Gift
import dev.inmo.tgbotapi.types.gifts.UniqueGiftName
import dev.inmo.tgbotapi.types.nameField
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy

/** Retrieves a unique gift by its collectible name. */
@Serializable
data class GetUniqueGift(
    @SerialName(nameField) val name: UniqueGiftName,
) : SimpleRequest<Gift.Unique> {
    override fun method(): String = "getUniqueGift"
    override val resultDeserializer: DeserializationStrategy<Gift.Unique>
        get() = Gift.Unique.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
