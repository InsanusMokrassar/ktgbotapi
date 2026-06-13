package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.chat.PreviewChat
import dev.inmo.tgbotapi.types.chat.PreviewGroupChat
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.GroupEvent

interface GroupEventMessage<T : GroupEvent> : ChatEventMessage<T>, FromUserChatMessage {
    override val chat: PreviewGroupChat
}
