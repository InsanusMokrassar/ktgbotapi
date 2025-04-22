package dev.inmo.tgbotapi.types.message.ChatEvents

import dev.inmo.tgbotapi.types.IdChatIdentifier
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.GroupEvent

class GroupChatCreated(
    val migratedTo: IdChatIdentifier?,
) : GroupEvent
