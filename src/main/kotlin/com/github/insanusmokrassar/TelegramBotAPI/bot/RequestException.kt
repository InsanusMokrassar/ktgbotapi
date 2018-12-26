package com.github.insanusmokrassar.TelegramBotAPI.bot

import com.github.insanusmokrassar.TelegramBotAPI.types.ResponseParameters
import java.io.IOException

class RequestException(
    val response: ResponseParameters<*>,
    message: String? = null,
    cause: Throwable? = null
) : IOException(
    message,
    cause
)