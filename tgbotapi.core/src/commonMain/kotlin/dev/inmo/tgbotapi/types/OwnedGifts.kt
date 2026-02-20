package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.types.gifts.GiftSentOrReceivedEvent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OwnedGifts<GiftType: GiftSentOrReceivedEvent>(
    @SerialName(totalCountField)
    val totalCount: Int,
    @SerialName(giftsField)
    val gifts: List<GiftType>,
    @SerialName(nextOffsetField)
    val nextOffset: String? = null
)
