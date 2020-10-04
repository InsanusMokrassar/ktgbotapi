package dev.inmo.tgbotapi.types.CallbackQuery

import dev.inmo.tgbotapi.types.InlineMessageIdentifier

interface InlineMessageIdCallbackQuery : CallbackQuery {
    val inlineMessageId: InlineMessageIdentifier
}