package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ChatEvent

interface ChatEventMessage : Message {
    val chatEvent: ChatEvent
}