package dev.inmo.tgbotapi.types.message.ChatEvents

import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.CommonEvent
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.abstracts.Message

data class PinnedMessage(
    val message: Message
): CommonEvent
