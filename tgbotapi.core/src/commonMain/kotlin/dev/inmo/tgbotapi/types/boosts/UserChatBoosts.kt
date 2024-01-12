package dev.inmo.tgbotapi.types.boosts

import dev.inmo.tgbotapi.types.boostsField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserChatBoosts(
    @SerialName(boostsField)
    val boosts: List<ChatBoost>
)
