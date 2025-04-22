package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.CommonEvent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaidMessagePriceChanged(
    @SerialName(paidMessageStarCountField)
    val cost: Int,
) : CommonEvent
