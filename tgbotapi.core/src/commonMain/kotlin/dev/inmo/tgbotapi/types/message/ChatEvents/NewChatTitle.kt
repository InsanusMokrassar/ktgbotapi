package dev.inmo.tgbotapi.types.message.ChatEvents

import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.PublicChatEvent

data class NewChatTitle(
    val title: String
): PublicChatEvent
