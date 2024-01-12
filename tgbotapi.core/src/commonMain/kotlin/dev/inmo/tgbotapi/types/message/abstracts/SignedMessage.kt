package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.AuthorSignature

interface SignedMessage : AccessibleMessage {
    val authorSignature: AuthorSignature?
}