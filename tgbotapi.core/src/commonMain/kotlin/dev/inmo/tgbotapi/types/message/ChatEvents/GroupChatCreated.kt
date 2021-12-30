package dev.inmo.tgbotapi.types.message.ChatEvents

import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.GroupEvent

class GroupChatCreated(
    val migratedTo: ChatId?
): GroupEvent
