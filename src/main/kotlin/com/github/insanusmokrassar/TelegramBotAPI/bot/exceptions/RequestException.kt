package com.github.insanusmokrassar.TelegramBotAPI.bot.exceptions

import com.github.insanusmokrassar.TelegramBotAPI.types.Response
import java.io.IOException

fun newRequestException(
    response: Response<*>,
    plainAnswer: String,
    message: String? = null,
    cause: Throwable? = null
) = when (response.description) {
    "Bad Request: reply message not found" -> ReplyMessageNotFound(response, plainAnswer, message, cause)
    else -> RequestException(response, plainAnswer, message, cause)
}

open class RequestException internal constructor(
    val response: Response<*>,
    val plainAnswer: String,
    message: String? = null,
    cause: Throwable? = null
) : IOException(
    message,
    cause
)