package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.chat.PreviewSupergroupChat
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.SupergroupEvent

interface SupergroupEventMessage<T : SupergroupEvent> : GroupEventMessage<T> {
    override val chat: PreviewSupergroupChat
}
