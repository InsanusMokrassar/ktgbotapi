package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.GuestQueryId
import dev.inmo.tgbotapi.types.message.content.MessageContent

interface GuestContentMessage<T: MessageContent> : SpecialMessage,
    CommonContentMessage<T>,
    GuestMessage,
    FromUserMessage,
    PossiblySentViaBot {
    override val guestQueryId: GuestQueryId
}
