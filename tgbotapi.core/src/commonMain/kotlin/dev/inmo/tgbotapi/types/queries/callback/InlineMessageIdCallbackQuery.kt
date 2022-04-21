package dev.inmo.tgbotapi.types.queries.callback

import dev.inmo.tgbotapi.types.InlineMessageIdentifier

sealed interface InlineMessageIdCallbackQuery : CallbackQuery {
    val inlineMessageId: InlineMessageIdentifier
}
