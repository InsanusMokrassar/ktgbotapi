package dev.inmo.tgbotapi.types.queries.callback

import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.MessageContent

sealed interface MessageCallbackQuery : AbstractMessageCallbackQuery {
    override val message: ContentMessage<MessageContent>
}
