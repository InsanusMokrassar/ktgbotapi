package dev.inmo.tgbotapi.types.CallbackQuery

import dev.inmo.tgbotapi.types.message.abstracts.Message

interface MessageCallbackQuery : CallbackQuery {
    val message: Message
}