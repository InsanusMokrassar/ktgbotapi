package dev.inmo.tgbotapi.types.queries.callback

import dev.inmo.tgbotapi.types.message.abstracts.InaccessibleMessage

sealed interface InaccessibleMessageCallbackQuery : AbstractMessageCallbackQuery {
    override val message: InaccessibleMessage
}
