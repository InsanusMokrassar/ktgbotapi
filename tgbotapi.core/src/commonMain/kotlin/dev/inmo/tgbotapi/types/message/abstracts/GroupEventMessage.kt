package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.chat.PreviewChat
import dev.inmo.tgbotapi.types.chat.PreviewGroupChat
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.GroupEvent

interface GroupEventMessage<T : GroupEvent> : ChatEventMessage<T>, FromUserMessage {
    override val chat: PreviewGroupChat
}
