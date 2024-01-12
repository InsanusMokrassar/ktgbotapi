package dev.inmo.tgbotapi.abstracts.types

import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.ReplyParameters

@Deprecated("Renamed", ReplaceWith("WithReplyParameters", "dev.inmo.tgbotapi.abstracts.types.WithReplyParameters"))
interface ReplyMessageId {
    val replyToMessageId: MessageId?
    val allowSendingWithoutReply: Boolean?
}

interface WithReplyParameters : ReplyMessageId {
    val replyParameters: ReplyParameters?

    override val replyToMessageId: MessageId?
        get() = replyParameters ?.messageId
    override val allowSendingWithoutReply: Boolean?
        get() = replyParameters ?.allowSendingWithoutReply
}
