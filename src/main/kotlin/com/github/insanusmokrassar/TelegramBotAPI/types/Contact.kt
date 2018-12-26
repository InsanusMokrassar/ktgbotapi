package com.github.insanusmokrassar.TelegramBotAPI.types

import kotlinx.serialization.*

@Serializable
data class Contact(
    @SerialName(phoneNumberField)
    val phoneNumber: String,
    @SerialName(firstNameField)
    val firstName: String,
    @SerialName(lastNameField)
    @Optional
    val lastName: String? = null,
    @SerialName(userIdField)
    @Optional
    val userId: UserId? = null,
    @Optional
    val vcard: String? = null
)
