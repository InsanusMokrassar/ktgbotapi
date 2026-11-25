package dev.inmo.tgbotapi.types.queries.callback

import dev.inmo.tgbotapi.types.InlineMessageId

/** Also used for bot-sent messages when the bot's callback query mode supplies an inline message ID. */
sealed interface InlineMessageIdCallbackQuery : CallbackQuery {
    val inlineMessageId: InlineMessageId
}
