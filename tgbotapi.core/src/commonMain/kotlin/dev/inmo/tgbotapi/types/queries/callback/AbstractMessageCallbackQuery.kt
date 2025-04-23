package dev.inmo.tgbotapi.types.queries.callback

import dev.inmo.tgbotapi.types.message.abstracts.Message

sealed interface AbstractMessageCallbackQuery : CallbackQuery {
    val message: Message
}
