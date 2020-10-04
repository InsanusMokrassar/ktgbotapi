package dev.inmo.tgbotapi.types.message.ChatEvents

import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.CommonEvent

data class NewChatTitle(
    val title: String
): CommonEvent
