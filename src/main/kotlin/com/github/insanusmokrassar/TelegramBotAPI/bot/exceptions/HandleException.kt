package com.github.insanusmokrassar.TelegramBotAPI.bot.exceptions

import com.github.insanusmokrassar.TelegramBotAPI.types.chat.RawChat

sealed class HandleException (
    message: String
) : IllegalArgumentException(
    message
)

class IllegalChatRawObjectException(
    rawChat: RawChat
) : HandleException(
    "One of the fields in raw chat object is incorrect"
)
