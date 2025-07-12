package dev.inmo.tgbotapi.requests.set

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.builtins.serializer

@Serializable
data class SetUserEmojiStatus(
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(userIdField)
    val userId: UserId,
    @SerialName(emojiStatusCustomEmojiIdField)
    val customEmojiId: CustomEmojiId,
    @Serializable(TelegramDateSerializer::class)
    @SerialName(emojiStatusExpirationDateField)
    val expirationDate: TelegramDate? = null
) : SimpleRequest<Boolean> {
    override fun method(): String = "setUserEmojiStatus"

    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
}