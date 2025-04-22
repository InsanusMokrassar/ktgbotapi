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
    val unlimitedGifts: Boolean = false,
    @SerialName(limitedGiftsField)
    val limitedGifts: Boolean = false,
    @SerialName(uniqueGiftsField)
    val uniqueGifts: Boolean = false,
    @SerialName(premiumSubscriptionField)
    val premiumSubscription: Boolean = false,
)
