package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.chat.PreviewPrivateChat
import dev.inmo.tgbotapi.types.message.content.MessageContent

interface PrivateContentMessage<T: MessageContent> : PossiblySentViaBotCommonMessage<T>, FromUserMessage, PossiblyOfflineMessage, PossiblyWithEffectMessage {
    override val chat: PreviewPrivateChat
}
