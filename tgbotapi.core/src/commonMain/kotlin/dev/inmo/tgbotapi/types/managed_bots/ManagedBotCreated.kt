package dev.inmo.tgbotapi.types.managed_bots

import dev.inmo.tgbotapi.types.chat.PreviewBot
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.CommonEvent
import kotlinx.serialization.Serializable

@Serializable
data class ManagedBotCreated(
    val bot: PreviewBot
) : CommonEvent
