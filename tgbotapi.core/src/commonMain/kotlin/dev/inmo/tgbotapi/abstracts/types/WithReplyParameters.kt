package dev.inmo.tgbotapi.abstracts.types

import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.ReplyParameters

interface WithReplyParameters {
    val replyParameters: ReplyParameters?

    val replyToMessageId: MessageId?
        get() = replyParameters ?.messageId
    val allowSendingWithoutReply: Boolean?
        get() = replyParameters ?.allowSendingWithoutReply
}
