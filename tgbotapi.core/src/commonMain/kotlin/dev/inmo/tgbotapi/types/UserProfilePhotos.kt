package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.types.files.PhotoFile
import dev.inmo.tgbotapi.types.files.PhotoSerializer
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.serialization.*
import kotlinx.serialization.builtins.ListSerializer

@Serializable
data class UserProfilePhotos (
    @SerialName("total_count")
    val count: Int,
    @Serializable(UserProfilePhotosPhotosSerializer::class)
    val photos: List<PhotoFile>
)

@RiskFeature
object UserProfilePhotosPhotosSerializer : KSerializer<List<PhotoFile>> by ListSerializer(
    PhotoSerializer
)
