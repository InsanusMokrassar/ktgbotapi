package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.ReplyInfo

interface PossiblyReplyMessage {
    val replyInfo: ReplyInfo?
    val replyTo: Message?
        get() = (replyInfo as? ReplyInfo.Internal) ?.message
}