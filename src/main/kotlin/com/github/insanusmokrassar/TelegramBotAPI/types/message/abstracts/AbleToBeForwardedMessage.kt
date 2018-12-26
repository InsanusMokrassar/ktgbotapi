package com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.message.ForwardedMessage

interface AbleToBeForwardedMessage : Message {
    val forwarded: ForwardedMessage?
}