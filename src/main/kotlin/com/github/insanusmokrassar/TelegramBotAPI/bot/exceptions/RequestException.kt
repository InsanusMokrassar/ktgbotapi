package com.github.insanusmokrassar.TelegramBotAPI.bot.exceptions

import com.github.insanusmokrassar.TelegramBotAPI.types.Response
import java.io.IOException

fun newRequestException(
    response: Response,
    plainAnswer: String,
    message: String? = null,
    cause: Throwable? = null
) = when (response.description) {
    "Bad Request: reply message not found" -> ReplyMessageNotFoundException(response, plainAnswer, message, cause)
    "Unauthorized" -> UnauthorizedException(response, plainAnswer, message, cause)
    else -> CommonRequestException(response, plainAnswer, message, cause)
}

sealed class RequestException constructor(
    val response: Response,
    val plainAnswer: String,
    message: String? = null,
    cause: Throwable? = null
) : IOException(
    message,
    cause
)

class CommonRequestException(response: Response, plainAnswer: String, message: String?, cause: Throwable?) :
    RequestException(response, plainAnswer, message, cause)

class UnauthorizedException(response: Response, plainAnswer: String, message: String?, cause: Throwable?) :
    RequestException(response, plainAnswer, message, cause)

class ReplyMessageNotFoundException(response: Response, plainAnswer: String, message: String?, cause: Throwable?) :
    RequestException(response, plainAnswer, message, cause)

@Deprecated(
    "Replaced by ReplyMessageNotFoundException",
    ReplaceWith("ReplyMessageNotFoundException", "com.github.insanusmokrassar.TelegramBotAPI.bot.exceptions.ReplyMessageNotFoundException")
)
typealias ReplyMessageNotFound = ReplyMessageNotFoundException