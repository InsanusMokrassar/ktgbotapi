package dev.inmo.tgbotapi.CommonAbstracts.types

import dev.inmo.tgbotapi.types.MessageIdentifier

interface ReplyMessageId {
    val replyToMessageId: MessageIdentifier?
}