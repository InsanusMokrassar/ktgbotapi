package dev.inmo.tgbotapi.types.message.ChatEvents

import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.GroupEvent
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.SupergroupEvent

data class NewChatMembers(
    val members: List<User>
): GroupEvent, SupergroupEvent
