package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.chat.PreviewPrivateChat
import dev.inmo.tgbotapi.types.message.content.MessageContent

interface PrivateContentMessage<T: MessageContent> : PossiblySentViaBotCommonMessage<T>,
    FromUserChatMessage,
    PossiblyOfflineMessage,
    PossiblyWithEffectMessage,
    PossiblyGuestAnswerMessage,
    PossiblyEphemeralMessage {
    override val chat: PreviewPrivateChat
}
