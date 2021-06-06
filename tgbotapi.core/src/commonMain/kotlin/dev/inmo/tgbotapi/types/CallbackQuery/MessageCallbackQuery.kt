package dev.inmo.tgbotapi.types.CallbackQuery

import dev.inmo.tgbotapi.types.message.abstracts.Message

sealed interface MessageCallbackQuery : CallbackQuery {
    val message: Message
}
