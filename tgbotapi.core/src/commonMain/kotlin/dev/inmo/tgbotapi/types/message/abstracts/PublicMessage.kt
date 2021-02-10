package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.chat.abstracts.PublicChat
import dev.inmo.tgbotapi.types.message.content.abstracts.MessageContent
import dev.inmo.tgbotapi.types.message.content.abstracts.PossiblySentViaBotCommonMessage

interface PublicContentMessage<T: MessageContent> : PossiblySentViaBotCommonMessage<T> {
    override val chat: PublicChat
}
@Deprecated("Renamed due to ambiguity of naming", ReplaceWith("PublicContentMessage", "dev.inmo.tgbotapi.types.message.PublicContentMessage"))
typealias PublicMessage<T> = PublicContentMessage<T>
