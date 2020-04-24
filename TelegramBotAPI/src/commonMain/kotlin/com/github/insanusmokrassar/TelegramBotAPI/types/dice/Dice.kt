package com.github.insanusmokrassar.TelegramBotAPI.types.dice

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Dice(
    @SerialName(valueField)
    val value: DiceResult,
    @SerialName(emojiField)
    val animationType: DiceAnimationType
)
