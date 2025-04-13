package dev.inmo.tgbotapi.types.gifts

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UniqueGiftInfo private constructor(
    @SerialName(giftField)
    val gift: UniqueGift,
    @SerialName(originField)
    val origin: String? = null,
    @SerialName(ownedGiftIdField)
    val ownedGiftId: GiftId? = null,
    @SerialName(transferStarCountField)
    val transferStarCount: Int? = null
)
