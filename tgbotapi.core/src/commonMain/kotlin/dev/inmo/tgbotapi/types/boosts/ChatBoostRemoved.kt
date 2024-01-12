package dev.inmo.tgbotapi.types.boosts

import dev.inmo.tgbotapi.abstracts.WithPreviewChat
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.PreviewChat
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatBoostRemoved(
    @SerialName(chatField)
    override val chat: PreviewChat,
    @SerialName(boostIdField)
    val boostId: BoostId,
    @SerialName(removeDateField)
    val removeDate: TelegramDate,
    @SerialName(sourceField)
    val source: ChatBoostSource
) : WithPreviewChat
