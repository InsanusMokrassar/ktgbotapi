package dev.inmo.tgbotapi.types.boosts

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatBoost(
    @SerialName(boostIdField)
    val id: BoostId,
    @SerialName(addDateField)
    val addDate: TelegramDate,
    @SerialName(expirationDateField)
    val expirationDate: TelegramDate,
    @SerialName(sourceField)
    val source: ChatBoostSource
)
