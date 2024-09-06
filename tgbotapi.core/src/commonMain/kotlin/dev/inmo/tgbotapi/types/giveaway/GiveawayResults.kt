package dev.inmo.tgbotapi.types.giveaway

import dev.inmo.tgbotapi.abstracts.WithPreviewChat
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ChatEvent
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.PublicChatEvent
import kotlinx.serialization.Serializable

@Serializable
sealed interface GiveawayResults : ChatEvent, PublicChatEvent {
    val unclaimedCount: Int
}
