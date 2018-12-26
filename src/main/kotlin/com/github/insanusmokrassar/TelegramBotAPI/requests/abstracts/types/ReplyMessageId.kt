package com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.types

import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier

interface ReplyMessageId {
    val replyToMessageId: MessageIdentifier?
}