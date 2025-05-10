package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.types.gifts.GiftSentOrReceived
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OwnedGifts<GiftType: GiftSentOrReceived>(
    @SerialName(totalCountField)
    val totalCount: Int,
    @SerialName(giftsField)
    val gifts: List<GiftType>,
    @SerialName(nextOffsetField)
    val nextOffset: String? = null
)
