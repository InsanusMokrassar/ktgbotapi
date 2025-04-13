package dev.inmo.tgbotapi.types.gifts

import dev.inmo.tgbotapi.types.limitedGiftsField
import dev.inmo.tgbotapi.types.premiumSubscriptionField
import dev.inmo.tgbotapi.types.uniqueGiftsField
import dev.inmo.tgbotapi.types.unlimitedGiftsField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * This object describes the types of gifts that can be gifted to a user or a chat.
 *
 * @param unlimitedGifts True, if unlimited regular gifts are accepted
 * @param limitedGifts True, if limited regular gifts are accepted
 * @param uniqueGifts True, if unique gifts or gifts that can be upgraded to unique for free are accepted
 * @param premiumSubscription True, if a Telegram Premium subscription is accepted
 */
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
