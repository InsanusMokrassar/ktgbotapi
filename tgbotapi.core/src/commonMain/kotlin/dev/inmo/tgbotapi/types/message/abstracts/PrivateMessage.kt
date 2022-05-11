package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.message.content.MessageContent

interface PrivateContentMessage<T: MessageContent> : PossiblySentViaBotCommonMessage<T>, FromUserMessage
