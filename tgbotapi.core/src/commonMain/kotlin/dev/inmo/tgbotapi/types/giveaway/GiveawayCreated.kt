package dev.inmo.tgbotapi.types.giveaway

import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ChatEvent
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.PublicChatEvent
import kotlinx.serialization.Serializable

@Serializable
object GiveawayCreated : ChatEvent, PublicChatEvent
