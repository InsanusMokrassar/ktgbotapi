package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.GuestQueryId
import dev.inmo.tgbotapi.types.chat.CommonBot
import dev.inmo.tgbotapi.types.message.content.MessageContent

interface RequestGuestContentMessage<T : MessageContent> : CommonContentMessage<T>,
    RequestGuestMessage,
    SpecialMessage,
    FromUserMessage,
    PossiblySentViaBot
