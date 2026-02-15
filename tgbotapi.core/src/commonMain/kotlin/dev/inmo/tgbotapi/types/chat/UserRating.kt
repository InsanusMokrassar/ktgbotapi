package dev.inmo.tgbotapi.types.chat

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserRating(
    @SerialName(levelField)
    val level: Level,
    @SerialName(ratingField)
    val rating: Rating,
    @SerialName(currentLevelRatingField)
    val currentLevelRating: Rating,
    @SerialName(nextLevelRatingField)
    val nextLevelRating: Rating? = null
)
