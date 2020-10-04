package dev.inmo.tgbotapi.requests.get

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*

@Serializable
data class GetUserProfilePhotos(
    @SerialName(userIdField)
    val userId: UserId,
    @SerialName(offsetField)
    val offset: Int? = null,
    @SerialName(limitField)
    val limit: Int? = null
): SimpleRequest<UserProfilePhotos> {
    init {
        if (offset != null && offset < 0) {
            throw IllegalArgumentException("Offset for getting user profile photos must be positive")
        }
        if (limit != null && limit !in userProfilePhotosRequestLimit) {
            throw IllegalArgumentException("Limit for getting user profile photos must be in 0 .. 100 range")
        }
    }

    override fun method(): String = "getUserProfilePhotos"
    override val resultDeserializer: DeserializationStrategy<UserProfilePhotos>
        get() = UserProfilePhotos.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
