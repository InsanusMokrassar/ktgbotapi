package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.GroupEvent

interface GroupEventMessage : ChatEventMessage, FromUserMessage {
    override val chatEvent: GroupEvent
}
