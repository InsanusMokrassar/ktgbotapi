package com.github.insanusmokrassar.TelegramBotAPI.types

import kotlinx.serialization.*
import java.util.*

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
    private val languageCode: String? = null
) {
    val userLocale: Locale? by lazy {
        languageCode ?.let {
            Locale.forLanguageTag(it)
        }
    }
}
