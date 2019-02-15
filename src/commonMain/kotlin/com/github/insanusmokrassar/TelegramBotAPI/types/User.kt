package com.github.insanusmokrassar.TelegramBotAPI.types

import kotlinx.serialization.*
import kotlinx.serialization.Optional

@Serializable
data class User(
    val id: ChatId,
    @SerialName(isBotField)
    @Optional
    val isBot: Boolean = false,
    @SerialName(firstNameField)
    val firstName: String,
    @SerialName(lastNameField)
    @Optional
    val lastName: String? = null,
    @SerialName(usernameField)
    @Optional
    val username: String? = null,
    @SerialName(languageCodeField)
    @Optional
    internal val languageCode: String? = null
)
