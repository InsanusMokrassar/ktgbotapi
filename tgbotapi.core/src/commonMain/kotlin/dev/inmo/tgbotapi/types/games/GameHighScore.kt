package dev.inmo.tgbotapi.types.games

import dev.inmo.tgbotapi.CommonAbstracts.FromUser
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameHighScore (
    @SerialName(positionField)
    val position: Long,
    @SerialName(userField)
    override val user: User,
    @SerialName(scoreField)
    val score: Long
) : FromUser