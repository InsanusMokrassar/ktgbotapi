package com.github.insanusmokrassar.TelegramBotAPI.types.passport

import com.github.insanusmokrassar.TelegramBotAPI.types.Base64
import com.github.insanusmokrassar.TelegramBotAPI.types.dataField
import com.github.insanusmokrassar.TelegramBotAPI.types.hashField
import com.github.insanusmokrassar.TelegramBotAPI.types.secretField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EncryptedCredentials (
    @SerialName(dataField)
    val data: Base64,
    @SerialName(hashField)
    val hash: Base64,
    @SerialName(secretField)
    val secret: Base64
)
