package dev.inmo.tgbotapi.types.request

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.files.PhotoFile
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SharedUser(
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(userIdField)
    val id: UserId,
    @SerialName(firstNameField)
    val firstName: String? = null,
    @SerialName(lastNameField)
    val lastName: String? = null,
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(usernameField)
    val username: Username? = null,
    @SerialName(photoField)
    val photo: PhotoFile? = null,
)
