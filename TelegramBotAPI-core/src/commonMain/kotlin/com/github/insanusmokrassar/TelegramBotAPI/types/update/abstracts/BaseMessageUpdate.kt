package com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.Message

interface BaseMessageUpdate : Update {
    override val data: Message
}