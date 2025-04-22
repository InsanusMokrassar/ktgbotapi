package dev.inmo.tgbotapi.types.giveaway

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ChatEvent
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.PublicChatEvent
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializeOnlySerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GiveawayPrivateResults(
    @SerialName(winnersCountField)
    val count: Int,
    @SerialName(unclaimedPrizeCountField)
    override val unclaimedCount: Int,
    @SerialName(giveawayMessageField)
    @Serializable(TelegramBotAPIMessageDeserializeOnlySerializer::class)
    val message: AccessibleMessage? = null,
    @SerialName(isStarGiveawayField)
    val isStarGiveaway: Boolean = false,
) : GiveawayResults, ChatEvent, PublicChatEvent
