package dev.inmo.tgbotapi.types.request

import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.PrivateEvent

sealed interface ChatSharedRequest : RequestResponse, PrivateEvent {
    val chatId: ChatIdentifier
}
