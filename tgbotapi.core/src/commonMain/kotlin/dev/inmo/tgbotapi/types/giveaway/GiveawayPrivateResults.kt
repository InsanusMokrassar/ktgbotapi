package dev.inmo.tgbotapi.types.giveaway

import dev.inmo.tgbotapi.types.chat.PreviewChat
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ChannelEvent
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ChatEvent
import dev.inmo.tgbotapi.types.message.abstracts.Message
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class GiveawayPrivateResults(
    override val chat: PreviewChat,
    override val unclaimedCount: Int,
    @Transient // TODO::Add message serializer
    val message: Message? = null
) : GiveawayResults
