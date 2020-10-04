package dev.inmo.tgbotapi.types.message.ChatEvents

import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.SupergroupEvent

class SupergroupChatCreated(
    val migratedFrom: ChatIdentifier?
): SupergroupEvent
