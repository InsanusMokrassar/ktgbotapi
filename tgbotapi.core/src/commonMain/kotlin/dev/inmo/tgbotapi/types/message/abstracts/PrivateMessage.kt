package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.message.content.abstracts.MessageContent
import dev.inmo.tgbotapi.types.message.content.abstracts.PossiblySentViaBotCommonMessage

interface PrivateContentMessage<T: MessageContent> : PossiblySentViaBotCommonMessage<T>, FromUserMessage
@Deprecated("Renamed due to ambiguity of naming", ReplaceWith("PrivateContentMessage", "dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage"))
typealias PrivateMessage<T> = PrivateContentMessage<T>
