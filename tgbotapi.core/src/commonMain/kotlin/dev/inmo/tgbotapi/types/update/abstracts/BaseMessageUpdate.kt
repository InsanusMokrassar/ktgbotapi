package dev.inmo.tgbotapi.types.update.abstracts

import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.abstracts.CommonContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.Message

interface BaseMessageUpdate : Update {
    override val data: AccessibleMessage
}