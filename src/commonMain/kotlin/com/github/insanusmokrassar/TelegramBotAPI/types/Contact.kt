package com.github.insanusmokrassar.TelegramBotAPI.types

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.CommonContactData
import kotlinx.serialization.*

@Serializable
data class Contact(
    @SerialName(phoneNumberField)
    override val phoneNumber: String,
    @SerialName(firstNameField)
    override val firstName: String,
    @SerialName(lastNameField)
    @Optional
    override val lastName: String? = null,
    @SerialName(userIdField)
    @Optional
    val userId: UserId? = null,
    @SerialName(vcardField)
    @Optional
    override val vcard: String? = null
) : CommonContactData
