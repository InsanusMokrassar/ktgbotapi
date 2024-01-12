package dev.inmo.tgbotapi.types.boosts

import dev.inmo.tgbotapi.abstracts.WithPreviewChat
import dev.inmo.tgbotapi.types.boostField
import dev.inmo.tgbotapi.types.chat.PreviewChat
import dev.inmo.tgbotapi.types.chatField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatBoostUpdated(
    @SerialName(chatField)
    override val chat: PreviewChat,
    @SerialName(boostField)
    val boost: ChatBoost
) : WithPreviewChat
