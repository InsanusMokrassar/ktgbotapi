package dev.inmo.tgbotapi.requests.business_connection

import dev.inmo.tgbotapi.requests.abstracts.BusinessRequest
import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.types.businessConnectionIdField
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.isPublicField
import dev.inmo.tgbotapi.types.photoField
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.builtins.serializer

@Serializable
data class SetBusinessAccountProfilePhoto(
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId,
    @SerialName(photoField)
    val photo: InputProfilePhoto,
    @SerialName(isPublicField)
    val isPublic: Boolean = false
) : BusinessRequest.Multipart<Boolean> {
    override fun method(): String = "setBusinessAccountProfilePhoto"

    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    override val mediaMap: Map<String, MultipartFile> = mapOf(
        photo.mediaPair
    )
}