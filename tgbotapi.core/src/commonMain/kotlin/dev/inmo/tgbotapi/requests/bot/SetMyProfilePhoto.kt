package dev.inmo.tgbotapi.requests.bot

import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.business_connection.InputProfilePhoto
import dev.inmo.tgbotapi.types.photoField
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.JsonObject

@Serializable
data class SetMyProfilePhoto(
    @SerialName(photoField)
    val photo: InputProfilePhoto
) : MultipartRequest.Common<Unit> {
    override fun method(): String = "setMyProfilePhoto"

    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override val data: SimpleRequest<Unit>
        get() = this

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    override val mediaMap: Map<String, MultipartFile> = mapOf(
        photo.mediaPair
    )
}
