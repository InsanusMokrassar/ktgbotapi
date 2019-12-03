package com.github.insanusmokrassar.TelegramBotAPI.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class Response(
    val ok: Boolean = false,
    val description: String? = null,
    @SerialName("error_code")
    val errorCode: Int? = null,
    val result: JsonElement? = null,
    val parameters: ResponseParametersRaw? = null
)
