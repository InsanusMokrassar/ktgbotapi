package dev.inmo.tgbotapi.types.chat

import dev.inmo.tgbotapi.types.BackgroundType
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.CommonEvent
import dev.inmo.tgbotapi.types.typeField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatBackground(
    @SerialName(typeField)
    val type: BackgroundType
) : CommonEvent
