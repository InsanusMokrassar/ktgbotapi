package com.github.insanusmokrassar.TelegramBotAPI.types

import kotlinx.serialization.*

@Deprecated(
    "Deprecated because incorrect name",
    ReplaceWith("Response")
)
typealias ResponseParameters<T> = Response<T>

@Serializable
data class Response<T : Any>(
    val ok: Boolean = false,
    val description: String? = null,
    @SerialName("error_code")
    val errorCode: Int? = null,
    val result: T? = null,
    val parameters: ResponseParametersRaw? = null
)
