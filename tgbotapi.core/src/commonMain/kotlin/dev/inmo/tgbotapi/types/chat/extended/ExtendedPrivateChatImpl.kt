package dev.inmo.tgbotapi.types.chat.extended

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.abstracts.extended.ExtendedPrivateChat
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExtendedPrivateChatImpl(
    @SerialName(idField)
    override val id: ChatId,
    @SerialName(photoField)
    override val chatPhoto: ChatPhoto? = null,
    @SerialName(usernameField)
    override val username: Username? = null,
    @SerialName(firstNameField)
    override val firstName: String = "",
    @SerialName(lastNameField)
    override val lastName: String = ""
) : ExtendedPrivateChat
