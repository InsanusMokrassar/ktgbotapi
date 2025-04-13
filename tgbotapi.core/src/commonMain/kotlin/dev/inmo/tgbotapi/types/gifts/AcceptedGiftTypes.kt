package dev.inmo.tgbotapi.types.gifts

import dev.inmo.tgbotapi.types.limitedGiftsField
import dev.inmo.tgbotapi.types.premiumSubscriptionField
import dev.inmo.tgbotapi.types.uniqueGiftsField
import dev.inmo.tgbotapi.types.unlimitedGiftsField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class AcceptedGiftTypes(
    @SerialName(unlimitedGiftsField)
    val unlimitedGifts: Boolean = true,
    @SerialName(limitedGiftsField)
    val limitedGifts: Boolean = true,
    @SerialName(uniqueGiftsField)
    val uniqueGifts: Boolean = true,
    @SerialName(premiumSubscriptionField)
    val premiumSubscription: Boolean = true
)
