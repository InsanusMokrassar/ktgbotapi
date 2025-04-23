package dev.inmo.tgbotapi.types.giveaway

import kotlinx.serialization.Serializable

@Serializable
sealed interface GiveawayResults {
    val unclaimedCount: Int
}
