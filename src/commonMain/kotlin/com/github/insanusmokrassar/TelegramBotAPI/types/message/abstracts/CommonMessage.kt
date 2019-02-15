package com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MessageContent

interface CommonMessage<T: MessageContent> : Message,
    AbleToBeForwardedMessage,
    AbleToBeEditedMessage,
    AbleToReplyMessage,
    ContentMessage<T>
