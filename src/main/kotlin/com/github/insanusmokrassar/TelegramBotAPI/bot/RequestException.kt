package com.github.insanusmokrassar.TelegramBotAPI.bot

import com.github.insanusmokrassar.TelegramBotAPI.types.Response
import java.io.IOException

class RequestException(
    val response: Response<*>,
    message: String? = null,
    cause: Throwable? = null
) : IOException(
    message,
    cause
)