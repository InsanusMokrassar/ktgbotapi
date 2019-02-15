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
    @Optional
    val description: String? = null,
    @SerialName("error_code")
    @Optional
    val errorCode: Int? = null,
    @Optional
    val result: T? = null,
    @Optional
    val parameters: ResponseParametersRaw? = null
)
