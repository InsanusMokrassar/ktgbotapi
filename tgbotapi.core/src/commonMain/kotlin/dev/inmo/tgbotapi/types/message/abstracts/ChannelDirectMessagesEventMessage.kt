package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.chat.PreviewChannelDirectMessagesChat
import dev.inmo.tgbotapi.types.chat.PreviewGroupChat
import dev.inmo.tgbotapi.types.chat.PreviewSupergroupChat
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ChannelDirectMessagesEvent
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.SupergroupEvent

interface ChannelDirectMessagesEventMessage<T : ChannelDirectMessagesEvent> : GroupEventMessage<T> {
    override val chat: PreviewChannelDirectMessagesChat
}
