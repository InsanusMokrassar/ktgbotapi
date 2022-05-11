package dev.inmo.tgbotapi.abstracts.types

import dev.inmo.tgbotapi.types.MessageIdentifier

interface ReplyMessageId {
    val replyToMessageId: MessageIdentifier?
    val allowSendingWithoutReply: Boolean?
}
