package com.github.insanusmokrassar.TelegramBotAPI.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: ChatId,
    @SerialName(isBotField)
    val isBot: Boolean = false,
    @SerialName(firstNameField)
    val firstName: String,
    @SerialName(lastNameField)
    val lastName: String? = null,
    @SerialName(usernameField)
    val username: Username? = null,
    @SerialName(languageCodeField)
    val languageCode: String? = null
)
