package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.message.ForwardInfo

interface PossiblyForwardedMessage : AccessibleMessage {
    val forwardInfo: ForwardInfo?
}