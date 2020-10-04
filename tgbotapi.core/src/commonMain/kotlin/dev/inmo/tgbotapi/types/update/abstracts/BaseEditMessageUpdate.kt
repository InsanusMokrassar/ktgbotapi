package dev.inmo.tgbotapi.types.update.abstracts

import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage

interface BaseEditMessageUpdate : BaseMessageUpdate {
    override val data: CommonMessage<*>
}
