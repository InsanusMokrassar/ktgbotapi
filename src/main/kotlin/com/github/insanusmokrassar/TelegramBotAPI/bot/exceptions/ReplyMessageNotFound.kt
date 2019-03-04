package com.github.insanusmokrassar.TelegramBotAPI.bot.exceptions

import com.github.insanusmokrassar.TelegramBotAPI.types.Response

open class ReplyMessageNotFound(response: Response<*>, plainAnswer: String, message: String?, cause: Throwable?) :
    RequestException(response, plainAnswer, message, cause)