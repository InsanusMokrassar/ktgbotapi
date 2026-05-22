package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.GuestQueryId
import dev.inmo.tgbotapi.types.chat.CommonBot
import dev.inmo.tgbotapi.types.message.content.MessageContent

sealed interface GuestContentMessage<T: MessageContent> : CommonContentMessage<T>,
    GuestMessage,
    FromUserMessage,
    PossiblySentViaBot {
}

interface AnswerGuestContentMessage<T : MessageContent> : GuestContentMessage<T>, AnswerGuestMessage, PossiblySentViaBot {
    override val senderBot: CommonBot
}

interface RequestGuestContentMessage<T : MessageContent> : GuestContentMessage<T>, RequestGuestMessage, SpecialMessage
