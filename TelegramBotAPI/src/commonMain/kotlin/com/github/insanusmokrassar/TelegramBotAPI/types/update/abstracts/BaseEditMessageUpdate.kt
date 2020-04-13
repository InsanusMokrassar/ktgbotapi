package com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.CommonMessage

interface BaseEditMessageUpdate : BaseMessageUpdate {
    override val data: CommonMessage<*>
}
