package dev.inmo.tgbotapi.types.update.abstracts

import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage

interface BaseSentMessageUpdate : BaseMessageUpdate {
    fun copy(newData: AccessibleMessage): BaseSentMessageUpdate
}
