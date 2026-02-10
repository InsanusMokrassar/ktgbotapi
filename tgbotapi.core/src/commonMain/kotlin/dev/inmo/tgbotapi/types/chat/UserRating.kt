package dev.inmo.tgbotapi.types.chat

import dev.inmo.tgbotapi.types.countField
import dev.inmo.tgbotapi.types.ratingField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserRating(
    @SerialName(countField)
    val count: Int,
    @SerialName(ratingField)
    val rating: Double
)
