package com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MessageContent

interface CommonMessage<T: MessageContent> : Message,
    PossiblyForwardedMessage,
    PossiblyEditedMessage,
    PossiblyReplyMessage,
    PossiblyMarkedUp,
    ContentMessage<T>
