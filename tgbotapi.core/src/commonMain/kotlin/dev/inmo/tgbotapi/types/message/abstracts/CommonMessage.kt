package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.message.content.MessageContent

sealed interface CommonMessage<T: MessageContent> : Message,
    PossiblyForwardedMessage,
    PossiblyEditedMessage,
    PossiblyReplyMessage,
    PossiblyMarkedUp,
    ContentMessage<T>
