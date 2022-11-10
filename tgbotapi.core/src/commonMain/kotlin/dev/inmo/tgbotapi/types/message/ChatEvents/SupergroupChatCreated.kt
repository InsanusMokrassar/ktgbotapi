package dev.inmo.tgbotapi.types.message.ChatEvents

import dev.inmo.tgbotapi.types.IdChatIdentifier
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.SupergroupEvent

class SupergroupChatCreated(
    val migratedFrom: IdChatIdentifier?
): SupergroupEvent
