package dev.inmo.tgbotapi.types.message.ChatEvents

import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.CommonGroupEvent

data class NewChatTitle(
    val title: String
): CommonGroupEvent
