package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.message.ForwardInfo
import dev.inmo.tgbotapi.types.message.MessageOrigin
import dev.inmo.tgbotapi.types.message.forwardInfo

interface PossiblyForwardedMessage : AccessibleMessage {
    val forwardOrigin: MessageOrigin?
    val forwardInfo: ForwardInfo?
        get() = forwardOrigin ?.forwardInfo()
}
