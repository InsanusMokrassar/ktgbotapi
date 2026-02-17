package dev.inmo.tgbotapi.requests.bot

import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.business_connection.InputProfilePhoto
import dev.inmo.tgbotapi.types.photoField
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.JsonObject

@Serializable
data class SetMyProfilePhoto(
    @SerialName(photoField)
    val photo: InputProfilePhoto
) : MultipartRequest.Common<Boolean> {
    override fun method(): String = "setMyProfilePhoto"

    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override val data: SimpleRequest<Boolean>
        get() = this

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    override val mediaMap: Map<String, MultipartFile> = mapOf(
        photo.mediaPair
    )
}
