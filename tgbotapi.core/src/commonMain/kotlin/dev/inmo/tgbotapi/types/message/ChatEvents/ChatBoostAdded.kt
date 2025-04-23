package dev.inmo.tgbotapi.types.message.ChatEvents

import dev.inmo.tgbotapi.types.boostCountField
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.PublicChatEvent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatBoostAdded(
    @SerialName(boostCountField)
    val count: Int,
) : PublicChatEvent
