package com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.message.ChatEvents.abstracts.ChatEvent

interface ChatEventMessage : Message {
    val chatEvent: ChatEvent
}