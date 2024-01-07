package dev.inmo.tgbotapi.types.update.abstracts

import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage

interface BaseMessageUpdate : Update {
    override val data: AccessibleMessage
}