package com.github.insanusmokrassar.TelegramBotAPI.bot.exceptions

import com.github.insanusmokrassar.TelegramBotAPI.types.Response
import java.io.IOException

fun newRequestException(
    response: Response<*>,
    message: String? = null,
    cause: Throwable? = null
) = when (response.description) {
    "Bad Request: reply message not found" -> ReplyMessageNotFound(response, message, cause)
    else -> RequestException(response, message, cause)
}

open class RequestException internal constructor(
    val response: Response<*>,
    message: String? = null,
    cause: Throwable? = null
) : IOException(
    message,
    cause
)