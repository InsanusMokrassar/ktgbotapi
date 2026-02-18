package dev.inmo.tgbotapi.requests.get

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.UserProfileAudios
import kotlinx.serialization.*

@Serializable
data class GetUserProfileAudios(
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(userIdField)
    val userId: UserId,
    @SerialName(offsetField)
    val offset: Int? = null,
    @SerialName(limitField)
    val limit: Int? = null
): SimpleRequest<UserProfileAudios> {
    init {
        if (offset != null && offset < 0) {
            throw IllegalArgumentException("Offset for getting user profile audios must be positive")
        }
        if (limit != null && limit !in userProfileAudiosRequestLimit) {
            throw IllegalArgumentException("Limit for getting user profile audios must be in 0 .. 100 range")
        }
    }

    override fun method(): String = "getUserProfileAudios"
    override val resultDeserializer: DeserializationStrategy<UserProfileAudios>
        get() = UserProfileAudios.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
