package com.github.insanusmokrassar.TelegramBotAPI.types.games

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameHighScore (
    @SerialName(positionField)
    val position: Long,
    @SerialName(userField)
    val user: User,
    @SerialName(scoreField)
    val score: Long
)