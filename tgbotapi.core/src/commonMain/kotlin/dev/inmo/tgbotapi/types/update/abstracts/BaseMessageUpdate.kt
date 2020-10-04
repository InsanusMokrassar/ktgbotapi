package dev.inmo.tgbotapi.types.update.abstracts

import dev.inmo.tgbotapi.types.message.abstracts.Message

interface BaseMessageUpdate : Update {
    override val data: Message
}