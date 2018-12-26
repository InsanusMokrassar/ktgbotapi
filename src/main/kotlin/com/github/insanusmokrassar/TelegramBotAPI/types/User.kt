package com.github.insanusmokrassar.TelegramBotAPI.types

import kotlinx.serialization.*
import kotlinx.serialization.Optional
import java.util.*

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
    private val languageCode: String? = null
) {
    @Transient
    val userLocale: Locale? by lazy {
        languageCode ?.let {
            Locale.forLanguageTag(it)
        }
    }
}
