package dev.inmo.tgbotapi.types.update.abstracts

import dev.inmo.tgbotapi.types.message.abstracts.ChatContentMessage

interface BaseEditMessageUpdate : BaseMessageUpdate {
    override val data: ChatContentMessage<*>
}
