package dev.inmo.tgbotapi.types.gifts

import dev.inmo.tgbotapi.types.giftsField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Gifts(
    @SerialName(giftsField)
    val gifts: List<Gift.Regular>,
)
