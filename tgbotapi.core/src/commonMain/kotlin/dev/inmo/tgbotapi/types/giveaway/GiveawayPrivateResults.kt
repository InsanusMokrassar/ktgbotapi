package dev.inmo.tgbotapi.types.giveaway

import dev.inmo.tgbotapi.types.chat.PreviewChat
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ChatEvent
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.PublicChatEvent
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class GiveawayPrivateResults(
    override val chat: PreviewChat,
    override val unclaimedCount: Int,
    @Transient // TODO::Add message serializer
    val message: AccessibleMessage? = null
) : GiveawayResults, ChatEvent, PublicChatEvent
