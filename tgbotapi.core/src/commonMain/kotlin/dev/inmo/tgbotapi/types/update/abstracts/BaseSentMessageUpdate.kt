package dev.inmo.tgbotapi.types.update.abstracts

import dev.inmo.tgbotapi.types.message.abstracts.Message

interface BaseSentMessageUpdate : BaseMessageUpdate {
    fun copy(newData: Message): BaseSentMessageUpdate
}
