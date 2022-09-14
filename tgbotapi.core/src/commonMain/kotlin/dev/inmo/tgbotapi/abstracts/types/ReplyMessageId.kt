package dev.inmo.tgbotapi.abstracts.types

import dev.inmo.tgbotapi.types.MessageId

interface ReplyMessageId {
    val replyToMessageId: MessageId?
    val allowSendingWithoutReply: Boolean?
}
