package dev.inmo.tgbotapi.types.update.abstracts

import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage

interface BaseSentMessageUpdate : BaseMessageUpdate {
    fun copy(newData: ContentMessage<*>): BaseSentMessageUpdate
}
