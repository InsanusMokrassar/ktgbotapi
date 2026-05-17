package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.message.content.MessageContent

sealed interface CommonContentMessage<out T: MessageContent> : AccessibleMessage,
    PossiblyForwardedMessage,
    PossiblyEditedMessage,
    PossiblyReplyMessage,
    PossiblyMarkedUp,
    PossiblyBusinessMessage,
    PossiblyOfflineMessage,
    PossiblyMediaGroupMessage<T>,
    PossiblyPaidMessage,
    ContentMessage<T>