package dev.inmo.tgbotapi.types.queries.callback

import dev.inmo.tgbotapi.types.InlineMessageId

sealed interface InlineMessageIdCallbackQuery : CallbackQuery {
    val inlineMessageId: InlineMessageId
}
