package dev.inmo.tgbotapi.types.request

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.files.Photo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SharedUser(
    @SerialName(userIdField)
    val id: UserId,
    @SerialName(firstNameField)
    val firstName: String? = null,
    @SerialName(lastNameField)
    val lastName: String? = null,
    @SerialName(usernameField)
    val username: Username? = null,
    @SerialName(photoField)
    val photo: Photo? = null,
)
