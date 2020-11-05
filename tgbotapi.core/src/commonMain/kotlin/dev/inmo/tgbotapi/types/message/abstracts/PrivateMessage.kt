package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.message.content.abstracts.MessageContent
import dev.inmo.tgbotapi.types.message.content.abstracts.PossiblySentViaBotCommonMessage

interface PrivateMessage<T: MessageContent> : PossiblySentViaBotCommonMessage<T>, FromUserMessage
