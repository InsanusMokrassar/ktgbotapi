package com.github.insanusmokrassar.TelegramBotAPI.types.passport

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EncryptedPassportElement (
    @SerialName(typeField)
    val type: String,
    @SerialName(dataField)
    val data: Base64?,
    @SerialName(phoneNumberField)
    val phoneNumber: String?,
    @SerialName(emailField)
    val email: String?,
    @SerialName(filesField)
    val files: List<PassportFile>?,
    @SerialName(frontSideField)
    val frontSide: PassportFile?,
    @SerialName(reverseSideField)
    val reverseSide: PassportFile?,
    @SerialName(selfieField)
    val selfie: PassportFile?,
    @SerialName(translationField)
    val translation: List<PassportFile>?,
    @SerialName(hashField)
    val hash: Base64
)
