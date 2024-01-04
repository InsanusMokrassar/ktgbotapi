package dev.inmo.tgbotapi.types.giveaway

import dev.inmo.tgbotapi.abstracts.WithPreviewChat
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ChatEvent
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.PublicChatEvent
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import kotlinx.serialization.Serializable

@Serializable
@ClassCastsIncluded
sealed interface GiveawayResults : WithPreviewChat, ChatEvent, PublicChatEvent {
    val unclaimedCount: Int
}
