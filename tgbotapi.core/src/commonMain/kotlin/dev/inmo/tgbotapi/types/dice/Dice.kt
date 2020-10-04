package dev.inmo.tgbotapi.types.dice

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Dice(
    @SerialName(valueField)
    val value: DiceResult,
    @SerialName(emojiField)
    val animationType: DiceAnimationType
)
