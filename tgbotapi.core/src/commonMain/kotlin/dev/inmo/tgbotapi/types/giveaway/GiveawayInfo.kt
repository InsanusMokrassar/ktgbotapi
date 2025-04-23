package dev.inmo.tgbotapi.types.giveaway

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.Serializable

@Serializable
sealed interface GiveawayInfo {
    val selectionDate: TelegramDate
    val onlyNewMembers: Boolean
    val additionalPrizeDescription: String?

    @Serializable
    sealed interface OptionallyPremium : GiveawayInfo {
        val premiumMonths: Int?
    }

    @Serializable
    sealed interface OptionallyStars : GiveawayInfo {
        val prizeStarCount: Int?
    }
}
