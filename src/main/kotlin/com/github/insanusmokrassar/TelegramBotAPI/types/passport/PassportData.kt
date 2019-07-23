package com.github.insanusmokrassar.TelegramBotAPI.types.passport

import com.github.insanusmokrassar.TelegramBotAPI.types.credentialsField
import com.github.insanusmokrassar.TelegramBotAPI.types.dataField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PassportData (
    @SerialName(dataField)
    val data: List<EncryptedPassportElement>,
    @SerialName(credentialsField)
    val credentials: EncryptedCredentials
)