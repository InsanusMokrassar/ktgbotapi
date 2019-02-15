package com.github.insanusmokrassar.TelegramBotAPI.bot.exceptions

import com.github.insanusmokrassar.TelegramBotAPI.types.Response

open class ReplyMessageNotFound(response: Response<*>, message: String?, cause: Throwable?) :
    RequestException(response, message, cause)