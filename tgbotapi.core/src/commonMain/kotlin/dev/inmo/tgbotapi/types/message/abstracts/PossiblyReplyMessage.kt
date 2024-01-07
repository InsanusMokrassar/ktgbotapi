package dev.inmo.tgbotapi.types.message.abstracts

interface PossiblyReplyMessage {
    val replyTo: AccessibleMessage?
}