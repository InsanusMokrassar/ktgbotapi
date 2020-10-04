package dev.inmo.tgbotapi.types.message.ChatEvents

import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.GroupEvent

class GroupChatCreated(
    val migratedTo: ChatIdentifier?
): GroupEvent
